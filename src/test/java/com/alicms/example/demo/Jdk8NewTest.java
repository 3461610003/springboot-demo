package com.alicms.example.demo;

import com.alicms.example.demo.model.Hello;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description
 * @author zhenghao
 * @date 2019/12/19 17:19
 */
public class Jdk8NewTest {

    public static void main(String[] args) throws Exception {
        t2();
    }

    // lambda表达式
    public static void t1() throws Exception {
        Callable<Integer> integerCallable = () -> 5;
        System.out.println(integerCallable.call());
        IntFunction<Integer> integerIntFunction = (int a) -> a * a;
        System.out.println(integerIntFunction.apply(3));
    }
    /* Java 8 允许使用 :: 关键字来传递方法(静态方法和非静态方法)。
        静态方法引用 ContainingClass::staticMethodName
        特定对象的方法引用 containingObject::instanceMethodName
        构造器引用 ClassName::new
     */

    // Stream
    public static void t2() {
        // Stream对象的构建
        String[] strArr = {"hello", "world", "java"};
        List<String> list = Arrays.asList(strArr);
        Stream<Integer> s = Stream.of(1, 2, 3, 2);
        Stream<String> s2 = Stream.of(strArr);
        Stream<String> s3 = Arrays.stream(strArr);
        Stream<String> s4 = list.stream();

        /*System.out.println("============================================");
        // 目前有三种对应的包装类型 Stream：IntStream、LongStream、DoubleStream
        //示例IntStream：
        IntStream is = IntStream.of(1, 2, 3, 8, 9);
        is.forEach(System.out::println);
        IntStream is2 = IntStream.range(1, 5);
        is2.forEach(System.out::println);
        IntStream is3 = IntStream.rangeClosed(1, 5);
        is3.forEach(System.out::println);*/

        System.out.println("============================================");
        // Stream转为其他类型
        //转数组
//        String[] arr = s2.toArray(String[]::new);
//        System.out.println(Arrays.toString(arr));
        // 转String
//        String str = s2.collect(Collectors.joining());
//        System.out.println(str);

        // 转Collection
        //List<String> list2 = s2.collect(Collectors.toList());
//        List<String> list3 = s2.collect(Collectors.toCollection(ArrayList::new));
//        Set<String> set = s2.collect(Collectors.toSet());
//        HashSet<String> set2 = s2.collect(Collectors.toCollection(HashSet::new));

        System.out.println("============================================");
        // forEach,一个 Stream 只可以使用一次
        //s2.forEach(System.out::println);

        // filter()，过滤不符合要求的元素，返回类型还是Stream
//        s2.filter(e -> e.contains("o")).forEach(System.out::println);

        // count()，返回元素个数，返回类型为long类型。
//        System.out.println(s2.count());

        // limit(maxSize)，截取长度不超过maxSize的Stream，返回类型为Stream
//        s2.limit(2).forEach(System.out::println);

        // map(Function<? super T,? extends R> mapper)，对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素，转换一般都是一对一。
//        s2.map(s1 -> s1.toUpperCase() + ",").forEach(System.out::println);

        // flatMap(Function<? super T,? extends Stream<? extends R>> mapper)，flatMap的参数为Function，Function的参数为Stream类型，可用于一对一，一对多，多对多转换。
//        s2.flatMap(e -> Arrays.stream(e.split(""))).forEach(System.out::println);   // 分割字符

        // reduce()，归约。这是一个最终操作，允许通过指定的函数来将stream中的多个元素规约合并为一个元素
//        System.out.println(s.reduce(0, Integer::sum));

        System.out.println("============================================");
        //示例：（统计一个数组中数字出现的个数）
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 6, 6, 3, 2);
        Map<Integer, Long> map = stream.collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        System.out.println(map);

    }

    public void finalTest() {
        AtomicReference<Hello> hello = new AtomicReference<>(new Hello());
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.forEach(e -> {
            System.out.println(e);
            hello.get().setIdCard(e);
            hello.set(new Hello());
        });
    }
}
