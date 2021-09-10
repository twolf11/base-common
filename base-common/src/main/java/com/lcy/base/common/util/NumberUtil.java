package com.lcy.base.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @Description 数字工具类-包含金额等等
 * @Author lcy
 * @Date 2021/4/20 14:11
 */
public class NumberUtil {

    /**
     * 金额通用格式，整数不变，小数点保留两位
     */
    private static final String AMOUNT_PATTERN = "###0.00";

    /**
     * 金额格式，整数每三位通过,分割，小数点保留两位
     */
    private static final String AMOUNT_PATTERN_SEMICOLON = "###,###0.00";

    /**
     * 金额格式化 丢弃小数点后两位值
     *
     * @param amount 金额
     * @return java.math.BigDecimal
     * @author lcy
     * @date 2021/4/20 14:24
     **/
    public static BigDecimal amountFormat(int amount){
        return new BigDecimal(String.valueOf(amount)).setScale(2,RoundingMode.DOWN);
    }

    /**
     * 金额格式化 丢弃小数点后两位值
     *
     * @param amount 金额
     * @return java.math.BigDecimal
     * @author lcy
     * @date 2021/4/20 14:24
     **/
    public static BigDecimal amountFormat(long amount){
        return new BigDecimal(String.valueOf(amount)).setScale(2,RoundingMode.DOWN);
    }

    /**
     * 金额格式化 丢弃小数点后两位值
     *
     * @param amount 金额
     * @return java.math.BigDecimal
     * @author lcy
     * @date 2021/4/20 14:24
     **/
    public static BigDecimal amountFormat(float amount){
        return new BigDecimal(String.valueOf(amount)).setScale(2,RoundingMode.DOWN);
    }

    /**
     * 金额格式化 丢弃小数点后两位值
     *
     * @param amount 金额
     * @return java.math.BigDecimal
     * @author lcy
     * @date 2021/4/20 14:24
     **/
    public static BigDecimal amountFormat(double amount){
        return new BigDecimal(String.valueOf(amount)).setScale(2,RoundingMode.DOWN);
    }

    /**
     * 金额格式化 丢弃小数点后两位值
     *
     * @param amount 金额
     * @return java.math.BigDecimal
     * @author lcy
     * @date 2021/4/20 14:24
     **/
    public static BigDecimal amountFormat(String amount){
        return new BigDecimal(String.valueOf(amount)).setScale(2,RoundingMode.DOWN);
    }

    /**
     * 金额格式化 根据参数选择格式化方式
     * <p>
     * RoundingMode.CEILING：取右边最近的整数，如果是负数则去掉小数部分，正数向上取整
     * <p>
     * RoundingMode.DOWN：去掉小数部分取整。也就是正数取左边，负数取右边，相当于向原点靠近的方向取整
     * <p>
     * RoundingMode.FLOOR：取左边最近的正数，如果是正数则去掉小数部分，负数向下取整
     * <p>
     * RoundingMode.HALF_DOWN:五舍六入，负数先取绝对值再五舍六入再负数
     * <p>
     * RoundingMode.HALF_UP:四舍五入，负数原理同上
     * <p>
     * RoundingMode.HALF_EVEN:这个比较绕，整数位若是奇数则四舍五入，若是偶数则五舍六入
     *
     * @param amount       金额
     * @param scale        小数保留位数
     * @param roundingMode 舍位模式
     * @return java.math.BigDecimal
     * @author lcy
     * @date 2021/4/20 14:33
     **/
    public static BigDecimal amountFormat(int amount,int scale,RoundingMode roundingMode){
        return new BigDecimal(String.valueOf(amount)).setScale(scale,roundingMode);
    }

    /**
     * 金额格式化 根据参数选择格式化方式
     *
     * @param amount       金额
     * @param scale        小数保留位数
     * @param roundingMode 舍位模式
     * @return java.math.BigDecimal
     * @author lcy
     * @date 2021/4/20 14:33
     **/
    public static BigDecimal amountFormat(long amount,int scale,RoundingMode roundingMode){
        return new BigDecimal(String.valueOf(amount)).setScale(scale,roundingMode);
    }

    /**
     * 金额格式化 根据参数选择格式化方式
     *
     * @param amount       金额
     * @param scale        小数保留位数
     * @param roundingMode 舍位模式
     * @return java.math.BigDecimal
     * @author lcy
     * @date 2021/4/20 14:33
     **/
    public static BigDecimal amountFormat(float amount,int scale,RoundingMode roundingMode){
        return new BigDecimal(String.valueOf(amount)).setScale(scale,roundingMode);
    }

    /**
     * 金额格式化 根据参数选择格式化方式
     *
     * @param amount       金额
     * @param scale        小数保留位数
     * @param roundingMode 舍位模式
     * @return java.math.BigDecimal
     * @author lcy
     * @date 2021/4/20 14:33
     **/
    public static BigDecimal amountFormat(double amount,int scale,RoundingMode roundingMode){
        return new BigDecimal(String.valueOf(amount)).setScale(scale,roundingMode);
    }

    /**
     * 金额格式化 根据参数选择格式化方式
     *
     * @param amount       金额
     * @param scale        小数保留位数
     * @param roundingMode 舍位模式
     * @return java.math.BigDecimal
     * @author lcy
     * @date 2021/4/20 14:33
     **/
    public static BigDecimal amountFormat(String amount,int scale,RoundingMode roundingMode){
        return new BigDecimal(String.valueOf(amount)).setScale(scale,roundingMode);
    }

    /**
     * 根据指定格式格式化数字
     *
     * @param number  数字
     * @param pattern 格式
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/20 14:39
     **/
    public static String format(double number,String pattern){
        NumberFormat fmt = Tools.isNotEmpty(pattern) ? new DecimalFormat(pattern) : new DecimalFormat();
        return fmt.format(number);
    }

    /**
     * 格式化数字:整数不变，小数点保留两位
     *
     * @param number 数字
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/20 14:39
     **/
    public static String format(double number){
        return new DecimalFormat(AMOUNT_PATTERN).format(number);
    }

    /**
     * 根据指定格式格式化数字
     *
     * @param number  数字
     * @param pattern 格式
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/20 14:39
     **/
    public static String format(int number,String pattern){
        NumberFormat fmt = Tools.isNotEmpty(pattern) ? new DecimalFormat(pattern) : new DecimalFormat();
        return fmt.format(number);
    }

    /**
     * 格式化数字:整数不变，小数点保留两位
     *
     * @param number 数字
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/20 14:39
     **/
    public static String format(int number){
        return new DecimalFormat(AMOUNT_PATTERN).format(number);
    }

    /**
     * 根据指定格式格式化数字
     *
     * @param number  数字
     * @param pattern 格式
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/20 14:39
     **/
    public static String format(long number,String pattern){
        NumberFormat fmt = Tools.isNotEmpty(pattern) ? new DecimalFormat(pattern) : new DecimalFormat();
        return fmt.format(number);
    }

    /**
     * 格式化数字:整数不变，小数点保留两位
     *
     * @param number 数字
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/20 14:39
     **/
    public static String format(long number){
        return new DecimalFormat(AMOUNT_PATTERN).format(number);
    }

}
