package com.lhgogo.lock_sys;

import java.util.concurrent.TimeUnit;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-02-23 9:05
 * @version:
 */

public class SynchronizedDemo01 {

     static Object object = new Object();
//    public static synchronized void method01(){
//        System.out.println(Thread.currentThread().getName()+"\t"+"静态同步方法");
//    }

    public static void method02(){
        synchronized (object){
            System.out.println(Thread.currentThread().getName()+"\t"+"同步代码块");
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"我的名字");
    }

//    public synchronized void method03(){
//        System.out.println(Thread.currentThread().getName()+"\t"+"普通同步方法");
//    }



    public static void main(String[] args) {
        SynchronizedDemo01 synchronizedDemo01 = new SynchronizedDemo01();
//        new Thread(() -> {
////           method01();
//        },"t1").start();
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            method02();
        },"t2").start();
        System.out.println(Thread.currentThread().getName()+"\t"+"这是我的名字");
//        synchronizedDemo01.method03();

    }
}
