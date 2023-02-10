package com.lcy.common.core.web.entity.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Description 分页字段
 * @Author lcy
 * @Date 2021/4/13 17:59
 */
@Schema(description = "分页查询参数")
public class PageInfo<T> implements Serializable {

    @Schema(description = "当前页,默认第一页", example = "1")
    protected int page = 1;

    @Schema(description = "当前页的数量，默认10条", example = "10")
    protected int size = 10;

    @Schema(description = "查询参数对象")
    protected T params;

    @Schema(description = "字段")
    protected List<String> columns;

    @Schema(description = "排序类型")
    protected List<String> orderTypes;

    public PageInfo(){
    }

    public PageInfo(int page,int size,T params){
        this.page = page;
        this.size = size;
        this.params = params;
    }

    public PageInfo(int page,int size,T params,List<String> columns,List<String> orderTypes){
        this.page = page;
        this.size = size;
        this.params = params;
        this.columns = columns;
        this.orderTypes = orderTypes;
    }

    public int getPage(){
        return page;
    }

    public PageInfo<T> setPage(int page){
        this.page = page;
        return this;
    }

    public int getSize(){
        return size;
    }

    public PageInfo<T> setSize(int size){
        this.size = size;
        return this;
    }

    public T getParams(){
        return params;
    }

    public PageInfo<T> setParams(T params){
        this.params = params;
        return this;
    }

    public List<String> getColumns(){
        return columns;
    }

    public PageInfo<T> setColumns(List<String> columns){
        this.columns = columns;
        return this;
    }

    public List<String> getOrderTypes(){
        return orderTypes;
    }

    public PageInfo<T> setOrderTypes(List<String> orderTypes){
        this.orderTypes = orderTypes;
        return this;
    }

    @Override public String toString(){
        return "PageInfo{" +
                "page=" + page +
                ", size=" + size +
                ", params=" + params +
                ", columns=" + columns +
                ", orderTypes=" + orderTypes +
                '}';
    }
}
