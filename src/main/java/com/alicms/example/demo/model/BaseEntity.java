package com.alicms.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Description:基础类
 */
@Data
public class BaseEntity implements Serializable{

    private static final long serialVersionUID = 7859422933869392344L;

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键自增
    private Long id;

    /** 创建人ID */
    private Long creator;

    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /** 更新人ID */
    private Long updater;

    /** 更新时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
