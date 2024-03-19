package com.twolf.common.core.data;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页字段
 * @Author lcy
 * @Date 2021/4/13 17:59
 */
@Data
@NoArgsConstructor
@Schema(description = "分页查询参数")
public class Page<T> implements Serializable {

    @Schema(description = "当前页,默认第一页", example = "1")
    protected int page = 1;

    @Schema(description = "当前页的数量，默认10条", example = "10")
    protected int size = 10;

    @Schema(description = "查询参数")
    protected T param;

    @Schema(description = "排序字段")
    protected List<OrderInfo> orderInfos;

}
