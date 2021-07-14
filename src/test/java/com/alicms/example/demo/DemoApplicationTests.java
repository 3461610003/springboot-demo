package com.alicms.example.demo;

import com.alicms.example.demo.db.RedisUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * redis分布式锁测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Resource
    private JedisPool jedisPool;

    /**
     * @description 分布式锁测试
     * @date 14:57 2019/9/17
     **/
    @Test
    public void redisTest1() {
        Jedis jedis = jedisPool.getResource();
        String ping = jedis.ping();
        System.out.println("================================jedis连接:" + ping);
        /*System.out.println(lock(jedis, "a", "1", 60L));
        System.out.println(jedis.get("a"));
        System.out.println(unLock(jedis, "a", "2"));*/
        String key = "aaa";
        String value = UUID.randomUUID().toString().replaceAll("-", "");
        // 锁定过期时间 s
        Long lockTime = 60L;
        // 尝试次数
        Integer tryCount = 20;
        // 每次尝试休闲时间 ms
        Long trySleepTime = 1500L;
        // 业务代码执行时间 s
        Integer serSecond = 30;

        Boolean lock = false;
        try {
            lock =  RedisUtils.lockRetry(jedis, key, value, lockTime, tryCount, trySleepTime);
            if (lock) {
                System.out.println("锁定成功-------------------------");
                System.out.println("业务代码执行.......................................");
                for (int i = 0; i < serSecond; i++) {
                    System.out.println(" ... " + (i + 1));
                    Thread.sleep(1000L);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock) {
                Boolean unLock = RedisUtils.unLock(jedis, key, value);
                if (unLock) {
                    System.out.println("解锁成功-------------------------");
                } else {
                    System.out.println("解锁失败-------------------------");
                }
            }
        }
        System.out.println("=================  关闭连接  ==================");
        jedis.close();
    }

    @Test
    public void contextLoads() {
        System.out.println("断言前=======");
        Assert.assertNotEquals(1, 2);
        System.out.println("断言后=======");
    }

}
