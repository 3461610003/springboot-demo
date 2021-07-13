package com.alicms.example.demo.model;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author zhenghao
 * @date 2021/7/13 17:40
 */
public class UserEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserEvent(Object source) {
        super(source);
    }
}
