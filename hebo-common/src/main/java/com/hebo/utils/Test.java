package com.hebo.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * @author tech-winning
 * @version 1.0
 * @description: TODO
 * @date 2022/6/21 9:37
 */
public class Test {


    public static void main(String[] args) {
        long timeSec = getTimeSec();
        System.out.println(timeSec);

        String hsf_timestamp = String.valueOf(timeSec);
        String infosyscode = "5e06667d126c4077aa5f369fa17623f8";
        String hsf_noce = "kqrccaxdqzqdynoerzexdfyado";

        System.out.println(getSha256Str(hsf_timestamp + infosyscode + hsf_noce));

    }

    private static long getTimeSec() {

        LocalDate localDate= LocalDate.now();

        LocalTime localTime= LocalTime.now();

        return LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), localTime.getHour(), localTime.getMinute(), localTime.getSecond()).atZone(ZoneId.systemDefault()).toEpochSecond();

    }




    /**
     * sha256加密工具类
     *
     * @author liHu
     * @version 1.0
     * @date 2021/8/3 10:48
     * @since JDK 1.8
     */

        /**
         * sha256加密
         *
         * @param str 要加密的字符串
         * @return 加密后的字符串
         */
        public static String getSha256Str(String str) {
            MessageDigest messageDigest;
            String encodeStr = "";
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
                encodeStr = byte2Hex(messageDigest.digest());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return encodeStr;
        }

        /**
         * sha256加密 将byte转为16进制
         *
         * @param bytes 字节码
         * @return 加密后的字符串
         */
        private static String byte2Hex(byte[] bytes) {
            StringBuilder stringBuilder = new StringBuilder();
            String temp;
            for (byte aByte : bytes) {
                temp = Integer.toHexString(aByte & 0xFF);
                if (temp.length() == 1) {
                    //1得到一位的进行补0操作
                    stringBuilder.append("0");
                }
                stringBuilder.append(temp);
            }
            return stringBuilder.toString();
        }
}
