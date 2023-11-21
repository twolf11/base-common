package com.lcy.common.orm.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 数据库基本字段--加了乐观锁
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

    public Long getVersion(){
        return version;
    }

    public BaseLockDo<T> setVersion(Long version){
        this.version = version;
        return this;
    }

    @Override public String toString(){
        return "BaseLockDo{" +
                "version=" + version +
                ", isDelete=" + isDelete +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
