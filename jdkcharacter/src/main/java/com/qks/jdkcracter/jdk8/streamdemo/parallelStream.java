package com.qks.jdkcracter.jdk8.streamdemo;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Dessert
 * @Description 并行 parallelStream 在使用方法上和串行 Stream 一样。
 * 主要区别是 parallelStream 可多线程执行，基于 ForkJoin 框架实现
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-19 11:41
 */
public class parallelStream {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.parallelStream().forEach(num -> System.out.println(Thread.currentThread().getName() + "-->" + num));
    }
}
