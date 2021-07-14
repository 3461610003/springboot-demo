package com.alicms.example.demo.db;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * <p>
 * redis工具
 * </p>
 *
 * @author zhenghao
 * @date 2021/7/14 14:03
 */
public class RedisUtils {

    /**
     * @description 重试去获取锁(锁定)
     * 参数：jedis,k,v, 过期时间，重试次数
     * @date 14:58 2019/9/17
     **/
    public static Boolean lockRetry(Jedis jedis, String key, String value, Long timeOut, Integer retry, Long sleepTime) {
        Boolean flag = false;
        try {
            for (int i = 0; i < retry; i++) {
                flag = lock(jedis, key, value, timeOut);
                System.out.println("重试" + (i + 1) + "次....... " + key + " -> " + value);
                if (flag) {
                    System.out.println("成功获取锁--------");
                    break;
                }
                Thread.sleep(sleepTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @description 加锁
     * @date 15:05 2019/9/17
     **/
    public static Boolean lock(Jedis jedis, String key, String value, Long timeOut) {
        String result = jedis.set(key, value, "NX", "EX", timeOut);
        if ("OK".equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * @description 解锁
     * @date 15:06 2019/9/17
     **/
    public static Boolean unLock(Jedis jedis, String key, String value) {
        String luaScript = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else  return 0 end";
        Long var2 = (Long) jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value));
        if (1L == var2) {
            return true;
        }
        return false;
    }
}
