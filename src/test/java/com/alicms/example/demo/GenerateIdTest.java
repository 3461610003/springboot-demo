package com.alicms.example.demo;

import com.alicms.example.demo.dao.LevelOrderDao;
import com.alicms.example.demo.dao.UserDao;
import com.alicms.example.demo.model.LevelOrder;
import com.alicms.example.demo.model.User;
import com.alicms.example.demo.utils.NameGenerate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GenerateIdTest {

    @Test
    public void contextLoads() {
        for (int i = 0; i < 20; i++) {
            System.out.println(genUniqueKey());
        }
        /*System.out.println("断言前=======");
        Assert.assertNotEquals(1, 2);
        System.out.println("断言后=======");*/
    }


    @Resource
    private UserDao userDao;



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

    static Set<Long> phoneSet = new HashSet<>();

    @Test   // 生成用户数据
    public void generateUserData() throws InterruptedException {

       /* List<User> list = userDao.findAll();
        System.out.println(list);*/
        Random random = new Random();
        List<User> list = new ArrayList<>();
        for (int i = 5000000; i < 10000001; i++) {
            String areaCode = random.nextInt(900000) + 100000 + "";
            String phone = "1" + (random.nextInt(90) + 10) + (random.nextInt(90000000) + 10000000);
            while (!phoneSet.add(Long.parseLong(phone))) {
                phone = "1" + (random.nextInt(90) + 10) + (random.nextInt(90000000) + 10000000);
            }
            String str = "ABCDEFGHJKMNPQRSTUVWXYZ123456789";
            int num = random.nextInt(6) + 6;
            StringBuilder sb = new StringBuilder(num);
            for (int t = 0; t < num; t++) {
                char ch = str.charAt(new SecureRandom().nextInt(str.length()));
                sb.append(ch);
            }
            int nextInt = random.nextInt(3);
            String tt = nextInt == 0 ? "qq" : (nextInt == 1 ? "163" : "139");
            String email = sb.toString().toLowerCase() + "@" + tt + ".com";
            String password = NameGenerate.get10Random();
            String salt = NameGenerate.get5Random();
            String transactionPassword = NameGenerate.get10Random();
            int isRemember = (nextInt + 1) % 2;
            String nickname = NameGenerate.randomName(true, 4);
            String image = "http://" + (email.substring(2, 5)) + ".gif";
            String trueName = NameGenerate.getRandomJianHan(3);
            String nationality = nextInt == 0 ? "中国" : (nextInt == 1 ? "韩国" : "美国");
            Integer idCardType = nextInt % 2;
            String idCard = NameGenerate.getIdNo(nextInt == 1);
            Integer idCardAuth = i % 97 == 0 ? 1 : 0;
            String idCardImg1 = "http://" + NameGenerate.get5Random() + ".gif";
            ;
            String idCardImg2 = "http://" + NameGenerate.get5Random() + ".gif";
            ;
            String idCardImg3 = "http://" + NameGenerate.get5Random() + ".gif";
            ;
            // 辅助货币 1=usd 2=cny
            Integer assistantCurrency = random.nextInt(2);
            Integer exchangeFlag = random.nextInt(2);
            String language = random.nextInt(100) % 10 == 0 ? "en" : "cn";
            Integer bankCardFlag = random.nextInt(2);
            String inviteCode = NameGenerate.get5Random().toUpperCase();
            Long inviter1 = 1000000L + random.nextInt(i + 2);
            Long inviter2 = 1000000L + random.nextInt(i + 2);
            Integer status = random.nextInt(100) % 28 != 0 ? 1 : 0;
            String loginIp = NameGenerate.getRandomIp();
            Integer isBot = random.nextInt(3);
            Integer isDeleted = random.nextInt(100) % 28 == 0 ? 1 : 0;
            Long creator = 1000000L + random.nextInt(i + 2);
            Long updater = 1000000L + random.nextInt(i + 2);

            User user = new User(
                    null, areaCode, phone, email,
                    password, salt, transactionPassword, isRemember, nickname, image,
                    trueName, nationality,
                    idCardType, idCard, idCardAuth, idCardImg1, idCardImg2, idCardImg3,
                    assistantCurrency, exchangeFlag, language, bankCardFlag, inviteCode, inviter1,
                    inviter2, status, loginIp, isBot, isDeleted, creator, updater);
            list.add(user);
            if (i % 50 == 0) {
                try {
                    List<User> resultList = userDao.saveAll(list);
                    System.out.println("********i=" + i + " ,size=" + resultList.size());
                } catch (Exception e) {
                    System.out.println("########异常i=" + i);
                }
                list.clear();
                Thread.sleep(10);
            }
        }
    }

    @Resource
    private LevelOrderDao levelOrderDao;

    @Test // 生成订单数据
    public void generateLevelOrderData() {
        Random random = new Random();
        List<LevelOrder> list = new ArrayList<>();
        for (int i = 5; i < 10000000; i++) {
            Long userId = 1000000L + random.nextInt(i);
            Integer grade = random.nextInt(10) + 1;
            // 状态：0=申请，1=同意，2=驳回
            int nextInt = random.nextInt(100);
            Integer status = nextInt % 17 == 0 ? 1 : (nextInt % 37 == 0 ? 2 : 0);
            Long creator = 1000000L + random.nextInt(i);
            Long updater = 1000000L + random.nextInt(i);

            LevelOrder levelOrder = new LevelOrder(
                    userId, grade, status,
                    creator, updater);
            list.add(levelOrder);
            if (i % 97 == 0) {
                try {
                    List<LevelOrder> resultList = levelOrderDao.saveAll(list);
                    levelOrderDao.flush();
                    log.info("***i={}, size={}", i, resultList.size());
                    Thread.sleep(20);
                } catch (Exception e) {
                    log.warn("添加异常。。。。。。。i={}", i);
                }
                list.clear();
            }
        }
    }


    /**
     * 生成唯一主键
     * 格式：时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
