package com.lcy.base.common.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description 时间对比工具，执行代码过程的时间
 * @Author lcy
 * @Date 2020/9/3 9:17
 */
public class TimeTool {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public interface TimeInterface {

        /**
         * 执行方法
         *
         * @author lcy
         * @date 2020/9/3 9:55
         **/
        void executeMethod();
    }

    public interface PrintInterface {

        /**
         * 打印方法
         *
         * @author lcy
         * @date 2020/9/3 9:55
         **/
        void printMethod();
    }

    /**
     * 计算方法执行过程所消耗的时间
     *
     * @param title         标题
     * @param timeInterface 执行方法实现类
     * @author lcy
     * @date 2020/9/3 9:54
     **/
    public static void checkTime(String title,TimeInterface timeInterface){
        if (timeInterface == null) {
            return;
        }
        System.out.println("---------------------check start-------------------");
        System.out.println("title: " + title);
        LocalDateTime start = LocalDateTime.now();
        System.out.println("begin: " + DATE_TIME_FORMATTER.format(start));

        //执行方法
        timeInterface.executeMethod();

        LocalDateTime end = LocalDateTime.now();
        System.out.println("end: " + DATE_TIME_FORMATTER.format(end));
        //System.out.println("耗时秒数(s)：" + Duration.between(start,end).toMillis() / 1000);
        System.out.println("耗时毫秒数(ms)：" + Duration.between(start,end).toMillis());
        System.out.println("---------------------check end--------------------");
    }

    /**
     * 计算方法执行过程所消耗的时间
     *
     * @param title          标题
     * @param timeInterface  执行方法实现类
     * @param printInterface 打印实现类
     * @author lcy
     * @date 2020/9/3 9:54
     **/
    public static void checkTime(String title,TimeInterface timeInterface,PrintInterface printInterface){
        checkTime(title,timeInterface);
        System.out.println("--------------------print--------------------");
        //执行打印方法
        if (printInterface != null) {
            printInterface.printMethod();
        }
        System.out.println("--------------------print--------------------");
    }

}
