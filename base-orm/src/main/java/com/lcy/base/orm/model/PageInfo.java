package com.lcy.base.orm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcy.base.common.util.Tools;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 分页字段
 * @Author lcy
 * @Date 2021/4/13 17:59
 */
@ApiModel(value = "分页查询参数")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo<T> implements Serializable {

    @ApiModelProperty(value = "当前页,默认第一页")
    private int page = 1;

    @ApiModelProperty(value = "当前页的数量，默认10条")
    private int size = 10;

    @ApiModelProperty(value = "升序字段")
    private List<String> ascOrder;

    @ApiModelProperty(value = "降序字段")
    private List<String> descOrder;

    @ApiModelProperty(value = "查询参数对象")
    private T params;

    /**
     * 返回mybatis plus分页的对象
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<V>
     * @author lcy
     * @date 2021/8/20 13:34
     **/
    public <V> Page<V> plusPage(){
        //排序
        List<OrderItem> orderItems = new ArrayList<>();
        if (Tools.isNotEmpty(ascOrder)) {
            ascOrder.forEach(asc -> orderItems.add(new OrderItem(asc,true)));
        }
        if (Tools.isNotEmpty(descOrder)) {
            descOrder.forEach(desc -> orderItems.add(new OrderItem(desc,false)));
        }
        Page<V> objectPage = new Page<>(page,size);
        //判断是否排序
        if (Tools.isNotEmpty(orderItems)) {
            objectPage = objectPage.addOrder(orderItems);
        }
        return objectPage;
    }

}
