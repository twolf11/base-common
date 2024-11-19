package com.twolf.common.core.util;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

/**
 * 获取spring上下文对象工具
 * @Author twolf
 * @Date 2020/12/7 17:02
 */
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * spring上下文对象
     */
    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 根据class获取spring容器里的bean
     * @param clazz 需要获取bean的class
     * @return T
     * @author lcy
     * @date 2020/12/7 17:09
     **/
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据bean的名称获取bean
     * @param beanName bean的别名
     * @return T
     * @author lcy
     * @date 2021/3/15 12:39
     **/
    @SuppressWarnings(value = {"unchecked"})
    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 根据key获取配置文件
     * @param key key
     * @return java.lang.String
     * @author lcy
     * @date 2020/12/7 17:10
     **/
    public static String getProperty(String key) {
        //获取配置文件对象
        Environment environment = getBean(Environment.class);
        //根据key获取值
        return environment.getProperty(key);
    }
}

