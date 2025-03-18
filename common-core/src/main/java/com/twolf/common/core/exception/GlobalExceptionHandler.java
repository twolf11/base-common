package com.twolf.common.core.exception;

import com.twolf.common.core.data.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常捕获
 * @Author lcy
 * @Date 2020/12/7 14:18
 */
@Order(300)
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Exception异常
     * @param request   请求对象
     * @param exception 异常信息
     * @author lcy
     * @date 2019/10/26 9:44
     **/
    @ExceptionHandler({Exception.class})
    public Result<Object> exception(HttpServletRequest request, Exception exception) {
        log.error("system error! requestURI:{} ", request.getRequestURI(), exception);
        return Result.error();
    }

    /**
     * 自定义服务异常
     * @param request          请求对象
     * @param serviceException 异常信息
     * @author lcy
     * @date 2019/10/26 9:44
     **/
    @ExceptionHandler({ServiceException.class})
    @ResponseStatus()
    public String serviceException(HttpServletRequest request, ServiceException serviceException) {
        log.error("ServiceException requestURI:{},message:{} ", request.getRequestURI(), serviceException.getMessage());
        return serviceException.getMessage();
    }

    /**
     * 参数校验异常
     * @param request       请求对象
     * @param bindException 异常信息
     * @author lcy
     * @date 2019/10/26 9:44
     **/
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public Result<Object> bindException(HttpServletRequest request, BindException bindException) {
        log.error("param BindException!requestURI:{} ", request.getRequestURI(), bindException);

        StringBuilder stringBuilder = new StringBuilder();

        //获取所有的错误参数提示
        bindException.getBindingResult().getFieldErrors()
                .forEach(fieldError -> stringBuilder.append(fieldError.getDefaultMessage()).append(","));

        //如果有错误提示，去除最后的逗号。
        if (!stringBuilder.isEmpty()) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        } else {
            stringBuilder.append("传入参数异常！");
        }

        return Result.error(stringBuilder.toString());
    }

}
