package com.twolf.common.orm.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.twolf.common.core.model.PageRequest;
import com.twolf.common.core.model.PageResponse;

/**
 * 基础service实现
 * @Author twolf
 * @Date 2023/9/15 15:33
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 分页查询-无参数
     * @param pageRequest 分页参数
     * @author twolf
     * @date 2025/3/17 15:53
     **/
    PageResponse<T> page(PageRequest<?> pageRequest);

    /**
     * 分页查询-有参数
     * @param pageRequest  分页参数
     * @param queryWrapper 查询参数
     * @author twolf
     * @date 2025/3/17 15:53
     **/
    PageResponse<T> page(PageRequest<?> pageRequest, Wrapper<T> queryWrapper);

}
