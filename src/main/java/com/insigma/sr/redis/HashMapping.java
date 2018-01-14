package com.insigma.sr.redis;

import com.insigma.sr.bean.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("hashMapping")
public class HashMapping {

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOperations;

    private BeanUtilsHashMapper<User> mapper = new BeanUtilsHashMapper<User>(User.class);

    public void writeHash(String key, User obj) {

        Map<String, String> mappedHash = mapper.toHash(obj);
        hashOperations.putAll(key, mappedHash);
    }

    @SuppressWarnings("unchecked")
    public User loadHash(String key) {
        Map<String, String> loadedHash = hashOperations.entries(key);
        return mapper.fromHash(loadedHash);
    }

}