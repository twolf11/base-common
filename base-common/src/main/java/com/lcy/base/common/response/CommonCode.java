package com.lcy.base.common.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @Description 公共错误返回值
 * @Author lcy
 * @Date 2019/12/26 20:16
 */
@ToString
public enum CommonCode implements ResultCode {
    /**
     * 操作成功
     */
    SUCCESS(true,"200","操作成功！"),
    /**
     * 操作失败
     */
    FAIL(false,"999","操作失败！"),
    /**
     * 非法参数
     */
    INVALID_PARAM(false,"602","非法参数！"),
    /**
     * 必要参数为空
     */
    PARAMETER_NULL(false,"603","必要参数不能为空"),
    /**
     * 没有登录
     */
    UN_LOGIN(false,"604","此操作需要登陆系统！"),
    /**
     * 没有权限
     */
    UN_AUTHORISE(false,"605","权限不足，无权操作！"),
    /**
     * 增加失败
     */
    ADD_FAIL(false,"606","新增失败"),
    /**
     * 删除失败
     */
    DELETE_FAIL(false,"607","删除失败"),
    /**
     * 修改失败
     */
    UPDATE_FAIL(false,"608","修改失败"),
    /**
     * 该记录已存在
     */
    RECORD_EXIT(false,"609","该记录关键字段已经存在"),
    /**
     * 无查询结果
     */
    NO_QUERY_RESULT(false,"611","无查询结果!"),
    /**
     * 找不到路径
     */
    PATH_NOT_FOUND(false,"612","未找到路径"),
    /**
     * 系统异常
     */
    SERVER_EXCEPTION(false,"601","抱歉，系统出现异常，请联系系统管理员！"),
    /**
     * 系统错误
     */
    SERVER_ERROR(false,"602","抱歉，系统繁忙，请联系系统管理员！");

    /**
     * 操作是否成功
     */
    @Getter
    private final boolean success;

    /**
     * 操作代码
     */
    @Getter
    private final String code;

    /**
     * 提示信息
     */
    @Getter
    private final String message;

    CommonCode(boolean success,String code,String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

}