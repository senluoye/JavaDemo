package com.qks.jdkcracter.jdk8.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @ClassName Dessert
 * @Description 字符串转日期，在JDK8之后只需要调用of方法或者parse方法即可
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-20 12:59
 */
public class demo2 {
    public static void main(String[] args) throws ParseException {
        oldAdapter();
        newAdapter();
    }

    public static void oldAdapter() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2021-01-26");
        System.out.println("old_date: " + date1);
    }

    public static void newAdapter() {
        LocalDate date = LocalDate.of(2021, 1, 26);
        System.out.println("date: " + date);
        LocalDate data1 = LocalDate.parse("2021-01-26");
        System.out.println("data1: " + data1);

        LocalDateTime dateTime = LocalDateTime.of(2021, 1, 26, 12, 12, 22);
        System.out.println("dateTime: " + dateTime);
        LocalDateTime localDateTime = LocalDateTime.parse("2021-01-26T12:12:22");
        System.out.println("localDateTime: " + localDateTime);

        LocalTime time = LocalTime.of(12, 12, 22);
        System.out.println("time: " + time);
        LocalTime localTime = LocalTime.parse("12:12:22");
        System.out.println("localTime: " + localTime);
    }
}
