package com.twolf.common.orm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiniaojin.gracefulresponse.data.PageBean;
import com.twolf.common.core.data.Page;
import com.twolf.common.core.util.Tools;
import com.twolf.common.orm.service.BaseService;

/**
 * 基础service实现
 * @Author twolf
 * @Date 2023/9/15 15:34
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> implements BaseService<T> {

    @Override public PageBean<T> page(Page<Object> page){
        return page(page,Wrappers.emptyWrapper());
    }

    @Override public PageBean<T> page(Page<Object> page,Wrapper<T> queryWrapper){
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> tPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(page.getPage(),page.getSize());
        if (Tools.isNotEmpty(page.getOrderInfos())) {
            List<OrderItem> orderItems = page.getOrderInfos().stream().map(orderInfo -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(orderInfo.getColumn());
                orderItem.setAsc(orderInfo.isAsc());
                return orderItem;
            }).collect(Collectors.toList());
            tPage.setOrders(orderItems);
        }
        page(tPage,queryWrapper);
        PageBean<T> pageBean = new PageBean<>();
        pageBean.setPage((int)tPage.getCurrent());
        pageBean.setPageSize((int)tPage.getSize());
        pageBean.setTotal((int)tPage.getTotal());
        pageBean.setList(tPage.getRecords());
        return pageBean;
    }
}
