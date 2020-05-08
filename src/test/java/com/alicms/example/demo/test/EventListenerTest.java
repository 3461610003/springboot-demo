package com.alicms.example.demo.test;

import com.alicms.example.demo.model.BuilderTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhenghao
 * @description EventListener测试
 * @date 2020/4/29 13:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventListenerTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void send1() {
        context.publishEvent(
                BuilderTest.builder()
                        .code(200)
                        .message("操作成功")
                .build());
    }

}
