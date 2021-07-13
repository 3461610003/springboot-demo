package com.alicms.example.demo.controller;

import com.alicms.example.demo.model.User;
import com.alicms.example.demo.model.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * com.alicms.example.demo.controller.EventListenerController
 * </p>
 *
 * @author zhenghao
 * @date 2021/7/13 17:43
 */
@RestController
@RequestMapping("/event")
public class EventListenerController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping(value = "/send")
    public String sayHello(String msg) {
        User user = new User();
        user.setId(1111L);
        user.setTrueName("name-1111");
        user.setNickname(msg);
        // 发生事件（发邮件、发短信、、、）
        applicationContext.publishEvent(new UserEvent(user));

        user.setId(2222L);
        user.setTrueName("name-2222");
        // 两种发生方式一致
        applicationEventPublisher.publishEvent(new UserEvent(user));
        return "success, msg=" + msg;
    }
}
