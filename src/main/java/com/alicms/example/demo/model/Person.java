package com.alicms.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @description Person实体
 * @author zhenghao
 * @date 2019/9/10 16:08
 */
@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键自增
    private Long id;

    @NotNull @Min(0) @Max(200)
    @Column
    private Integer age;

    @Size(min = 2)
    @Column
    private String name;

    public Person() {
    }

    public Person(@NotNull @Min(0) @Max(200) Integer age, @Size(min = 2) String name) {
        this.age = age;
        this.name = name;
    }

}
