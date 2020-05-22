package com.alicms.example.demo.controller;

import com.alicms.example.demo.service.TestService;
import com.alicms.example.demo.utils.AppUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 跨域请求测试
 * @author zhenghao
 * @date 2019/12/9 15:15
 */
@Controller
public class IndexController {

//    @CrossOrigin(origins = "http://localhost:8080", maxAge = 3600) // 第四种，注解
    @RequestMapping("/idx")
    public String idx(HttpServletResponse response) {
          // 第三种解决跨域, 设置相应头
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080"); // * 代表所有
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
//        response.setHeader("Access-Control-Max-Age", "3600");
        JedisPool jedisPool = AppUtils.getBean(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String res = jedis.set("key1", "hello --- world");
        System.out.println(res);
        System.out.println(jedis.get("key1"));
//        TestService testService = AppUtils.getBean(TestServiceImpl.class);
        TestService testService = (TestService) AppUtils.getObject("testServiceImpl");
        testService.test();
        return "index";
    }


    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/t2")
    public String test2() {
        return "test2";
    }
    @RequestMapping("/jsencrypt")
    public String js() {
        return "jsencrypt.js";
    }



    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

    @GetMapping("wrong")
    public Map wrong(@RequestParam("userId") Integer userId) {
        //设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before  = Thread.currentThread().getName() + ":" + currentUser.get();
        //设置用户信息到ThreadLocal
        currentUser.set(userId);
        //设置用户信息之后再查询一次ThreadLocal中的用户信息
        String after  = Thread.currentThread().getName() + ":" + currentUser.get();
        //汇总输出两次查询结果
        Map result = new HashMap();
        result.put("before", before);
        result.put("after", after);
        return result;
    }


}
