package com.lcy.base.common.util;

import com.lcy.base.common.response.CommonCode;
import com.lcy.base.common.response.Result;
import com.lcy.base.common.response.ResultCode;

/**
 * @Description 响应结果工具类
 * @Author lcy
 * @Date 2020/12/7 11:17
 */
public class ResultUtil {

    /**
     * 创建返回消息对象
     *
     * @param code    响应码
     * @param message 返回提示
     * @return com.lcy.base.common.response.Result<Object>
     * @author lcy
     * @date 2020/12/7 14:32
     **/
    public static <T> Result<T> create(String code,String message){
        return Result.<T>builder().success("200".equals(code)).code(code).message(message).build();
    }

    /**
     * 创建返回消息对象
     *
     * @param code    响应码
     * @param message 返回提示
     * @param data    响应数据
     * @return com.lcy.base.common.response.Result<T>
     * @author lcy
     * @date 2020/12/7 14:38
     **/
    public static <T> Result<T> create(String code,String message,T data){
        return Result.<T>builder().success("200".equals(code)).code(code).message(message).data(data).build();
    }

    /**
     * 创建返回消息对象
     *
     * @param resultCode 枚举对象
     * @return com.lcy.base.common.response.Result<T>
     * @author lcy
     * @date 2020/12/7 14:38
     **/
    public static <T> Result<T> create(ResultCode resultCode){
        return Result.<T>builder().success(resultCode.isSuccess()).code(resultCode.getCode()).message(resultCode.getMessage()).build();
    }

    /**
     * 创建返回消息对象
     *
     * @param resultCode 枚举对象
     * @param data       数据集合
     * @return com.lcy.base.common.response.Result<T>
     * @author lcy
     * @date 2020/12/7 14:38
     **/
    public static <T> Result<T> create(ResultCode resultCode,T data){
        return Result.<T>builder().success(resultCode.isSuccess()).code(resultCode.getCode())
                .message(resultCode.getMessage()).data(data).build();
    }

    /**
     * 创建返回消息对象
     *
     * @param data 返回数据
     * @return com.lcy.base.common.response.Result<T>
     * @author lcy
     * @date 2020/12/7 14:38
     **/
    public static <T> Result<T> create(T data){
        return Result.<T>builder().success(CommonCode.SUCCESS.isSuccess()).code(CommonCode.SUCCESS.getCode())
                .message(CommonCode.SUCCESS.getMessage()).data(data).build();
    }

    /**
     * 返回成功的消息对象
     *
     * @return com.lcy.base.common.response.Result<Object>
     * @author lcy
     * @date 2020/12/7 14:55
     **/
    public static <T> Result<T> success(){
        return create(CommonCode.SUCCESS);
    }

    /**
     * 返回带数据的成功消息对象
     *
     * @return com.lcy.project.entity.response.Result<T>
     * @author lcy
     * @date 2020/4/10 16:15
     **/
    public static <T> Result<T> success(T data){
        return create(data);
    }

    /**
     * 返回成功的消息对象
     *
     * @return com.lcy.base.common.response.Result<Object>
     * @author lcy
     * @date 2020/12/7 14:55
     **/
    public static <T> Result<T> failed(String message){
        return Result.<T>builder().success(false).code("601").message(message).build();
    }

    /**
     * 返回系统异常的信息
     *
     * @return com.lcy.project.entity.com.response.Result
     * @author lcy
     * @date 2020/4/10 16:16
     **/
    public static <T> Result<T> exception(){
        return create(CommonCode.SERVER_EXCEPTION);
    }

    /**
     * 返回系统错误的信息
     *
     * @return com.lcy.project.entity.com.response.Result
     * @author lcy
     * @date 2020/4/10 16:16
     **/
    public static <T> Result<T> error(){
        return create(CommonCode.SERVER_EXCEPTION);
    }

}
