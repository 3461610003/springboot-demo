package com.alicms.example.demo.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * <p>
 * MapDemo
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/9 13:29
 */
public class MapDemo {
    public static void main(String[] args) {
        System.out.println(new MapDemo().hashCode());
        System.out.println(new Object().hashCode());
//        MyMap<Integer, Integer> myMap = new MyMap<>();
//        Random random = new Random();
//        for (int i = 0; i < 10000; i++) {
//            int r = random.nextInt(20);
//            myMap.put(r, myMap.get(r) == null ? 0 : myMap.get(r) + 1);
//        }
//        System.out.println(myMap);
//        int length = myMap.nodes.length;
//        int count = 0;
//        for (int i = 0; i < length; i++) {
//            MyMap.Node<Integer, Integer> node = myMap.nodes[i];
//            int t = 0;
//            while (node != null) {
//                System.out.println(node.key + "--->" + node.value);
//                node = node.next;
//                ++ count;
//                ++ t;
//            }
//            System.out.println("tttt:" + t);
//        }
//        System.out.println("total:" + count);
    }
}


class MyMap<K, V> {
    private static final int size = 17;
    private int count = 0;
    Node<K, V>[] nodes = (Node<K,V>[])new Node[size];

    @Data
    @AllArgsConstructor
    static class Node<K,V> {
        final K key;
        V value;
        Node<K,V> next;
    }

    public V put(K k, V v) {
        if (k == null)  return null;
        int index = Objects.hashCode(k) % size;
        Node<K, V> oldNode = nodes[index];
        Node<K, V> endNode = oldNode;
        while (oldNode != null) {
            K key = oldNode.key;
            if (key.equals(k)) {    // 存在覆盖
                V oldVal = oldNode.value;
                oldNode.value = v;
                return oldVal;
            }
            if (oldNode.next == null) {
                endNode = oldNode;
            }
            oldNode = oldNode.next;
        }
        if (endNode != null) {
            endNode.next = new Node<>(k, v, null);
        } else {
            nodes[index] = new Node<>(k, v, null);
        }
        count ++;
        return null;

    }

    public V get(K k) {
        if (k == null)  return null;
        int index = Objects.hashCode(k) % size;
        Node<K, V> oldNode = nodes[index];
        while (oldNode != null) {
            if (oldNode.key.equals(k)) {
                return oldNode.value;
            }
            oldNode = oldNode.next;
        }
        return null;
    }

    @Override
    public String toString() {
        return "MyMap{" +
                "count=" + count +
                ", nodes=" + Arrays.toString(nodes) +
                '}';
    }

}
