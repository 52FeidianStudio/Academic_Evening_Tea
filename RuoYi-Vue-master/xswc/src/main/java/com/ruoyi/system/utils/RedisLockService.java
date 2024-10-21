package com.ruoyi.system.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLockService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 锁的默认超时时间，防止死锁
    private static final long LOCK_TIMEOUT = 10L;

    // 获取锁的方法
    public boolean tryLock(String key, String value, long timeout) {
        // 使用 SETNX 尝试获取锁
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
        return success != null && success;
    }

    // 释放锁的方法
    public boolean releaseLock(String key, String value) {
        // 使用 Lua 脚本保证原子性释放锁
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                "return redis.call('del', KEYS[1]) else return 0 end";
        Long result = redisTemplate.execute((RedisScript<Long>) RedisScript.of(luaScript, Long.class), Collections.singletonList(key), value);
        return result != null && result > 0;
    }
}
