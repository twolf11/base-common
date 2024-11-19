package com.twolf.common.orm.data;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * 数据库基本字段--加了乐观锁
 * @Author twolf
 * @Date 2021/4/13 17:59
 */
@Data
public class BaseLockDo extends BaseDo {

    /**
     * 版本号--乐观锁
     */
    @TableField("version")
    @Version
    protected Long version;

}
