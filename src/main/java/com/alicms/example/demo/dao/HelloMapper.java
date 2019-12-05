package com.alicms.example.demo.dao;

import com.alicms.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Description TODO
 * @Author zhenghao
 * @Date 2019/9/10 16:38
 */
//@Mapper
public interface HelloMapper {
    @Select("select cron from alicms_cron limit 1")
    String getCron();

    @Update("update alicms_cron set cron = #{cron} where cron_id=1")
    int updateCron(String cron);
}
