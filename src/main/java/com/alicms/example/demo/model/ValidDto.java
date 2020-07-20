package com.alicms.example.demo.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhenghao
 * @description Valid校验实体
 * @date 2020/7/20 9:15
 */
@Data
public class ValidDto {
    @Null(message = "id必须为null")
    private Long id;

    @NotNull(message = "生日不能为空")
    @PastOrPresent(message = "生日必须是当前或过去的日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    @AssertTrue(message = "必须为开启状态")
    private Boolean isOpen;

    @AssertFalse(message = "默认为非商家")
    private Boolean isMerchant;

    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+$", message = "密码必须由字母和数字组成")
    @Size(min=5, max=12, message="密码的长度应该在5和10之间")
    private String password;

    @NotEmpty(message="签名不能为空")
    private String sign;

    @NotBlank(message="姓名不能为空(空格算为空)")
    private String name;

    @Min(value = 18, message = "年龄必须满18岁")
    @Max(value = 200, message = "年龄不能大于等于200岁")
    private int age;

    @DecimalMax(value = "999.99", message = "单价不能小于等于999.99")
    @DecimalMin(value = "0.99",message = "单价必须大于0.99", inclusive = false)
    private BigDecimal price;

    @Range(min = 1, max = 100, message = "数量必须在1~100内")
    @Digits(integer = 2, fraction = 0, message = "数量不能超过两位数，并且必须为整数")
    private BigDecimal number;

    @Future(message = "预约时间必须是未来的时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date appointTime;

    @NotEmpty(message="爱好不能为空")
    private List<String> hobbies;

    @Email(message="邮箱格式错误")
    private String email;

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new ValidDto(),
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteMapNullValue));
    }

}
/*
常用校验注解
    @Null 只能是null
    @NotNull 不能为null 注意用在基本类型上无效，基本类型有默认初始值
    @AssertFalse 必须为false
    @AssertTrue 必须是true

字符串/数组/集合检查：(字符串本身就是个数组)
    @Pattern(regexp="reg") 验证字符串满足正则
    @Size(max, min) 验证字符串、数组、集合长度范围
    @NotEmpty 验证字符串不为空或者null
    @NotBlank 验证字符串不为null或者trim()后不为空

数值检查：同时能验证一个字符串是否是满足限制的数字的字符串
    @Max 规定值得上限int
    @Min 规定值得下限
    @DecimalMax("10.8") 以传入字符串构建一个BigDecimal，规定值要小于这个值 
    @DecimalMin 可以用来限制浮点数大小
    @Digits(int1, int2) 限制一个小数，整数精度小于int1；小数部分精度小于int2
    @Digits 无参数，验证字符串是否合法
    @Range(min=long1,max=long2) 检查数字是否在范围之间这些都包括边界值

日期检查：Date/Calendar
    @Future 验证日期为当前时间之后     @FutureOrPresent 验证日期为当前时间或之后一个时间
    @Past 验证日期为当前时间之前        @PastOrPresent 验证日期为当前时间或之前

其他验证：
    @Vaild 递归验证，用于对象、数组和集合，会对对象的元素、数组的元素进行一一校验
    @Email 用于验证一个字符串是否是一个合法的右键地址，空字符串或null算验证通过
    @URL(protocol=,host=,port=,regexp=,flags=) 用于校验一个字符串是否是合法UR
 */