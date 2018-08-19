package com.mcwl.authentication.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author Jerry
 * @date 2018/7/25
 * @description 对redis里面token的操作
 */
@Component
public class TokenDao {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * @Author: Jerry
     * @Date: 2018/8/13
     * @Description: 写入缓存
     * */
    public boolean set(final long key, Object value, int expireTime){
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        redisTemplate.expire(key,expireTime,TimeUnit.HOURS);
        return true;
    }

    /**
     * @Author: Jerry
     * @Date: 2018/8/13
     * 删除对应的value
     * @param key
     */
    public void remove(final long key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * @Author: Jerry
     * @Date: 2018/8/13
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final long key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * @Author: Jerry
     * @Date: 2018/8/13
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final long key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

}
