<#ftl encoding="UTF-8">

package ${control.package};

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.bridje.web.view.Defines;
import org.bridje.web.view.controls.*;
import org.bridje.http.UploadedFile;
import javax.annotation.Generated;

/**
 * This object represents the ${control.name} for the ${theme.name} Theme.
 */
@Generated(value = "org.bridje.web.srcgen.WebSourceGenerator", date = "${.now?string("yyyy-MM-dd")}", comments = "Generated by Bridje Web API")
@XmlAccessorType(XmlAccessType.FIELD)
public class ${control.name} extends ${control.base}
{
    <#if control.hasChildren>
    @XmlTransient
    private List<Control> childs;

    </#if>
    <#if control.hasInputs>
    @XmlTransient
    private List<UIInputExpression> inputs;

    </#if>
    <#if control.hasInputFiles>
    @XmlTransient
    private List<UIFileExpression> inputFiles;

    </#if>
    <#if control.hasEvents>
    @XmlTransient
    private List<UIEvent> events;

    </#if>
    <#if control.hasResources>
    @XmlTransient
    private List<String> resources;

    </#if>
    <#list control.fields as f>
    <#if f.fieldType == "attribute">
    @XmlAttribute
    <#elseif f.fieldType == "value">
    @XmlValue
    <#elseif f.fieldType == "children">
    <#if f.wrapper != "">
    @XmlElementWrapper( name = "${f.wrapper}" )
    </#if>
    @XmlElements(
    {
        <#list f.content![] as c>
        @XmlElement( name = "${c.name}", type = ${c.type}.class ),
        </#list>
        <#if f.allowPlaceHolder>
        @XmlElement( name = "placeholder", type = ControlPlaceHolder.class ),
        </#if>
    })
    <#elseif f.fieldType == "child">
    @XmlElements(
    {
        @XmlElement( name = "${f.name}", type = ${f.javaType}.class ),
        <#if f.allowPlaceHolder>
        @XmlElement( name = "placeholder", type = ControlPlaceHolder.class ),
        </#if>
    })
    </#if>
    private ${f.javaType} ${f.name};

    </#list>
    <#list control.fields as f>
    <#if f.javaType == "UIExpression">
    public ${f.javaType} get${f.name?cap_first}Expression()
    {
        return ${f.name};
    }

    public ${f.type} get${f.name?cap_first}()
    {
        return get(${f.name}, ${f.type}.class, ${f.defaultValue});
    }
    <#elseif f.javaType == "UIInputExpression">
    public ${f.javaType} get${f.name?cap_first}Expression()
    {
        return ${f.name};
    }

    public ${f.type} get${f.name?cap_first}()
    {
        return get(${f.name}, ${f.type}.class, ${f.defaultValue});
    }
    <#elseif f.javaType == "UIFileExpression">
    public ${f.javaType} get${f.name?cap_first}Expression()
    {
        return ${f.name};
    }

    public UploadedFile get${f.name?cap_first}()
    {
        return get(${f.name});
    }
    <#elseif f.javaType == "UIEvent">
    public ${f.javaType} get${f.name?cap_first}()
    {
        return ${f.name};
    }
    <#else>
    public ${f.javaType} get${f.name?cap_first}()
    {
        <#if f.defaultValue??>
        if(${f.name} == null)
        {
            ${f.name} = ${f.defaultValue};
        }
        </#if>
        return ${f.name};
    }
    </#if>

    </#list>
    <#if control.hasChildren>
    @Override
    public List<? extends Control> childs()
    {
        if(childs == null)
        {
            childs = new ArrayList<>();
            <#list control.fields as f>
            <#if f.fieldType == "children">
            if(${f.name} != null)
            {
                <#if f.isSingle>
                childs.add(${f.name});
                <#else>
                childs.addAll(${f.name});
                </#if>
            }
            </#if>
            <#if f.fieldType == "child">
            if(${f.name} != null)
            {
                childs.add(${f.name});
            }
            </#if>
            </#list>
        }
        return childs;
    }

    </#if>
    <#if control.hasInputs>
    @Override
    public List<UIInputExpression> inputs()
    {
        if(inputs == null)
        {
            inputs = new ArrayList<>();
            <#list control.fields as f>
            <#if f.isInput>
            if(${f.name} != null && ${f.name}.isValid())
            {
                inputs.add(${f.name});
            }
            </#if>
            </#list>
        }
        return inputs;
    }

    </#if>
    <#if control.hasInputFiles>
    @Override
    public List<UIFileExpression> inputFiles()
    {
        if(inputFiles == null)
        {
            inputFiles = new ArrayList<>();
            <#list control.fields as f>
            <#if f.isInputFile>
            if(${f.name} != null && ${f.name}.isValid())
            {
                inputFiles.add(${f.name});
            }
            </#if>
            </#list>
        }
        return inputFiles;
    }

    </#if>
    <#if control.hasEvents>
    @Override
    public List<UIEvent> events()
    {
        if(events == null)
        {
            events = new ArrayList<>();
            <#list control.fields as f>
            <#if f.isEvent>
            if(${f.name} != null)
            {
                events.add(${f.name});
            }
            </#if>
            </#list>
        }
        return events;
    }

    </#if>
    <#if control.hasResources>
    @Override
    public List<String> resources()
    {
        if(resources == null)
        {
            resources = new ArrayList<>();
            <#list control.resources as r>
            resources.add("${r.name}");
            </#list>
        }
        return resources;
    }

    </#if>
    @Override
    public void doOverride(Map<String, Defines> definesMap)
    {
        <#list control.fields as f>
        <#if f.fieldType == "children">
        <#if f.allowPlaceHolder>
        if(${f.name} != null)
        {
            <#if f.isSingle>
            ${f.name} = (${f.javaType})Control.doOverride(${f.name}, definesMap);
            <#else>
            ${f.name} = Control.doOverride(${f.name}, definesMap);
            </#if>
        }
        </#if>
        </#if>
        <#if f.fieldType == "child">
        <#if f.allowPlaceHolder>
        if(${f.name} != null)
        {
            ${f.name} = (${f.javaType})Control.doOverride(${f.name}, definesMap);
        }
        </#if>
        </#if>
        </#list>
    }

    <#if control.readInputFlow??>
    @Override
    public void readInput(ControlImputReader req)
    {
        <#list control.readInputFlow.actions as ria>
        <#switch ria.class.simpleName>
            <#case "ForEachData">
                <#break>
            <#case "PopFieldInput">
                <#if control.findField(ria.fieldName).javaType == "UIFileExpression" >
        if(this.${ria.fieldName} != null) set(this.${ria.fieldName}, req.popUploadedFile(this.${ria.fieldName}.getParameter()));
                <#elseif control.findField(ria.fieldName).javaType == "UIInputExpression" >
        if(this.${ria.fieldName} != null) set(this.${ria.fieldName}, req.popParameter(this.${ria.fieldName}.getParameter()));
                </#if>
                <#break>
            <#case "PopAllFieldInputs">
        inputFiles().stream().forEach(inputFile -> set(inputFile, req.popUploadedFile(inputFile.getParameter())));
        inputs().stream().forEach(input -> set(input, req.popParameter(input.getParameter())));
                <#break>
            <#case "ReadFieldInput">
                <#if control.findField(ria.fieldName).javaType == "UIFileExpression" >
        if(this.${ria.fieldName} != null) set(this.${ria.fieldName}, req.getUploadedFile(this.${ria.fieldName}.getParameter()));
                <#elseif control.findField(ria.fieldName).javaType == "UIInputExpression" >
        if(this.${ria.fieldName} != null) set(this.${ria.fieldName}, req.getParameter(this.${ria.fieldName}.getParameter()));
                </#if>
                <#break>
            <#case "ReadAllFieldInputs">
        inputFiles().stream().forEach(inputFile -> set(inputFile, req.getUploadedFile(inputFile.getParameter())));
        inputs().stream().forEach(input -> set(input, req.getParameter(input.getParameter())));
                <#break>
            <#case "ReadChildren">
                <#if control.findField(ria.fieldName).javaType.startsWith("List")>
        if(this.${ria.fieldName} != null) this.${ria.fieldName}.forEach(control -> control.readInput(req));
                <#else>
        if(this.${ria.fieldName} != null) this.${ria.fieldName}.readInput(req);
                </#if>
                <#break>
            <#case "ReadAllChildren">
        childs().forEach(control -> control.readInput(req));
                <#break>
        </#switch>
        </#list>
    }

    </#if>
}