package com.lcy.common.orm.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * @Description 数据库基本字段--加了乐观锁
 * @Author lcy
 * @Date 2021/4/13 17:59
 */
public class BaseLockDo<T extends Model<?>> extends BaseDo<T> {

    /**
     * 版本号--乐观锁
     */
    @TableField("version")
    @Version
    protected Long version;
}
