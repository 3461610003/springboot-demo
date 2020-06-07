package com.alicms.example.demo;

import java.util.Arrays;

/**
 * @author zhenghao
 * @description Jvm相关测试
 * @date 2020/6/2 17:22
 */
public class JvmTest {

    public static void main(String[] args) {
        JvmTest j = new JvmTest();
        j.invoke(null, 1);    // 调用第二个invoke方法
        j.invoke(null, 1, 2); // 调用第二个invoke方法
        j.invoke(null, new Object[]{1, "hello", 'a', true}); // 调用第二个invoke方法
        j.invoke(null, new Object[]{1}); // 只有手动绕开可变长参数的语法糖，才能调用第一个invoke方法
    }


    void invoke(Object obj, Object... args) {
        System.out.println("方法一, obj=" + obj + ",args=" + Arrays.toString(args));
    }

    void invoke(String s, Object obj, Object... args) {
        System.out.println("方法二, s=" + s + "obj=" + obj + ",args=" + Arrays.toString(args));
    }


}
