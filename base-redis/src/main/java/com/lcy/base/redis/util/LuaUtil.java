package com.lcy.base.redis.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import com.lcy.base.common.spring.SpringApplicationContext;
import com.lcy.base.common.util.Log;
import com.lcy.base.common.util.Tools;
import com.lcy.base.redis.constant.LuaConstant;

/**
 * @Description lua脚本工具类
 * @Author lcy
 * @Date 2021/3/15 11:01
 */
@SuppressWarnings("unchecked")
public class LuaUtil {

    /**
     * redis
     */
    private static final RedisTemplate REDIS_TEMPLATE = SpringApplicationContext.getBean("redisTemplate");

    /**
     * redisson
     */
    private static final RedissonClient REDISSON_CLIENT = SpringApplicationContext.getBean(RedissonClient.class);

    /**
     * 存储脚本信息，脚本执行一次则缓存到本地里
     */
    private static final Map<String,String> SHA_MAP = new HashMap<>();

    /**
     * redis脚本加锁操作，setnx和setex连用
     *
     * @param key     key
     * @param value   value
     * @param express 过期时间
     * @return boolean
     * @author lcy
     * @date 2021/3/15 11:05
     **/
    public static boolean lock(Object key,String value,long express){
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>();
        //设置脚本路径
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource(LuaConstant.LOCK_PATH)));
        script.setResultType(Boolean.class);
        return (boolean)REDIS_TEMPLATE.execute(script,Collections.singletonList(key),value,express);
    }

    /**
     * lua脚本解锁操作，get和delete连用
     *
     * @param key   key
     * @param value value
     * @return boolean
     * @author lcy
     * @date 2021/3/15 11:05
     **/
    public static boolean unlock(Object key,String value){
        return executeLua(LuaConstant.UNLOCK_PATH,key,value);
    }

    /**
     * lua脚本操作
     *
     * @param luaPath 脚本路径
     * @param keyList key集合
     * @param objects 参数
     * @return boolean
     * @author lcy
     * @date 2021/3/15 11:05
     **/
    public static boolean executeLua(String luaPath,List<Object> keyList,Object... objects){
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>();
        //设置脚本路径
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource(luaPath)));
        script.setResultType(Boolean.class);
        return (boolean)REDIS_TEMPLATE.execute(script,keyList,objects);
    }

    /**
     * lua脚本操作
     *
     * @param luaPath 脚本路径
     * @param objects 参数
     * @param key     单个key
     * @return boolean
     * @author lcy
     * @date 2021/4/20 15:33
     **/
    public static boolean executeLua(String luaPath,Object key,Object... objects){
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>();
        //设置脚本路径
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource(luaPath)));
        script.setResultType(Boolean.class);
        return (boolean)REDIS_TEMPLATE.execute(script,Collections.singletonList(key),objects);
    }

    /**
     * lua脚本操作
     *
     * @param luaPath    脚本路径
     * @param resultType 返回值class
     * @param objects    参数
     * @param key        单个key
     * @return boolean
     * @author lcy
     * @date 2021/4/20 15:33
     **/
    public static <T> T executeLua(String luaPath,Class<T> resultType,Object key,Object... objects){
        DefaultRedisScript<T> script = new DefaultRedisScript<>();
        //设置脚本路径
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource(luaPath)));
        script.setResultType(resultType);
        return (T)REDIS_TEMPLATE.execute(script,Collections.singletonList(key),objects);
    }

    /**
     * 通过redisson执行lua脚本操作
     *
     * @param luaPath 脚本路径
     * @param keyList key集合
     * @param objects 参数
     * @return boolean
     * @author lcy
     * @date 2021/3/15 11:05
     **/
    public static <R> R executeByRedisson(RScript.Mode mode,String luaPath,RScript.ReturnType returnType,List<Object> keyList,Object... objects){

        RScript script = REDISSON_CLIENT.getScript();
        ResourceScriptSource resourceScriptSource = new ResourceScriptSource(new ClassPathResource(luaPath));
        try {
            return script.eval(mode,resourceScriptSource.getScriptAsString(),returnType,keyList,objects);
        } catch (IOException e) {
            Log.error("获取脚本路径异常！luaPath:{} ",luaPath,e);
            return null;
        }
    }

    /**
     * 通过redisson执行只读操作的lua脚本操作
     *
     * @param luaPath 脚本路径
     * @param keyList key集合
     * @param objects 参数
     * @return boolean
     * @author lcy
     * @date 2021/3/15 11:05
     **/
    public static <R> R readByRedisson(String luaPath,RScript.ReturnType returnType,List<Object> keyList,Object... objects){
        return executeByRedisson(RScript.Mode.READ_ONLY,luaPath,returnType,keyList,objects);
    }

    /**
     * 通过redisson执行读写操作的lua脚本操作
     *
     * @param luaPath 脚本路径
     * @param keyList key集合
     * @param objects 参数
     * @return boolean
     * @author lcy
     * @date 2021/3/15 11:05
     **/
    public static <R> R writeByRedisson(String luaPath,RScript.ReturnType returnType,List<Object> keyList,Object... objects){
        return executeByRedisson(RScript.Mode.READ_WRITE,luaPath,returnType,keyList,objects);
    }

    /**
     * 通过redisson执行lua脚本操作
     *
     * @param luaPath 脚本路径
     * @param key     key
     * @param objects 参数
     * @return boolean
     * @author lcy
     * @date 2021/3/15 11:05
     **/
    public static <R> R executeByRedisson(RScript.Mode mode,String luaPath,RScript.ReturnType returnType,Object key,Object... objects){
        RScript script = REDISSON_CLIENT.getScript();
        String luaSha = SHA_MAP.get(luaPath);
        if (Tools.isEmpty(luaSha)) {
            ResourceScriptSource resourceScriptSource = new ResourceScriptSource(new ClassPathResource(luaPath));
            try {
                luaSha = script.scriptLoad(resourceScriptSource.getScriptAsString());
                SHA_MAP.put(luaPath,luaSha);

            } catch (IOException e) {
                Log.error("获取脚本路径异常！luaPath:{} ",luaPath,e);
                return null;
            }
        }
        return script.evalSha(mode,SHA_MAP.get(luaPath),returnType,Collections.singletonList(key),objects);
    }

    /**
     * 通过redisson执行只读操作的lua脚本操作
     *
     * @param luaPath 脚本路径
     * @param key     key
     * @param objects 参数
     * @return boolean
     * @author lcy
     * @date 2021/3/15 11:05
     **/
    public static <R> R readByRedisson(String luaPath,RScript.ReturnType returnType,Object key,Object... objects){
        return executeByRedisson(RScript.Mode.READ_WRITE,luaPath,returnType,key,objects);
    }

    /**
     * 通过redisson执行读写操作的lua脚本操作
     *
     * @param luaPath 脚本路径
     * @param key     key
     * @param objects 参数
     * @return boolean
     * @author lcy
     * @date 2021/3/15 11:05
     **/
    public static <R> R writeByRedisson(String luaPath,RScript.ReturnType returnType,Object key,Object... objects){
        return executeByRedisson(RScript.Mode.READ_WRITE,luaPath,returnType,key,objects);
    }

}
