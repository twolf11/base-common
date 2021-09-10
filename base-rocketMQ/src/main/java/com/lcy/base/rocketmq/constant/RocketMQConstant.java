package com.lcy.base.rocketmq.constant;

/**
 * @Description rocketMq常量信息，
 * @Author lcy
 * @Date 2021/4/22 15:54
 */
public class RocketMQConstant {

    //指定nameServer的地址，如果是多个地址（集群）用;进行隔开
    //如单机为192.168.56.101:9876，集群为"192.168.56.101:9876;192.168.56.102:9876"
    public final static String ADDR_KEY = "rocketmq.nameServerAddr";

}
