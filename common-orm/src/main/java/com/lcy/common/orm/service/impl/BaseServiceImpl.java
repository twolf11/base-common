package com.lcy.common.orm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcy.common.core.common.page.Page;
import com.lcy.common.core.common.page.PageInfo;
import com.lcy.common.core.util.Tools;
import com.lcy.common.orm.service.BaseService;

/**
 * 基础service实现
 * @Author lcy
 * @Date 2023/9/15 15:34
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> implements BaseService<T> {

    @Override public PageInfo<T> page(Page page){

        return page(page,Wrappers.emptyWrapper());
    }

    @Override public PageInfo<T> page(Page page,Wrapper<T> queryWrapper){
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
        return new PageInfo<>(tPage.getCurrent(),tPage.getSize(),tPage.getTotal(),tPage.getRecords());
    }

}
