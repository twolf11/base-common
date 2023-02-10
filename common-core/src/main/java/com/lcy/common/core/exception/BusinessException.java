package com.lcy.common.core.exception;

import com.lcy.common.core.web.entity.domain.ResultCode;

/**
 * @Description 自定义业务异常
 * @Author lcy
 * @Date 2020/12/7 14:20
 */
public class BusinessException extends RuntimeException {

    /**
     * 响应码
     */
    private Integer code;

    public BusinessException(String message){
        super(message);
        this.code = 500;
    }

    public BusinessException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public BusinessException(ResultCode resultCode){
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public Integer getCode(){
        return code;
    }

    public BusinessException setCode(Integer code){
        this.code = code;
        return this;
    }
}
