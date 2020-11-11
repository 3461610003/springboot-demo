package com.alicms.example.demo.service.impl;

import com.alicms.example.demo.dao.HelloMapper;
import com.alicms.example.demo.service.HelloService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description ServiceImpl
 * @Author zhenghao
 * @Date 2019/9/3 16:49
 */
@Service
public class HelloServiceImpl implements HelloService {
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private HelloMapper helloMapper;

    @Override
    public String sayHello() {
//        return "hello:" + helloMapper.getCron() + ":" + df.format(new Date());
        String result = "hello ..." + df.format(new Date());
        System.out.println(result);
        return result;
    }

    @Override
    public String getCron() {
        return helloMapper.getCron();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int updateCron(String cron) {
        return helloMapper.updateCron(cron);
    }

    public static void main(String[] args) {
        System.out.println(new Object() instanceof Class);
        System.out.println(new Object().getClass() instanceof Class);
        System.out.println(Object.class instanceof Object);
        System.out.println((Integer)1 instanceof Object);
    }
}
