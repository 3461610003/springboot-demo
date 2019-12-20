package com.alicms.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 测试@EqualsAndHashCode类
 * @Author zhenghao
 * @Date 2019/12/20 9:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Hello extends BaseEntity implements Serializable {
    private String name;
    @EqualsAndHashCode.Exclude
    private String sayText;
    private Integer idCard;
}
