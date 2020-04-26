package com.alicms.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description 姓名操作类
 * @author zhenghao
 * @date 2019/12/19 10:23
 */
public class NameUtil {
    public static void main(String[] args) {
        System.out.println("张:" + hiddenName("张"));
        System.out.println("张三:" + hiddenName("张三"));
        System.out.println("张三爷:" + hiddenName("张三爷"));
        System.out.println("张三爷爷:" + hiddenName("张三爷爷"));
        System.out.println("张一二三四:" + hiddenName("张一二三四"));
        System.out.println("张一二三四五:" + hiddenName("张一二三四五"));
    }

    public static String hiddenName(String name) {
//        return name.replaceAll("([\\d\\D]{1})(.*)", "$1**");
        StringBuffer sb = new StringBuffer();
        sb.append("$1");
        for (int i = 0; i < name.length() - 1; i++) {
            sb.append("*");
        }
        // 保留第一个字
        return name.replaceAll("(.)(.*)", sb.toString());
        // 保留最后一个字
//        sb.append("$2");
//        return name.replaceAll("(.*)(.)", sb.toString());
        // 保留第一个和最后一个
//        sb.append("$3");
//        return name.replaceAll("(.)(.*)(.)", sb.toString());
    }

    // 隐藏名字
    public static String replaceXName(String name) {
        String reg = ".{1}";
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(name);
        int i = 0;
        while (m.find()) {
            i++;
            if (i == 1) continue;
            m.appendReplacement(sb, "*");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    // 只保留最后一个字
    public static String replaceNameX(String name) {
        // 获取姓名长度
        int length = name.length();
        String reg = ".{1}";
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(name);
        int i = 0;
        while (m.find()) {
            i++;
            if (i == length)  continue;
            m.appendReplacement(sb, "*");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    // 隐藏中间部分
    public static String replaceName(String name) {
        // 获取姓名长度
        int length = name.length();
        if (length <= 2) return replaceXName(name);
        String reg = ".{1}";
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(name);
        int i = 0;
        while (m.find()) {
            i++;
            if (i == 1 || i == length)  continue;
            m.appendReplacement(sb, "*");
        }
        m.appendTail(sb);
        return sb.toString();
    }

}
