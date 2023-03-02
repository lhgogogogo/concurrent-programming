package com.lhgogo.thread_test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-02-21 15:08
 * @version:
 */

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(new C1());
        new Thread(futureTask).start();
        Object o = futureTask.get();
        System.out.println(o);
        System.out.println(futureTask.isDone());
        System.out.println(futureTask.isCancelled());
    }

    static class C1 implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName()+"\t当前线程的名称");
            return "我实现了callable";
        }
    }
}

