package com.alicms.example.demo.model;

import javax.validation.constraints.*;

/**
 * @Description TODO
 * @Author zhenghao
 * @Date 2019/9/10 16:08
 */
public class Person {

    @Null
    private Long id;
    @NotNull @Min(0) @Max(200)
    private Integer age;
    @Size(min = 2)
    private String name;

    public Person() {
    }

    public Person(@Null Long id, @NotNull @Min(0) @Max(200) Integer age, @Size(min = 2) String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
