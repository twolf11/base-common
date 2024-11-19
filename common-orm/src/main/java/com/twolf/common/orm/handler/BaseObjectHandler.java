package com.twolf.common.orm.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * 公共数据切面，如果需要再手动引入配置
 * @Author twolf
 * @Date 2021/5/14 14:14
 */
public class BaseObjectHandler implements MetaObjectHandler {

    /**
     * 创建人员ID
     */
    private static final String CREATE_USER = "createBy";

    /**
     * 创建时间
     */
    private static final String CREATE_TIME = "createTime";

    /**
     * 更新人ID
     */
    private static final String UPDATE_USER = "updateBy";

    /**
     * 更新时间
     */
    private static final String UPDATE_TIME = "updateTime";

    /**
     * 删除标记
     */
    private static final String IS_DELETE = "isDelete";

    /**
     * 版本号
     */
    private static final String VERSION = "version";

    /**
     * 新增
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //3.3.3以后的版本使用strictInsertFill，之前使用setFieldValByName
        //实际项目通过jwt获取
        this.strictInsertFill(metaObject, CREATE_USER, () -> null, String.class);
        // 创建时间
        this.strictInsertFill(metaObject, CREATE_TIME, LocalDateTime::now, LocalDateTime.class);
        // 删除标记
        this.strictInsertFill(metaObject, IS_DELETE, () -> 0, Integer.class);
        // 版本号
        this.strictInsertFill(metaObject, VERSION, () -> 0L, Long.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //实际项目通过jwt获取
        this.setFieldValByName(UPDATE_USER, null, metaObject);
        // 创建时间
        this.setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
    }
}
