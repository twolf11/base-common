
package ${package.Mapper};

import java.util.List;
import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if generateKeyXml??>
import org.apache.ibatis.annotations.Param;
</#if>
/**
* ${table.comment} Mapper接口
* @Author ${author}
* @Date ${date}
*/
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {


    /**
    * 根据条件查询表${table.name}信息
    * @param ${table.entityPath} 查询参数
    * @return java.util.List<${package.Entity}.${entity}>
    * @author ${author}
    * @date ${date}
    **/
	List<${entity}> findByCondition(${entity} ${table.entityPath});

<#if generateKeyXml??>
    <#list table.fields as field>
        <#if field.keyFlag>

            /**
            * 根据主键${field.propertyName}查询表${table.name}信息
            * @param ${field.propertyName} 主键
            * @return java.util.List<${package.Entity}.${entity}>
            * @author ${author}
            * @date ${date}
            **/
            ${entity} findBy${field.propertyName}(@Param("${field.propertyName}") ${field.propertyType} ${field.propertyName});

            /**
            * 新增表${table.name}信息
            * @param ${table.entityPath} 添加信息
            * @return java.lang.Integer
            * @author ${author}
            * @date ${date}
            **/
            Integer add${entity}(${entity} ${table.entityPath});

            /**
            * 根据主键${field.propertyName}更新表${table.name}信息
            * @param ${table.entityPath} 更新信息
            * @return java.lang.Integer
            * @author ${author}
            * @date ${date}
            **/
            Integer update${entity}By${field.propertyName}(${entity} ${table.entityPath});

            /**
            * 根据主键${field.propertyName}查询表${table.name}信息
            * @param ${field.propertyName} 主键id
            * @return java.lang.Integer
            * @author ${author}
            * @date ${date}
            **/
            Integer delete${entity}By${field.propertyName}(@Param("${field.propertyName}") ${field.propertyType} ${field.propertyName});
        </#if>
    </#list>
</#if>

}
