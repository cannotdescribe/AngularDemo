package com.insigma.sr.test.redis;

import com.insigma.sr.Application;
import com.insigma.sr.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class SerializedRedisTemplateTest {
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, User> redisTemplate;

    /**
     * Description: 展示序列化Value值功能及手动置顶序列化方法<br>
     * Create By:  <br>
     * Create Date: 2017年3月20日 上午9:05:02
     */
    @Test
    public void testValueOperations() {
        String key = "user";
        ValueOperations<String, User> valueOp = redisTemplate.opsForValue();
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        User stu = new User("王辉", 18, "神秘人");
        valueOp.set(key, stu);
        System.out.println(valueOp.get(key));
        Assert.assertEquals(stu, valueOp.get(key));
        redisTemplate.delete(key);
        Assert.assertNull(valueOp.get(key));

    }

    /**
     * Description: 展示序列化HashValue值功能<br>
     * Create By:  <br>
     * Create Date: 2017年3月20日 上午9:05:48
     */
    @Test
    public void testHashOperations() {
        String key = "SerializedRedisTemplateTest:TEST:Hash:1";
        HashOperations<String, String, User> op = redisTemplate.opsForHash();
        User stu1 = new User("王辉1", 18, "神秘人1");
        op.put(key, "1", stu1);

        Map<String, User> map = new HashMap<String, User>();
        User stu2 = new User("王辉2", 18, "神秘人2");
        map.put("2", stu2);
        User stu3 = new User("王辉3", 18, "神秘人3");
        map.put("3", stu3);
        User stu4 = new User("王辉4", 18, "神秘人4");
        map.put("4", stu4);
        User stu5 = new User("王辉5", 18, "神秘人5");
        map.put("5", stu5);

        op.putAll(key, map);

        Assert.assertEquals(stu4, op.get(key, stu4.getName()));

        Assert.assertEquals(5, op.entries(key).size());

        op.delete(key, "4", "5");
        Assert.assertEquals(3, op.entries(key).size());

        redisTemplate.delete(key);
        Assert.assertEquals(0, op.entries(key).size());

    }

}
