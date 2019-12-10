package com.alicms.example.demo.service.impl;

import com.alicms.example.demo.dao.PersonDao;
import com.alicms.example.demo.model.Person;
import com.alicms.example.demo.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: TODO
 * @Author zhenghao
 * @Date 2019/12/5 9:46
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonDao personDao;

    @Override
    @Transactional
    public Boolean batchAdd(List<Person> list) {
        final List<Person> newList = personDao.saveAll(list);
        System.out.println("save--------------------------------------------------------------------" +
                "\nlist:" + list +
                "\nnewList:" + newList);
        return newList != null;
    }

    @Override
    public Boolean add(Person person) {
        Person newPerson = personDao.save(person);
        System.out.println("save--------------------------------------------------------------------" +
                "\nperson:" + person +
                "\nnewPerson:" + newPerson);
        return newPerson != null;
    }

    @Override
    @Transactional
    public Boolean del(String ids) {
        String[] idArr = ids.split(",");
        List<Person> list = new ArrayList<>();
        for (String id : idArr) {
            Person person = new Person();
            person.setId(Long.parseLong(id));
            list.add(person);
        }
        personDao.deleteInBatch(list);
        return true;
    }

    @Override
    public Boolean edit(Person person) {
        Person newPerson = personDao.save(person);
        System.out.println("edit--------------------------------------------------------------------" +
                "\nperson:" + person +
                "\nnewPerson:" + newPerson);
        return newPerson != null;
    }

    @Override
    public Person find(Long id) {
        Optional<Person> optional = personDao.findById(id);
        final boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }
}
