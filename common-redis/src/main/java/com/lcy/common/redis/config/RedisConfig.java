package com.lcy.common.redis.config;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

/**
 * redis配置类
 * @Author lcy
 * @Date 2019/10/30 20:15
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * 自定义spring cache缓存key规则
     * @return org.springframework.cache.interceptor.KeyGenerator
     * @author lcy
     * @date 2021/6/29 14:17
     **/
    @Bean
    public KeyGenerator keyGenerator(){
        return (Object target,Method method,Object... params) ->
        {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @Bean
    public <K,V> RedisTemplate<K,V> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<K,V> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(serializingObjectMapper(),Object.class);
        // value值的序列化采用jacksonRedisSerializer
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


    /**
     * 获取序列化的jackson对象
     * @return com.fasterxml.jackson.databind.ObjectMapper
     * @author lcy
     * @date 2021/6/29 14:12
     **/
    public ObjectMapper serializingObjectMapper(){
        //创建jsr310包下的javaTimeModule对象，其中包含了jdk8以后的时间类型序列化和反序列化配置
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //LocalDateTime序列化设置为yyyy-MM-dd HH:mm:ss
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //增加序列化到配置当中
        javaTimeModule.addSerializer(LocalDateTime.class,localDateTimeSerializer);
        //LocalDateTime反序列化设置为yyyy-MM-dd HH:mm:ss
        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //增加反序列化到配置当中
        javaTimeModule.addDeserializer(LocalDateTime.class,localDateTimeDeserializer);
        ObjectMapper objectMapper = new ObjectMapper();
        //注册配置--jdk8类型
        objectMapper.registerModule(javaTimeModule);
        // 只针对非空的值进行序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 将类型序列化到属性json字符串中
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL,JsonTypeInfo.As.PROPERTY);
        objectMapper.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);

        return objectMapper;
    }
}