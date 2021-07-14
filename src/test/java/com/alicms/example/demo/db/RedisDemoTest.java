package com.alicms.example.demo.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description Redis api测试
 * @Author zhenghao
 * @Date 2020/1/18 13:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemoTest {

    @Resource
    private JedisPool jedisPool;

    @Test
    public void contextLoads() {
        Jedis jedis = jedisPool.getResource();
        // str
        String res = jedis.set("key1", "value111");
        System.out.println(res);


    }


}
