package com.alicms.example.demo.service;

import com.alicms.example.demo.model.UserEvent;
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
    public void handleEvent(UserEvent userEvent) {
        System.out.println("EventListener 监听收到信息：" + userEvent);
    }
    @EventListener
    public void handleEvent2(UserEvent userEvent) {
        System.out.println("EventListener2 监听收到信息：" + userEvent);
    }
}
