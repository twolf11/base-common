package com.twolf.common.orm.data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据库基本字段
 * @Author twolf
 * @Date 2021/4/13 17:59
 */
@Data
public class BaseDo {

    /**
     * 逻辑删除标志,0-未删除，1-已删除
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    protected Integer isDelete;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    protected String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    /**
     * 最后修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    protected String updateBy;

    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    protected LocalDateTime updateTime;

}
