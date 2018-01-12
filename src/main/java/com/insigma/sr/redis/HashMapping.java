package com.insigma.sr.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

@Component("hashMapping")
public class HashMapping {

    //HashOperations<String, byte[], byte[]> hashOperations;
    @Resource(name = "redisTemplate")
    RedisTemplate<String, Object> redisTemplate;

    ObjectHashMapper mapper = new ObjectHashMapper();

    public <T> void writeHash(String key, T obj) {
        Map<byte[], byte[]>  mappedHash = mapper.toHash(obj);
        HashOperations<String, byte[], byte[]> ho = redisTemplate.opsForHash();
        ho.putAll(key, mappedHash);
    }

    @SuppressWarnings("unchecked")
    public Object loadHash(String key) {
//        return hashOperations.
        HashOperations<String, byte[], byte[]> ho = redisTemplate.opsForHash();
        Map<byte[], byte[]> map = ho.entries(key);
        return mapper.fromHash(map);
//        return
    }
}