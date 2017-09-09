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
import org.bridje.web.view.EventResult;
import org.bridje.web.view.Defines;
import org.bridje.web.view.controls.*;
import org.bridje.http.UploadedFile;
import org.bridje.el.ElEnvironment;
import javax.annotation.Generated;

/**
 * This object represents the ${control.name} for the ${uisuite.name} Theme.
 */
@Generated(value = "org.bridje.web.srcgen.WebSourceGenerator", date = "${.now?string("yyyy-MM-dd")}", comments = "Generated by Bridje Web API")
@XmlAccessorType(XmlAccessType.FIELD)
<#if control.isTransient>@XmlTransient</#if>
public class ${control.name} extends ${control.baseName}
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
    <#if f.wrapper?? && f.wrapper != "">
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
    private ${f.javaType!} ${f.name};

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
        if(${f.name} == null) ${f.name} = ${f.defaultValue};
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
            if(${f.name} != null) childs.add(${f.name});
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
            if(${f.name} != null && ${f.name}.isValid()) inputs.add(${f.name});
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
            if(${f.name} != null && ${f.name}.isValid()) inputFiles.add(${f.name});
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
            if(${f.name} != null) events.add(${f.name});
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
            resources.addAll(super.resources());
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
        if(${f.name} != null) ${f.name} = (${f.javaType})Control.doOverride(${f.name}, definesMap);
        </#if>
        </#if>
        </#list>
    }

    <#if control.input??>
    <#macro printReadInputActions actions ident>
        <#list actions as ria>
        <#switch ria.class.simpleName>
            <#case "PushEnvVar">
        ${ident}env.pushVar(${ria.var}, ${ria.value!});
                <#break>
            <#case "PopEnvVar">
        ${ident}env.popVar(${ria.var});
                <#break>
            <#case "ReadForEachData">
                <@printForActions ria ident />
                <#break>
            <#case "ReadIfData">
                <@printIfActions ria ident />
                <#break>
            <#case "ReadElseData">
                <@printElseActions ria ident />
                <#break>
            <#case "PopFieldInput">
                <#if control.findField(ria.fieldName).javaType == "UIFileExpression" >
        ${ident}if(this.${ria.fieldName} != null) set(this.${ria.fieldName}, req.popUploadedFile(this.${ria.fieldName}.getParameter()));
                <#elseif control.findField(ria.fieldName).javaType == "UIInputExpression" >
        ${ident}if(this.${ria.fieldName} != null) set(this.${ria.fieldName}, req.popParameter(this.${ria.fieldName}.getParameter()));
                </#if>
                <#break>
            <#case "PopAllFieldInputs">
        ${ident}inputFiles().stream().forEachOrdered(inputFile -> set(inputFile, req.popUploadedFile(inputFile.getParameter())));
        ${ident}inputs().stream().forEachOrdered(input -> set(input, req.popParameter(input.getParameter())));
                <#break>
            <#case "ReadFieldInput">
                <#if control.findField(ria.fieldName).javaType == "UIFileExpression" >
        ${ident}if(this.${ria.fieldName} != null) set(this.${ria.fieldName}, req.getUploadedFile(this.${ria.fieldName}.getParameter()));
                <#elseif control.findField(ria.fieldName).javaType == "UIInputExpression" >
        ${ident}if(this.${ria.fieldName} != null) set(this.${ria.fieldName}, req.getParameter(this.${ria.fieldName}.getParameter()));
                </#if>
                <#break>
            <#case "ReadAllFieldInputs">
        ${ident}inputFiles().stream().forEachOrdered(inputFile -> set(inputFile, req.getUploadedFile(inputFile.getParameter())));
        ${ident}inputs().stream().forEachOrdered(input -> set(input, req.getParameter(input.getParameter())));
                <#break>
            <#case "ReadChildren">
                <#if control.findField(ria.fieldName).javaType.startsWith("List")>
        ${ident}if(this.${ria.fieldName} != null) this.${ria.fieldName}.forEachOrdered(control -> control.readInput(req, env));
                <#else>
        ${ident}if(this.${ria.fieldName} != null) this.${ria.fieldName}.readInput(req, env);
                </#if>
                <#break>
            <#case "ReadAllChildren">
        ${ident}childs().forEach(control -> control.readInput(req, env));
                <#break>
        </#switch>
        </#list>
    </#macro>
    <#macro printForActions forStmt ident>
        ${ident}for(Object ${forStmt.var} : ${forStmt.in})
        ${ident}{
        <#assign newIdent = ident + "    " />
        <@printReadInputActions forStmt.actions newIdent />
        ${ident}}
    </#macro>
    <#macro printIfActions ifStmt ident>
        ${ident}if(${ifStmt.condition})
        ${ident}{
        <#assign newIdent = ident + "    " />
        <@printReadInputActions ifStmt.actions newIdent />
        ${ident}}
    </#macro>
    <#macro printElseActions ifStmt ident>
        ${ident}else
        ${ident}{
        <#assign newIdent = ident + "    " />
        <@printReadInputActions ifStmt.actions newIdent />
        ${ident}}
    </#macro>
    @Override
    public void readInput(ControlInputReader req, ElEnvironment env)
    {
        <@printReadInputActions control.input.actions "" />
    }

    </#if>
    <#if control.execute??>
    <#macro printExecuteEventActions actions ident>
        <#list actions as ria>
        <#switch ria.class.simpleName>
            <#case "PushEnvVar">
        ${ident}env.pushVar(${ria.var}, ${ria.value!});
                <#break>
            <#case "PopEnvVar">
        ${ident}env.popVar(${ria.var});
                <#break>
            <#case "ExecuteForEachData">
                <@printExecForActions ria ident />
                <#break>
            <#case "ExecuteIfData">
                <@printExecIfActions ria ident />
                <#break>
            <#case "ExecuteElseData">
                <@printExecElseActions ria ident />
                <#break>
            <#case "ExecuteAllEvents">
        ${ident}for (UIEvent event : events()) if(eventTriggered(req, event)) return invokeEvent(event);
                <#break>
            <#case "ReadAllChildren">
        ${ident}for (Control control : childs())
        ${ident}{
        ${ident}${"    "}EventResult result = control.executeEvent(req, env);
        ${ident}${"    "}if(result != null) return result;
        ${ident}}
                <#break>
        </#switch>
        </#list>
    </#macro>
    <#macro printExecForActions forStmt ident>
        ${ident}for(Object ${forStmt.var} : ${forStmt.in})
        ${ident}{
        <#assign newIdent = ident + "    " />
        <@printExecuteEventActions forStmt.actions newIdent />
        ${ident}}
    </#macro>
    <#macro printExecIfActions ifStmt ident>
        ${ident}if(${ifStmt.condition})
        ${ident}{
        <#assign newIdent = ident + "    " />
        <@printExecuteEventActions ifStmt.actions newIdent />
        ${ident}}
    </#macro>
    <#macro printExecElseActions ifStmt ident>
        ${ident}else
        ${ident}{
        <#assign newIdent = ident + "    " />
        <@printExecuteEventActions ifStmt.actions newIdent />
        ${ident}}
    </#macro>
    @Override
    public EventResult executeEvent(ControlInputReader req, ElEnvironment env)
    {
        <@printExecuteEventActions control.execute.actions "" />
        return null;
    }

    </#if>
}
