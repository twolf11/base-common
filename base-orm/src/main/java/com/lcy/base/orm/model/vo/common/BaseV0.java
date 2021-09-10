package com.lcy.base.orm.model.vo.common;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 数据库基本字段
 * @Author lcy
 * @Date 2021/4/13 17:59
 */
@ApiModel(value = "数据库基本字段")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class BaseV0 implements Serializable {

    @ApiModelProperty(value = "是否删除标志")
    private boolean delFlag;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后修改人")
    private String updateUser;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "版本号")
    private Long version;
}
