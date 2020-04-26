package com.alicms.example.demo.controller;

import com.alicms.example.demo.service.TestService;
import com.alicms.example.demo.utils.AppUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 跨域请求测试
 * @Author zhenghao
 * @Date 2019/12/9 15:15
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


}
