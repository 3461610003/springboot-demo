package com.alicms.example.demo;

import com.alicms.example.demo.utils.HttpClientUtils;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

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
        downFile(url, path);  // 下载文件
        mergeFile(path, "03 线程池：业务代码最常用也最容易犯错的组件");  // 合并文件
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
        header.put("cookie", "_ga=GA1.2.1270688324.1591065724; _gid=GA1.2.699077602.1591065724; LF_ID=1591065724089-8778170-3346309; GCID=1c25569-c938d68-672ef70-021eeb9; GRID=1c25569-c938d68-672ef70-021eeb9; GCESS=BQoEAAAAAAcE.OsTEQYE7UWdYwMEiLzVXgwBAQkBAQQEAC8NAAUEAAAAAAEI6GYeAAAAAAAIAQMLAgUAAgSIvNVe; ECID=7ed8717-247cfad-c402029-bf194ca; ERID=7ed8717-247cfad-c402029-bf194ca; Hm_lvt_59c4ff31a9ee6263811b23eb921a5083=1591277858,1591320196,1591333653,1591343507; Hm_lpvt_59c4ff31a9ee6263811b23eb921a5083=1591347574; gk_process_ev={%22count%22:7%2C%22target%22:%22page_course_article_detail%22%2C%22referrer%22:%22https://time.geekbang.org/dashboard/course%22%2C%22referrerTarget%22:%22%22%2C%22utime%22:1591347341305}");
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
        File f = new File("file\\ts\\" + path);
        File[] fs = f.listFiles();
        for (File file : fs) {
            System.out.println(file);
        }
        // 10 集合类：坑满地的List列表操作.ts
        merge(fs, new File("C:\\Users\\34616\\Desktop\\merge\\" + newFileName + ".ts"));
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
                System.out.println(bi.length);
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
