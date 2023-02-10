package com.lcy.common.orm.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcy.common.core.exception.BusinessException;
import com.lcy.common.core.util.AssertUtil;
import com.lcy.common.core.util.BeanUtil;
import com.lcy.common.core.util.Tools;
import com.lcy.common.core.web.entity.dto.PageInfo;
import com.lcy.common.core.web.entity.vo.PageVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 分页排序查询参数
 * @Author lcy
 * @Date 2021/4/13 17:59
 */
@Slf4j
public class PageUtil {

    /**
     * 返回mybatis plus分页的对象
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<V>
     * @author lcy
     * @date 2021/8/20 13:34
     **/
    public static <T,V> Page<V> plusPage(PageInfo<T> pageInfo){
        //排序
        List<String> columns = pageInfo.getColumns();
        List<String> orderTypes = pageInfo.getOrderTypes();
        AssertUtil.isTrue(pageInfo.getColumns().size() != orderTypes.size(),"排序输入条件有误");
        //预先创建对象
        Page<V> objectPage = new Page<>(pageInfo.getPage(),pageInfo.getSize());
        if (Tools.isNotEmpty(columns) && Tools.isNotEmpty(orderTypes)) {
            List<OrderItem> orderItems = new ArrayList<>(orderTypes.size());
            for (int i = 0; i < orderTypes.size(); i++) {
                //判断排序类型
                if ("asc".equals(orderTypes.get(i))) {
                    orderItems.add(OrderItem.asc(columns.get(i)));
                } else if ("desc".equals(orderTypes.get(i))) {
                    orderItems.add(OrderItem.desc(columns.get(i)));
                } else {
                    throw new BusinessException("排序方式有误");
                }
            }
            objectPage.addOrder(orderItems);
        }

        return objectPage;
    }

    /**
     * 获取根据数据库对象获取pageVo对象
     * @param page 分页对象
     * @return com.lcy.base.orm.model.vo.common.PageVo<T>
     * @author lcy
     * @date 2021/8/20 14:21
     **/
    public static <T,V> PageVo<V> getPageVo(Page<T> page,Class<V> vClass){
        //转换成VO对象
        List<V> records = page.getRecords().stream().map(t -> {
            try {
                V v = vClass.getDeclaredConstructor().newInstance();
                BeanUtil.copyBean(t,v);
                return v;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.error("构造对象失败，对象={} ",vClass.getName(),e);
                return null;
            }
        }).collect(Collectors.toList());
        return new PageVo<>(page.getCurrent(),page.getSize(),page.getTotal(),records);
    }

    /**
     * 获取根据数据库对象获取pageVo对象
     * @param page 分页对象
     * @return com.lcy.base.orm.model.vo.common.PageVo<T>
     * @author lcy
     * @date 2021/8/20 14:21
     **/
    public static <T> PageVo<T> getPageVo(Page<T> page){
        return new PageVo<>(page.getCurrent(),page.getSize(),page.getTotal(),page.getRecords());
    }

}
