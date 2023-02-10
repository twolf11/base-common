<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

    <#if baseResultMap>
        <!-- 通用查询映射结果 -->
    <resultMap id="${entity}Map" type="${package.Entity}.${entity}">
        <#list table.fields as field>
            <#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}"/>
            </#if>
        </#list>
        <#list table.commonFields as field><#--生成公共字段 -->
        <result column="${field.name}" property="${field.propertyName}"/>
        </#list>
        <#list table.fields as field>
            <#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}"/>
            </#if>
        </#list>
    </resultMap>
    </#if>

    <!-- 通用设置 -->
<#if baseColumnList>
    <!-- 通用查询列 -->
    <sql id="Base_Column_List">
        <#list table.commonFields as field>${field.name},</#list>${table.fieldNames}
    </sql>

    <!-- 通用条件列 -->
    <sql id="${entity}ByCondition">
    <#list table.commonFields as field><#--生成公共字段-->
        <if test="${field.propertyName} != null <#if field.columnType=="STRING"> and ${field.propertyName} != ''</#if>">
            AND ${field.name} = ${r"#{"}${field.propertyName}${r"}"}
        </if>
    </#list>
    <#list table.fields as field>
        <#if !field.keyFlag><#--生成普通字段 -->
        <if test="${field.propertyName} != null<#if field.columnType=="STRING"> and ${field.propertyName} != ''</#if>">
            AND ${field.name} = ${r"#{"}${field.propertyName}${r"}"}
        </if>
        </#if>
    </#list>
    </sql>

    <!-- 通用设置列 -->
    <sql id="${entity}SetColumns">
    <#list table.commonFields as field><#--生成公共字段-->
        <if test="${field.propertyName} != null<#if field.columnType=="STRING"> and ${field.propertyName} != ''</#if>">
            ${field.name} = ${r"#{"}${field.propertyName}${r"}"},
        </if>
    </#list>
    <#list table.fields as field>
        <#if !field.keyFlag><#--生成普通字段 -->
        <if test="${field.propertyName} != null<#if field.columnType=="STRING"> and ${field.propertyName} != ''</#if>">
            ${field.name} = ${r"#{"}${field.propertyName}${r"}"},
        </if>
        </#if>
    </#list>
    </sql>
</#if>

    <!-- 根据条件查询表${table.name}信息 -->
    <select id="findByCondition" resultMap="${entity}Map">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${table.name}
        <where>
            <include refid="${entity}ByCondition" />
        </where>
    </select>

<#if generateKeyXml??>
    <#list table.fields as field>
        <#if field.keyFlag>
            <!-- 根据主键${field.propertyName}查询表${table.name}信息 -->
            <select id="findBy${field.propertyName}" resultMap="${entity}Map">
                SELECT
                <include refid="Base_Column_List"/>
                FROM ${table.name}
                <where>
                    ${field.name}=${r"#{"}${field.propertyName}${r"}"}
                </where>
            </select>

            <!-- 新增表${table.name}信息 -->
            <insert id="add${entity}">
                INSERT INTO ${table.name} (
                <#list table.fields as field>
                    <#if field_index gt 0>,</#if>${field.name}
                </#list>
                ) VALUES (
                <#list table.fields as field>
                    <#if field_index gt 0>,</#if>${r"#{"}${field.propertyName}${r"}"}
                </#list>
                )
            </insert>

            <!-- 根据主键${field.propertyName}更新表${table.name}信息 -->
            <update id="update${entity}By${field.propertyName}" parameterType="${package.Entity}.${entity}">
                UPDATE ${table.name}
                <set>
                    <include refid="${entity}SetColumns"/>
                </set>
                <where>
                    <#list table.fields as field><#if field.keyFlag>${field.name}=${r"#{"}${field.propertyName}${r"}"}</#if></#list>
                </where>
            </update>

            <!-- 根据主键${field.propertyName}删除表${table.name}信息 -->
            <delete id="delete${entity}By${field.propertyName}">
                DELETE FROM
                ${table.name}
                <where>
                    ${field.name}=${r"#{"}${field.propertyName}${r"}"}
                </where>
            </delete>
        </#if>
    </#list>
</#if>
</mapper>
