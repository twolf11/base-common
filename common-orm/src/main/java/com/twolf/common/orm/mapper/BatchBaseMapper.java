package com.twolf.common.orm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 批量操作mapper--注意jdbc需要增加连接串：rewriteBatchedStatements=true
 * @Author twolf
 * @Date 2022/5/23 18:11
 */
public interface BatchBaseMapper<T> extends BaseMapper<T> {

    /**
     * 自定义批量插入 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     * @param list list集合
     * @return int
     * @author twolf
     * @date 2022/5/23 18:13
     **/
    int insertBatch(@Param("list") List<T> list);

    /**
     * 自定义批量更新，条件为主键 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     * @param list list list集合
     * @return int
     * @author twolf
     * @date 2022/5/23 18:13
     **/
    int updateBatch(@Param("list") List<T> list);

}
