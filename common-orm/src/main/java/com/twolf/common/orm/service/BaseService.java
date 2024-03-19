package com.twolf.common.orm.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feiniaojin.gracefulresponse.data.PageBean;
import com.twolf.common.core.data.Page;

/**
 * 基础service实现
 * @Author lcy
 * @Date 2023/9/15 15:33
 */
public interface BaseService<T> extends IService<T> {

    PageBean<T> page(Page<Object> page);

    PageBean<T> page(Page<Object> page,Wrapper<T> queryWrapper);

}
