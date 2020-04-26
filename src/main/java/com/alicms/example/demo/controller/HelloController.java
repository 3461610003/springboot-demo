package com.alicms.example.demo.controller;

import com.alicms.example.demo.model.Person;
import com.alicms.example.demo.model.User;
import com.alicms.example.demo.service.HelloService;
import com.alicms.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @description hello
 * @author zhenghao
 * @date 2019/8/29 14:01
 */
//@RestController
//@RequestMapping("hello")
public class HelloController {

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


    @Resource
    private PersonService personService;

    // 查询
    @GetMapping(path = "{id}")
    public Person getPerson(@PathVariable("id") Long id) {
        final Person person = personService.find(id);
        return person;
    }

    @GetMapping
    public List<Person> findAll() {
        final List<Person> list = personService.findAll();
        return list;
    }

    // 添加
    @PostMapping
    public Person postPerson(@RequestBody @Valid Person person) {
        person.setId(null);
        System.out.println("要添加的：" + person);
        personService.add(person);
        return person;
    }
    @PostMapping("/batch-add")
    public List<Person> batchAdd(@RequestBody @Valid List<Person> list) {
        personService.batchAdd(list);
        return list;
    }

    // 删除
    @DeleteMapping("{ids}")
    public Boolean delPerson(@PathVariable("ids") String ids) {
        System.out.println("=====================删除的id=" + ids);
        personService.del(ids);
        return true;
    }

    // 修改
    @PutMapping
    public Person putPerson(@RequestBody @Valid Person person) {
        System.out.println("要修改的内容：" + person);
        personService.edit(person);
        return person;
    }


}
