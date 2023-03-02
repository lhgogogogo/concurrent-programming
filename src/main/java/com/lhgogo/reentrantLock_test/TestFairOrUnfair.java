package com.lhgogo.reentrantLock_test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-03-02 9:59
 * @version:
 */

public class TestFairOrUnfair {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();
        try {

        } finally {
            lock.unlock();
        }
    }
}
