package com.qks.SpringEvent;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-27 09:13
 */
public class EventDemo extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    private String msg;

    public EventDemo(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
