1.定时任务（静态/动态定时任务）
2.redis实现分布式锁
3.测试回退提交
4.测试跨域请求解决方式：
    1.CORS全局配置-实现WebMvcConfigurer
    2.实现Fiter接口在请求中添加一些Header来解决跨域的问题
    3.在controller内设置相应头
    4.在Controller上使用@CrossOrigin注解
5.姓名生成工具类、HashCodeTest、jdk8新特性（lambda表达式，Stream相关操作）
6.@EqualsAndHashCode注解测试
7.RSAUtils公钥加密私钥解密测试，前端公钥加密，后端私钥解密，避免前端明文传输
8.gzip进行压缩
9.reids测试
10.ApplicationContextAware接口实现 获取bean
11.lombok 之 @Builder注解
12.stream流操作学习
   流的操作：把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
     Intermediate：中间处理
         map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、
         peek、 limit、 skip、 parallel、 sequential、 unordered
     Terminal：最终操作
         forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、
         count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
     Short-circuiting：短路循环
         anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
13.文件下载（爬虫爬文件）
14.BindingResult Valid 的使用
15.百度语音合成sdk demo
16.验证码生成
17.事务中有创表语句不能回滚问题
18.FastJson测试
    简单使用，解析多重泛型等
19.jsoup 爬虫案例
20.java动态代理：jdk、cglib
21.排序算法：堆排序，归并排序，快排
22.自定义Map实现set、put操作
23.springboot默认使用cglib动态代理测试
24.注解的定义、使用与解析
25.