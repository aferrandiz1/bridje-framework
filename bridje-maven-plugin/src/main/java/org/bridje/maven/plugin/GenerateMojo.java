/*
 * Copyright 2015 Bridje Framework.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bridje.maven.plugin;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.classworlds.DuplicateRealmException;

/**
 *
 */
@Mojo(
    name = "generate-sources"
)
public class GenerateMojo extends AbstractMojo
{
    @Parameter(defaultValue = "src/main/bridje", readonly = false)
    private String dataFilesBasePath;

    @Parameter(defaultValue = "src/main/bridje", readonly = false)
    private String templatesBasePath;

    @Parameter(defaultValue="${project.build.directory}/generated-sources/bridje", readonly = false)
    private String outputBasePath;
    
    @Parameter(readonly = false)
    private DataFile[] dataFiles;
    
    @Parameter(defaultValue="${project}", readonly=true, required=true)
    private MavenProject project;
    
    private ClassLoader clsRealm;
    
    private Configuration cfg;
    
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException
    {
        try
        {
            clsRealm = ClassPathUtils.createClassPath(project);
            createFreeMarkerConfiguration();
        }
        catch (IOException | DuplicateRealmException | DependencyResolutionRequiredException e)
        {
            throw new MojoExecutionException(e.getMessage(), e);
        }
        getLog().info("Generating Classes");
        try
        {
            List<GenerationConfig> genConfigs = readAllConfigs();
            for (GenerationConfig genConfig : genConfigs)
            {
                DataFile[] df = genConfig.getDataFiles();
                if(df != null)
                {
                    for (DataFile dataFile : df)
                    {
                        dataFile.generate(this);
                    }
                }
            }
            if(dataFiles != null)
            {
                for (DataFile dataFile : dataFiles)
                {
                    dataFile.generate(this);
                }
            }

            project.addCompileSourceRoot(outputBasePath);
        }
        catch (MojoExecutionException e)
        {
            getLog().error(e.getMessage(), e);
            throw  e;
        }
    }

    protected String getDataFilesBasePath()
    {
        return dataFilesBasePath;
    }

    protected String getTemplatesBasePath()
    {
        return templatesBasePath;
    }

    protected String getOutputBasePath()
    {
        return outputBasePath;
    }

    protected DataFile[] getDataFiles()
    {
        return dataFiles;
    }

    protected MavenProject getProject()
    {
        return project;
    }

    public void createFreeMarkerConfiguration() throws IOException
    {
        //Freemarker configuration
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        TemplateLoader fileLoader = new FileTemplateLoader(new File(getTemplatesBasePath()));
        TemplateLoader cpLoader = new ClassTemplateLoader(clsRealm, "/BRIDJE-INF/srcgen/");
        TemplateLoader tplLoader = new MultiTemplateLoader(new TemplateLoader[]
        {
            fileLoader, cpLoader
        });
        cfg.setTemplateLoader(tplLoader);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
    }

    public Configuration getFreeMarkerConfiguration() throws IOException
    {
        return cfg;
    }

    private List<GenerationConfig> readAllConfigs()
    {
        List<GenerationConfig> result = new LinkedList<>();
        String file = "BRIDJE-INF/srcgen/generation.xml";
        try
        {
            Enumeration<URL> resources = clsRealm.getResources(file);
            while (resources.hasMoreElements())
            {
                URL url = resources.nextElement();
                GenerationConfig cfg = readConfig(url);
                if(cfg != null)
                {
                    result.add(cfg);
                }
            }
        }
        catch (IOException | JAXBException ex)
        {
            getLog().error(ex.getMessage(), ex);
        }
        return result;
    }

    private GenerationConfig readConfig(URL url) throws JAXBException, IOException
    {
        JAXBContext ctx = JAXBContext.newInstance(GenerationConfig.class);
        Unmarshaller unm = ctx.createUnmarshaller();
        Object result = unm.unmarshal(url.openStream());
        return (GenerationConfig)result;
    }
}
