package com.lhgogo.lock_sys;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-02-23 15:47
 * @version:
 */

public class DeadLockDemo {
    static final ReentrantLock lock1 = new ReentrantLock();
    static final ReentrantLock lock2 = new ReentrantLock();
    public static void main(String[] args) {
//        new Thread(() -> {
//           lock1.lock();
//           try {
//               System.out.println("我的名字\t"+Thread.currentThread().getName());
//           } finally {
//               lock2.unlock();
//           }
//        },"t1").start();
//
//        new Thread(() -> {
//            lock2.lock();
//            try {
//                System.out.println("我的名字\t"+Thread.currentThread().getName());
//            } finally {
//                lock1.unlock();
//            }
//        },"t2").start();
//        System.out.println("我的名字\t"+Thread.currentThread().getName());
        Object o1 = new Object();
        Object o2 = new Object();

        new Thread(() -> {
            synchronized (o1){
                System.out.println("已经获得o1锁，尝试获得o2锁");
                try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace();}
                synchronized (o2){
                    System.out.println("获得o1锁,成功获得o2锁");
                }
            }
        },"A").start();

        new Thread(() -> {
            synchronized (o2){
                System.out.println("已经获得o2锁，尝试获得o1锁");
                try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace();}
                synchronized (o1){
                    System.out.println("获得o2锁,成功获得o1锁");
                }
            }
        },"B").start();
    }
}
