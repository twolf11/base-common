package com.lcy.base.redis.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lcy.base.common.util.Log;
import com.lcy.base.common.util.Tools;

/**
 * @Description redisson配置类
 * @Author lcy
 * @Date 2021/4/26 12:27
 */
@Configuration
public class RedissonConfig {

    /**
     * redisson配置类
     */
    @Autowired
    private RedissonProperties redissonProperties;

    @Bean
    public RedissonClient redissonClient() throws IOException{

        //默认使用单例模式
        String configPath = "redisson-single.yml";
        //如果是其它类型
        if ("cluster".equals(redissonProperties.getType()) || "sentinel".equals(redissonProperties.getType())) {
            configPath = configPath.replace("single",redissonProperties.getType());
        }
        //这里使用的是yml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource(configPath));
        try {
            if (Tools.isNotEmpty(redissonProperties.getAddress()) && config != null) {
                String[] addressArr = redissonProperties.getAddress().split(",");
                switch (redissonProperties.getType()) {
                    case "cluster":
                        //设置密码
                        config.useClusterServers().setPassword(redissonProperties.getPassword());
                        List<String> nodeAddresses = config.useClusterServers().getNodeAddresses();
                        nodeAddresses.clear();
                        nodeAddresses.addAll(Arrays.asList(addressArr));
                        break;
                    case "sentinel":
                        //设置密码
                        config.useSentinelServers().setPassword(redissonProperties.getPassword());
                        //接触检查哨兵至少需要一个的限制
                        config.useSentinelServers().setCheckSentinelsList(false);
                        config.useSentinelServers().setMasterName(redissonProperties.getMasterName());
                        List<String> sentinelAddresses = config.useSentinelServers().getSentinelAddresses();
                        sentinelAddresses.clear();
                        sentinelAddresses.addAll(Arrays.asList(addressArr));
                        break;
                    default:
                        //设置密码
                        config.useSingleServer().setPassword(redissonProperties.getPassword());
                        config.useSingleServer().setAddress(addressArr[0]);
                        break;
                }
            }
        } catch (Exception e) {
            Log.error("redisson地址配置有误 ",e);
        }
        return Redisson.create(config);
    }
}
