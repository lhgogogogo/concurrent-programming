package com.lhgogo.lock_sys;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-02-23 16:02
 * @version:
 */

public class InterruptDemo01 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
//            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("线程"+Thread.currentThread().getName()+"\t执行了");
            System.out.println(Thread.currentThread().getName() +"\t"+ Thread.interrupted());
            System.out.println(Thread.currentThread().getName() +"\t"+ Thread.interrupted());
            boolean interrupted = Thread.currentThread().isInterrupted();
        }, "A");
        thread.start();
//        try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace();}
        thread.interrupt();
        System.out.println(thread.getName()+"\t"+thread.isInterrupted());
    }
}
