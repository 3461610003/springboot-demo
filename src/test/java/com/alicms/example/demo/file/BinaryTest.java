package com.alicms.example.demo.file;

import org.junit.Test;

/**
 * <p>
 * 二进制测试
 * </p>
 *
 * @author zhenghao
 * @date 2021/7/14 13:58
 */
public class BinaryTest {

    /**
     *  1.原码
     *      将最高位作为符号位（以0代表正，1代表负），其余各位代表数值本身的绝对值（以二进制表示）。
     *      为了简单起见，我们用1个字节来表示一个整数,如:
     *       +7的原码为： 00000111
     *       -7的原码为： 10000111
     *
     *  2.反码
     *      正数:反码=原码；负:符号位不变(为1),其余位取反
     *      如:
     *      +7的反码为：00000111
     *      -7的反码为： 11111000
     *
     *  3.补码
     *      正:原码=反码=补码 负:补码=反码+1
     *      如:
     *      +7的补码为： 00000111
     *      -7的补码为： 11111001
     */
    @Test   // 二进制测试
    public void binarySystemTest() {
        /*int a = 255;
        byte b = (byte) 255;
        int c = -1;
        byte d = (byte) 128;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));
        System.out.println(b);
        System.out.println(Integer.toBinaryString(b));
        System.out.println(c);
        System.out.println(Integer.toBinaryString(c));
        System.out.println(d);
        System.out.println(Integer.toBinaryString(d));

        System.out.println(a == b);
        System.out.println(c == b);*/


        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(-128));
        System.out.println(Integer.toBinaryString(-129));
        System.out.println(Integer.parseInt("111", 2));
        System.out.println(Integer.parseInt("-" + Integer.toBinaryString(- -1), 2));
        System.out.println(Integer.parseInt("-" + Integer.toBinaryString(- -2), 2));
        System.out.println(Integer.parseInt("-" + Integer.toBinaryString(- -128), 2));
        System.out.println(Integer.parseInt("-" + Integer.toBinaryString(- -129), 2));
        int t = 0b11111111111111111111111101111111;
        System.out.println((~t) + 1);
        System.out.println("0000000000000000000000000" + Integer.toBinaryString(127));
        System.out.println(Integer.toBinaryString(-127));
        System.out.println("0000000000000000000000000000000" + Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(-1));
        int x = 0b00000000000000000000000000000001;
        System.out.println((~x) + 1);

        int xx = 0b01111111111111111111111111111111;
        System.out.println((~x) & xx);
        System.out.println(((~x) & xx) + 1);
        /*int xx = 0b01111111111111111111111111111111;
        int x = 0b10000000000000000000000000000000;
        System.out.println(xx);
        System.out.println(x);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);*/

    }
}
