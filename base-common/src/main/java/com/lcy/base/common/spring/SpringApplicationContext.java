package com.lcy.base.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.data.util.CastUtils;
import org.springframework.stereotype.Component;

/**
 * @Description 获取spring上下文对象工具
 * @Author lcy
 * @Date 2020/12/7 17:02
 */
@Component
public class SpringApplicationContext implements ApplicationContextAware {

    /**
     * spring上下文对象
     */
    private static ApplicationContext applicationContext;

    @Override public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        SpringApplicationContext.applicationContext = applicationContext;
    }

    /**
     * 获取spring上下文对象
     *
     * @return org.springframework.context.ApplicationContext
     * @author lcy
     * @date 2020/12/7 17:08
     **/
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 根据class获取spring容器里的bean
     *
     * @param clazz 需要获取bean的class
     * @return T
     * @author lcy
     * @date 2020/12/7 17:09
     **/
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据bean的名称获取bean
     *
     * @param beanName bean的别名
     * @return T
     * @author lcy
     * @date 2021/3/15 12:39
     **/
    public static <T> T getBean(String beanName){
        return CastUtils.cast(applicationContext.getBean(beanName));
    }

    /**
     * 根据key获取配置文件
     *
     * @param key key
     * @return java.lang.String
     * @author lcy
     * @date 2020/12/7 17:10
     **/
    public static String getProperty(String key){
        //获取配置文件对象
        Environment environment = getBean(Environment.class);
        //根据key获取值
        return environment.getProperty(key);
    }
}
