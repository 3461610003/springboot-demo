package com.alicms.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <p>
 * 刷步数接口
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/17 16:38
 */
public class StepNumTest {
    public static void main(String[] args) {
        String accessToken= "D2A6AFB93531605DBE56DC2EEE74C4C9C7B227AD040AB9F11531814553FC1853CD9FF053B7E076ECBD015F9E74719EBBB4A498A63FF97A1464DC09E74E8F34AA17FD1B8256860C89F935AC12C80100035B45BF92669D4EA4939FB7853FEE4CCA.8A5B3D7981000DB4449E60E85CE2735E3124914FFACB72F95DD537A5CFDB7877";
        String userId = "1";
        String account = "";
        String password = "";
        int step = 35212;
        String accessTokenTemp = scanner("请输入accessToken");
        if (isNotBlank(accessTokenTemp)) {
            accessToken = accessTokenTemp;
        }
        String userIdTemp = scanner("请输入userId");
        if (isNotBlank(userIdTemp)) {
            userId = userIdTemp;
        }
        String accountTemp = scanner("请输入账号");
        if (isNotBlank(accountTemp)) {
            account = accountTemp;
        }
        String passwordTemp = scanner("请输入密码");
        if (isNotBlank(passwordTemp)) {
            account = passwordTemp;
        }
        String stepTemp = scanner("请输入步数");
        if (isNotBlank(stepTemp)) {
            step = Integer.parseInt(stepTemp);
        }
        JSONObject updateJson = JSONObject.parseObject(update(accessToken, userId, step));
        if (isSuccess(updateJson)) {
            System.out.println("==============【同步步数成功】");
        } else {
            System.out.println("==============【同步步数失败】\n尝试重新登录并同步。。。。。");
            reUpdate(account, password, step);
        }
    }

    private static boolean isNotBlank(String str) {
        return str != null && str.trim().length() > 0 && !str.trim().equals("0") && !str.trim().equals("n")
                && !str.trim().equals("N") && !str.trim().toLowerCase().equals("no");
    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(tip + "（输入0/n/no表示不输入取默认值）：");
        if (scanner.hasNext()) {
            return scanner.next();
        }
        scanner.close();
        throw new RuntimeException("请输入正确的" + tip + "！");
    }

    private static void reUpdate(String account, String password, int step) {
        JSONObject loginJson = JSONObject.parseObject(login(account, password));
        if (isSuccess(loginJson)) {
            String accessToken = loginJson.getString("accessToken");
            String userId = loginJson.getString("userId");
            System.out.println("==============【登录成功】请记住以下信息，用于同步步数（无需重复登录）====");
            System.out.println("accessToken=" + accessToken + "\nuserId=" + userId);
            System.out.println("==============【登录成功】=============================================");
            JSONObject updateJson = JSONObject.parseObject(update(accessToken, userId, step));
            if (isSuccess(updateJson)) {
                System.out.println("==============【同步步数成功】result=" + updateJson);
                return;
            } else {
                System.out.println("==============【同步步数失败】result=" + updateJson + "\n尝试重新登录并同步。。。。。");
            }
        } else {
            System.out.println("==============【重新登录失败】---------");
        }
        if (isNotBlank(scanner("是否继续重试？0/n/N=否，1/y/Y=是"))) {
            reUpdate(account, password, step);
        }
    }

    private static boolean isSuccess(JSONObject jsonObject) {
        return jsonObject != null && jsonObject.getInteger("code").equals(200);
    }

    /**
     * 更新步数
     * @param accessToken token
     * @param userId 用户id
     * @param step 步数
     * @return 更新结果 如：{"msg":"成功","code":200,"data":{"pedometerRecordHourlyList":[{"distance":"0,0,0,0,0,0,0,17925.00,0,0,18723.00,19206.00,10673.00,11704.00,0,11737.00,0,0,0,0,0,0,0,0","created":"2020-09-18 07:44:23","measurementTime":"2020-09-18 00:00:00","active":0,"step":"0,0,0,0,0,0,0,29876,0,0,31205,32010,32020,35112,0,35212,0,0,0,0,0,0,0,0","id":"1822cd8ababb4174be5a89c68bfab39f","calories":"0,0,0,0,0,0,0,746.00,0,0,780.00,800.00,8005.00,8778.00,0,8803.00,0,0,0,0,0,0,0,0","userId":27231098,"deviceId":"M_NULL","dataSource":2,"updated":1600412486817}]}}
     */
    private static String update(String accessToken, String userId, int step) {
        String updateUrl = "https://sports.lifesense.com/sport_service/sport/sport/uploadMobileStepV2?version=4.5&systemType=2";
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json; charset=utf-8");
        header.put("Cookie", "accessToken=" + accessToken);
        String params = "{\"list\":[{\"DataSource\":2,\"active\":1,\"calories\":\"" + step / 4 + "\"," +
                "\"dataSource\":2,\"deviceId\":\"M_NULL\",\"distance\":" + step / 3 + ",\"exerciseTime\":0,\"isUpload\":0," +
                "\"measurementTime\":\"" + sdf.format(now) + "\",\"priority\":0,\"step\":" + step + "," +
                "\"type\":2,\"updated\":" + now.getTime() + ",\"userId\":" + userId + "}]};";
        System.out.println("============== 【更新步数参数】 updateUrl=" + updateUrl + ", params=" + params + ", header=" + header);
        String result = doPost(updateUrl, params, header);
        System.out.println("============== 【更新步数结果】result=" + result);
        return result;
    }

    /**
     * 登录
     * @param account 账号
     * @param password 密码
     * @return 结果
     */
    private static String login(String account, String password) {
        String loginUrl = "https://sports.lifesense.com/sessions_service/login?systemType=2&version=4.6.7";
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        String params = "{\"appType\":6,\"clientId\":\"88888\",\"loginName\":\"" + account + "\",\"password\":\"" + md5Pass + "\",\"roleType\":0}";
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json; charset=utf-8");
        System.out.println("==============【登录接口参数】loginUrl=" + loginUrl + ", params=" + params + ", header=" + header);
        String result = doPost(loginUrl, params, header);
        System.out.println("==============【登录接口结果】result=" + result);
        return result;
    }

    private static String doPost(String httpUrl, String param, Map<String, String> header) {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
//            connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            if (header != null && header.size() > 0) {
                for (String key : header.keySet()) {
                    connection.setRequestProperty(key, header.get(key));
                }
            }
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
}
