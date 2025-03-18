package com.twolf.common.core.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 响应信息主体，返回的报文信息类
 * @Author lcy
 * @Date 2020/4/10 14:23
 */
@Schema(description = "默认返回结果对象")
public class Result<T> implements Serializable {

    /**
     * 响应码
     */
    @Schema(description = "响应码")
    protected Integer code;

    /**
     * 响应消息
     */
    @Schema(description = "响应消息")
    protected String msg;

    /**
     * 响应数据
     */
    @Schema(description = "响应数据")
    protected T data;

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, T data) {
        this(resultCode);
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 创建消息对象
     * @param code    code
     * @param message message
     * @return com.lcy.common.web.model.domain.Result<Object>
     * @author lcy
     * @date 2020/12/7 14:55
     **/
    public static <T> Result<T> create(Integer code, String message) {
        return new Result<>(code, message);
    }

    /**
     * 创建消息对象
     * @param resultCode resultCode
     * @return com.lcy.common.web.entity.domain.Result<T>
     * @author lcy
     * @date 2020/12/7 14:55
     **/
    public static <T> Result<T> create(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    /**
     * 返回成功的消息对象
     * @return com.lcy.common.web.model.domain.Result<Object>
     * @author lcy
     * @date 2020/12/7 14:55
     **/
    public static <T> Result<T> success() {
        return new Result<>(CommonCode.SUCCESS);
    }

    /**
     * 返回带数据的成功消息对象
     * @param data data
     * @return com.lcy.project.entity.response.Result<T>
     * @author lcy
     * @date 2020/4/10 16:15
     **/
    public static <T> Result<T> success(T data) {
        return new Result<>(CommonCode.SUCCESS, data);
    }

    /**
     * 返回失败的消息对象
     * @param message message
     * @return com.lcy.common.web.model.domain.Result<Object>
     * @author lcy
     * @date 2020/12/7 14:55
     **/
    public static <T> Result<T> error(String message) {
        return new Result<>(CommonCode.FAIL.getCode(), message);
    }

    /**
     * 返回系统异常的信息
     * @return com.lcy.common.web.model.domain.Result
     * @author lcy
     * @date 2020/4/10 16:16
     **/
    public static <T> Result<T> error() {
        return create(CommonCode.SERVER_EXCEPTION);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return CommonCode.SUCCESS.getCode().equals(this.code);
    }

    public Integer getCode() {
        return code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}