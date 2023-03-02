package com.lhgogo.thread_test;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-02-21 14:42
 * @version:
 */

public class JoinDemo01 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("test");
        });
        thread.start();
        System.out.println("main1");
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main2");

    }
}
