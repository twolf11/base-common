package com.lcy.base.redis.util;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import com.lcy.base.common.spring.SpringApplicationContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 加锁工具类
 * @Author lcy
 * @Date 2021/8/20 15:07
 */
@Slf4j
public class LockUtil {

    /**
     * @Description 加锁操作类
     * @Author lcy
     * @Date 2021/8/20 15:07
     */
    public interface LockOperation {

        /**
         * 加锁以后的操作
         * @author lcy
         * @date 2021/8/20 15:08
         **/
        void lockOperation();
    }

    /**
     * 通过redis加锁操作
     * @param lockKey       锁的key
     * @param lockOperation 加锁以后的操作
     * @author lcy
     * @date 2021/8/20 15:12
     **/
    public static void lockByRedis(String lockKey,LockOperation lockOperation){
        RedissonClient redissonClient = SpringApplicationContext.getBean(RedissonClient.class);
        //获取锁
        RLock lock = redissonClient.getLock(lockKey);
        try {
            lock.lock();
            //加锁以后的操作
            lockOperation.lockOperation();
        } finally {
            //释放锁
            if (lock.isLocked()) {
                lock.unlock();
                log.info("解锁成功");
            }
        }
    }

    /**
     * 通过redis加锁操作，并且设置超时时间
     * @param lockKey       锁的key
     * @param timeout       超时时间
     * @param timeUnit      超时时间单位
     * @param lockOperation 加锁以后的操作
     * @author lcy
     * @date 2021/8/20 15:12
     **/
    public static void lockByRedis(String lockKey,long timeout,TimeUnit timeUnit,LockOperation lockOperation){
        RedissonClient redissonClient = SpringApplicationContext.getBean(RedissonClient.class);
        //获取锁
        RLock lock = redissonClient.getLock(lockKey);
        try {
            lock.lock(timeout,timeUnit);
            //加锁以后的操作
            lockOperation.lockOperation();
        } finally {
            //释放锁
            if (lock.isLocked()) {
                lock.unlock();
                log.info("解锁成功");
            }
        }
    }

    /**
     * 通过redis加锁操作，并且设置超时时间，单位为秒
     * @param lockKey       锁的key
     * @param timeout       超时时间
     * @param lockOperation 加锁以后的操作
     * @author lcy
     * @date 2021/8/20 15:12
     **/
    public static void lockByRedis(String lockKey,long timeout,LockOperation lockOperation){
        lockByRedis(lockKey,timeout,TimeUnit.SECONDS,lockOperation);
    }
}
