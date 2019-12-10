package com.alicms.example.demo.service;

import com.alicms.example.demo.model.Person;

import java.util.List;

/**
 * @Description: PersonService
 * @Author zhenghao
 * @Date 2019/12/5 9:41
 */
public interface PersonService {
    /**
     * 添加
     * @param person 实体
     * @return 结果
     */
    Boolean add(Person person);
    Boolean batchAdd(List<Person> list);

    /**
     * 删除
     * @param ids 要删除的id
     * @return 结果
     */
    Boolean del(String ids);

    /**
     * 修改
     * @param person 实体
     * @return 结果
     */
    Boolean edit(Person person);

    /**
     * 查询一个
     * @param id 要查询的id
     * @return 查询结果
     */
    Person find(Long id);

    /**
     * 查询所有
     * @return 查询结果集
     */
    List<Person> findAll();

}
