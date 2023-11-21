package com.lcy.common.core.util;

import java.util.Collection;
import java.util.Map;

import com.lcy.common.core.common.response.ResultCode;
import com.lcy.common.core.exception.BusinessException;

/**
 * 断言工具，抛出BusinessException
 * @Author lcy
 * @Date 2020/12/7 15:58
 */
public class AssertUtil {

    /**
     * 判断结果为true，抛出异常提示
     * @param expression 布尔值
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static void isTrue(boolean expression){
        isTrue(expression,"Assert Exception");
    }

    /**
     * 判断结果为true，抛出异常提示
     * @param expression 布尔值
     * @param message    提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static void isTrue(boolean expression,String message){
        if (expression) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断字符为空，抛出异常提示
     * @param str     需要判断的字符串
     * @param message 提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static void isEmpty(String str,String message){
        if (Tools.isEmpty(str)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断对象为null，抛出异常提示
     * @param t       需要判断的对象
     * @param message 提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isEmpty(T t,String message){
        if (Tools.isEmpty(t)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断数组为空，抛出异常提示
     * @param t       需要判断的对象
     * @param message 提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isEmpty(T[] t,String message){
        if (Tools.isEmpty(t)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断map为空，抛出异常提示
     * @param map     需要判断的对象
     * @param message 提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <K,V> void isEmpty(Map<K,V> map,String message){
        if (Tools.isEmpty(map)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断对象为空，抛出异常提示
     * @param collection 集合对象
     * @param message    提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isEmpty(Collection<T> collection,String message){
        if (Tools.isEmpty(collection)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断字符不为空，抛出异常提示
     * @param str     需要判断的字符串
     * @param message 提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static void isNotEmpty(String str,String message){
        if (Tools.isNotEmpty(str)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断对象不为null，抛出异常提示
     * @param t       需要判断的对象
     * @param message 提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isNotEmpty(T t,String message){
        if (Tools.isNotEmpty(t)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断数组不为空，抛出异常提示
     * @param t       需要判断的对象
     * @param message 提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isNotEmpty(T[] t,String message){
        if (Tools.isNotEmpty(t)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断map不为空，抛出异常提示
     * @param map     需要判断的对象
     * @param message 提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <K,V> void isNotEmpty(Map<K,V> map,String message){
        if (Tools.isNotEmpty(map)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断对象不为空，抛出异常提示
     * @param collection 集合对象
     * @param message    提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isNotEmpty(Collection<T> collection,String message){
        if (Tools.isNotEmpty(collection)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断结果为true，抛出异常提示
     * @param expression 布尔值
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static void isTrue(boolean expression,ResultCode resultCode){
        if (expression) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断字符为空，抛出异常提示
     * @param str        需要判断的字符串
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static void isEmpty(String str,ResultCode resultCode){
        if (Tools.isEmpty(str)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断对象为null，抛出异常提示
     * @param t          需要判断的对象
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isEmpty(T t,ResultCode resultCode){
        if (Tools.isEmpty(t)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断数组为空，抛出异常提示
     * @param t          需要判断的对象
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isEmpty(T[] t,ResultCode resultCode){
        if (Tools.isEmpty(t)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断map为空，抛出异常提示
     * @param map        需要判断的对象
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <K,V> void isEmpty(Map<K,V> map,ResultCode resultCode){
        if (Tools.isEmpty(map)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断对象为空，抛出异常提示
     * @param collection 集合对象
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isEmpty(Collection<T> collection,ResultCode resultCode){
        if (Tools.isEmpty(collection)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断字符不为空，抛出异常提示
     * @param str        需要判断的字符串
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static void isNotEmpty(String str,ResultCode resultCode){
        if (Tools.isNotEmpty(str)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断对象不为null，抛出异常提示
     * @param t          需要判断的对象
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isNotEmpty(T t,ResultCode resultCode){
        if (Tools.isNotEmpty(t)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断数组不为空，抛出异常提示
     * @param t          需要判断的对象
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isNotEmpty(T[] t,ResultCode resultCode){
        if (Tools.isNotEmpty(t)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断map不为空，抛出异常提示
     * @param map        需要判断的对象
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <K,V> void isNotEmpty(Map<K,V> map,ResultCode resultCode){
        if (Tools.isNotEmpty(map)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断对象不为空，抛出异常提示
     * @param collection 集合对象
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T> void isNotEmpty(Collection<T> collection,ResultCode resultCode){
        if (Tools.isNotEmpty(collection)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断对象相等，抛出异常提示
     * @param t          对象1
     * @param e          对象2
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T,E> void equals(T t,E e,ResultCode resultCode){
        if (Tools.equals(t,e)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断对象不相等，抛出异常提示
     * @param t          对象1
     * @param e          对象2
     * @param resultCode 结果枚举
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T,E> void notEquals(T t,E e,ResultCode resultCode){
        if (!Tools.equals(t,e)) {
            throw new BusinessException(resultCode);
        }
    }

    /**
     * 判断对象相等，抛出异常提示
     * @param t       对象1
     * @param e       对象2
     * @param message 提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T,E> void equals(T t,E e,String message){
        if (Tools.equals(t,e)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断对象不相等，抛出异常提示
     * @param t       对象1
     * @param e       对象2
     * @param message 提示信息
     * @author lcy
     * @date 2020/12/7 16:03
     **/
    public static <T,E> void notEquals(T t,E e,String message){
        if (!Tools.equals(t,e)) {
            throw new BusinessException(message);
        }
    }

}
