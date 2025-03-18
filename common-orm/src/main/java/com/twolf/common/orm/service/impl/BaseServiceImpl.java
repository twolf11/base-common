package com.twolf.common.orm.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twolf.common.core.model.PageRequest;
import com.twolf.common.core.model.PageResponse;
import com.twolf.common.core.util.Tools;
import com.twolf.common.orm.service.BaseService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础service实现
 * @Author twolf
 * @Date 2023/9/15 15:34
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    public PageResponse<T> page(PageRequest<?> pageRequest) {
        return page(pageRequest, Wrappers.emptyWrapper());
    }

    @Override
    public PageResponse<T> page(PageRequest<?> pageRequest, Wrapper<T> queryWrapper) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> tPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageRequest.getPage(), pageRequest.getSize());
        if (Tools.isNotEmpty(pageRequest.getOrderInfos())) {
            List<OrderItem> orderItems = pageRequest.getOrderInfos().stream().map(orderInfo -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(orderInfo.getColumn());
                orderItem.setAsc(orderInfo.isAsc());
                return orderItem;
            }).collect(Collectors.toList());
            tPage.setOrders(orderItems);
        }
        page(tPage, queryWrapper);
        return new PageResponse<>(tPage.getCurrent(), tPage.getSize(), tPage.getTotal(), tPage.getRecords());
    }
}
