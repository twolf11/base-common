package com.twolf.common.core.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页字段
 * @Author lcy
 * @Date 2021/4/13 17:59
 */
@Schema(description = "分页字段")
public class PageResponse<T> {

    @Schema(description = "当前页")
    private long page;

    @Schema(description = "当前页的数量")
    private long size;

    @Schema(description = "总记录数")
    private long total;

    @Schema(description = "数据结果集")
    private List<T> records;

    /**
     * 数据转换
     * @param converter convert方法
     * @author twolf
     * @date 2025/3/17 15:23
     **/
    public <R> PageResponse<R> convert(Function<T, R> converter) {
        // 使用提供的转换函数对 records 中的每个元素进行转换
        List<R> convertedRecords = this.records.stream()
                .map(converter)
                .collect(Collectors.toList());

        // 创建一个新的 PageResponse 对象，并设置相同的分页信息，记录类型转换后的数据
        return new PageResponse<>(page, size, total, convertedRecords);
    }

    public PageResponse() {
    }

    public PageResponse(long page, long size, long total, List<T> records) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.records = records;
    }

    public long getPage() {
        return page;
    }

    public PageResponse<T> setPage(long page) {
        this.page = page;
        return this;
    }

    public long getSize() {
        return size;
    }

    public PageResponse<T> setSize(long size) {
        this.size = size;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public PageResponse<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public List<T> getRecords() {
        return records;
    }

    public PageResponse<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }
}
