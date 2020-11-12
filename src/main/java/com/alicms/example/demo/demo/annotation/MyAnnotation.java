package com.alicms.example.demo.demo.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 自定义注解中定义成员变量的规则：
 *  1.不带参数，比如name()，website()；
 *  2.返回值类型：基本类型、String、Enums、Annotation 以及前面这些类型的数组类型
 *  3.可有默认值，比如default "hello",如果没有默认值，需要在使用注解时，指定成员变量的值
 *
 *
 *
 *
 */
// 生成文档
@Documented
// 用在方法上
@Target({ElementType.METHOD})
// 可继承
@Inherited
// 作用到vm中
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String name();
    String[] value() default {"hello", "world"};
    int revision() default 1;
}
