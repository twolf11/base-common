package com.lcy.base.common.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lcy.base.common.exception.ServiceException;
import com.lcy.base.common.response.Result;
import com.lcy.base.common.util.Log;
import com.lcy.base.common.util.ResultUtil;

/**
 * @Description 异常捕获
 * @Author lcy
 * @Date 2020/12/7 14:18
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception异常
     * @param request   请求对象
     * @param exception 异常信息
     * @author lcy
     * @date 2019/10/26 9:44
     **/
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Object> exception(HttpServletRequest request,Exception exception){
        Log.error("system error! requestURI:{} ",request.getRequestURI(),exception);
        return ResultUtil.exception();
    }

    /**
     * 自定义业务异常
     * @param request          请求对象
     * @param serviceException 异常信息
     * @author lcy
     * @date 2019/10/26 9:44
     **/
    @ExceptionHandler({ServiceException.class})
    public Result<Object> serviceException(HttpServletRequest request,ServiceException serviceException){
        Log.error("ServiceException requestURI:{},message:{} ",request.getRequestURI(),serviceException.getMessage());
        return ResultUtil.create(serviceException.getCode(),serviceException.getMessage());
    }

    /**
     * 参数校验异常
     * @param request       请求对象
     * @param bindException 异常信息
     * @author lcy
     * @date 2019/10/26 9:44
     **/
    @ExceptionHandler({BindException.class})
    public Result<Object> bindException(HttpServletRequest request,BindException bindException){
        Log.error("param BindException!requestURI:{} ",request.getRequestURI(),bindException);

        StringBuilder stringBuilder = new StringBuilder();

        //获取所有的错误参数提示
        bindException.getBindingResult().getFieldErrors()
                .forEach(fieldError -> stringBuilder.append(fieldError.getDefaultMessage()).append(","));

        //如果有错误提示，去除最后的逗号。
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        } else {
            stringBuilder.append("传入参数异常！");
        }

        return ResultUtil.failed(stringBuilder.toString());
    }

}
