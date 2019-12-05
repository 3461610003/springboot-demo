package com.alicms.example.demo.dao;

import com.alicms.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description: TODO
 * @Author zhenghao
 * @Date 2019/10/17
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
