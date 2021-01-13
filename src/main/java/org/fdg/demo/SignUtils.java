package org.fdg.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class SignUtils {

    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static DateFormat dateFormat4 = new SimpleDateFormat("yyyy-MM-dd");

    static Random r = new Random();

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getNow() {
        return dateFormat.format(new Date());
    }


    public static String getNowDate() {
        return dateFormat4.format(new Date());
    }

    public static String dateFormat4Day(Date date) {
        return dateFormat4.format(date);
    }

    /**
     * uuid
     * @return
     */
    public static String getUUID() throws UnknownHostException {
        // [充值支付流水ID] 对应商户订单号=毫秒数 + 服务器IP尾号 + 线程ID + 5位随机数
        String ip = r.nextInt(1000) + "";

        String hostAddress = InetAddress.getLocalHost().getHostAddress();

        if (hostAddress != null) {
            String[] split = hostAddress.split("\\.");
            ip = split[split.length - 1];

        }
        return dateFormat2.format(new Date()) + Thread.currentThread().getId() + ip + r.nextInt(100000);
    }
}
