package com.alicms.example.demo;

import com.alicms.example.demo.model.Hello;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author zhenghao
 * @Date 2019/12/20 9:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EqualsTest {

    @Test
    public void contextLoads() {
        Hello h1 = new Hello();
        h1.setId(1L);
        h1.setName("hello");
        h1.setSayText("123456");
        h1.setIdCard(21);
        Hello h2 = new Hello();
        h2.setId(2L);
        h2.setName("hello");
        h2.setSayText("654321");
        h2.setIdCard(21);

        System.out.println(h1.equals(h2));
    }

}
