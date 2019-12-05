package com.alicms.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DynamicUpdate
@Data
@Table(name = "alicms_level_order")
public class LevelOrder extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6565163174214334851L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键自增
    private Long id;

    /** 用户id */
    private Long userId;

    /** 等级 */
    private Integer grade;

    /** 状态：0=申请，1=同意，2=驳回 */
    private Integer status;

    /** 更新人ID */
    private Long updater;

    public LevelOrder(Long userId, Integer grade, Integer status, Long creator, Long updater) {
        this.userId = userId;
        this.grade = grade;
        this.status = status;
        super.setCreator(creator);
        this.updater = updater;
    }

    public LevelOrder() {
    }


}