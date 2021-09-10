package com.lcy.base.orm.util;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.lcy.base.orm.model.domain.common.BaseDO;

/**
 * @Description 基础数据库对象工具类
 * @Author lcy
 * @Date 2021/5/6 18:20
 */
public class BaseUtil {

    /**
     * 修改基本数据对象参数--用于新增，默认当前时间
     *
     * @param baseDo baseDo对象
     * @param uid    uid
     * @author lcy
     * @date 2021/4/14 18:51
     **/
    public static <T extends Model<?>> void createBaseParam(BaseDO<T> baseDo,String uid){
        LocalDateTime now = LocalDateTime.now();
        createBaseParam(baseDo,uid,now);
    }

    /**
     * 修改基本数据对象参数--用于新增
     *
     * @param baseDo   baseDo对象
     * @param uid      uid
     * @param dateTime 时间
     * @author lcy
     * @date 2021/4/14 18:51
     **/

    public static <T extends Model<?>> void createBaseParam(BaseDO<T> baseDo,String uid,LocalDateTime dateTime){
        baseDo.setCreateTime(dateTime);
        baseDo.setUpdateTime(dateTime);
        baseDo.setCreateUser(String.valueOf(uid));
        baseDo.setUpdateUser(String.valueOf(uid));
    }

    /**
     * 修改基本数据对象参数--用于修改，默认当前时间
     *
     * @param baseDo baseDo对象
     * @param uid    uid
     * @author lcy
     * @date 2021/4/14 18:51
     **/
    public static <T extends Model<?>> void updateBaseParam(BaseDO<T> baseDo,String uid){
        LocalDateTime now = LocalDateTime.now();
        updateBaseParam(baseDo,uid,now);
    }

    /**
     * 修改基本数据对象参数--用于修改
     *
     * @param baseDo   baseDo对象
     * @param uid      uid
     * @param dateTime 时间
     * @author lcy
     * @date 2021/4/14 18:51
     **/
    public static <T extends Model<?>> void updateBaseParam(BaseDO<T> baseDo,String uid,LocalDateTime dateTime){
        baseDo.setUpdateTime(dateTime);
        baseDo.setUpdateUser(uid);
    }

}
