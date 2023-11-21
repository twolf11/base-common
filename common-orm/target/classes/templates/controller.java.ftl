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

}