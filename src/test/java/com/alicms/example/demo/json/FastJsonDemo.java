package com.alicms.example.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * <p>
 * fastJson序列化测试
 * </p>
 *
 * @author zhenghao
 * @date 2020/10/19 17:26
 */
public class FastJsonDemo {
    private static ValueFilter filter = (obj, s, v) -> {
        if (v == null)
            return "";
        return v;
    };

    public static void main(String[] args) {
        new FastJsonDemo().tv();
//        new FastJsonDemo().foo();
//        new FastJsonDemo().bar();
    }

    private void tv() {
        List<Map<String, Student>> list = new ArrayList<>();
        Map<String, Student> map = new HashMap<>();
        map.put("null", new Student());
        Student s = new Student();
        s.setAge(18);
        s.setMale(true);
        s.setName("zhangsan");
        s.setGf(new Student("zs222", 20, false, new Student()));
        map.put("zs", s);
        list.add(map);
        list.add(new HashMap<String, Student>(){{put("xxx", new Student());}});
        String json = JSON.toJSONString(list);
        System.out.println(json);
        List<Map<String, Student>> results = JSONObject.parseObject(json, new TypeReference<List<Map<String, Student>>>() {});
        System.out.println(results);
    }

    private void foo() {
        System.out.println("foo()---------------------------");
        JSONObject j1 = new JSONObject();
        j1.put("name", "zhangsan");
        j1.put("age", 13);
        j1.put("isMale", true);
        j1.put("gf", null);

        Map<String, Object> fav = new HashMap<>();
        Set<String> books = new HashSet<>();
        books.add("三国");
        books.add("史记");
        fav.put("history", books);

        String[] arts = new String[] {};
        fav.put("arts", arts);
        String[] musics = new String[] { "北京欢迎你", "画心" };
        fav.put("musics", musics);
        List<String> sports = new ArrayList<>();
        fav.put("sports", sports);
        j1.put("fav", fav);

        List<Student> classmates = new ArrayList<>();
        classmates.add(new Student());

        Student lisi = new Student();
        lisi.setMale(false);
        lisi.setAge(11);
        classmates.add(lisi);

        Student zhangsan = new Student();
        zhangsan.setAge(13);
        zhangsan.setName("张三");
        zhangsan.setMale(true);
        zhangsan.setGf(lisi);
        classmates.add(zhangsan);
        j1.put("classmates", classmates);

        String str = null;
        j1.put("str", str);
        System.out.println("原字符串-------------------------------------------------------------");
        System.out.println(j1.toString());
        System.out.println("\n all 。。。。。。。。。。。。。。。。。。。。。。。。。");
        System.out.println(JSON.toJSONString(j1, SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteBigDecimalAsPlain,
                SerializerFeature.DisableCheckSpecialChar));

        System.out.println("\n all2 -------------------------------------------------------------");
        Student student = new Student();
        student.setGf(new Student());
        System.out.println(JSON.toJSONString(
                student,
                SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteBigDecimalAsPlain,
                SerializerFeature.DisableCheckSpecialChar));

        System.out.println("\nmap string 。。。。。。。。。。。。。。。。。。。。。。。。。");
        System.out.println(JSON.toJSONString(j1, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println("\nmap string filter 。。。。。。。。。。。。。。。。。。。。。。。。。");
        System.out.println(JSON.toJSONString(j1, filter, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println("\nstring 。。。。。。。。。。。。。。。。。。。。。。。。。");
        System.out.println(JSON.toJSONString(j1, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println("\nstring filter 。。。。。。。。。。。。。。。。。。。。。。。。。");
        System.out.println(JSON.toJSONString(j1, filter, SerializerFeature.WriteNullStringAsEmpty));

        Map<String, JSONObject> m = new HashMap<String, JSONObject>();
        m.put("key", j1);
        System.out.println("\nstringKey string 。。。。。。。。。。。。。。。。。。。。。。。。。");
        System.out.println(JSON.toJSONString(m, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println("\nstringKey string filter 。。。。。。。。。。。。。。。。。。。。。。。。。");
        System.out.println(JSON.toJSONString(m, filter, SerializerFeature.WriteNonStringKeyAsString,
                SerializerFeature.WriteNullStringAsEmpty));

    }

    private void bar() {
        System.out.println("bar()---------------------------");
        Student zhangsan = new Student();
        zhangsan.setAge(13);
        zhangsan.setName("张三");
        zhangsan.setMale(true);
        Student lisi = new Student();
        // lisi.setName("lisi");
        lisi.setMale(false);
        lisi.setAge(11);
        zhangsan.setGf(lisi);
        System.out.println(
                JSON.toJSONString(zhangsan, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println(JSON.toJSONString(zhangsan, SerializerFeature.WriteMapNullValue));
        System.out.println(JSON.toJSONString(zhangsan, SerializerFeature.WriteNullStringAsEmpty));
        System.out.println(JSON.toJSONString(zhangsan));
        System.out.println(JSON.toJSONString(zhangsan, filter));
        System.out.println(JSON.toJSONString(zhangsan, filter, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty));
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Student {
    private String name;
    private int age;
    private boolean isMale;
    private Student gf;
}