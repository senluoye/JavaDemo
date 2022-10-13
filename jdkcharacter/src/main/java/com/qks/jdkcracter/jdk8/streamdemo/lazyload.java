package com.qks.jdkcracter.jdk8.streamdemo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @ClassName Dessert
 * @Description 在执行返回 Stream 的方法时，并不立刻执行，而是等返回一个非 Stream 的方法后才执行。
 * 因为拿到 Stream 并不能直接用，而是需要处理成一个常规类型。
 * 这里的 Stream 可以想象成是二进制流（2 个完全不一样的东东），拿到也看不懂
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-19 10:32
 */
public class lazyload {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        Stream<String> stream = list.stream().filter(s -> {
            System.out.println("Predicate执行-->" + s);
            return true;
        });
        System.out.println("stream还未执行");
        long count = stream.count();
        System.out.println("stream执行完成, count=" + count);
    }
}
