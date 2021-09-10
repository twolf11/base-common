package com.lcy.base.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @Description redisson配置类
 * @Author lcy
 * @Date 2021/5/11 11:23
 */
@ConfigurationProperties(prefix = "spring.redis.redisson")
@Component
@Data
public class RedissonProperties {

    /**
     * 配置的类型
     */
    private String type;

    /**
     * 配置的地址
     */
    private String address;

    /**
     * 配置的地址
     */
    private String password;

    /**
     * 主节点名称--这里只针对sentinel哨兵有作用
     */
    private String masterName;
}
