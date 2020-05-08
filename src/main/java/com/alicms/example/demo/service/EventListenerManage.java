package com.alicms.example.demo.service;

import com.alicms.example.demo.model.BuilderTest;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author zhenghao
 * @description EventListener测试
 * @date 2020/4/29 13:31
 */
@Component
public class EventListenerManage {

    @EventListener
    public void handleEvent(BuilderTest builderTest) {
        System.out.println("EventListener 监听收到信息：" + builderTest);
    }

}
