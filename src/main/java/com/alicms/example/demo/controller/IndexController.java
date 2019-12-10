package com.alicms.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "index";
    }

}