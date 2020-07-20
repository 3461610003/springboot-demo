package com.alicms.example.demo.utils;

import java.io.*;

/**
 * @author zhenghao
 * @description 文本文件的读写
 * @date 2020/7/11 15:12
 */
public class TextFileStreamUtil {
    public static void writeTextFile(String fileName, String content) {
        /* 写入文件 */
        // 获取要写入的文件对象
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName));
            // 往文件中写入内容（支持转义符）
            bw.write(content);
            // 往文件中写入换行
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bw != null;
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readTextFile(String fileName) {
        /* 读取文件 */
        // 获取要读取的文件对象
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader( new FileReader(fileName));
            String line = null;
            // 遍历读取文件的一行内容
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
