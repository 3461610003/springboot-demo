package com.alicms.example.demo.dao;

import com.alicms.example.demo.model.Person;
import com.alicms.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description: PersonDao
 * @Author zhenghao
 * @Date 2019/10/17
 */
@Repository
public interface PersonDao extends JpaRepository<Person, Long> {
}
