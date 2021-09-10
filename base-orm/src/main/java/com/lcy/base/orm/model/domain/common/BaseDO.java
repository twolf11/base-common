package com.lcy.base.orm.model.domain.common;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description 数据库基本字段
 * @Author lcy
 * @Date 2021/4/13 17:59
 */
@ApiModel(value = "数据库基本字段")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class BaseDO<T extends Model<?>> extends Model<T> {

    @ApiModelProperty(value = "逻辑删除标志,0-未删除，1-已删除")
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后修改人")
    @TableField(value = "updated_by", fill = FieldFill.UPDATE)
    private String updateUser;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "版本号")
    @TableField("version")
    @Version
    private Long version;
}
