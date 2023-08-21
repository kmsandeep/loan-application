package com.kmsandeep.loanApplication.thread.blockingQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class MyBlockingQueue<E> {
    private int size;
    private Queue<E> queue;

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public MyBlockingQueue(int size) {
        this.size = size;
        this.queue = new LinkedList<>();
    }

    public void add(E element) {
        try {
            lock.lock();
            while (queue.size() == this.size)
                notFull.await();
            queue.add(element);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public E remove() {
        try {
            lock.lock();
            while (queue.size() == 0)
                notEmpty.await();
            E element = queue.remove();
            notFull.signalAll();
            return element;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}
