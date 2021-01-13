package org.fdg.demo;



import org.apache.http.client.HttpClient;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class TestSign {


//     public static void main(String [] args) throws Exception {
//         long timestamp = System.currentTimeMillis();
//         String paymentId = SignUtils.getUUID();
//         String sign = getSign("15350503506", "86","81241.32" , timestamp+"" , paymentId);
//         String remitUrl =  "https://www.tacu.com/unique/tacuRemit/transIn?uid=737779&currencyId=238&amount=81241.32&mobile=15350503506" +
//                 "&mobileCode=86&idCard=132127198109261128&wdcCode=86&wdcMobile=15350503506&time=" + timestamp + "&orderId=" + paymentId + "&sign=" + sign;
//         System.out.println(remitUrl);
//         String data=HttpClientTool.doPost(remitUrl,"");
//         System.out.println(data);
//     }
//
//    public static String getSign(String mobile, String areaCode, String amount,   String timeStamp, String orderId) throws Exception {
//        String salt = "zhemekuaishangxian";
//        String s = MD5.md5(areaCode + mobile + amount + timeStamp + salt);
//        return s;
//    }

    public static void main(String[] args) {
        // 访问url
        String url = "http://54.95.63.91:20001/unique/user/signLogin";
        // 登陆账号
        String email = "18012345678";
        // 登陆密码  详见MD5Util工具类
        String pwd = MD5Util.encryptPwd("123qweasd");
        // 时间戳  获取13位毫秒级时间戳
        String timestamp = System.currentTimeMillis()+"";
        // 私钥  建议用pkcs8
        String secretPrivate = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKW5+BCWlN3culqCaPmltmplooADFbJs25jxVZC9gQvjIkxFyI76AvvbVFCea0tQwJq2cFI8jbPawG/WUPEpDWohaetWR72HMO8sGNd2pJ+e4sWSMIIsEEBW9YtM9JTF15N+zWmSZQ3nAUe52qW3FbVQT33/Q1nzA0Vh330JDGNvAgMBAAECgYBy86WfS/OCWKJiOWa7EziOYGTwyjL4ig1f7rXdKbUjG68knJraoYwESkQXj2r42NBf//+06w+XGzqz2EJYpJMKVk5Q/hsOidcp+ldfLsw2JcGhEO6OS9xCrI4/zBgEhHlbq6Zj2poAZBD8Mr8laBY2iBx13q8KTjiuQX/aZ6TfAQJBANsJn/6jDWYGgc+yYaXb01VKTSiVGagug4LYa/o1H0vKc22j9iNw788Hu169VlGkEtTXMEBPsQJ2UGZMxNofvEECQQDBsVBlqmfzRST22BjFEBaf2Zm5yqCxgXIr/q/2bMiyhn7/l1B9U3/hQC2OOBTvALfboQrnakcctTVwz4HXafOvAkEAzz8PJEawsb0sbBFy9RTGo+eWk/Rnf0rrYg1WNTq4LuUSTEYb6TK9pFzDe4OYPc0JiFtHK0J70EGUFwdCJsRqQQJALuyLAqY+2rOkmdyOW6djI8SutlD9jyNCCqB/9p4yHPXybG83A7Wn5GUM5Eh34dL1t9KS9q9LnQvVb4gF967mZwJAS9PFtlCYehxQYkMQnZXcHc+vfmUgr4Ns5Wp26LPis947kWiLk1lIaA5r8vJ9234eOXUd/lrQi/Il6JeodZQ09g==";
        // sign签名  详见RSAUtil工具类
        String sign =RSAUtil.sign(secretPrivate, timestamp);

        //以下是调用方式,可以用自己习惯的方式。  demo选择的方式是HttpClient,不做详细说明。
        Map<String, String> params = new HashMap<String,String>();
        params.put("email", email);
        params.put("pwd", pwd);
        params.put("timestamp", timestamp);
        params.put("sign", sign);
        System.out.println(params);

     String data=   HttpClientTool.sendPostByForm(url,params,1);
        System.out.println(data);
    }
}
