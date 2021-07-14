package com.alicms.example.demo.java;

import com.alicms.example.demo.model.Hello;
import com.alicms.example.demo.model.Person;
import org.assertj.core.util.Lists;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author zhenghao
 * @description
 * @date 2019/12/19 17:19
 */
public class Jdk8NewTest {

    public static void main(String[] args) throws Exception {
        t3();
        // new ArrayList<String>().stream().forEach(x -> System.out.println("x:" + x));
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
    private static void t2() {
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

    // 测试lambda内局部变量
    private void finalTest() {
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

    // Stream 相关方法详解
    private static void t3() {
        /*流的操作
            接下来，当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
            Intermediate：
                map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、
                peek、 limit、 skip、 parallel、 sequential、 unordered
            Terminal：
                forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、
                count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
            Short-circuiting：
                anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
         */
        // 创建Stream
//        t300();

        // map/flatMap
        // t301();

        // filter
//        t302();

        // forEach 不能修改自己包含的本地变量值，也不能用 break/return 之类的关键字提前结束循环。
//        t303();

        // findFirst 这是一个 termimal 兼 short-circuiting 操作，它总是返回 Stream 的第一个元素，或者空。
        // 这里比较重点的是它的返回值类型：Optional。这也是一个模仿 Scala 语言中的概念，作为一个容器，它可能含有某值，或者不包含。使用它的目的是尽可能避免 NullPointerException。
        // findAny方法可以在集合中只要找到任何一个所匹配的元素，就返回，此方法在对流并行执行时十分有效
        //  （任何片段中发现第一个匹配元素都会结束计算，串行流中和findFirst返回一样)。
        // anyMatch方法可以判定集合中是否还有匹配的元素。返回结果是一个boolean类型值。
        // noneMatch在所有元素没有元素匹配时返回true
//        t304();

        //reduce 这个方法的主要作用是把 Stream 元素组合起来。它提供一个起始值（种子），然后依照运算规则（BinaryOperator），
        // 和前面 Stream 的第一个、第二个、第 n 个元素组合。从这个意义上说，字符串拼接、数值的
        // sum、min、max、average 都是特殊的 reduce
//        t305();

        // limit/skip limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素（它是由一个叫 subStream 的方法改名而来）。
//        t306();

        // 对 Stream 的排序通过 sorted 进行，它比数组的排序更强之处在于你可以首先对 Stream 进行各类
        // map、filter、limit、skip 甚至 distinct 来减少元素数量后，再排序，这能帮助程序明显缩短执行时间。
//        t307();

        // count、sum
//        t308();

        // 收集结果 当处理完流之后，通常是想查看一下结果，而不是将他们聚合为一个值。Collectorts类为我们提供了常用的收集类的各个工厂方法。
//        t309();

        // 分组分片：在一个集合中，对具有相同特性的值进行分组是一个很常见的功能，在Stream的API中也提供了相应的方法。
//        t310();

        // 并行流
//        t311();


        // 串行排序和并行排序对比
//        tt();
        // 测试for、迭代器、stream性能
        streamAndForTest(8 * 1000 * 10);
    }

    private static void t300() {
        // Arrays.stream()
        Integer[] array = new Integer[]{3, 4, 8, 16, 19, 27, 23, 99, 76, 232, 33, 96};
        long count = Arrays.stream(array).filter(i -> i > 20).count();

        // Stream.of()
        long sum = Stream.of(12, 77, 59, 3, 654).filter(i -> i > 20).mapToInt(Integer::intValue).sum();
        System.out.println("count：" + count + ",sum:" + sum);

        // Stream.generate()
        Stream<String> stream = Stream.generate(() -> "test").limit(10);
        stream.reduce((a, b) -> a + "," + b).ifPresent(System.out::println);

        // Stream.iterate()
        Stream<BigInteger> bigIntStream = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.TEN)).limit(10);
        System.out.println(Arrays.toString(bigIntStream.toArray(BigInteger[]::new)));

        // Collection.stream()
        List<Integer> numbers = Arrays.asList(3, 4, 5, 6, 8, 16);
        numbers.stream().forEach(System.out::println);

        // StreamSupport.stream()
        //通过查看Collection.stream()的方法，我们可以看出来，Colleciton.stream()其实是调用了StreamSupport.stream()来实现的。
        // 所以我们也可以使用StreamSupport.stream()来创建一个Stream。
        // 当我们面对的是一个迭代器的时候，使用StreamSupport.stream()就可以创建一个Stream。
        // 第一个参数是传入一个迭代器，第二个参数是true代表使用并行来进行处理。false代表串行来处理Stream。
        Stream<Integer> s1 = StreamSupport.stream(numbers.spliterator(), true);// 等价于numbers.parallelStream()
        Stream<Integer> s2 = StreamSupport.stream(numbers.spliterator(), false);// 等价于numbers.stream()
        System.out.println(s1 + "," + s2);
    }

    private static void t301() {
        // map
        List<String> wordList = Arrays.asList("hello", "world", "java", "test", "map");
        wordList.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

        // flatMap
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3, 4),
                Arrays.asList(4, 5, 6)
        );
        inputStream.flatMap(Collection::stream).forEach(System.out::println);
    }

    private static void t302() {
        // 留下偶数
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> (n & 1) == 0).toArray(Integer[]::new);
        Stream.of(evens).forEach(System.out::println);
    }

    private static void t303() {
        List<String> roster = Arrays.asList("1", "2", "3");
        roster.forEach(System.out::println);
        // Pre-Java 8
        for (String p : roster) {
            System.out.println(p);
        }
    }

    private static void t304() {
        // Optional 的两个用例
//        String strA = " abcd ", strB = null;
//        print(strA);
//        print("");
//        print(strB);
//        System.out.println(getLength(strA));
//        System.out.println(getLength(""));
//        System.out.println(getLength(strB));
        //

        // findFirst
        List<Integer> hearList = Lists.newArrayList();
        hearList.add(15);
        hearList.add(32);
        hearList.add(5);
        hearList.add(232);
        hearList.add(56);
        hearList.add(29);
        hearList.add(104);
        hearList.stream().filter(i -> i > 100).findFirst().ifPresent(System.out::println);

        // findAny 任何片段中发现第一个匹配元素都会结束计算，串行流中和findFirst返回一样
        Integer anyItem = hearList.parallelStream().filter(i -> i > 100).findAny().get();
        boolean isHas = hearList.parallelStream().anyMatch(i -> i > 100);
        boolean noHas = hearList.parallelStream().noneMatch(i -> i > 100);

    }

    private static void print(String text) {
        // Java 8
        Optional.ofNullable(text).ifPresent(System.out::println);
        // Pre-Java 8
//        if (text != null) {
//            System.out.println(text);
//        }
    }

    private static int getLength(String text) {
        // Java 8
        return Optional.ofNullable(text).map(String::length).orElse(-1);
        // Pre-Java 8
        // return if (text != null) ? text.length() : -1;
    }

    private static void t305() {
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue);
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
        System.out.println(concat);
    }

    private static void t306() {
        List<Integer> integers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            integers.add(i);
        }
        List<Integer> personList2 = integers.stream().skip(10).limit(10).collect(Collectors.toList());
        System.out.println(personList2);
    }

    private static void t307() {
        List<Person> persons = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        // 对象排序
        List<Person> personList2 = persons.stream().limit(2).sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
        System.out.println(personList2);

        List<Integer> myTestList = Lists.newArrayList();
        myTestList.add(39);
        myTestList.add(78);
        myTestList.add(10);
        myTestList.add(22);
        myTestList.add(56);
        // 数值排序
        List<Integer> sortList = myTestList.stream().sorted(Integer::compareTo).collect(Collectors.toList());
//        List<Integer> sortList2 = myTestList.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        System.out.println("sortList：" + sortList);
    }

    private static void t308() {
        List<Integer> hearList = Lists.newArrayList();
        hearList.add(15);
        hearList.add(32);
        hearList.add(5);
        hearList.add(232);
        hearList.add(56);
        hearList.add(29);
        hearList.add(94);
        hearList.stream().max(Integer::compareTo).ifPresent(System.out::println);
        hearList.stream().min(Integer::compareTo).ifPresent(System.out::println);
    }

    private static void t309() {
        // 集合间互转
        t3091();

        // 将结果集收集到Map --------------------------
        t3092();


    }

    private static void t3091() {
        List<Integer> hereList = Arrays.asList(1, 3, 5, 6, 2, 9);
        // 集合间的转换 --------------------------
        // list --> set
        Set<Integer> set = hereList.stream().filter(x -> x > 0).collect(Collectors.toSet());
        // set --> list
        List<Integer> list = set.stream().filter(x -> x > 0).collect(Collectors.toList());
        // 控制集合类型
        TreeSet<Integer> treeSet = list.stream().filter(x -> x > 0).collect(Collectors.toCollection(TreeSet::new));
        LinkedList<Integer> linkedList = list.stream().filter(x -> x > 0).collect(Collectors.toCollection(LinkedList::new));

        // 拼接：将字流中的字符串连接并收集起来。 --------------------------
        List<String> strList = Arrays.asList("hello", "java", "world", "one", "one", "zero");
        String strs = strList.stream().collect(Collectors.joining());
        String strs11 = String.join("", strList);
        System.out.println("strs:" + strs + "\n" + "strs11:" + strs11);
        // 加分隔符拼接
        String str2 = strList.stream().collect(Collectors.joining(","));
        String str22 = String.join(",", strList);
        System.out.println("str2:" + strs + "\n" + "str22:" + strs11);

        // 转换拼接
        String str3 = hereList.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println("str2:" + str3);

        //总和、平均值，最大值，最小值 --------------------------
        int sum = hereList.stream().collect(Collectors.summingInt(Integer::intValue));
        int sum11 = hereList.stream().mapToInt(Integer::intValue).sum();

        Double ave = hereList.stream().collect(Collectors.averagingInt(Integer::intValue));
        double ave11 = hereList.stream().mapToInt(Integer::intValue).average().getAsDouble();

        Integer max = hereList.stream().collect(Collectors.maxBy(Integer::compare)).get();
        Integer max11 = hereList.stream().max(Integer::compare).get();

        Integer min = hereList.stream().collect(Collectors.minBy(Integer::compare)).get();
        Integer min11 = hereList.stream().min(Integer::compare).get();
        System.out.println("sum:" + sum + ",ave:" + ave + ",max:" + max + ",min:" + min);

        // 一次性收集流中的结果，聚合为一个总和，平均值，最大值或最小值的对象。
        IntSummaryStatistics summaryStatistics = hereList.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("总：" + summaryStatistics);
    }
    private static void t3092() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(12, "zhangsan"));
        personList.add(new Person(20, "lisi"));
        personList.add(new Person(18, "wangwu"));
        // 将结果集收集到Map --------------------------
        Map<String, Integer> map = personList.stream().collect(Collectors.toMap(Person::getName, Person::getAge));
        System.out.println("map:" + map);
        // 但是通常还是以具体元素作为值的情况多，可以使用Function.identity()来获取实际元素。
//        personList.add(new Person(19, "zhangsan"));
        Map<String, Person> map2 = personList.stream().collect(Collectors.toMap(Person::getName, Function.identity()));
        System.out.println("map2:" + map2);

        // 如果多个元素拥有相同的键，在收集结果时会抛出java.lang.IllegalStateException异常。
        // 可以使用第三个参数来解决，第三个参数用来确定当出现键冲突时，该如何处理结果，
        // 如果当出现键冲突时只保留一个并且是保留已经存在的值时，就是如下方式：
        personList.add(new Person(19, "zhangsan"));
        Map<String, Person> map3 = personList.stream()
                .collect(Collectors.toMap(Person::getName, Function.identity(), (nowValue, oldValue) -> nowValue));
        System.out.println("map3:" + map3);

        // 指定生成的map类型
        TreeMap<String, Person> map4 = personList.stream()
                .collect(Collectors.toMap(Person::getName, Function.identity(), (nowValue, oldValue) -> oldValue, TreeMap::new));
        System.out.println("map4:" + map4);
    }

    private static void t310() {
        // 分组操作 ------------
        List<Person> personList = Lists.newArrayList(
                new Person(18, "张三"),
                new Person(18, "李四"),
                new Person(16, "赵四"),
                new Person(20, "孙八"),
                new Person(16, "王五"),
                new Person(16, "老六")
                );
        AtomicLong i = new AtomicLong();
        personList.forEach(e -> e.setId(i.incrementAndGet()));

        Map<Integer,List<Person>> groupMap = personList.stream().collect(Collectors.groupingBy(Person::getAge));
        groupMap.forEach((key, value) -> System.out.println("key=" + key + ",value=" + value));
        System.out.println("----------------------------------");

        // 分片操作 ------------
        Map<Boolean, List<Person>> partitioningMap = personList.stream().collect(Collectors.partitioningBy(e -> e.getAge() >= 18));
        partitioningMap.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
        System.out.println("----------------------------------");

        // 扩展功能 counting、summing、maxBy、minBy、mapping
        //下面要介绍的这些方法功能，无论是groupingBy方法还是partitioningBy方法都是支持的。
        // 统计每组个数
        Map<Integer, Long> countMap = personList.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        countMap.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
        System.out.println("----------------------------------");
        // 统计每组和
        Map<Integer, Integer> sumMap = personList.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.summingInt(Person::getAge)));
        sumMap.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
        System.out.println("----------------------------------");
        // 统计每组最大和最小id
        Map<Integer, Optional<Person>> maxMap = personList.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.maxBy(Comparator.comparing(Person::getId))));
        Map<Integer, Optional<Person>> minMap = personList.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.minBy(Comparator.comparing(Person::getId))));
        maxMap.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
        System.out.println("--");
        minMap.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
        System.out.println("----------------------------------");
        // 统计每组最大的id
        Map<Integer, Optional<Long>> mappingMap = personList.stream()
                .collect(Collectors.groupingBy(Person::getAge,
                        Collectors.mapping(Person::getId, Collectors.maxBy(Long::compare))));
        mappingMap.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
        System.out.println("----------------------------------");

        // summarizing：每组的对象中取出函数值的总和、平均值、总数、最大值和最小值。
        Map<Integer, IntSummaryStatistics> totalMap = personList.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.summarizingInt(Person::getAge)));
        totalMap.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
        System.out.println("----------------------------------");

        // 多级分组
        // 先按年龄分组，再按id的奇偶分组
        Map<Integer, Map<Boolean, List<Person>>> groupsMap = personList.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.partitioningBy(e -> (e.getId() & 1) == 1L)));
        groupsMap.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
        System.out.println("----------------------------------");
    }
    private static void t311() {
        //parallel方法可以将任意的串行流转换为一个并行流。
//        Stream<Integer> stream = Stream.iterate(0, x -> x + 1).limit(10);
//        stream.forEach(System.out::println);
//        System.out.println("---");
//        Stream<Integer> parallel = Stream.iterate(0, x -> x + 1).limit(10).parallel();
//        parallel.forEach(System.out::println);


        //其次要确保传递给并行流操作的函数是线程安全的。
        Stream<String> intPar = Stream.of("1,2", "2,3", "3,4", "3,5", "6,5").parallel();
        List<String> intList = intPar.map(e -> e.split(",")).flatMap(Arrays::stream).sorted(String::compareTo).collect(Collectors.toList());
        System.out.println(intList);

        // 带下标的遍历数组(先遍历下标再遍历集合)
        IntStream.range(0, intList.size()).forEach(i->{
            System.out.println(intList.get(i));
        });

    }



    // JDK1.8--串行排序和并行排序对比
    private static void tt(){
        int size = 5000000;
        List<String> uuidList = new ArrayList<>(size);

        //生成500万个uuid
        for (int i = 0; i< size; i++){
            uuidList.add(UUID.randomUUID().toString());
        }
        parallelSorted(uuidList);
        streamSorted(uuidList);
    }

    //并行排序
    private static void parallelSorted(List<String> uuidList){
        System.out.println("开始并行排序");
        long startTime = System.nanoTime();//纳秒，更为精确
        long count = uuidList.parallelStream().sorted().count();//并行排序
        long endTime = System.nanoTime();//纳秒，更为精确
        long distanceTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("结束并行排序 耗时为 " + distanceTime);
        System.out.println(count);
    }

    //串行排序
    private static void streamSorted(List<String> uuidList){
        System.out.println("开始串行排序");
        long startTime = System.nanoTime();//纳秒，更为精确
        long count = uuidList.stream().sorted().count();//串行排序
        long endTime = System.nanoTime();//纳秒，更为精确
        long distanceTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("结束串行排序 耗时为 " + distanceTime);
        System.out.println(count);
    }


    /**
     * for和stream性能比较
     */
    private static void streamAndForTest(int size) {
        System.out.println("length:" + size);
        // 为了防止读一个list读性能影响，各自读一个list
        List<String> list1 = new ArrayList<>(size);
        List<String> list2 = new ArrayList<>(size);
        List<String> list3 = new ArrayList<>(size);
        List<String> list4 = new ArrayList<>(size);
        List<String> list5 = new ArrayList<>(size);
        List<String> list6 = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            String uuid = UUID.randomUUID().toString();
            list1.add(uuid);
            list2.add(uuid);
            list3.add(uuid);
            list4.add(uuid);
            list5.add(uuid);
            list6.add(uuid);
        }

        // 用线程池来执行
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        executorService.submit(()->{
            long t = System.nanoTime();
            List<String> result = new ArrayList<>(size);
            for (int i = 0; i <list2.size(); i++) {
                result.add(list2.get(i));
            }
            System.out.println("for i i++ for time:      "+ (System.nanoTime()-t));
        });

        executorService.submit(()->{
            long t = System.nanoTime();
            List<String> result = new ArrayList<>(size);
            for(String i : list3){
                result.add(i);
            }
            System.out.println("for i:list time:         " + (System.nanoTime()-t));
        });

        executorService.submit(()->{
            long t = System.nanoTime();
            List<String> result = new ArrayList<>(size);
            list4.forEach(i->{
                result.add(i);
            });
            System.out.println("forEach time:            " + (System.nanoTime()-t));
        });
        executorService.submit(()->{
            long t = System.nanoTime();
            List<String> result = new ArrayList<>(size);
            list5.stream().forEach(i->{
                result.add(i);
            });
            System.out.println("stream forEach time:     " + (System.nanoTime()-t));
        });
        executorService.submit(()->{
            long t = System.nanoTime();
            List<String> result = new ArrayList<>(size);
            Iterator<String> iterator = list6.iterator();
            while (iterator.hasNext()){
                result.add(iterator.next());
            }
            System.out.println("Iterator time:           " + (System.nanoTime()-t));
        });

        executorService.submit(()->{
            long t = System.nanoTime();
            List<String> result = new ArrayList<>(size);
            list1.stream().parallel().forEach(i->{
                result.add(i);
            });
            System.out.println("stream parallel time:    " + (System.nanoTime()-t));
        });
        executorService.shutdown();
    }

}