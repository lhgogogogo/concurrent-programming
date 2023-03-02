package com.lhgogo.lock_sys;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-02-23 16:25
 * @version:
 */

public class InterruptDemo02 {
    private static volatile boolean isStop = false;
    private static AtomicInteger number = new AtomicInteger(1);

    public static void main(String[] args) {
//        use_volatile();
//        useAtomicInteger();
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "线程interrupted == true,自己退出");
                    break;
                }
                System.out.println("-------hello interrupted--------");
            }
        }, "A");
        thread.start();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
        thread.interrupt();
        System.out.println("A是否被中断：" + thread.isInterrupted());
    }

    private static void useAtomicInteger() {
        new Thread(() -> {
            while (true){
                if(number.get() == 2){
                    System.out.println(Thread.currentThread().getName() + "线程number = 2,自己退出");
                    break;
                }
                System.out.println("-------hello AtomicInteger--------");
            }
        },"A").start();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
        number.set(2);
    }

    private static void use_volatile() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "线程isStop = true,自己退出");
                    break;
                }
                System.out.println("-------hello interrupt--------");
            }
        }, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isStop = true;
    }
}
