# 线程池

## 概述

线程池的创建方式可以分成 2 种，分别为
- 通过 Executor 创建
- 通过 ThreadPoolExecutor 创建

线程池的种类主要有 7 种，分别为：
- Executors.newFixedThreadPool：创建一个固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待；
- Executors.newCachedThreadPool：创建一个可缓存的线程池，若线程数超过处理所需，缓存一段时间后会回收，若线程数不够，则新建线程；
- Executors.newSingleThreadExecutor：创建单个线程数的线程池，它可以保证先进先出的执行顺序；
- Executors.newScheduledThreadPool：创建一个可以执行延迟任务的线程池；
- Executors.newSingleThreadScheduledExecutor：创建一个单线程的可以执行延迟任务的线程池；
- Executors.newWorkStealingPool：创建一个抢占式执行的线程池（任务执行顺序不确定）【JDK 1.8 添加】。
- ThreadPoolExecutor：最原始的创建线程池的方式，它包含了 7 个参数可供设置

ExecutorService 是一个接口，描述了线程的异步执行，它用于实现线程池

## 关于线程池的结束

这里有一篇[博客](https://blog.csdn.net/alinshen/article/details/78090043)讲的挺好。

当需要结束线程池时，可以调用 shutdown 方法，通知线程池执行完所有任务之后需要结束。
可以通过 awaitTermination 方法设置等待时间。当该方法执行之后线程池还没有结束，可以调用 shutdownNow 方法直接主动结束线程池。

## 关于线程池的七个参数

参数 1：corePoolSize
核心线程数，线程池中始终存活的线程数。

参数 2：maximumPoolSize
最大线程数，线程池中允许的最大线程数，当线程池的任务队列满了之后可以创建的最大线程数。

参数 3：keepAliveTime
最大线程数可以存活的时间，当线程中没有任务执行时，最大线程就会销毁一部分，最终保持核心线程数量的线程。

参数 4：unit
单位是和参数 3 存活时间配合使用的，合在一起用于设定线程的存活时间 ，参数 keepAliveTime 的时间单位有以下 7 种可选：

- TimeUnit.DAYS：天
- TimeUnit.HOURS：小时
- TimeUnit.MINUTES：分
- TimeUnit.SECONDS：秒
- TimeUnit.MILLISECONDS：毫秒
- TimeUnit.MICROSECONDS：微妙
- TimeUnit.NANOSECONDS：纳秒

参数 5：workQueue
一个阻塞队列，用来存储线程池等待执行的任务，均为线程安全，它包含以下 7 种类型：

- ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列。
- LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列。
- SynchronousQueue：一个不存储元素的阻塞队列，即直接提交给线程不保持它们。
- PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列。
- DelayQueue：一个使用优先级队列实现的无界阻塞队列，只有在延迟期满时才能从中提取元素。
- LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。与SynchronousQueue类似，还含有非阻塞方法。
- LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
- 较常用的是 LinkedBlockingQueue 和 Synchronous，线程池的排队策略与 BlockingQueue 有关。

参数 6：threadFactory
线程工厂，主要用来创建线程，默认为正常优先级、非守护线程。

参数 7：handler
拒绝策略，拒绝处理任务时的策略，系统提供了 4 种可选：

- AbortPolicy：拒绝并抛出异常。
- CallerRunsPolicy：使用当前调用的线程来执行此任务。
- DiscardOldestPolicy：抛弃队列头部（最旧）的一个任务，并执行当前任务。
- DiscardPolicy：忽略并抛弃当前任务。

默认策略为 AbortPolicy。

## 线程池的 5 种状态

1. RUNNING：线程池一旦被创建，就处于 RUNNING 状态，任务数为 0，能够接收新任务，对已排队的任务进行处理。

2. SHUTDOWN：不接收新任务，但能处理已排队的任务。调用线程池的 shutdown() 方法，线程池由 RUNNING 转变为 SHUTDOWN 状态。

3. STOP：不接收新任务，不处理已排队的任务，并且会中断正在处理的任务。调用线程池的 shutdownNow() 方法，线程池由(RUNNING 或 SHUTDOWN ) 转变为 STOP 状态。

4. TIDYING：SHUTDOWN 状态下，任务数为 0， 其他所有任务已终止，线程池会变为 TIDYING 状态，会执行 terminated() 方法。线程池中的 terminated() 方法是空实现，可以重写该方法进行相应的处理。
线程池在 SHUTDOWN 状态，任务队列为空且执行中任务为空，线程池就会由 SHUTDOWN 转变为 TIDYING 状态。
线程池在 STOP 状态，线程池中执行中任务为空时，就会由 STOP 转变为 TIDYING 状态。

5. TERMINATED：线程池彻底终止。线程池在 TIDYING 状态执行完 terminated() 方法就会由 TIDYING 转变为 TERMINATED 状态。