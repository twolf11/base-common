package com.lcy.base.redis.constant;

/**
 * @Description lua脚本常量
 * @Author lcy
 * @Date 2021/4/20 16:37
 */
public class LuaConstant {

    /**
     * 加锁的脚本路径
     */
    public final static String LOCK_PATH = "lua/lock.lua";

    /**
     * 解锁的脚本路径
     */
    public final static String UNLOCK_PATH = "lua/unlock.lua";

    /**
     * 加锁的脚本名称
     */
    public final static String LOCK_NAME = "lock.lua";

    /**
     * 解锁的脚本名称
     */
    public final static String UNLOCK_NAME = "unlock.lua";
}
