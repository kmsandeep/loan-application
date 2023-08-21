package com.kmsandeep.loanApplication.thread.sharedObject;

public class MyRunnable implements Runnable {

    private volatile int count = 0;
    @Override
    public void run() {
        for (int i = 0; i < 100_000; i++) {
            count++;
//            synchronized (this){count++;}
        }
        System.out.println(Thread.currentThread().getName()+" count: " + count);
    }

    public static void main(String[] args) {
        MyRunnable myRunnable1 = new MyRunnable();
        Thread thread1 = new Thread(myRunnable1,"T1");
        Thread thread2 = new Thread(myRunnable1,"T2");
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(myRunnable1.count);
    }
}
