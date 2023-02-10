package com.lcy.common.core.web.controller;

import com.lcy.common.core.web.entity.domain.CommonCode;
import com.lcy.common.core.web.entity.domain.Result;

/**
 * @Description 基础controller
 * @Author lcy
 * @Date 2022/3/1 14:28
 */
public abstract class BaseController {

    /**
     * 返回成功
     */
    public <T> Result<T> success(){
        return Result.success();
    }

    /**
     * 返回成功
     */
    public <T> Result<T> success(T data){
        return Result.success(data);
    }

    /**
     * 返回成功消息
     */
    public <T> Result<T> successMessage(String message){
        return Result.create(CommonCode.SUCCESS.getCode(),message);
    }

    /**
     * 返回失败消息
     */
    public <T> Result<T> error(){
        return Result.error();
    }

    /**
     * 返回失败消息
     */
    public <T> Result<T> error(String message){
        return Result.error(message);
    }

}
