package com.alicms.example.demo.controller;

import com.alicms.example.demo.model.Person;
import com.alicms.example.demo.model.User;
import com.alicms.example.demo.service.HelloService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Description hello
 * @Author zhenghao
 * @Date 2019/8/29 14:01
 */
@RestController
@RequestMapping("hello")
public class HelloController {
// 测试提交。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
    @Resource
    private HelloService helloService;

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public String sayHello() {
        String hello = "hello,";// helloService.sayHello();
        System.out.println(hello);
        User user = new User();
        String phone = user.getPhone();
        user.setPhone("4465465");
        phone += "||||" + user.getPhone();
        return hello + phone;
    }

    @GetMapping
    public Person getPerson() {
        Person person = new Person(1L, 20, "张三");
        return person;
    }

    @PostMapping
    public Person postPerson(@RequestBody @Valid Person person) {
        System.out.println(person);
        return person;
    }

    @DeleteMapping("{id}")
    public void delPerson(@PathVariable("id") Long id) {
        System.out.println("=====================删除的id=" + id);
    }

    @PutMapping("{id}")
    public Person putPerson(@RequestBody @Valid Person person, @PathVariable("id") Long id) {
        person.setId(id);
        System.out.println(person);
        return person;
    }


}
