package com.kmsandeep.loanApplication.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
    private BlockingQueue<Runnable> taskQueue = null;
    private final List<ThreadPoolRunnable> threadPoolRunnable = new ArrayList<>();
    private boolean isStopped = false;

    public ThreadPool(int nThreads, int nTasks) {
        taskQueue = new ArrayBlockingQueue<>(nTasks);
        for (int i = 0; i < nThreads; i++) {
            ThreadPoolRunnable runnable = new ThreadPoolRunnable(taskQueue);
            threadPoolRunnable.add(runnable);
        }
        for (ThreadPoolRunnable runnable: threadPoolRunnable){
            new Thread(runnable).start();
        }
    }

    public synchronized void execute(Runnable task){
        if(isStopped){
            throw new IllegalStateException("ThreadPool is stopped");
        }
        taskQueue.add(task);
    }

    public synchronized void stop(){
        isStopped = true;
        for(ThreadPoolRunnable runnable: threadPoolRunnable){
            runnable.doStop();
        }
    }
    public synchronized void waitUntilAllTaskCompleted(){
        while (taskQueue.size()>0){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
