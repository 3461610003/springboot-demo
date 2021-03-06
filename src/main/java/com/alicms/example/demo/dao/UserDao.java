package com.alicms.example.demo.dao;

import com.alicms.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description
 * @author zhenghao
 * @date 2019/10/17
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
