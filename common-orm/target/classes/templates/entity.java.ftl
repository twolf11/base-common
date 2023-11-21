package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if entityLombokModel>
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
</#if>

/**
* ${table.comment}
* @Author ${author}
* @Date ${date}
*/
<#if entityLombokModel>
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
</#if>
<#if table.convert>
@TableName("${table.name}")
</#if>
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#else>
public class ${entity} implements Serializable {
</#if>

    <#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>
        <#if field.keyFlag>
            <#assign keyPropertyName="${field.propertyName}"/>
        </#if>
        <#if field.keyFlag>
            <#assign keyPropertyName="${field.propertyName}"/>
        </#if>
<#--        <#if field.comment != "">-->
<#--            @Schema(description = "${field.comment}")-->
<#--        </#if>-->
        <#if field.comment != "">
            /** ${field.comment} */
        </#if>
        <#-- ----------  主键  ---------->
        <#if field.keyFlag>
            <#if field.keyIdentityFlag>
                @TableId(value = "${field.name}", type = IdType.AUTO)
            <#elseif idType?? && idType!= "">
                @TableId(value = "${field.name}", type = IdType.${idType})
            <#elseif field.convert>
                @TableId("${field.name}")
            </#if>
        <#-- ----------  普通字段  ---------->
        <#elseif field.fill??>
        <#-- ----------  存在字段填充设置  ---------->
            <#if field.convert>
                @TableField(value = "${field.name}", fill = FieldFill.${field.fill})
            <#else>
                @TableField(fill = FieldFill.${field.fill})
            </#if>
        <#elseif field.convert>
            @TableField("${field.name}")
        </#if>
        <#-- ----------  乐观锁注解  ---------->
        <#if versionFieldName?? && versionFieldName == field.name>
        @Version
        </#if>
        <#-- ----------  逻辑删除注解  ---------->
        <#if logicDeleteFieldName?? && logicDeleteFieldName == field.name>
        @Tablelogic
        </#if>
        private ${field.propertyType} ${field.propertyName};
   </#list>
    <#-- ----------  END 字段循环遍历  ---------->
    <#if !entityLombokModel>
        <#list table.fields as field>
            <#if field.columnType=="BOOLEAN">
                <#assign getprefix="is"/>
            <#else>
                <#assign getprefix="get"/>
            </#if>

    public ${field.propertyType} ${getprefix}${field.capitalName}() {
    return ${field.propertyName};
    }

            <#if entityBuilderModel>
                public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
            <#else>
                public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
            </#if>
            this.${field.propertyName} = ${field.propertyName};
            <#if entityBuilderModel>
                return this;
            </#if>
            }
        </#list>
    </#if>


    <#if !entityLombokModel>
    @Override
    public String toString() {
    return "${entity}{" +
    <#assign index = 0>
    <#list table.fields as field>
        <#if index == 0>
        "${field.propertyName}=" + ${field.propertyName} +
            <#assign index++>
        <#else>
        ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
    "}";
    }
    </#if>

}
