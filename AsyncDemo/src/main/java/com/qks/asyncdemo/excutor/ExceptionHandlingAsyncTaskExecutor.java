package com.qks.asyncdemo.excutor;

import org.springframework.core.task.AsyncTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-03 15:57
 */
public class ExceptionHandlingAsyncTaskExecutor implements AsyncTaskExecutor {

    private AsyncTaskExecutor executor;

    public ExceptionHandlingAsyncTaskExecutor(AsyncTaskExecutor executor) {
        this.executor = executor;
    }

    /**
     * 用独立的线程来包装，@Async其本质就是如此
     * 
     * @param task the {@code Runnable} to execute (never {@code null})
     */
    @Override
    public void execute(Runnable task) {
        executor.execute(createWrappedRunnable(task));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        executor.execute(createWrappedRunnable(task), startTimeout);
    }

    @Override
    public Future submit(Runnable task) {
        return executor.submit(createWrappedRunnable(task));
    }

    @Override
    public Future submit(final Callable task) {
        // 用独立的线程来包装，@Async其本质就是如此。
        return executor.submit(createCallable(task));
    }

    // @Override
    private Callable createCallable(final Callable task) {
        return () -> {
            try {
                return task.call();
            } catch (Exception ex) {
                handle(ex);
                throw ex;
            }
        };
    }

    private Runnable createWrappedRunnable(final Runnable task) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    task.run();
                } catch (Exception ex) {
                    handle(ex);
                }
            }
        };
    }

    private void handle(Exception ex) {
        // 具体的异常逻辑处理的地方
        System.out.println("Error during @Async execution: " + ex);
    }
}
