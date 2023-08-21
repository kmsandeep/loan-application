package com.kmsandeep.loanApplication.thread.dto;

public class ShareMonitorEx {
    public static void main(String[] args) {
        Object monitor1 = new Object();
        Object monitor2 = new Object();
        MonitorObject object1 = new MonitorObject(monitor1);
        MonitorObject object2 = new MonitorObject(monitor2);
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                object1.printHello();
            }
        }, "T1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                object1.printHello();
            }
        }, "T2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                object2.printNamaste();
            }
        }, "T3").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                object2.printNamaste();
            }
        }, "T4").start();
    }
}
