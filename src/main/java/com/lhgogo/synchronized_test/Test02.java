package com.lhgogo.synchronized_test;


import java.util.logging.Logger;

/**
 * @author ：linhui
 * @description ：测试可见性
 * @date ：2023-03-01 16:51
 * @version:
 */


public class Test02 {
    private static Logger logger = Logger.getLogger("cccc");
    public static boolean sign = false;
    public static void main(String[] args) {
        Thread Thread01 = new Thread(() -> {
            int i = 0;
            while (!sign) {
                i++;
                add(i);
            }
        });
        Thread Thread02 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignore) {
            }
            sign = true;
            logger.info("vt.sign = true  while (!sign)");
        });
        Thread01.start();
        Thread02.start();
    }

    /*
    * 为什么synchronized也可以保证可见性
    * 1. 线程解锁前，会将共享变量的最新值数据刷新到主内存
    * 2. 线程加锁前，将会清空共享变量的值，然后去主内存读取最新的值
    * 3. volatile是用过内存屏障实现的
    * 4. synchronized是通过系统内核互斥锁实现的，相当于JMM模型中的lock和unlock
    *
    * */
    public static synchronized int add(int i) {
        return i + 1;
    }
}
