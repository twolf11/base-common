package com.lcy.common.core.web.entity.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Description 分页字段
 * @Author lcy
 * @Date 2021/4/13 17:59
 */
@Schema(description = "分页字段")
public class PageVo<T> implements Serializable {

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

    public PageVo(long page,long size,long total,List<T> records){
        this.totalSize = Double.valueOf(Math.ceil((double)total / size)).intValue();
        this.page = page > totalSize ? totalSize : page;
        this.size = size;
        this.total = total;
        this.records = records;
        this.isFirstPage = page == 1;
        this.isLastPage = page == totalSize;
        this.hasPreviousPage = page < 1;
        this.hasNextPage = page > totalSize;
    }

    public PageVo(){
    }

    public long getPage(){
        return page;
    }

    public void setPage(long page){
        this.page = page;
    }

    public long getTotalSize(){
        return totalSize;
    }

    public void setTotalSize(long totalSize){
        this.totalSize = totalSize;
    }

    public long getSize(){
        return size;
    }

    public void setSize(long size){
        this.size = size;
    }

    public long getTotal(){
        return total;
    }

    public void setTotal(long total){
        this.total = total;
    }

    public List<T> getRecords(){
        return records;
    }

    public void setRecords(List<T> records){
        this.records = records;
    }

    public boolean isFirstPage(){
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage){
        isFirstPage = firstPage;
    }

    public boolean isLastPage(){
        return isLastPage;
    }

    public void setLastPage(boolean lastPage){
        isLastPage = lastPage;
    }

    public boolean isHasPreviousPage(){
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage){
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage(){
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage){
        this.hasNextPage = hasNextPage;
    }

    @Override public String toString(){
        return "PageVo{" +
                "page=" + page +
                ", totalSize=" + totalSize +
                ", size=" + size +
                ", total=" + total +
                ", records=" + records +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                ", hasPreviousPage=" + hasPreviousPage +
                ", hasNextPage=" + hasNextPage +
                '}';
    }
}
