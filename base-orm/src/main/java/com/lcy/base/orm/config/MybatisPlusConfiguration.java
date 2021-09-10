package com.lcy.base.orm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

/**
 * @Description mybatis-plus配置
 * @Author lcy
 * @Date 2019/10/24 19:12
 */
@Configuration
@MapperScan(basePackages = {"com.lcy.**.mapper","com.lcy.**.**.mapper"})
public class MybatisPlusConfiguration {

    ///**
    // * 3.4此方式弃用分页插件
    // */
    //@Bean
    //public PaginationInterceptor paginationInterceptor(){
    //    return new PaginationInterceptor();
    //}

    ///**
    // * 乐观锁插件--3.3使用
    // */
    //@Bean
    //public OptimisticLockerInterceptor optimisticLockerInterceptor(){
    //    return new OptimisticLockerInterceptor();
    //}

    /**
     * 插件--3.4以后通过插件管理类夹在
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //乐观锁
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
