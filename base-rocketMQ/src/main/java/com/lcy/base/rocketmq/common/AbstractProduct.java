package com.lcy.base.rocketmq.common;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import com.lcy.base.rocketmq.constant.RocketMQConstant;
import lombok.Getter;

/**
 * @Description 消息生产者抽象类
 * @Author lcy
 * @Date 2021/4/22 15:52
 */
public abstract class AbstractProduct implements InitializingBean {

    /**
     * 获取配置文件配置的rocketMQ地址
     */
    @Value("${" + RocketMQConstant.ADDR_KEY + "}")
    @Getter
    private String addr;

    /**
     * 默认mq生产者
     */
    @Getter
    private final DefaultMQProducer producer = new DefaultMQProducer();

    /**
     * 初始化生产者对象消息，如设置group、addrServer等等，如果
     *
     * @param producer 生产者消息
     * @author lcy
     * @date 2021/4/22 16:43
     **/
    public abstract void initProducer(DefaultMQProducer producer);

    @Override public void afterPropertiesSet(){
        //默认先设置配置文件
        if (com.lcy.base.common.util.Tools.isNotEmpty(addr)) {
            producer.setNamesrvAddr(addr);
        }
        initProducer(producer);
        start();
    }

    /**
     * 初始化MQ，对象在使用生产者之前，只能初始化一次
     *
     * @author lcy
     * @date 2020/12/7 10:44
     **/
    public void start(){
        try {
            this.producer.start();
        } catch (MQClientException e) {
            com.lcy.base.common.util.Log.error("producer init failed！producerGroup:{},nameServerAddr:{} ",producer.getProducerGroup(),addr,e);
        }
    }

    /**
     * 一般应用在上下文，使用上下文监听器，进行关闭
     *
     * @author lcy
     * @date 2020/12/7 10:49
     **/
    public void shutdown(){
        this.producer.shutdown();
    }

}
