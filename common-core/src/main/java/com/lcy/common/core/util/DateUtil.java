package com.lcy.common.core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 * @Author lcy
 * @Date To0To1/1/1To 15:To7
 */
public class DateUtil {

    /**
     * LocalDate转Date
     * @param localDate localDate
     * @return java.util.Date
     * @author lcy
     * @date 2021/1/12 15:33
     **/
    public static Date localDateToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime转换为Date
     * @param localDateTime localDateTime
     * @return java.util.Date
     * @author lcy
     * @date 2021/1/12 15:34
     **/
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转LocalDate
     * @param date date
     * @return java.time.LocalDate
     * @author lcy
     * @date 2021/1/12 15:33
     **/
    public static LocalDate dateToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date转换为LocalDateTime
     * @param date date
     * @return java.time.LocalDateTime
     * @author lcy
     * @date 2021/1/12 15:34
     **/
    public static LocalDateTime dateToLocalDateTime(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Date转换为LocalTime
     * @param date date
     * @return java.time.LocalDateTime
     * @author lcy
     * @date 2021/1/12 15:34
     **/
    public static LocalTime dateToLocalTime(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    /**
     * 获取指定日期的毫秒
     * @param localDateTime LocalDateTime
     * @return java.lang.Long
     * @author lcy
     * @date 2021/1/12 15:41
     **/
    public static Long getMilliByTime(LocalDateTime localDateTime){
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     * @param localDateTime LocalDateTime
     * @return java.lang.Long
     * @author lcy
     * @date 2021/1/12 15:41
     **/
    public static Long getSecondsByTime(LocalDateTime localDateTime){
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式
     * @param localDateTime localDateTime
     * @param pattern       指定格式，如yyyy-MM-dd HH:mm:ss
     * @return java.lang.String
     * @author lcy
     * @date 2021/1/12 15:47
     **/
    public static String formatTime(LocalDateTime localDateTime,String pattern){
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取指定时间的指定格式
     * @param localDate localDate
     * @param pattern   指定格式，如yyyy-MM-dd
     * @return java.lang.String
     * @author lcy
     * @date 2021/1/12 15:47
     **/
    public static String formatTime(LocalDate localDate,String pattern){
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式
     * @param pattern 指定格式，如yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss
     * @return java.lang.String
     * @author lcy
     * @date 2021/1/12 15:52
     **/
    public static String now(String pattern){
        return formatTime(LocalDateTime.now(),pattern);
    }

    /**
     * 获取两个日期的相差的天数
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return long
     * @author lcy
     * @date 2021/1/12 15:57
     **/
    public static long betweenDay(LocalDateTime startTime,LocalDateTime endTime){
        Period period = Period.between(LocalDate.from(startTime),LocalDate.from(endTime));
        return period.getDays();
    }

    /**
     * 获取两个日期的相差的月数
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return long
     * @author lcy
     * @date 2021/1/12 15:57
     **/
    public static long betweenMonth(LocalDateTime startTime,LocalDateTime endTime){
        Period period = Period.between(LocalDate.from(startTime),LocalDate.from(endTime));
        return period.getMonths();
    }

    /**
     * 获取两个日期的相差的年数
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return long
     * @author lcy
     * @date 2021/1/12 15:57
     **/
    public static long betweenYear(LocalDateTime startTime,LocalDateTime endTime){
        Period period = Period.between(LocalDate.from(startTime),LocalDate.from(endTime));
        return period.getYears();
    }

    /**
     * 获取一天的开始时间 如2021,1,12 00:00
     * @param localDateTime localDateTime
     * @return java.time.LocalDateTime
     * @author lcy
     * @date 2021/1/12 15:59
     **/
    public static LocalDateTime getDayStart(LocalDateTime localDateTime){
        return localDateTime.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取一天的结束时间 如2021,1,12 00:00
     * @param localDateTime localDateTime
     * @return java.time.LocalDateTime
     * @author lcy
     * @date 2021/1/12 15:59
     **/
    public static LocalDateTime getDayEnd(LocalDateTime localDateTime){
        return localDateTime.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }

}
