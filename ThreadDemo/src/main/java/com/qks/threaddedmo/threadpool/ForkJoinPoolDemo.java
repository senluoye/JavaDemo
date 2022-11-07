package com.qks.threaddedmo.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.Serial;
import java.util.concurrent.*;

/**
 * @ClassName ForkJoinPoolDemo
 * @Description ForkJoinPool 是 JDK1.7 开始提供的线程池。为了解决 CPU 负载不均衡的问题。
 * 如某个较大的任务，被一个线程去执行，而其他线程处于空闲状态。
 * <p>ForkJoinTask 表示一个任务，ForkJoinTask 的子类中有 RecursiveAction 和 RecursiveTask。
 * RecursiveAction 无返回结果；RecursiveTask 有返回结果。</p>
 * <p>重写 RecursiveAction 或 RecursiveTask 的 compute()，完成计算或者可以进行任务拆分</p>
 * <p>调用 ForkJoinTask 的 fork() 的方法，可以让其他空闲的线程执行这个 ForkJoinTask；
 * 调用 ForkJoinTask 的 join() 的方法，将多个小任务的结果进行汇总。</p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-24 16:22
 */
public class ForkJoinPoolDemo {

    @Test
    public void test1() throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new PrintTask(1, 200));
        pool.awaitTermination(2, TimeUnit.SECONDS);
        pool.shutdown();
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = pool.submit(new CalculateTask(1, 200));
        int result = task.get();
        System.out.println("并行计算1-200的值：" + result);
        pool.awaitTermination(2, TimeUnit.SECONDS);
        pool.shutdown();
    }
}

/**
 * 无返回值的打印任务
 */
@Slf4j
class PrintTask extends RecursiveAction {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final int THRESHOLD = 49;

    private final int start;

    private final int end;

    public PrintTask(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }

    /**
     * 当 结束值 比 初始值 大于 49 时，按数值区间平均拆分为两个任务，否则直接打印该区间的值
     */
    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i <= end; i++) {
                log.info("{}, i = {}", Thread.currentThread().getName(), i);
            }
        } else {
            int mid = (end + start) / 2;
            PrintTask firstTask = new PrintTask(start, mid);
            PrintTask secondTask = new PrintTask(mid + 1, end);
            firstTask.fork();
            secondTask.fork();
        }
    }
}

/**
 * 有返回值的计算任务
 */
class CalculateTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;
    private static final int THRESHOLD = 49;
    private int start;
    private int end;

    public CalculateTask(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        /**
         * 当 结束值 比 起始值 大于 49 时，按数值区间平均拆分为两个任务，进行两个任务的累加值汇总；否则直接计算累加值
         */
        if (end - start <= THRESHOLD) {
            int result = 0;
            for (int i = start; i <= end; i++) {
                result += i;
            }
            return result;
        } else {
            int middle = (start + end) / 2;
            CalculateTask firstTask = new CalculateTask(start, middle);
            CalculateTask secondTask = new CalculateTask(middle + 1, end);
            firstTask.fork();
            secondTask.fork();
            return firstTask.join() + secondTask.join();
        }
    }
}