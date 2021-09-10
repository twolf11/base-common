package com.lcy.base.common.entity;

/**
 * @Description 线程上下文对象
 * @Author lcy
 * @Date 2021/5/17 14:39
 */
public class Context {

    /**
     * 线程私有变量
     */
    private static final ThreadLocal<UserToken> TOKEN_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取线程变量的对象
     * @return com.turing.common.entity.Context.TokenContext
     * @author lcy
     * @date 2021/5/17 15:20
     **/
    public static UserToken getTokenContext(){
        //对空值处理
        if(TOKEN_CONTEXT_THREAD_LOCAL.get() == null){
            setTokenContext(UserToken.builder().build());
        }
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

