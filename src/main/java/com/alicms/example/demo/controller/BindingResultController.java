package com.alicms.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alicms.example.demo.model.ValidDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhenghao
 * @description BindingResult 的使用
 * @date 2020/7/20 9:12
 */
@RestController
@RequestMapping(value = "/brc", produces = "text/plain;charset=UTF-8")
@Slf4j
public class BindingResultController {

    @PostMapping("/valid")
    public String valid(@RequestBody @Valid ValidDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
            log.warn(defaultMessage);

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                //日志打印不符合校验的字段名和错误提示
                log.error("error field is : {}, message is : {}", fieldError.getField(), fieldError.getDefaultMessage());
            });
//            for (int i = 0; i < fieldErrors.size(); i++) {
////                //控制台打印不符合校验的字段名和错误提示
////                System.out.println("error field is :" + fieldErrors.get(i).getField() + ",message is :" + fieldErrors.get(i).getDefaultMessage());
////            }
        }
        String result = JSON.toJSONString(dto, SerializerFeature.PrettyFormat, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(result);
        return result;
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }


}
