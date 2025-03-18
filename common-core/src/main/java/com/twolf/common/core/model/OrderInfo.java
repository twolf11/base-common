package com.twolf.common.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * 分页字段
 * @Author twolf
 * @Date 2021/4/13 17:59
 */
@Data
@Schema(description = "分页查询参数")
public class OrderInfo implements Serializable {

    /**
     * 预编译SQL过滤正则表达式
     */
    private final static Pattern SQL_PATTERN = Pattern.compile(
            "([()])|'|--|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete"
                    + "|insert|trancate|char|substr|ascii|declare|exec|count|master|into|drop|execute)\\b|([*;+'%]))");

    @Schema(description = "字段")
    protected String column;

    @Schema(description = "排序类型")
    protected String orderType;

    @JsonIgnore
    protected boolean asc;

    public OrderInfo() {
    }

    public OrderInfo(String column) {
        this(column, "asc");
    }

    public OrderInfo(String column, String orderType) {
        this.column = column;
        if (SQL_PATTERN.matcher(column).find()) {
            throw new SecurityException("包含非法字符");
        }
        this.orderType = orderType;
        this.asc = "asc".equals(orderType);
    }

    public OrderInfo setColumn(String column) {
        if (SQL_PATTERN.matcher(column).find()) {
            throw new SecurityException("包含非法字符");
        }
        this.column = column;
        return this;
    }

    public boolean isAsc() {
        return "asc".equals(orderType);
    }
}
