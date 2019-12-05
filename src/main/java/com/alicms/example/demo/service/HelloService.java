package com.alicms.example.demo.service;

import org.apache.ibatis.annotations.Update;

/**
 * @Description TODO
 * @Author zhenghao
 * @Date 2019/9/3 16:48
 */
public interface HelloService {

    String sayHello();
    String getCron();
    int updateCron(String cron);
}
