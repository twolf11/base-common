package com.lcy.common.orm.entity;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.lcy.common.core.web.entity.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Description 数据库分页排序对象
 * @Author lcy
 * @Date 2021/4/18 10:55
 */
public class PageOrderInfo<T> extends PageInfo<T> {

    @Schema(description = "分页对象")
    private List<OrderItem> order;

    public PageOrderInfo(List<OrderItem> order){
        this.order = order;
    }

    public PageOrderInfo(int page,int size,T params,List<OrderItem> order){
        super(page,size,params);
        this.order = order;
    }

    public PageOrderInfo(int page,int size,T params,List<String> columns,List<String> orderTypes,List<OrderItem> order){
        super(page,size,params,columns,orderTypes);
        this.order = order;
    }

    public List<OrderItem> getOrder(){
        return order;
    }

    public PageOrderInfo<T> setOrder(List<OrderItem> order){
        this.order = order;
        return this;
    }

    @Override public String toString(){
        return "PageOrderInfo{" +
                "page=" + page +
                ", size=" + size +
                ", params=" + params +
                ", order=" + order +
                '}';
    }
}
