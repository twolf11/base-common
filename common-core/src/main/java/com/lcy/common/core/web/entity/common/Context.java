package com.lcy.common.core.web.entity.common;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @Description 线程上下文对象
 * @Author lcy
 * @Date 2021/5/17 14:39
 */
public class Context {

    /**
     * 线程私有变量
     */
    private static final TransmittableThreadLocal<UserToken> TOKEN_CONTEXT_THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 获取线程变量的对象
     * @return com.turing.common.entity.Context.TokenContext
     * @author lcy
     * @date 2021/5/17 15:20
     **/
    public static UserToken getTokenContext(){
        return TOKEN_CONTEXT_THREAD_LOCAL.get();
    }

    /**
     * 设置线程变量的对象
     * @param userToken token变量
     * @author lcy
     * @date 2021/5/17 15:28
     **/
    public static void setTokenContext(UserToken userToken){
        TOKEN_CONTEXT_THREAD_LOCAL.set(userToken);
    }

    /**
     * 删除当前线程变量
     * @author lcy
     * @date 2021/5/17 15:28
     **/
    public static void removeTokenContext(){
        TOKEN_CONTEXT_THREAD_LOCAL.remove();
    }

}

