
package ${model.package};

import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import org.bridje.ioc.Ioc;
import org.bridje.orm.*;
import javax.annotation.Generated;
<#list support.allImports as imp>${imp}</#list>

/**
 * This class represents the ${model.name} data model.
 * it must be use to read and write ${model.name} entities.
 * The full list of ${model.name} entities is the following.
 * <ul>
<#list model.entities as entity >
 * <li>${entity.name}<br></li>
</#list>
 * </ul>
 */
@Generated(value = "org.bridje.orm.srcgen.OrmSourceGenerator", date = "${.now?string("yyyy-MM-dd")}", comments = "Generated by Bridje ORM API")
public class ${model.name} extends OrmModel
{
    private static OrmService ormServ;

    <#list support.allClasses as cls>
    private ${cls.name} support${cls.name};

    </#list>
    /**
     * Constructor for this ORM model, this constructor is mean to be called 
     * by the ORM service.
     * 
     * @param context The context to be use.
     * @param entities The entities for this model.
     * @param tables The tables for this model.
     */
    private ${model.name}(EntityContext context, List<Class<?>> entities, List<Table<?>> tables)
    {
        super(context, entities, tables);
    }

    /**
     * Gets the model available in the current thread local storage.
     * @return The current ${model.name} model.
     */
    public static ${model.name} get()
    {
        if(ormServ == null) ormServ = Ioc.context().find(OrmService.class);
        return ormServ.getModel(${model.name}.class);
    }

    <#list model.entities as entity >
    <#list entity.operations as crudOp >
    <#if crudOp.operationType == "CREATE" >
    /**
     * This method creates a new ${entity.name} entity. and insert it into the database.
    <#list crudOp.params as param>
     * @param ${param.name} ${param.description!}
    </#list>
     * @return The created ${entity.name} object.
     * @throws SQLException If any SQLException occurs.
     */
    ${crudOp.modifier?lower_case} ${entity.name} ${crudOp.name}(<#list crudOp.params as param>${param.javaType} ${param.name}<#if param_has_next>, </#if></#list>) throws SQLException
    {
        ${entity.name} entity = new ${entity.name}();
        <#list crudOp.sets as setField>
        <#if setField.ifNull>
        if(entity.get${setField.field.name?cap_first}() == null)
        {
            entity.set${setField.field.name?cap_first}(${setField.value});
        }
        <#else>
        entity.set${setField.field.name?cap_first}(${setField.value});
        </#if>
        </#list>
        <#list crudOp.params as param>
        entity.set${param.name?cap_first}(${param.name});
        </#list>
        getContext().insert(entity);
        return entity;
    }

    <#elseif crudOp.operationType == "READ" && crudOp.resultType == "ALL" >
    /**
     * This method finds a list of <#if crudOp.resultField??>${crudOp.resultField.javaType}<#else>${entity.name}</#if> object from the database.
    <#list crudOp.params as param>
     * @param ${param.name} ${param.description!}
    </#list>
     * @return A list of <#if crudOp.resultField??>${crudOp.resultField.javaType}<#else>${entity.name}</#if> objects.
     * @throws SQLException If any SQLException occurs.
     */
    ${crudOp.modifier?lower_case} List<<#if crudOp.resultField??>${crudOp.resultField.javaType}<#else>${entity.name}</#if>> ${crudOp.name}(<#list crudOp.params as param>${param.javaType} ${param.name}<#if param_has_next>, </#if></#list>) throws SQLException
    {
        return getContext().query(${entity.name}.TABLE)
                        <#if (crudOp.params?? && crudOp.params?has_content) || (crudOp.conditions?? && crudOp.conditions?has_content)>
                        .where(
                            <#assign first = true />
                            <#list crudOp.params as param>
                            <#if first>
                            ${param.entity.name}.${param.column?upper_case}.eq(${param.name})
                            <#else>
                            .and(${param.entity.name}.${param.column?upper_case}.eq(${param.name}))
                            </#if>
                            <#assign first = false />
                            </#list>
                            <#list crudOp.conditions as cond>
                            <#if first>
                            ${cond.field.entity.name}.${cond.field.column?upper_case}.${cond.operator}(${cond.value})
                            <#else>
                            .and(${cond.field.entity.name}.${cond.field.column?upper_case}.${cond.operator}(${cond.value}))
                            </#if>
                            <#assign first = false />
                            </#list>
                        )
                        </#if>
                        <#if crudOp.orderBys?? && crudOp.orderBys?has_content>
                        .orderBy(<#list crudOp.orderBys as ob>${entity.name}.${ob.field.column?upper_case}.${ob.type?lower_case}()<#sep>, </#sep></#list>)
                        </#if>
                        .fetchAll(<#if crudOp.resultField??>${entity.name}.${crudOp.resultField.column?upper_case}</#if>);
    }

    <#elseif crudOp.operationType == "READ" && crudOp.resultType == "ONE" >
    /**
     * This method finds a <#if crudOp.resultField??>${crudOp.resultField.javaType}<#else>${entity.name}</#if> object from the database.
    <#list crudOp.params as param>
     * @param ${param.name} ${param.description!}
    </#list>
     * @return A <#if crudOp.resultField??>${crudOp.resultField.javaType}<#else>${entity.name}</#if> object.
     * @throws SQLException If any SQLException occurs.
     */
    ${crudOp.modifier?lower_case} <#if crudOp.resultField??>${crudOp.resultField.javaType}<#else>${entity.name}</#if> ${crudOp.name}(<#list crudOp.params as param>${param.javaType} ${param.name}<#if param_has_next>, </#if></#list>) throws SQLException
    {
        return getContext().query(${entity.name}.TABLE)
                        <#if (crudOp.params?? && crudOp.params?has_content) || (crudOp.conditions?? && crudOp.conditions?has_content)>
                        .where(
                            <#assign first = true />
                            <#list crudOp.params as param>
                            <#if first>
                            ${param.entity.name}.${param.column?upper_case}.eq(${param.name})
                            <#else>
                            .and(${param.entity.name}.${param.column?upper_case}.eq(${param.name}))
                            </#if>
                            <#assign first = false />
                            </#list>
                            <#list crudOp.conditions as cond>
                            <#if first>
                            ${cond.field.entity.name}.${cond.field.column?upper_case}.${cond.operator}(${cond.value})
                            <#else>
                            .and(${cond.field.entity.name}.${cond.field.column?upper_case}.${cond.operator}(${cond.value}))
                            </#if>
                            <#assign first = false />
                            </#list>
                        )
                        </#if>
                        <#if crudOp.orderBys?? && crudOp.orderBys?has_content>
                        .orderBy(<#list crudOp.orderBys as ob>${entity.name}.${ob.field.column?upper_case}.${ob.type?lower_case}()<#sep>, </#sep></#list>)
                        </#if>
                        .fetchOne(<#if crudOp.resultField??>${entity.name}.${crudOp.resultField.column?upper_case}</#if>);
    }

    <#elseif crudOp.operationType == "UPDATE" >
    /**
     * This method updates an ${entity.name} object into the database.
     * @param entity The entity to be updated.
    <#list crudOp.params as param>
     * @param ${param.name} ${param.description!}
    </#list>
     * @throws SQLException If any SQLException occurs.
     */
    ${crudOp.modifier?lower_case} void ${crudOp.name}(${entity.name} entity<#list crudOp.params as param>, ${param.javaType} ${param.name}</#list>) throws SQLException
    {
        <#list crudOp.sets as setField>
        <#if setField.ifNull>
        if(entity.get${setField.field.name?cap_first}() == null)
        {
            entity.set${setField.field.name?cap_first}(${setField.value});
        }
        <#else>
        entity.set${setField.field.name?cap_first}(${setField.value});
        </#if>
        </#list>
        <#list crudOp.params as param>
        entity.set${param.name?cap_first}(${param.name});
        </#list>
        getContext().update(entity);
    }

    <#elseif crudOp.operationType == "DELETE_ENTITY" >
    /**
     * This method deletes the given ${entity.name} object into the database.
     * @param entity The entity to be deleted.
     * @throws SQLException If any SQLException occurs.
     */
    ${crudOp.modifier?lower_case} void ${crudOp.name}(${entity.name} entity) throws SQLException
    {
        getContext().delete(entity);
    }

    <#elseif crudOp.operationType == "DELETE">
    /**
     * This method deletes all ${entity.name} objects in the database that match the given parameters.
     * @return An integer representing the number of record deleted in the database.
    <#list crudOp.params as param>
     * @param ${param.name} ${param.description!}
    </#list>
     * @throws SQLException If any SQLException occurs.
     */
    ${crudOp.modifier?lower_case} int ${crudOp.name}(<#list crudOp.params as param>${param.javaType} ${param.name}<#if param_has_next>, </#if></#list>) throws SQLException
    {
        return getContext().query(${entity.name}.TABLE)
                        <#if crudOp.params?? && crudOp.params?has_content>
                        .where(
                            <#assign first = true />
                            <#list crudOp.params as param>
                            <#if first>
                            ${param.entity.name}.${param.column?upper_case}.eq(${param.name})
                            <#else>
                            .and(${param.entity.name}.${param.column?upper_case}.eq(${param.name}))
                            </#if>
                            <#assign first = false />
                            </#list>
                            <#list crudOp.conditions as cond>
                            <#if first>
                            ${cond.field.entity.name}.${cond.field.column?upper_case}.${cond.operator}(${cond.value})
                            <#else>
                            .and(${cond.field.entity.name}.${cond.field.column?upper_case}.${cond.operator}(${cond.value}))
                            </#if>
                            <#assign first = false />
                            </#list>
                        )
                        </#if>
                        .delete();
    }

    <#elseif crudOp.operationType == "SAVE">
    /**
     * This method save a new ${entity.name} object in the database.
     * @param entity The entity to be saved.
     * @throws SQLException If any SQLException occurs.
     */
    ${crudOp.modifier?lower_case} void ${crudOp.name}(${entity.name} entity) throws SQLException
    {
        <#list crudOp.sets as setField>
        <#if setField.ifNull>
        if(entity.get${setField.field.name?cap_first}() == null)
        {
            entity.set${setField.field.name?cap_first}(${setField.value});
        }
        <#else>
        entity.set${setField.field.name?cap_first}(${setField.value});
        </#if>
        </#list>
        if(entity.get${entity.keyField.name?cap_first}() == null)
        {
            getContext().insert(entity);
        }
        else
        {
            getContext().update(entity);
        }
    }

    </#if>
    </#list>
    </#list>
    <#list support.allMethods as methodInf>
    <#if methodInf.method.javaDoc! != "">${methodInf.method.javaDoc}    </#if>${methodInf.method.declarationAsString}
    {
        if(support${methodInf.classDec.name} == null) support${methodInf.classDec.name} = Ioc.context().find(${methodInf.classDec.name}.class);
        <#if methodInf.method.type != "void">return </#if>support${methodInf.classDec.name}.${methodInf.method.name}(this<#if methodInf.method.parameters?has_content>, </#if><#list methodInf.method.parameters as param>${param.name}<#sep>, </#sep></#list>);
    }

    </#list>
}