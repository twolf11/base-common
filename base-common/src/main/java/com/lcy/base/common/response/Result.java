package com.lcy.base.common.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 响应信息主体，返回的报文信息类
 * @Author lcy
 * @Date 2020/4/10 14:23
 */
@ApiModel(description = "默认返回结果对象")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
@Data
public class Result<T> implements Serializable {

    /**
     * 响应状态
     */
    @ApiModelProperty(value = "响应状态", required = true)
    protected boolean success;

    /**
     * 响应码
     */
    @ApiModelProperty(value = "响应码", required = true)
    protected String code;

    /**
     * 响应消息
     */
    @ApiModelProperty(value = "响应消息", required = true)
    protected String message;

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    protected T data;

    public Result(ResultCode resultCode){
        this.success = resultCode.isSuccess();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode,T data){
        this.success = resultCode.isSuccess();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

}