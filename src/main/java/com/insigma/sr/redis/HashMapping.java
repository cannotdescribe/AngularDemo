package com.insigma.sr.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("hashMapping")
public class HashMapping {

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOperations;

    private Jackson2HashMapper mapper = new Jackson2HashMapper(true);

    public <T> void writeHash(String key, T obj) {

        Map<String, Object> mappedHash = mapper.toHash(obj);
        hashOperations.putAll(key, mappedHash);
    }

    @SuppressWarnings("unchecked")
    public <T> T loadHash(String key) {

        Map<String, Object> loadedHash = hashOperations.entries(key);
        return (T) mapper.fromHash(loadedHash);
    }
}