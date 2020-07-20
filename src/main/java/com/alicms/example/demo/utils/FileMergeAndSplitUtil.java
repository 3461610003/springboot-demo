package com.alicms.example.demo.utils;

import java.io.*;
import java.util.Arrays;

/**
 * @author zhenghao
 * @description 文件的合并与拆分
 * @date 2020/7/18 15:58
 */
public class FileMergeAndSplitUtil {
    public static void main(String[] args) {
        String srcPath = "C:\\Users\\34616\\Desktop\\file\\";
        String desPath = "C:\\Users\\34616\\Desktop\\file\\";
        String srcFileName = "jvm.jpg";

        int splitCount = 70;
        //1.根据现有的文件编写文件编写文件切片工具类
        //2.写入到二进制临时文件
        splitFile(srcPath, srcFileName, desPath + "split\\", splitCount);
        //3.根据实际的需求合并指定数量的文件
        String mergeFile = "merge.jpg";
        merge(srcPath + "split\\", desPath + "merge\\", mergeFile);
    }

    /**
     * 分割文件
     * @param srcPath 源目录
     * @param fileName 文件名
     * @param desPath 目标目录
     * @param count 分割次数
     */
    public static void splitFile(String srcPath, String fileName, String desPath, int count) {
        //预分配文件所占用的磁盘空间，在磁盘创建一个指定大小的文件，“r”表示只读，“rw”支持随机读写
        try {
            File desFile = new File(desPath);
            if (!desFile.exists())   desFile.mkdirs();

            // 只读文件
            RandomAccessFile raf = new RandomAccessFile(new File(srcPath + fileName), "r");
            //计算文件大小
            long length = raf.length();
            System.out.println("file total size:" + length);
            //计算文件切片后每一份文件的大小
            long maxSize = length / count;
            System.out.println("every file size:" + maxSize);

            long offset = 0L; //定义初始文件的偏移量(读取进度)
            //开始切割文件
            for (int i = 0; i < count - 1; i++) { //count-1最后一份文件不处理
                //标记初始化
                long fbegin = offset;
                //分割第几份文件
                long fend = (i + 1) * maxSize;
                //写入文件
                offset = getWrite(srcPath, fileName, desPath, i, fbegin, fend);
            }
            System.out.println("offset:" + offset + ",length:" + length);
            //剩余部分文件写入到最后一份(如果不能平平均分配的时候)
            if (length - offset > 0) {
                //写入文件
                getWrite(srcPath, fileName, desPath, count - 1, offset, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定文件每一份的边界，写入不同文件中
     *
     * @param fileName 源文件
     * @param index 源文件的顺序标识
     * @param begin 开始指针的位置
     * @param end 结束指针的位置
     * @return long
     */
    public static long getWrite(String srcPath, String fileName, String desPath, int index, long begin, long end) {
        long endPointer = 0L;
        try {
            //申明文件切割后的文件磁盘
            RandomAccessFile in = new RandomAccessFile(new File(srcPath + fileName), "r");
            //定义一个可读，可写的文件并且后缀名为.tmp的二进制文件
            String[] fileNames = fileName.split("\\.");
            RandomAccessFile out = new RandomAccessFile(new File(desPath
                    + fileNames[0] + "_" + index + (fileNames.length > 1 ? fileNames[1] : "") + ".tmp"), "rw");
            //申明具体每一文件的字节数组
            byte[] b = new byte[1024];
            int n;
            //从指定位置读取文件字节流
            in.seek(begin);
            //判断文件流读取的边界
            while (in.getFilePointer() <= end && (n = in.read(b)) != -1) {
                //从指定每一份文件的范围，写入不同的文件
                out.write(b, 0, n);
            }
            //定义当前读取文件的指针
            endPointer = in.getFilePointer();
            //关闭输入流
            in.close();
            //关闭输出流
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return endPointer;
    }

    /**
     * 文件合并
     * @param srcPath 源目录
     * @param desPath 目标目录
     * @param mergeFile 合并后的文件
     */
    public static void merge(String srcPath, String desPath, String mergeFile) {
        RandomAccessFile raf = null;
        try {
            File desFile = new File(desPath);
            if (!desFile.exists())   desFile.mkdirs();

            File srcFile = new File(srcPath);
            File[] srcFiles = srcFile.listFiles();
            if (srcFiles == null || srcFiles.length <= 0) {
                return;
            }

            //申明随机读取文件RandomAccessFile
            raf = new RandomAccessFile(new File(desPath + mergeFile), "rw");
            //开始合并文件，对应切片的二进制文件
            for (File file : srcFiles) {
                //读取切片文件
                RandomAccessFile reader = new RandomAccessFile(file, "r");
                byte[] b = new byte[1024];
                int n;
                while ((n = reader.read(b)) != -1) {
                    raf.write(b, 0, n);//一边读，一边写
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 文件合并
     * @param srcPath 源目录
     * @param srcFileNames 文件集合
     * @param desPath 目标目录
     * @param mergeFile 合并后的文件
     */
    public static void merge(String srcPath, String[] srcFileNames, String desPath, String mergeFile) {
        RandomAccessFile raf = null;
        try {
            //申明随机读取文件RandomAccessFile
            raf = new RandomAccessFile(new File(desPath + mergeFile), "rw");
            //开始合并文件，对应切片的二进制文件
            for (String srcFileName : srcFileNames) {
                //读取切片文件
                RandomAccessFile reader = new RandomAccessFile(srcPath + srcFileName, "r");
                byte[] b = new byte[1024];
                int n;
                while ((n = reader.read(b)) != -1) {
                    raf.write(b, 0, n);//一边读，一边写
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 文件合并
     * @param mergeFilePath 指定合并文件
     * @param tempFile  分割前的文件名
     * @param tempCount 文件个数
     */
    public static void merge(String mergeFilePath, String tempFile, int tempCount) {
        RandomAccessFile raf = null;
        try {
            //申明随机读取文件RandomAccessFile
            raf = new RandomAccessFile(new File(mergeFilePath), "rw");
            //开始合并文件，对应切片的二进制文件
            for (int i = 0; i < tempCount; i++) {
                //读取切片文件
                RandomAccessFile reader = new RandomAccessFile(new File(tempFile + "_" + i + ".tmp"), "r");
                byte[] b = new byte[1024];
                int n = 0;
                while ((n = reader.read(b)) != -1) {
                    raf.write(b, 0, n);//一边读，一边写
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
