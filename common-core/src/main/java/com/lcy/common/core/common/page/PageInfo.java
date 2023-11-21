package com.lcy.common.core.common.page;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static java.util.stream.Collectors.toList;

/**
 * 分页字段
 * @Author lcy
 * @Date 2021/4/13 17:59
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Schema(description = "分页字段")
@SuppressWarnings(value = {"unchecked"})
public class PageInfo<T> implements Serializable {

    @Schema(description = "当前页")
    private long page;

    @Schema(description = "总页数")
    private long totalSize;

    @Schema(description = "当前页的数量")
    private long size;

    @Schema(description = "总记录数")
    private long total;

    @Schema(description = "数据结果集")
    private List<T> records;

    @Schema(description = "是否为第一页")
    private boolean isFirstPage = false;

    @Schema(description = "是否为最后一页")
    private boolean isLastPage = false;

    @Schema(description = "是否有前一页")
    private boolean hasPreviousPage = false;

    @Schema(description = "是否有下一页")
    private boolean hasNextPage = false;

    public PageInfo(long page,long size,long total,List<T> records){
        this.size = size;
        this.total = total;
        this.records = records;
        this.totalSize = Double.valueOf(Math.ceil((double)total / size)).intValue();
        this.page = page > totalSize ? totalSize : page;
        this.isFirstPage = page == 1;
        this.isLastPage = page == totalSize;
        this.hasPreviousPage = page < 1;
        this.hasNextPage = page > totalSize;
    }

    public <R> PageInfo<R> convert(Function<? super T,? extends R> mapper){
        List<R> collect = this.getRecords().stream().map(mapper).collect(toList());
        return ((PageInfo<R>)this).setRecords(collect);
    }

    public boolean isFirstPage(){
        return page == 1;
    }

    public boolean isLastPage(){
        return page == totalSize;
    }

    public boolean isHasPreviousPage(){
        return page < 1;
    }

    public boolean isHasNextPage(){
        return page > totalSize;
    }
}
