package com.kmsandeep.loanApplication.thread.dto;

import java.time.LocalDateTime;

public class MyObject {
    private Object object = null;

    public synchronized Object getObject() {
        System.out.println(Thread.currentThread().getName() + " enters getObject at: " + LocalDateTime.now().toLocalTime());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " " + this.object);
        System.out.println(Thread.currentThread().getName() + " exits getObject at: " + LocalDateTime.now().toLocalTime());

        return object;
    }

    public synchronized void setObject(Object object) {
        this.object = object;
        System.out.println(Thread.currentThread().getName() + " enters setObject at: " + LocalDateTime.now().toLocalTime());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " " + this.object);
        System.out.println(Thread.currentThread().getName() + " exits setObject at: " + LocalDateTime.now().toLocalTime());

    }
}
