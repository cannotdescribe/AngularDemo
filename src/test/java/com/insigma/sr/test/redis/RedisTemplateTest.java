package com.insigma.sr.test.redis;

import com.insigma.sr.Application;
import com.insigma.sr.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class RedisTemplateTest {

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    /**
     *
     * Description: 展示String数据结构操作<br>
     * Create By: <br>
     * Create Date: 2017年3月20日 上午9:06:12
     *
     */
    @Test
    public void testValueOperations() {
//        String key = "RedisTemplateTest:TEST:String:1";
        String key = "demo01";
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        valueOp.set(key, "12");
//        Assert.assertEquals("1", valueOp.get(key));
//        redisTemplate.delete(key);
//        Assert.assertNull(valueOp.get(key));

    }

    /**
     *
     * Description: 展示Set数据结构操作<br>
     * Create By: <br>
     * Create Date: 2017年3月20日 上午9:06:12
     *
     */
    @Test
    public void testSetOperations() {
        String key = "RedisTemplateTest:TEST:Set:1";
        SetOperations<String, String> op = redisTemplate.opsForSet();
        op.add(key, "1", "2");
        op.add(key, "2", "3", "4");
        op.remove(key, "4");
        System.out.println(op.members(key));
        Assert.assertEquals(Long.valueOf(3), op.size(key));
        redisTemplate.delete(key);
        Assert.assertEquals(Long.valueOf(0), op.size(key));
        System.out.println(op.members(key));
    }

    /**
     *
     * Description: 展示SortedSet数据结构操作<br>
     * Create By: <br>
     * Create Date: 2017年3月20日 上午9:06:12
     *
     */
    @Test
    public void testZSetOperations() {
        String key = "RedisTemplateTest:TEST:ZSet:1";
        ZSetOperations<String, String> op = redisTemplate.opsForZSet();
        // 添加Key及用于排序的得分
        op.add(key, "1", 1);
        op.add(key, "2", 2);
        op.add(key, "3", 3);
        op.add(key, "4", 2);
        // 更新
        op.add(key, "4", 4);

        Assert.assertEquals(Long.valueOf(2), op.count(key, 3, 4));
        System.out.println(op.range(key, 0,10));
        op.remove(key, "3");
        System.out.println(op.range(key, 0,10));
        Assert.assertEquals(Long.valueOf(3), op.size(key));

        redisTemplate.delete(key);
        Assert.assertEquals(Long.valueOf(0), op.size(key));
        System.out.println(op.range(key, 0,10));
    }
}
