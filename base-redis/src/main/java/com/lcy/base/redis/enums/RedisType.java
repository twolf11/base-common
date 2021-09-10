package com.lcy.base.redis.enums;

/**
 * @Description redis类型
 * @Author lcy
 * @Date 2021/5/11 11:25
 */
public enum RedisType {

    /**
     * 集群
     */
    cluster,
    /**
     * 单机
     */
    single,
    /**
     * 哨兵
     */
    sentinel,
    /**
     * 主从
     */
    masterSlave;

}
