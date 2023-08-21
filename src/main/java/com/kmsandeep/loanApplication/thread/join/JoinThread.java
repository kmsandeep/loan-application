package com.kmsandeep.loanApplication.thread.join;

public class JoinThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000); // makes current thread in-active
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Running "+ Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        JoinThread runnable = new JoinThread();
        Thread mythread = new Thread(runnable,"joinRunnable");
        mythread.setDaemon(true);
        mythread.start();
        try {
            System.out.println(Thread.currentThread().getName());
            mythread.join(2000); // main-thread i.e. current thread wait at most 2 seconds for mythread to complete
            mythread.join(); // main-thread i.e. current thread waits until mythread is complete
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
