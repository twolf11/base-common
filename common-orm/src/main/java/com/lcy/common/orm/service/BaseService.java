package com.lcy.common.orm.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcy.common.core.common.page.Page;
import com.lcy.common.core.common.page.PageInfo;

/**
 * 基础service实现
 * @Author lcy
 * @Date 2023/9/15 15:33
 */
public interface BaseService<T> extends IService<T> {

    PageInfo<T> page(Page page);

    PageInfo<T> page(Page page,Wrapper<T> queryWrapper);

}
