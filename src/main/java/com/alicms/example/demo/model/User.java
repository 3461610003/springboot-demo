package com.alicms.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @description 用户Entity
 *
 * @author shaoqiping
 * @date 2018/8/15 08:51
 */
@Entity
@Table(name = "alicms_user")
@Data
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 289752917256480569L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键自增
    private Long id;

    /** 区号 */
    @Column
    private String areaCode;

    /** 手机号 */
    @Column
    private String phone;

    /** 邮箱 */
    @Column
    private String email;

    /** 密码 */
    @Column
    private String password;

    /** 加盐 */
    @Column
    private String salt;

    /** 交易密码 */
    @Column
    private String transactionPassword;

    /** 是否记住交易密码 1=记住 0=不记住 */
    @Column
    private Integer isRemember;

    /** 用户昵称 */
    @Column
    private String nickname;

    /** 用户头像 */
    @Column
    private String image;

    /** 真实姓名 */
    @Column
    private String trueName;

    /** 国籍 */
    @Column
    private String nationality;

    /** 证件类型 1=身份证 2=护照 3=其他 */
    @Column
    private Integer idCardType;

    /** 身份证号 */
    @Column
    private String idCard;

    /** 是否实名认证 1=已认证 0=未认证 */
    @Column
    private Integer idCardAuth;

    /** 身份证认证照片1 */
    @Column
    private String idCardImg1;

    /** 身份证认证照片2 */
    @Column
    private String idCardImg2;

    /** 身份证认证照片3 */
    @Column
    private String idCardImg3;

    /** 辅助货币 1=usd 2=cny */
    @Column
    private Integer assistantCurrency;
    /** 交易市场是否收手续费 1=收取 0=不收取 */
    @Column
    private Integer exchangeFlag;
    /** 语言 */
    @Column
    private String language;
    /** 是否有银行卡 */
    @Column
    private Integer bankCardFlag;

    /** 个人邀请码 */
    @Column
    private String inviteCode;

    /** 一级邀请人id */
    @Column(name = "inviter_1")
    private Long inviter1;

    /** 二级邀请人id */
    @Column(name = "inviter_2")
    private Long inviter2;

    /** 账号状态 1=可用 0=禁用 */
    @Column
    private Integer status;

    /** 登陆地ip */
    @Column
    private String loginIp;

    /** 是否为机器人 0=否 1=是 */
    @Column
    private Integer isBot;

    /** 删除状态 0=删除的 1=未删除 */
    @Column
    private Integer isDeleted;

    public User() {
    }

    public User(Long id, String areaCode, String phone, String email, String password, String salt, String transactionPassword, Integer isRemember, String nickname, String image, String trueName, String nationality, Integer idCardType, String idCard, Integer idCardAuth, String idCardImg1, String idCardImg2, String idCardImg3, Integer assistantCurrency, Integer exchangeFlag, String language, Integer bankCardFlag, String inviteCode, Long inviter1, Long inviter2, Integer status, String loginIp, Integer isBot, Integer isDeleted, Long creator, Long updater) {
        super.setId(id);
        this.areaCode = areaCode;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.transactionPassword = transactionPassword;
        this.isRemember = isRemember;
        this.nickname = nickname;
        this.image = image;
        this.trueName = trueName;
        this.nationality = nationality;
        this.idCardType = idCardType;
        this.idCard = idCard;
        this.idCardAuth = idCardAuth;
        this.idCardImg1 = idCardImg1;
        this.idCardImg2 = idCardImg2;
        this.idCardImg3 = idCardImg3;
        this.assistantCurrency = assistantCurrency;
        this.exchangeFlag = exchangeFlag;
        this.language = language;
        this.bankCardFlag = bankCardFlag;
        this.inviteCode = inviteCode;
        this.inviter1 = inviter1;
        this.inviter2 = inviter2;
        this.status = status;
        this.loginIp = loginIp;
        this.isBot = isBot;
        this.isDeleted = isDeleted;
        super.setCreator(creator);
        super.setUpdater(updater);
    }
}