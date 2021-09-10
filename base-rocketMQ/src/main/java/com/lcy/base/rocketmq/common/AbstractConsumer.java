package com.lcy.base.rocketmq.common;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import com.lcy.base.rocketmq.constant.RocketMQConstant;
import com.lcy.base.common.util.Log;
import lombok.Getter;

/**
 * @Description 消息消费者抽象类
 * @Author lcy
 * @Date 2021/4/22 15:52
 */
public abstract class AbstractConsumer implements InitializingBean {

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
    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();

    /**
     * 消费无序消息的方法，默认消费模式。实现该方法则不需要实现有序的方法
     *
     * @param messageExtList 消息集合
     * @param context        消息体
     * @return org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus
     * @author lcy
     * @date 2021/4/22 17:11
     **/
    public abstract ConsumeConcurrentlyStatus consumerMessage(List<MessageExt> messageExtList,ConsumeConcurrentlyContext context);

    /**
     * 消费有序消息的方法，实现该方法的同时要重写isOrder()方法，实现该方法则不需要实现无序的方法
     *
     * @param messageExtList 消息集合
     * @param context        消息体
     * @return org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus
     * @author lcy
     * @date 2021/4/22 17:11
     **/
    public abstract ConsumeOrderlyStatus consumerOrderMessage(List<MessageExt> messageExtList,ConsumeOrderlyContext context);

    /**
     * 初始化消费者对象消息，如设置group、topic、addrServer等等，如果
     *
     * @param consumer 消费者消息
     * @author lcy
     * @date 2021/4/22 16:43
     **/
    public abstract void initProducer(DefaultMQPushConsumer consumer);

    /**
     * 是否保证消费的顺序，默认是无序的
     *
     * @return boolean
     * @author lcy
     * @date 2021/4/22 17:02
     **/
    public boolean isOrder(){
        return false;
    }

    @Override public void afterPropertiesSet(){

        consumer.setNamesrvAddr(addr);
        //初始化消费者信息
        //消费模式--默认消费最后一个。CONSUME_FROM_LAST_OFFSET是消费第一个
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //集群模式,MessageModel.BROADCASTING为广播模式
        consumer.setMessageModel(MessageModel.CLUSTERING);
        //监听主题的内容，*代表监听所有
        //consumer.subscribe(RocketMQTopic.PAY_TOPIC,"*");
        initProducer(consumer);

        //注册监听器--多线程的消费注册MessageListenerConcurrently，返回对象为ConsumeConcurrentlyStatus
        // 如果是单线程的消费（保证消费有序）使用MessageListenerOrderly，返回对象为ConsumeOrderlyStatus。
        // 这个对象会锁住当前queue保证有序的进行消费
        if (isOrder()) {
            consumer.registerMessageListener((MessageListenerOrderly)(messages,context) -> {
                Log.info("{} consumer receive message ready to consume. messages:{} ",Thread.currentThread().getName(),messages);
                return consumerOrderMessage(messages,context);
            });
        } else {
            consumer.registerMessageListener((MessageListenerConcurrently)(messages,context) -> {
                Log.info("{} consumer receive message ready to consume. messages:{} ",Thread.currentThread().getName(),messages);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
        }

        start();
    }

    /**
     * 初始化MQ，对象在使用消费者之前，只能初始化一次
     *
     * @author lcy
     * @date 2020/12/7 10:44
     **/
    public void start(){
        try {
            this.consumer.start();
        } catch (MQClientException e) {
            Log.error("pay_consumer init failed！producerGroup:{},nameServerAddr:{} ",consumer.getConsumerGroup(),addr,e);
        }
    }

    /**
     * 一般应用在上下文，使用上下文监听器，进行关闭
     *
     * @author lcy
     * @date 2020/12/7 10:49
     **/
    public void shutdown(){
        this.consumer.shutdown();
    }

}
