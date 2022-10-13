package com.qks.jdkcracter.jdk8.streamdemo;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName Dessert
 * @Description java 新增了 java.util.stream 包，它和之前的流大同小异。
 * <p>Stream不存储数据，不同的是它可以检索(Retrieve)和逻辑处理集合数据、包括筛选、排序、统计、计数等。可以想象成是 Sql 语句。</p>
 * <p></>它的源数据可以是 Collection、Array 等。由于它的方法参数都是函数式接口类型，所以一般和 Lambda 配合使用。</p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-18 21:22
 */
public class SteamDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        Stream<String> stream = strings.stream();

        //返回符合条件的stream
        Stream<String> stringStream = stream.filter("abc"::equals);
        //计算流符合条件的流的数量
        long count = stringStream.count();
        System.out.println(count);

        //forEach遍历->打印元素
        strings.stream().forEach(System.out::println);

        //limit 获取到1个元素的stream
        Stream<String> limit = strings.stream().limit(1);
        //toArray 比如我们想看这个limitStream里面是什么，比如转换成String[],比如循环
        String[] array = limit.toArray(String[]::new);
        System.out.println(Arrays.toString(array));

        //map 对每个元素进行操作返回新流
        Stream<String> map = strings.stream().map(s -> s + "22");
        System.out.println(Arrays.toString(map.toArray(String[]::new)));

        //sorted 排序并打印
        strings.stream().sorted().forEach(System.out::println);

        //Collectors collect 把abc放入容器中
        List<String> list = strings.stream().filter("abc"::equals).toList();
        System.out.println(list);

        //把list转为string，各元素用，号隔开
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println(mergedString);

        //对数组的统计，比如用
        List<Integer> number = Arrays.asList(1, 2, 5, 4);
        IntSummaryStatistics statistics = number.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("列表中最大的数 : "+statistics.getMax());
        System.out.println("列表中最小的数 : "+statistics.getMin());
        System.out.println("平均数 : "+statistics.getAverage());
        System.out.println("所有数之和 : "+statistics.getSum());

        //concat 合并流
        List<String> strings2 = Arrays.asList("xyz", "jqx");
        Stream.concat(strings2.stream(), strings.stream()).count();

        //注意 一个Stream只能操作一次，不能断开，否则会报错。
        try {
            //第一次使用
            strings.stream().limit(2);
            //第二次使用
            strings.stream().forEach(System.out::println);
            //报错 java.lang.IllegalStateException: stream has already been operated upon or closed
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }

        //但是可以这样, 连续使用
        strings.stream().limit(2).forEach(System.out::println);
    }
}
