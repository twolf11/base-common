package com.twolf.common.core.exception;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * 自定义异常-http状态500
 * @Author lcy
 * @Date 2020/12/7 14:20
 */
@ExceptionMapper(code = "500", msg = "系统异常", msgReplaceable = true)
public class ServiceException extends RuntimeException {

    public ServiceException(){
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message,Throwable cause){
        super(message,cause);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }

    public ServiceException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace){
        super(message,cause,enableSuppression,writableStackTrace);
    }
}
