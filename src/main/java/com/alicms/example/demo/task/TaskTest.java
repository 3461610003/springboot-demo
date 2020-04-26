package com.alicms.example.demo.task;

import com.alicms.example.demo.utils.AppUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @Description 执行静态定时任务
 * @Author zhenghao
 * @Date 2019/8/29 14:06
 */
//@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class TaskTest {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        System.out.println("执行静态定时任务时间: " + df.format(Calendar.getInstance().getTime()));
        JedisPool jedisPool = AppUtils.getBean(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String val = jedis.get("key1");
        System.out.println(val);
    }
}