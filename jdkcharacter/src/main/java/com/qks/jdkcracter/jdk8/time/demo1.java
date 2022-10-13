package com.qks.jdkcracter.jdk8.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @ClassName Dessert
 * @Description Date-Time API
 * <p>这是对java.util.Date强有力的补充，解决了 Date 类的大部分痛点：</p>
 * <p>1.非线程安全</p>
 * <p>2.时区处理麻烦</p>
 * <p>3.各种格式化、和时间计算繁琐</p>
 * <p>4.设计有缺陷，Date 类同时包含日期和时间；还有一个 java.sql.Date，容易混淆。</p>
 * <p>LocalDateTime.class //日期+时间 format: yyyy-MM-ddTHH:mm:ss.SSS</p>
 * <p>LocalDate.class //日期 format: yyyy-MM-dd</p>
 * <p>LocalTime.class //时间 format: HH:mm:ss</p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-20 12:23
 */
public class demo1 {
    public static void main(String[] args) {
        oldFormat();
        newFormat();
    }

    /**
     * JDK8之前操作Date的方法
     */
    public static void oldFormat() {
        // format yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = sdf.format(new Date());
        System.out.printf("data format: %s%n", data);

        // format yyyy-MM-dd
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String data2 = sdf2.format(new Date());
        System.out.printf("data format: %s%n", data2);

        // format HH:mm:ss
        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
        String data3 = sdf3.format(new Date());
        System.out.printf("data format: %s%n", data3);
    }

    /**
     * JDK8（含）之后操作Date的方法
     */
    public static void newFormat() {
        // format yyyy-MM-dd
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate: " + localDate);

        // format HH:mm:ss
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime: " + localTime);

        // format yyyy-MM-dd HH:mm:ss
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime: " + localDateTime);
    }
}
