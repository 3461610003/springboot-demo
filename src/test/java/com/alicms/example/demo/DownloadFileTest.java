package com.alicms.example.demo;

import com.alicms.example.demo.utils.HttpClientUtils;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhenghao
 * @description 下载文件
 * @date 2020/6/5 16:56
 */
public class DownloadFileTest {

    public static void main(String[] args) throws InterruptedException {
        //https://res001.geekbang.org//media/audio/5f/ed/5fdcdac948fa08ba18ebb2a93dc1b9ed/ld/ld-00012.ts
//        String url = "https://res001.geekbang.org//media/audio/5f/ed/5fdcdac948fa08ba18ebb2a93dc1b9ed/ld/ld-";  // 01
//        String url = "https://res001.geekbang.org//media/audio/af/68/af92a2dc5157227187e97fcd4c971f68/ld/ld-";  // 02
        String url = "https://res001.geekbang.org//media/audio/1d/51/1d1be5eea91a86f842844c6fc6471551/ld/ld-";  // 03
//        String url = "https://res001.geekbang.org//media/audio/b5/c7/b5b350e25bdf6e625b4ee039f4c014c7/ld/ld-";  // 10
        String path = "03";
        Map<String, List<String>> paramMap = new HashMap<>();
        paramMap.put("00", Arrays.asList("https://res001.geekbang.org//media/audio/85/07/85b838c641ea24155eade11547886907/ld/ld-", "00 开篇词 业务代码真的会有这么多坑？"));
        paramMap.put("01", Arrays.asList("https://res001.geekbang.org//media/audio/5f/ed/5fdcdac948fa08ba18ebb2a93dc1b9ed/ld/ld-", "01 使用了并发工具类库，线程安全就高枕无忧了吗？"));
        paramMap.put("02", Arrays.asList("https://res001.geekbang.org//media/audio/af/68/af92a2dc5157227187e97fcd4c971f68/ld/ld-", "02 代码加锁：不要让“锁”事成为烦心事"));
        paramMap.put("03", Arrays.asList("https://res001.geekbang.org//media/audio/1d/51/1d1be5eea91a86f842844c6fc6471551/ld/ld-", "03 线程池：业务代码最常用也最容易犯错的组件"));
        paramMap.put("04", Arrays.asList("https://res001.geekbang.org//media/audio/3d/16/3de8fa9752b9c1daa08ce42ae15d3b16/ld/ld-", "04 连接池：别让连接池帮了倒忙"));
        paramMap.put("05", Arrays.asList("https://res001.geekbang.org//media/audio/1c/ad/1ceed88afba883f8aa305d4a6e9e18ad/ld/ld-", "05 HTTP调用：你考虑到超时、重试、并发了吗？"));
        paramMap.put("06", Arrays.asList("https://res001.geekbang.org//media/audio/23/2e/23c8957e2bcc2e490cc4b8e1ef6b792e/ld/ld-", "06 20%的业务代码的Spring声明式事务，可能都没处理正确"));
        paramMap.put("07", Arrays.asList("https://res001.geekbang.org//media/audio/94/34/943daa98fdac471ac0330e533b799934/ld/ld-", "07 数据库索引：索引并不是万能药"));
        paramMap.put("08", Arrays.asList("https://res001.geekbang.org//media/audio/f0/20/f04eaa79bec7e05e5f26e85a22403e20/ld/ld-", "08 判等问题：程序里如何确定你就是你？"));
        paramMap.put("09", Arrays.asList("https://res001.geekbang.org//media/audio/48/33/48b41f8dda454ee64d25947bebef7233/ld/ld-", "09 数值计算：注意精度、舍入和溢出问题"));
        paramMap.put("10", Arrays.asList("https://res001.geekbang.org//media/audio/b5/c7/b5b350e25bdf6e625b4ee039f4c014c7/ld/ld-", "10 集合类：坑满地的List列表操作"));
        paramMap.put("11", Arrays.asList("https://res001.geekbang.org//media/audio/e1/a5/e1b83e296fe174074b0db02ec34723a5/ld/ld-", "11 空值处理：分不清楚的null和恼人的空指针"));
        paramMap.put("12", Arrays.asList("https://res001.geekbang.org//media/audio/8b/42/8b3b488163b0f5f71dc823a0982b4b42/ld/ld-", "12 异常处理：别让自己在出问题的时候变为瞎子"));
        paramMap.put("13", Arrays.asList("https://res001.geekbang.org//media/audio/83/e5/83c4a643db07af639fd5c4476632d1e5/ld/ld-", "13 日志：日志记录真没你想象的那么简单"));
        paramMap.put("14", Arrays.asList("https://res001.geekbang.org//media/audio/96/49/965eeca1fbb99db6f2df80c30d845649/ld/ld-", "14 文件IO：实现高效正确的文件读写并非易事"));
        paramMap.put("15", Arrays.asList("https://res001.geekbang.org//media/audio/f2/f6/f22ac73a565ee8dfcb28a99f300629f6/ld/ld-", "15 序列化：一来一回你还是原来的你吗？"));
        paramMap.put("16", Arrays.asList("https://res001.geekbang.org//media/audio/9d/65/9dc24d400103e60d9f3f300d3b9fa565/ld/ld-", "16 用好Java 8的日期时间类，少踩一些“老三样”的坑"));
        paramMap.put("17", Arrays.asList("https://res001.geekbang.org//media/audio/99/b4/99f0029fc267d5385ffc6a6828d7b9b4/ld/ld-", "17 别以为“自动挡”就不可能出现OOM"));
        paramMap.put("18", Arrays.asList("https://res001.geekbang.org//media/audio/fa/4d/fa9bd7ef5d3156e34aa86eaec1fe0f4d/ld/ld-", "18 当反射、注解和泛型遇到OOP时，会有哪些坑？"));
        paramMap.put("19", Arrays.asList("https://res001.geekbang.org//media/audio/b1/eb/b1af6cf12f64ca0d2e64de93f69803eb/ld/ld-", "19 Spring框架：IoC和AOP是扩展的核心"));
        paramMap.put("20", Arrays.asList("https://res001.geekbang.org//media/audio/e1/d6/e1fd22aa528fd1a2971d5e5e2199a7d6/ld/ld-", "20 Spring框架：框架帮我们做了很多工作也带来了复杂度"));
        paramMap.put("21", Arrays.asList("https://res001.geekbang.org//media/audio/90/ad/9097195c3ed02c43a901dd9db67260ad/ld/ld-", "21 代码重复：搞定代码重复的三个绝招"));
        paramMap.put("22", Arrays.asList("https://res001.geekbang.org//media/audio/a6/86/a6c4225460e9687b003b4b632379ec86/ld/ld-", "22 接口设计：系统间对话的语言，一定要统一"));
        paramMap.put("23", Arrays.asList("https://res001.geekbang.org//media/audio/b0/10/b061b9c1ac00c39dba63e3795032f910/ld/ld-", "23 缓存设计：缓存可以锦上添花也可以落井下石"));
        paramMap.put("24", Arrays.asList("https://res001.geekbang.org//media/audio/bb/5f/bba4f8c0f9a63525ea52000b5d11b05f/ld/ld-", "24 业务代码写完，就意味着生产就绪了？"));
        paramMap.put("25", Arrays.asList("https://res001.geekbang.org//media/audio/01/24/0141dac641c535a30a2bdafadcca2924/ld/ld-", "25 异步处理好用，但非常容易用错"));
        paramMap.put("26", Arrays.asList("https://res001.geekbang.org//media/audio/99/74/99d6bb4b14d87138e87148d987122274/ld/ld-", "26 数据存储：NoSQL与RDBMS如何取长补短、相辅相成？"));
        paramMap.put("27", Arrays.asList("https://res001.geekbang.org//media/audio/84/11/849f7bc622c3b9cd6ba604906d16de11/ld/ld-", "27 数据源头：任何客户端的东西都不可信任"));
        paramMap.put("28", Arrays.asList("https://res001.geekbang.org//media/audio/5d/e0/5dd2bb30f551cde52711cf7486a92be0/ld/ld-", "28 安全兜底：涉及钱时，必须考虑防刷、限量和防重"));
        paramMap.put("29", Arrays.asList("https://res001.geekbang.org//media/audio/4a/6c/4a906366dfc1d7d0a4063d51dbb93d6c/ld/ld-", "29 数据和代码：数据就是数据，代码就是代码"));
        paramMap.put("30", Arrays.asList("https://res001.geekbang.org//media/audio/63/d6/63978f5797af79797ed9fbbe6b1596d6/ld/ld-", "30 如何正确保存和传输敏感数据？"));
        paramMap.put("31", Arrays.asList("https://res001.geekbang.org//media/audio/d1/02/d18d0d701acdb2459e3eeb9644acae02/ld/ld-", "加餐1 带你吃透课程中Java 8的那些重要知识点（一）"));
        paramMap.put("32", Arrays.asList("https://res001.geekbang.org//media/audio/60/4c/60f75bbd9b617b4e4991117b1803e64c/ld/ld-", "加餐2 带你吃透课程中Java 8的那些重要知识点（二）"));
        paramMap.put("33", Arrays.asList("https://res001.geekbang.org//media/audio/a3/89/a386559811a46f95445534120cc39889/ld/ld-", "加餐3 定位应用问题，排错套路很重要"));
        paramMap.put("34", Arrays.asList("https://res001.geekbang.org//media/audio/ba/35/ba5d4704e07cf01be6a394dc45bbda35/ld/ld-", "加餐4 分析定位Java问题，一定要用好这些工具（一）"));
        paramMap.put("35", Arrays.asList("https://res001.geekbang.org//media/audio/07/c0/07f593c7a0cf0e1ce8ddab21b56528c0/ld/ld-", "加餐5 分析定位Java问题，一定要用好这些工具（二）"));
        paramMap.put("36", Arrays.asList("https://res001.geekbang.org//media/audio/70/0c/7045230e84b0712f43b28fbbe2c3b30c/ld/ld-", "加餐6 这15年来，我是如何在工作中学习技术和英语的？"));
        paramMap.put("37", Arrays.asList("https://res001.geekbang.org//media/audio/d5/03/d588e7fe05c091169ef75ad7c850fc03/ld/ld-", "结束语 写代码时，如何才能尽量避免踩坑？"));
        for (String key : paramMap.keySet()) {
            path = key;
            url = paramMap.get(key).get(0);
            downFile(url, path);  // 下载文件
            mergeFile(path, paramMap.get(key).get(1));  // 合并文件
        }
    }

    public static void downFile(String url, String path) throws InterruptedException {
        HashMap<String, String> header = new HashMap<>();
//        header.put(":authority", "res001.geekbang.org");
//        header.put(":method", "GET");
//        header.put(":path", "//media/audio/b5/c7/b5b350e25bdf6e625b4ee039f4c014c7/ld/ld-01038.ts");
//        header.put(":scheme", "https");
        header.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        header.put("accept-encoding", "gzip, deflate, br");
        header.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
        header.put("cache-control", "max-age=0");
        header.put("cookie", "_ga=GA1.2.1496830962.1590209886; LF_ID=1590209892883-4390710-2616491; GCID=145daf1-20be682-99d2fd4-2eace7c; GRID=145daf1-20be682-99d2fd4-2eace7c; _gid=GA1.2.697412212.1591540341; GCESS=BQEI6GYeAAAAAAAFBAAAAAAGBM5z1TEDBHz63F4HBEAzsPkKBAAAAAACBHz63F4JAQEIAQMMAQEEBAAvDQALAgUA; Hm_lvt_59c4ff31a9ee6263811b23eb921a5083=1590209931,1590221901,1590271701,1591540352; gksskpitn=d1ae26c7-31ac-4870-8559-6f0a4219bc4a; SERVERID=1fa1f330efedec1559b3abbcb6e30f50|1591541350|1591540351; _gat=1; Hm_lpvt_59c4ff31a9ee6263811b23eb921a5083=1591542030; gk_process_ev={%22count%22:53%2C%22utime%22:1591540952611%2C%22referrer%22:%22https://time.geekbang.org/dashboard/course%22%2C%22target%22:%22page_course_article_detail%22%2C%22referrerTarget%22:%22%22}");
        header.put("sec-fetch-dest", "document");
        header.put("sec-fetch-mode", "navigate");
        header.put("sec-fetch-site", "none");
        header.put("sec-fetch-user", "?1");
        header.put("upgrade-insecure-requests", "1");
        header.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36");
        // https://res001.geekbang.org//media/audio/b5/c7/b5b350e25bdf6e625b4ee039f4c014c7/ld/ld-00001.ts
        int i = 1;
        boolean result = true;
        while (result) {
            String str = String.format("%05d", i++);
            result = HttpClientUtils.doGetFile(url + str + ".ts", header, path);
            Thread.sleep(80);
        }
    }

    public static void mergeFile(String path, String newFileName) {
        //		拆分
//		File f = new File("D:\\test\\信息.xlsx");
//		split(f);

        //合并
        File f = new File("C:\\Users\\anchorite\\Desktop\\file\\ts\\" + path);
        File[] fs = f.listFiles();
        for (File file : fs) {
            System.out.println(file);
        }
        // 10 集合类：坑满地的List列表操作.ts
        merge(fs, new File("C:\\Users\\anchorite\\Desktop\\file\\merge\\" + newFileName + ".ts"));
    }

    // 合并文件
    public static void merge(File[] fs, File newFile) {
        int len = 0;
        for (File file : fs) {
            len += file.length();
        }
        byte[] b = new byte[len];//构造一个数组，存放合并的数据
        int len1 = 0;//初始化复制到目的数组的起始值
        for (File file : fs) {
            byte[] bi = new byte[(int) file.length()];
            try {
                FileInputStream fis = new FileInputStream(file);
                int read = fis.read(bi);
                fis.close();
//                System.out.println(bi.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.arraycopy(bi, 0, b, len1, (int) file.length());
            len1 += file.length();

            try {
                FileOutputStream fos = new FileOutputStream(newFile);
                fos.write(b);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 拆分文件
    public static void split(File f) {
        byte[] data = new byte[(int) f.length()];
        try {
            FileInputStream fis = new FileInputStream(f);

            fis.read(data);// 读取文件到data中
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(data.length / 1024 + "k");

        int len = 1024 * 100;
        int n = (int) (f.length() / len);// 拆分为多少100k的文件夹
        int m = (int) (f.length() / len);// 余数再额外准备一个文件夹
        File[] fs = new File[n + 1];// 设立文件数组，用来存放文件。长度为总共需要生成文件的个数。
        for (int i = 0; i < n + 1; i++) {
            int start = i * 102400;
            int end = (i + 1) * 102400;
            fs[i] = new File(f + "-" + i);
            if (i < n) {
                byte[] b = Arrays.copyOfRange(data, start, end);

                try {
                    FileOutputStream fos = new FileOutputStream(fs[i]);
                    fos.write(b);
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                start = i * 102400;
                end = (int) f.length();
                byte[] b = Arrays.copyOfRange(data, start, end);

                try {
                    FileOutputStream fos = new FileOutputStream(fs[i]);
                    fos.write(b);
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            System.out.println("输出子文件夹：" + fs[i].getAbsolutePath()
                    + "  其长度为： " + fs[i].length());
        }
    }
}
