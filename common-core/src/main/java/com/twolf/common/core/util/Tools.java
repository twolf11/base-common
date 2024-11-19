package com.twolf.common.core.util;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 工具类
 * @Author twolf
 * @Date 2021/1/12 15:29
 */
public class Tools {

    /**
     * 字符串是否为空白，空白的定义如下：
     * null
     * 空字符串：""
     * 空格、全角空格、制表符、换行符，等不可见字符
     * @param cs 字符串
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static boolean isBlank(CharSequence cs) {
        final int length;
        if ((cs == null) || ((length = cs.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (!isBlankChar(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符是否为空白
     * @param index 下标
     * @author twolf
     * @date 2024/11/13 09:41
     **/
    public static boolean isBlankChar(int index) {
        return Character.isWhitespace(index)
                || Character.isSpaceChar(index)
                || index == '\ufeff'
                || index == '\u202a'
                || index == '\u0000'
                || index == '\u3164'
                || index == '\u2800'
                || index == '\u180e';
    }

    /**
     * 判断字符串是否有空白
     * @param charSequences 字符串数组
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static boolean hasBlank(CharSequence... charSequences) {
        if (isEmpty(charSequences)) {
            return true;
        }

        for (CharSequence str : charSequences) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否为空
     * @param cs 字符串
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static boolean isEmpty(CharSequence cs) {
        return cs != null && cs.isEmpty();
    }

    /**
     * 判断字符串是否为空
     * @param charSequences 字符串数组
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static boolean hasEmpty(CharSequence... charSequences) {
        if (isEmpty(charSequences)) {
            return true;
        }

        for (CharSequence str : charSequences) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为空
     * @param object 对象
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isEmpty(T object) {
        return Objects.isNull(object);
    }

    /**
     * 判断数组是否为空
     * @param array 数组
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断集合是否为空
     * @param collection 集合
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断map是否为空
     * @param map map
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断字符串是否不为空白
     * @param cs 字符串
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 判断字符串是否不为空
     * @param cs 字符串
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }


    /**
     * 判断对象是否不为空
     * @param object 对象
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isNotEmpty(T object) {
        return !isEmpty(object);
    }

    /**
     * 判断数组是否不为空
     * @param array 数组
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断集合是否不为空
     * @param collection 集合
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断map是否不为空
     * @param map map
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:03
     **/
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

    /**
     * 判断两个对象是否相等
     * @param first  对象1
     * @param second 对象2
     * @return boolean
     * @author twolf
     * @date 2021/1/12 16:19
     **/
    public static <F, S> boolean equals(F first, S second) {
        return Objects.equals(first, second);
    }

}
