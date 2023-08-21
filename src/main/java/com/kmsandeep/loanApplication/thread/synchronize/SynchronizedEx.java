package com.kmsandeep.loanApplication.thread.synchronize;


import com.kmsandeep.loanApplication.thread.dto.MyObject;

public class SynchronizedEx {

    public static void doSome() {
        synchronized (SynchronizedEx.class) {

        }
    }

    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                myObject.setObject(i);
            }
        }, "T1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
               myObject.getObject();
            }
        }, "T2");
        thread1.start();
        thread2.start();
    }
}
