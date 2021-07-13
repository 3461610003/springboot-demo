package com.alicms.example.demo;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

/**
  * Description:加密工具类
  *
  * @date 2018年4月2日下午6:00:14
*/
public class Encrypt {

	public static String md5(String str){
		if(str==null){
			return null;
		}
		return DigestUtils.md5Hex(str);
	}
	/**
	 * 获取MD5加密后的字符串
	 * @param str 明文
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String phpMD5(String str) throws Exception {
		/** 创建MD5加密对象 */
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		/** 进行加密 */
		md5.update(str.getBytes());
		/** 获取加密后的字节数组 */
		byte[] md5Bytes = md5.digest();
		String res = "";
		for (int i = 0; i < md5Bytes.length; i++) {
			int temp = md5Bytes[i] & 0xFF;
			if (temp <= 0XF) { // 转化成十六进制不够两位，前面加零
				res += "0";
			}
			res += Integer.toHexString(temp);
		}
		return res;
	}
}
