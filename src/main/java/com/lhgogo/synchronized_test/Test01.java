package com.lhgogo.synchronized_test;

import java.util.concurrent.TimeUnit;

/**
 * @author ：linhui
 * @description ：测试原子性
 * @date ：2023-03-01 16:32
 * @version:
 */

public class Test01 {
    private static volatile int counter = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    countSum();
                }
            }).start();
        }
        try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("计数器的数量为:"+counter);
    }

    /*
    * 未加synchronized 计数器的数量为:9374
    * 加synchronized 计数器的数量为:10000
    * */

    public static synchronized void countSum(){
        counter++;
    }
}
