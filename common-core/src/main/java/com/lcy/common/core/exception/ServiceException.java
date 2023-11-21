package com.lcy.common.core.exception;

import com.lcy.common.core.common.response.ResultCode;

/**
 * 自定义异常-http状态500
 * @Author lcy
 * @Date 2020/12/7 14:20
 */
public class ServiceException extends RuntimeException {

    /**
     * 响应码
     */
    private Integer code;

    public ServiceException(String message){
        super(message);
        this.code = 500;
    }

    public ServiceException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public ServiceException(ResultCode resultCode){
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public Integer getCode(){
        return code;
    }

    public ServiceException setCode(Integer code){
        this.code = code;
        return this;
    }
}
