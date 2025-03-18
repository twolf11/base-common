package com.twolf.common.core.exception;

import com.twolf.common.core.data.ResultCode;
import lombok.Getter;

/**
 * 自定义服务异常
 * @Author lcy
 * @Date 2020/12/7 14:20
 */
@Getter
public class ServiceException extends RuntimeException {

    /**
     * 响应码
     */
    private Integer code;

    public ServiceException(String message) {
        super(message);
        this.code = 500;
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

}
