package com.lcy.base.common.exception;

import com.lcy.base.common.response.ResultCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 自定义ServiceException异常
 * @Author lcy
 * @Date 2020/12/7 14:20
 */
public class ServiceException extends RuntimeException {

    /**
     * 响应码
     */
    @Getter
    @Setter
    private String code;

    public ServiceException(String message){
        super(message);
        this.code = "601";
    }

    public ServiceException(String code,String message){
        super(message);
        this.code = code;
    }

    public ServiceException(ResultCode resultCode){
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

}
