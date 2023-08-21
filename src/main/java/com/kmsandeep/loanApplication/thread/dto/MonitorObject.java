package com.kmsandeep.loanApplication.thread.dto;

import java.time.LocalDateTime;

public class MonitorObject {
    private Object monitor = null;
    private Object monitor2 = new Object();

    public MonitorObject(Object monitor) {
        this.monitor = monitor;
    }

    public void printHello(){
        synchronized (this.monitor){
            System.out.println(Thread.currentThread().getName() + " enters printHello at: " + LocalDateTime.now().toLocalTime());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " exits printHello at: " + LocalDateTime.now().toLocalTime());

        }
    }
    public void printNamaste(){
        synchronized (this.monitor){
            System.out.println(Thread.currentThread().getName() + " enters printNamaste at: " + LocalDateTime.now().toLocalTime());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " exits printNamaste at: " + LocalDateTime.now().toLocalTime());

        }
    }

    public void printHola(){
        synchronized (this.monitor2){
            System.out.println(Thread.currentThread().getName() + " enters printHola at: " + LocalDateTime.now().toLocalTime());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " exits printHola at: " + LocalDateTime.now().toLocalTime());

        }
    }

}
