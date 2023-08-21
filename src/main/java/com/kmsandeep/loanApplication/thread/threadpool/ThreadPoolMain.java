package com.kmsandeep.loanApplication.thread.threadpool;

public class ThreadPoolMain {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(10, 100);
        for (int i = 0; i < 100; i++) {
            int taskNo = i;
            threadPool.execute(() -> {

                System.out.println(Thread.currentThread().getName() + " Task: " + taskNo);
            });
        }
        threadPool.waitUntilAllTaskCompleted();
        threadPool.stop();
    }
}
