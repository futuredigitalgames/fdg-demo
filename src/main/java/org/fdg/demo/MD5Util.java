package org.fdg.demo;

import java.security.MessageDigest;

public class MD5Util {

    public MD5Util() {
    }

    public static final String encrypt(String s) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var10) {
            return null;
        }
    }

    public static final String encryptPwd(String pwd) {
        String afterFormat = null;
        String afterEncrypt = null;

        try {
            // 盐值
            String salt = "dig?F*ckDang5PaSsWOrd&%(12lian0160630).";
            // pwd+盐值
            afterFormat = pwd + salt;
            // 去进行MD5加密
            afterEncrypt = encrypt(afterFormat);
        } catch (Exception var4) {
        }

        return afterEncrypt;
    }
}
