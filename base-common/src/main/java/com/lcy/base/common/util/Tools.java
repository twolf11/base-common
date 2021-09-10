package com.lcy.base.common.util;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @Description 工具类
 * @Author lcy
 * @Date 2021/1/12 15:29
 */
public class Tools {

    /**
     * 判断字符串是否为空
     *
     * @param cs 字符串
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static boolean isEmpty(CharSequence cs){
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为空
     *
     * @param charSequences 字符串数组
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static boolean isEmpty(CharSequence... charSequences){
        for (CharSequence cs : charSequences) {
            if (isNotEmpty(cs)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断对象是否为空
     *
     * @param object 对象
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isEmpty(T object){
        return object == null;
    }

    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isEmpty(T[] array){
        return array == null || array.length == 0;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isEmpty(Collection<T> collection){
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断map是否为空
     *
     * @param map map
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static <K,V> boolean isEmpty(Map<K,V> map){
        return map == null || map.isEmpty();
    }

    /**
     * 判断字符串是否不为空
     *
     * @param cs 字符串
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static boolean isNotEmpty(CharSequence cs){
        return !isEmpty(cs);
    }

    /**
     * 判断多个字符串是否不为空
     *
     * @param charSequences 字符串数组
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static boolean isNotEmpty(CharSequence... charSequences){
        for (CharSequence cs : charSequences) {
            if (isEmpty(cs)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断对象是否不为空
     *
     * @param object 对象
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isNotEmpty(T object){
        return object != null;
    }

    /**
     * 判断数组是否不为空
     *
     * @param array 数组
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isNotEmpty(T[] array){
        return !isEmpty(array);
    }

    /**
     * 判断集合是否不为空
     *
     * @param collection 集合
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static <T> boolean isNotEmpty(Collection<T> collection){
        return !isEmpty(collection);
    }

    /**
     * 判断map是否不为空
     *
     * @param map map
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:03
     **/
    public static <K,V> boolean isNotEmpty(Map<K,V> map){
        return !isEmpty(map);
    }

    /**
     * 判断两个对象是否相等
     *
     * @param first  对象1
     * @param second 对象2
     * @return boolean
     * @author lcy
     * @date 2021/1/12 16:19
     **/
    public static <F,S> boolean equals(F first,S second){
        return Objects.equals(first,second);
    }

}
