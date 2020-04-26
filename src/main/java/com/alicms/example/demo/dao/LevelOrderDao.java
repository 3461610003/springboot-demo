package com.alicms.example.demo.dao;

import com.alicms.example.demo.model.LevelOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description
 * @author zhenghao
 * @date 2019/10/18
 */
@Repository
public interface LevelOrderDao extends JpaRepository<LevelOrder, Integer> {

}
