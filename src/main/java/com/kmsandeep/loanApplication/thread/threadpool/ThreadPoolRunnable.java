package com.kmsandeep.loanApplication.thread.threadpool;

import java.util.concurrent.BlockingQueue;

public class ThreadPoolRunnable implements Runnable {
    private BlockingQueue<Runnable> taskQueue = null;
    private boolean isStopped = false;
    private Thread thread = null;

    public ThreadPoolRunnable(BlockingQueue<Runnable> blockingQueue) {
        this.taskQueue = blockingQueue;
    }

    @Override
    public void run() {
        thread = Thread.currentThread();
        while (!isStopped()) {
            try {
                Runnable runnable = taskQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }

    }

    public synchronized void doStop() {
        isStopped = true;
        thread.interrupt();
    }

    public boolean isStopped() {
        return isStopped;
    }
}
