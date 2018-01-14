package com.insigma.sr.test.redis;

import com.insigma.sr.Application;
import com.insigma.sr.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class RedisOverStackTest {
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOperations;

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    Jackson2HashMapper jhm = new Jackson2HashMapper(true);

    public List<String> getKeys(int num){
        String key = "st";
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList<String>();
        long begin = (long) 1e16;
        System.out.println("begin: "+ begin);
        for(int i=0; i<num; i++){
            sb.delete(0, sb.length());
            sb.append(key);
            sb.append(":");
            sb.append(begin++);
            keys.add(sb.toString());
        }
        return keys;
    }

    @Test
    public void stringBuilderTest(){
        String key = "st";
        int num = 1000000;
        StringBuilder sb = new StringBuilder();
        long begin = (long) 1e16;
        System.out.println("--------------begin-------------------");
        long beginTime = System.currentTimeMillis();
        for(int i=0; i<num; i++){
            sb.setLength(0);
            sb.append(key);
            sb.append(":");
            sb.append(begin++);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
    }

    @Test
    public void stringTest(){
        String key = "st:";
        int num = 1000000;
        long begin = (long) 1e16;
        System.out.println("--------------begin-------------------");
        long beginTime = System.currentTimeMillis();
        for(int i=0; i<num; i++){
            long b = begin++;
            String sb = key+b;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
    }

    @Test
    public void hashCreate(){
        User u = new User("wanghui", 12, "deade");
        List<String> keys = getKeys(100000);
        System.out.println("------------begin-----------");
        long beginTime = System.currentTimeMillis();
        for(String k : keys){
            hashOperations.putAll(k, jhm.toHash(u));
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-beginTime);
    }

    @Test
    public void read(){
        List<String> keys = getKeys(100000);
        System.out.println("------------begin-----------");
        long beginTime = System.currentTimeMillis();
        for(String k : keys){
            hashOperations.entries(k);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-beginTime);
    }

    @Test
    public void likeRead(){
        System.out.println("------------begin-----------");
        long beginTime = System.currentTimeMillis();
        Set<String> keys = redisTemplate.keys("st:*");
        long endTime = System.currentTimeMillis();
        System.out.println("keys: "+keys.size());
        System.out.println(endTime-beginTime);
    }
}
