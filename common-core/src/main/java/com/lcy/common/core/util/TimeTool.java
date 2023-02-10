package com.lcy.common.core.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description 时间对比工具，执行代码过程的时间
 * @Author lcy
 * @Date 2020/9/3 9:17
 */
@Slf4j
public class TimeTool {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public interface TimeInterface {

        /**
         * 执行方法
         * @author lcy
         * @date 2020/9/3 9:55
         **/
        void executeMethod();
    }

    public interface PrintInterface {

        /**
         * 打印方法
         * @author lcy
         * @date 2020/9/3 9:55
         **/
        void printMethod();
    }

    /**
     * 计算方法执行过程所消耗的时间
     * @param title         标题
     * @param timeInterface 执行方法实现类
     * @author lcy
     * @date 2020/9/3 9:54
     **/
    public static void checkTime(String title,TimeInterface timeInterface){
        if (timeInterface == null) {
            return;
        }
        log.info("---------------------check start-------------------");
        log.info("title: " + title);
        LocalDateTime start = LocalDateTime.now();
        log.info("begin: " + DATE_TIME_FORMATTER.format(start));

        //执行方法
        timeInterface.executeMethod();

        LocalDateTime end = LocalDateTime.now();
        log.info("end: " + DATE_TIME_FORMATTER.format(end));
        //log.info("耗时秒数(s)：" + Duration.between(start,end).toMillis() / 1000);
        log.info("耗时毫秒数(ms)：" + Duration.between(start,end).toMillis());
        log.info("---------------------check end--------------------");
    }

    /**
     * 计算方法执行过程所消耗的时间
     * @param title          标题
     * @param timeInterface  执行方法实现类
     * @param printInterface 打印实现类
     * @author lcy
     * @date 2020/9/3 9:54
     **/
    public static void checkTime(String title,TimeInterface timeInterface,PrintInterface printInterface){
        checkTime(title,timeInterface);
        log.info("--------------------print--------------------");
        //执行打印方法
        if (printInterface != null) {
            printInterface.printMethod();
        }
        log.info("--------------------print--------------------");
    }

}
