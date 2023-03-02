package com.lhgogo.thread_test;

import java.util.concurrent.*;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-02-21 16:09
 * @version:
 */

public class ThreadPoolDemo {


    public static void main(String[] args) {
        // 最大线程数为Integer.MAX_VALUE
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 阻塞队列的最大长度为Integer.MAX_VALUE
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        // 阻塞队列的最大长度为Integer.MAX_VALUE
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        // 最大线程数为Integer.MAX_VALUE
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        // 底层使用的是java默认的线程池ForkJoinPool
        ExecutorService workStealingPool = Executors.newWorkStealingPool();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, // 默认线程数
                5, //最大线程数
                //当线程池中的线程数大于corePoolSize时，如果当前没有新的任务提交，核心线程外的线程
                //并不会被立即销毁，而是会等待，直到等待的时间超过了keepAliveTime后才会被销毁
                4L,
                TimeUnit.NANOSECONDS, //keepAliveTime时间的单位
                new LinkedBlockingQueue<>(), //阻塞队列，默认使用的是LinkedBlockingQueue
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略 默认的拒绝策略
        );

        threadPoolExecutor.submit(() -> System.out.println("你去死吧"));

        threadPoolExecutor.shutdown();

    }


}
