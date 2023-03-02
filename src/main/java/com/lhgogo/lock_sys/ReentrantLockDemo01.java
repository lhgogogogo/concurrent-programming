package com.lhgogo.lock_sys;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-02-23 10:12
 * @version:
 */

public class ReentrantLockDemo01 {
    static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Condition c = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                c.await();
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t" + "我的名字");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                try { TimeUnit.MILLISECONDS.sleep(60); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t" + "我的名字");
                c.signal();
                System.out.println("我准备唤醒你了");
            } finally {
                lock.unlock();
            }
        }, "t2").start();


        System.out.println(Thread.currentThread().getName());
    }
}
