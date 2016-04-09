/*
 * Copyright 2016 Bridje Framework.
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

package org.bridje.jfx;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import org.bridje.ioc.Component;
import org.bridje.ioc.ContextListener;

@Component
class FxContextListener implements ContextListener
{
    private static final Logger LOG = Logger.getLogger(FxContextListener.class.getName());

    @Override
    public void preCreateComponent(Class clazz)
    {
    }

    @Override
    public void preInitComponent(Class clazz, Object instance)
    {
        Annotation annot = clazz.getAnnotation(LoadFXML.class);
        if(annot != null)
        {
            URL resource = clazz.getResource(clazz.getSimpleName() + ".fxml");
            if(resource != null)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(resource);
                fxmlLoader.setRoot(instance);
                fxmlLoader.setController(instance);

                try
                {
                    fxmlLoader.load();
                }
                catch (IOException exception)
                {
                    LOG.log(Level.SEVERE, exception.getMessage(), exception);
                }
            }
        }
    }

    @Override
    public void postInitComponent(Class clazz, Object instance)
    {
        
    }
    
}
