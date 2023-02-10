package com.lcy.common.core.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * @Description springdoc配置类
 * @Author lcy
 * @Date 2022/2/23 11:14
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi usersGroup(){
        return GroupedOpenApi.builder().group("open aip")
                //这是固定请求参数设置
                //.addOperationCustomizer((operation, handlerMethod) -> {
                //    operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
                //    return operation;
                //})
                .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Open API").version("1.0.0").description(
                        "这是open api相关在线接口文档，可用于生成相关的离线文档")
                        .termsOfService("http://server:port/context/")
                        .license(new License().name("lcy").url("http://server:port/context/"))))
                .build();
    }

}
