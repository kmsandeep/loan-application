package com.kmsandeep.loanApplication.thread.synchronize;

public class InstanceLockSynchronization {
    private int count = 0;

    public synchronized void printSomething(String str) {
        System.out.println(str + " " + count++);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void runThread(){
        SyncObject syncObject = new SyncObject();
        new Thread(new MyRunnable(syncObject), "T1").start();
        new Thread(new MyRunnable(syncObject), "T2").start();
        new Thread(new MyRunnable(syncObject), "T3").start();
    }

    public static void main(String[] args) {
        InstanceLockSynchronization instance = new InstanceLockSynchronization();
        instance.runThread();

    }

    private  class MyRunnable implements Runnable {

        private SyncObject object = null;

        public MyRunnable(SyncObject object) {
            this.object = object;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {

//                SyncObject.incrementCountStatic();
                System.out.println(Thread.currentThread().getName() + "   static: " + SyncObject.incrementAndGetCountStatic());

//                object.incrementCount();
                System.out.println(Thread.currentThread().getName() + " instance:" + object.incrementAndGetCount());

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

