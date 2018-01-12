package com.insigma.sr.test.redis;

import com.insigma.sr.Application;
import com.insigma.sr.bean.User;
import com.insigma.sr.redis.HashMapping;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestHashMapping {
    @Autowired
    private HashMapping hashMapping;

    @Test
    public void testHashMapping() {
        String key = "userfsa";
        User stu1 = new User("1", 1, "desc");
        hashMapping.writeHash(key, stu1);
        Object result = hashMapping.loadHash(key);
        System.out.println(result);
        Assert.assertEquals(stu1,result);
    }
}
