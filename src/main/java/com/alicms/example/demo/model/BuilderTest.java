package com.alicms.example.demo.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhenghao
 * @description lombok 之 @Builder注解确认
 * @date 2020/4/29 13:13
 */
@Data
@Builder
public class BuilderTest {
    private Integer code;
    private String error;
    private String message;
}
