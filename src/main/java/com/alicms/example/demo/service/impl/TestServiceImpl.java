package com.alicms.example.demo.service.impl;

import com.alicms.example.demo.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @Description: 测试ServiceImpl
 * @Author zhenghao
 * @Date 2019/12/5 9:13
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public void test() {
        System.out.println("test ................................");
    }
}
