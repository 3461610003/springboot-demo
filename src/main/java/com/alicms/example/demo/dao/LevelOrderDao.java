package com.alicms.example.demo.dao;

import com.alicms.example.demo.model.LevelOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description: TODO
 * @Author zhenghao
 * @Date 2019/10/18
 */
@Repository
public interface LevelOrderDao extends JpaRepository<LevelOrder, Integer> {

}
