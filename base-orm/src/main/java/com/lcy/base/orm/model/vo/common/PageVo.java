package com.lcy.base.orm.model.vo.common;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcy.base.common.util.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 分页字段
 * @Author lcy
 * @Date 2021/4/13 17:59
 */
@ApiModel(value = "分页字段")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PageVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页")
    private long page;

    @ApiModelProperty(value = "总页数")
    private long totalSize;

    @ApiModelProperty(value = "当前页的数量")
    private long size;

    @ApiModelProperty(value = "总记录数")
    private long total;

    @ApiModelProperty(value = "数据结果集")
    private List<T> records;

    @ApiModelProperty(value = "是否为第一页")
    private boolean isFirstPage = false;

    @ApiModelProperty(value = "是否为最后一页")
    private boolean isLastPage = false;

    @ApiModelProperty(value = "是否有前一页")
    private boolean hasPreviousPage = false;

    @ApiModelProperty(value = "是否有下一页")
    private boolean hasNextPage = false;

    public PageVo(long page,long size,long total,List<T> records){
        this.page = page;
        this.totalSize = total / size;
        this.size = size;
        this.total = total;
        this.records = records;
        this.isFirstPage = page == 1;
        this.isLastPage = page == totalSize;
        this.hasPreviousPage = page < 1;
        this.hasNextPage = page > totalSize;
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
