package com.alicms.example.demo.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * User
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/3 11:26
 */
@Data
@AllArgsConstructor
public class User {
    private String name;
    private String pass;
    private Integer age;
    private Date birthday;
}
