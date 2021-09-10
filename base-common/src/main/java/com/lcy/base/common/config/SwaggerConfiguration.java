package com.lcy.base.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.lcy.base.common.response.CommonCode;
import lombok.Data;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Description swagger配置类，打开swagger配置
 * @Author lcy
 * @Date 2021/4/25 9:35
 */
@EnableOpenApi
@Component
@Data
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfiguration {

    /**
     * 是否开启swagger
     */
    private boolean enable;

    /**
     * swagger相关配置
     * @return springfox.documentation.spring.web.plugins.Docket
     * @author lcy
     * @date 2021/8/11 17:06
     **/
    @Bean
    public Docket webApiDoc(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("用户端接口文档")
                .pathMapping("/")
                // 定义是否开启swagger，false为关闭，可以通过变量控制，线上关闭
                .enable(enable)
                //配置api文档元信息
                .apiInfo(apiInfo())
                // 选择哪些接口作为swagger的doc发布
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lcy.paas"))
                //正则匹配请求路径，并分配至当前分组
                .paths(PathSelectors.ant("/api/**"))
                .build()
                //swagger 3.0新配置
                //配置请求参数
                .globalRequestParameters(globalRequestParameters())
                //配置响应参数
                .globalResponses(HttpMethod.GET,globalResponseMessage())
                .globalResponses(HttpMethod.POST,globalResponseMessage());
    }

    /**
     * 管理端的api相关配置
     * @return sp ringfox.documentation.service.ApiInfo
     * @author lcy
     * @date 2021/8/11 17:06
     **/
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("pass-cloud")
                .description("paas接口文档")
                .contact(new Contact("lichaoyu","127.0.0.1","563795085@qq.com"))
                .version("12")
                .build();
    }

    /**
     * 请求参数配置
     * @return java.util.List<springfox.documentation.service.RequestParameter>
     * @author lcy
     * @date 2021/8/11 17:18
     **/
    private List<RequestParameter> globalRequestParameters(){
        List<RequestParameter> requestParameters = new ArrayList<>(1);
        requestParameters.add(new RequestParameterBuilder()
                //字段名称
                .name("token")
                //注释
                .description("登录令牌")
                //类型
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return requestParameters;
    }

    /**
     * 生成通用响应信息
     * @return java.util.List<springfox.documentation.service.Response>
     * @author lcy
     * @date 2021/8/11 17:21
     **/
    private List<Response> globalResponseMessage(){
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code(CommonCode.SUCCESS.getCode()).description(CommonCode.SUCCESS.getMessage()).build());
        responseList.add(new ResponseBuilder().code(CommonCode.SERVER_EXCEPTION.getCode()).description(CommonCode.SERVER_EXCEPTION.getMessage()).build());
        responseList.add(new ResponseBuilder().code(CommonCode.SERVER_ERROR.getCode()).description(CommonCode.SERVER_ERROR.getMessage()).build());
        return responseList;
    }

}
