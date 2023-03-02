package com.lhgogo.reentrantLock_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author ：linhui
 * @description ：
 * @date ：2023-03-02 10:16
 * @version:
 */

public class CLHLock implements Lock {

    private final ThreadLocal<CLHLock.Node> prev;
    private final ThreadLocal<CLHLock.Node> node;
    private final AtomicReference<Node> tail = new AtomicReference<>(new CLHLock.Node());



    private static class Node{
        private volatile Boolean locked;
    }

    public CLHLock(){
        this.prev = ThreadLocal.withInitial(() -> null);
//        this.node = ThreadLocal.withInitial(() -> new CLHLock.Node());
        this.node = ThreadLocal.withInitial(CLHLock.Node::new);
    }

    @Override
    public void lock() {
        final Node node = this.node.get();
        node.locked = true;
        Node predNode = this.tail.getAndSet(node);
        this.prev.set(predNode);
        // 自旋
        while (predNode.locked);

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        Node node = this.node.get();
        node.locked = false;
        this.node.set(this.prev.get());
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        CLHLock clhLock = new CLHLock();
        clhLock.lock();
        clhLock.unlock();
    }
}
