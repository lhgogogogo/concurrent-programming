package com.lhgogo.thread_test;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-02-21 15:03
 * @version:
 */

public class RunnableDemo {
    public static void main(String[] args) {
     new Thread(() -> {
         System.out.println(Thread.currentThread().getName()+"\t我是线程t1,实现了Runnable");
     },"t1").start();
        System.out.println(Thread.currentThread().getName()+"\t我是主线程我执行了");
    }
}
