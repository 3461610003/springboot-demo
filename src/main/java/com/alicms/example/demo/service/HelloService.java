package com.alicms.example.demo.service;

/**
 * @Description HelloService
 * @Author zhenghao
 * @Date 2019/9/3 16:48
 */
public interface HelloService {

    /**
     * @description: sayHello
     * @author: zhenghao
     * @date: 2019/12/5 9:07
     */
    String sayHello();

    /**
     * 获取数据库cron表达式
     * @return cron表达式
     */
    String getCron();

    /**
     * 修改cron表达式
     * @param cron 要修改的cron表达式
     * @return
     */
    int updateCron(String cron);
}
