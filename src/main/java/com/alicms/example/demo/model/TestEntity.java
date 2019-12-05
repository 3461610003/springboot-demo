package com.alicms.example.demo.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author zhenghao
 * @Date 2019/11/28 15:15
 */
//@Data
public class TestEntity<T> {

    private T t;
    private String name;
    private List<String> list;
    private Map<Integer, Object> map = new HashMap<Integer, Object>(){{
        put(1, "b");
        put(2, 132);
    }};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<Integer, Object> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Object> map) {
        this.map = map;
    }

    public static void main(String[] args) {
        TestEntity testEntity = new TestEntity<>();
        Map map = testEntity.getMap();
        map.put("hello", "world");
        map.put(3, "world");
        System.out.println(testEntity.getMap());


        List<String> list = testEntity.getList();
    }
}
