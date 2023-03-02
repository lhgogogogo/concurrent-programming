package com.lhgogo.synchronized_test;

/**
 * @author ：linhui
 * @description ：单例模式通过双重检查锁实现
 * @date ：2023-03-01 17:06
 * @version:
 */

public class Singleton {

    private Singleton(){

    }

    /*
    *为什么，synchronized 也有可见性的特点，还需要 volatile 关键字？
    * 因为，synchronized 的有序性，不是 volatile 的防止指令重排序。
    * 那如果不加 volatile 关键字可能导致的结果，就是第一个线程在初始化初始化对象，
    * 设置 instance 指向内存地址时。第二个线程进入时，有指令重排。
    * 在判断 if (instance == null) 时就会有出错的可能，因为这会可能 instance 可能还没有初始化成功
    *
    * */
    private volatile static Singleton instance;

    public Singleton getInstance(){
        if(instance == null){
           synchronized (Singleton.class){
               if(instance == null){
                   return new Singleton();
               }
           }
        }
        return instance;
    }

}
