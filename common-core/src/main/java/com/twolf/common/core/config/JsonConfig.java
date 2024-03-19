package com.twolf.common.core.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.twolf.common.core.config.serializer.LongToStringSerializer;

/**
 * json配置类
 * @Author lcy
 * @Date 2022/5/6 16:20
 */
@Configuration
public class JsonConfig {

    /** 序列化参数，如果配置为空则用默认 */
    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    private LocalDateTimeSerializer localDateTimeSerializer(){
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    private LocalDateTimeDeserializer localDateTimeDeserializer(){
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 解决日期序列化和反序列化问题，解决long字符过长返回给前端时精度丢失问题
     * @return org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
     * @author lcy
     * @date 2023/8/14 14:22
     **/
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
        return builder -> builder.serializerByType(LocalDateTime.class,localDateTimeSerializer())
                .deserializerByType(LocalDateTime.class,localDateTimeDeserializer())
                .serializerByType(Long.class,new LongToStringSerializer());
    }
}
