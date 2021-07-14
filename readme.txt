1.定时任务（静态/动态定时任务），TaskTest、TaskTest2
2.redis实现分布式锁，DemoApplicationTests
3.测试回退提交 2019/12/5 9:25
4.测试跨域请求解决方式：
    1.CORS全局配置-实现WebMvcConfigurer CORSConfiguration
    2.实现Fiter接口在请求中添加一些Header来解决跨域的问题 CorsFilter
    3.在Controller内设置相应头 com.alicms.example.demo.controller.IndexController
    4.在Controller上使用@CrossOrigin注解 com.alicms.example.demo.controller.IndexController
5.姓名生成工具类、生成用户数据、HashCode作用测试、jdk8新特性（lambda表达式，Stream相关操作） NameGenerate、GenerateIdTest、HashCodeTest、Jdk8NewTest
6.@EqualsAndHashCode注解测试 com.alicms.example.demo.model.Hello
7.RSAUtils公钥加密私钥解密测试，前端公钥加密，后端私钥解密，避免前端明文传输 com.alicms.example.demo.RSAUtilsTest
8.gzip进行压缩 com.alicms.example.demo.GZipTest
9.reids测试 com.alicms.example.demo.test.RedisDemoTest
10.ApplicationContextAware接口实现 获取bean com.alicms.example.demo.utils.AppUtils
    ApplicationContext获取bean com.alicms.example.demo.controller.SpringDemoController
11.lombok 之 @Builder注解 com.alicms.example.demo.test.LombokBuilderTest
12.stream流操作学习 com.alicms.example.demo.Jdk8NewTest
   流的操作：把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
     Intermediate：中间处理
         map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、
         peek、 limit、 skip、 parallel、 sequential、 unordered
     Terminal：最终操作
         forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、
         count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
     Short-circuiting：短路循环
         anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
13.文件下载（爬虫爬文件），文件合并，文本文件读写 com.alicms.example.demo.DownloadFileTest、FileMergeAndSplitUtil、TextFileStreamUtil
14.BindingResult Valid 的使用 com.alicms.example.demo.DownloadFileTest
15.百度语音合成sdk测试 com.alicms.example.demo.baidu
16.验证码生成 com.alicms.example.demo.controller.ImgCodeDemoController
17.事务中有创表语句不能回滚问题 com.alicms.example.demo.controller.TransactionalDemoController
18.FastJson测试 com/alicms/example/demo/FastJsonDemo.java
    简单使用，解析多重泛型等
19.jsoup 爬取小说案例 com.alicms.example.demo.txt
20.java动态代理：jdk、cglib com.alicms.example.demo.proxy
21.排序算法：堆排序，归并排序，快排 com.alicms.example.demo.demo.sort
22.自定义Map实现set、put操作 com/alicms/example/demo/demo/MapDemo.java
23.springboot默认使用cglib动态代理测试  com.alicms.example.demo.proxy
24.注解的定义、使用与解析 com.alicms.example.demo.demo.annotation
25.二进制测试、订单数据生成 com.alicms.example.demo.GenerateIdTest
36.EventListener发布监听消息 com.alicms.example.demo.controller.EventListenerController、com.alicms.example.demo.test.EventListenerTest
37.curl,httpUtil,restTemplate请求测试，com.alicms.example.demo.test
38.区块链对接工具库测试，tron链代币工具类，获取btc手续费，com.alicms.example.demo.blockchain
39.计算文件md5值 Md5FileTest

