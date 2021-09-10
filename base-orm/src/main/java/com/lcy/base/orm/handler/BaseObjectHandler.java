package com.lcy.base.orm.handler;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lcy.base.common.entity.Context;
import com.lcy.base.common.util.Log;
import com.lcy.base.common.util.Tools;

/**
 * @Description 公共数据切面
 * @Author lcy
 * @Date 2021/5/14 14:14
 */
@Component
public class BaseObjectHandler implements MetaObjectHandler {

    /**
     * 创建人员ID
     */
    private static final String CREATE_USER = "createdBy";

    /**
     * 创建时间
     */
    private static final String CREATE_TIME = "createdTime";

    /**
     * 更新人ID
     */
    private static final String UPDATE_USER = "updatedBy";

    /**
     * 更新时间
     */
    private static final String UPDATE_TIME = "updatedTime";

    /**
     * 删除标记
     */
    private static final String DEL_FLAG = "delFlag";

    /**
     * 版本号
     */
    private static final String VERSION = "version";

    /**
     * 新增
     */
    @Override
    public void insertFill(MetaObject metaObject){
        Log.info("start insert fill ....");
        //实际项目通过jwt获取
        setFieldValByName(CREATE_USER,Tools.isEmpty(Context.getTokenContext().getUserCode()) ? "sysAdmin" : Context.getTokenContext().getUserCode(),metaObject);
        // 创建时间
        setFieldValByName(CREATE_TIME,LocalDateTime.now(),metaObject);
        // 删除标记
        setFieldValByName(DEL_FLAG,0,metaObject);
        // 版本号
        setFieldValByName(VERSION,0L,metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject){
        Log.info("start update fill ....");
        //实际项目通过jwt获取
        setFieldValByName(UPDATE_USER,Tools.isEmpty(Context.getTokenContext().getUserCode()) ? "sysAdmin" : Context.getTokenContext().getUserCode(),metaObject);
        // 创建时间
        setFieldValByName(UPDATE_TIME,LocalDateTime.now(),metaObject);
    }
}
