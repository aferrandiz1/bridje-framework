[#ftl]
<#ftl encoding="UTF-8">
<#include "../ThemeBase.ftl" >

[#list theme.resources as r]
<#macro render${r.name?cap_first}Scripts themeName>
[#list r.scripts as s]
    <@renderScript theme=themeName script="${s.href}" async=${s.async?c} defer=${s.defer?c} />
[/#list]
</#macro>

<#macro render${r.name?cap_first}Styles themeName>
[#list r.styles as s]
    <@renderStyle themeName "${s.href}" />
[/#list]
</#macro>

[/#list]
[#list theme.macros as m]
<#macro ${m.name} ${m.parameters}>
[#compress]${w.content}[/#compress]
</#macro>

[/#list]
[#list theme.controls as w]
<#macro render${w.name} control>
[#compress]${w.render}[/#compress]
</#macro>

[/#list]
<#macro renderControl control>
    <#if control.class.package.name == "${theme.package}">
        <#switch control.class.simpleName>
            [#list theme.controls as w]
            <#case "${w.name}">
              <@render${w.name} control />
              <#break>
            [/#list]
            <#default>
                Control control.class.simpleName Not Found
        </#switch>
    </#if>
</#macro>

<#macro renderThemeScripts themeName>
    [#list theme.defaultResources.scripts as s]
    <@renderScript theme=themeName script="${s.href}" async=${s.async?c} defer=${s.defer?c} />
    [/#list]
    <#list view.resources as r>
        <#assign macroName = "render" + r?cap_first + "Scripts" />
        <@.vars[macroName] themeName />
    </#list>
</#macro>

<#macro renderThemeStyles themeName>
    [#list theme.defaultResources.styles as s]
    <@renderStyle themeName "${s.href}" />
    [/#list]
    [#list theme.defaultResources.links as l]
    <@renderLink themeName "${l.rel}" "${l.href}" />
    [/#list]
    <#list view.resources as r>
        <#assign macroName = "render" + r?cap_first + "Styles" />
        <@.vars[macroName] themeName />
    </#list>
</#macro>

[#if theme.renderBody?? && theme.renderBody?has_content]
<#macro renderBody>
[#compress]${theme.renderBody!}[/#compress]
</#macro>
[/#if]

[#if theme.renderHead?? && theme.renderHead?has_content]
<#macro renderHead>
[#compress]${theme.renderHead!}[/#compress]
</#macro>
[/#if]

[#if theme.renderViewContainer?? && theme.renderViewContainer?has_content]
<#macro renderViewContainer>
[#compress]${theme.renderViewContainer!}[/#compress]
</#macro>
[/#if]

<@renderMain "${theme.name}" />