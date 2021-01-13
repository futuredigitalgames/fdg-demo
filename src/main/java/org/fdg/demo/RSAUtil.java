package org.fdg.demo;

import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;

public class RSAUtil {

    public static final String KEY_ALGORITHM = "RSA";
    //加密算法
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static String sign(String secretPrivate, String timestamp) {
        String out = null;
        try {
            out = sign(timestamp.getBytes(), secretPrivate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out;
    }

    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);

        return bytes2Hex(signature.sign());

    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static String bytes2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
