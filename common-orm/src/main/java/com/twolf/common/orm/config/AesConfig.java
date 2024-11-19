package com.twolf.common.orm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * aes秘钥配置类
 * @Author twolf
 * @Date 2022/4/28 15:23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aes")
public class AesConfig {

    /** 秘钥 */
    private String secret = "760018326a294f2bbc4e1d3c4e43b2b3";

}