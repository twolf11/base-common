package ${package.Controller};

<#if superControllerClass??>
import ${superControllerClassPackage};
</#if>
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.lcy.common.core.web.entity.domain.Result;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* ${table.comment}接口
* @Author ${author}
* @Date ${date}
*/
@RestController
@RequestMapping("/api/${table.name}/v1")
@Tag(name = "${table.comment}接口")
@AllArgsConstructor
@Slf4j
<#if superControllerClass??>
    public class ${table.controllerName} extends ${superControllerClass}{
<#else>
    public class ${table.controllerName} {
</#if>
/**
*${table.comment}service
**/
private final ${table.serviceName} ${table.entityPath}Service;

/**
* 新增
* @param ${table.entityPath} 添加信息
* @return Result
* @Author ${author}
* @date ${date}
**/
@PostMapping("add")
@Operation(summary = "新增")
public Result<Object> save(${entity} ${table.entityPath}){
${table.entityPath}Service.save(${table.entityPath});
    <#if superControllerClass??>
        return success();
    <#else>
        return Result.success();
    </#if>
}

/**
* 修改
* @param ${table.entityPath} 修改信息
* @return Result
* @Author ${author}
* @date ${date}
**/
@PostMapping("update")
@Operation(summary = "修改")
public Result<Object> update(${entity} ${table.entityPath}){
${table.entityPath}Service.updateById(${table.entityPath});
        <#if superControllerClass??>
            return success();
        <#else>
            return Result.success();
        </#if>
}
}