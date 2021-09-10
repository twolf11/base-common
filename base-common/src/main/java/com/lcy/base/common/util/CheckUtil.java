package com.lcy.base.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 校验工具类
 * @Author lcy
 * @Date 2021/8/15 13:37
 */
public class CheckUtil {

    /**
     * 邮箱正则
     */
    private static final Pattern MAIL_PATTERN = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

    /**
     * 手机号正则
     */
    private static final Pattern PHONE_PATTERN = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    /**
     * 判断是否邮箱地址
     * @param email 邮箱
     * @return boolean
     * @author lcy
     * @date 2021/8/15 13:38
     **/
    public static boolean isEmail(String email){
        if (Tools.isEmpty(email)) {
            return false;
        }
        Matcher m = MAIL_PATTERN.matcher(email);
        return m.matches();
    }

    /**
     * 判断是否电话号码
     * @param phone 电话号
     * @return boolean
     * @author lcy
     * @date 2021/8/15 13:38
     **/
    public static boolean isPhone(String phone){
        if (Tools.isEmpty(phone)) {
            return false;
        }
        Matcher m = PHONE_PATTERN.matcher(phone);
        return m.matches();
    }
}
