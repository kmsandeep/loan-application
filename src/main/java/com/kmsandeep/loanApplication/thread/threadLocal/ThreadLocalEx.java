package com.kmsandeep.loanApplication.thread.threadLocal;


public class ThreadLocalEx {
    private static void println(String s){
        System.out.println(Thread.currentThread().getName()+" "+s);
    }

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        ThreadLocal<String> threadLocal2 = ThreadLocal.withInitial(() -> "threadLocal2");
        ThreadLocal<String> threadLocal3 = new InheritableThreadLocal();

        Thread thread1 = new Thread(() -> {
//          System.out.println("======="+Thread.currentThread().getName()+"==========");
            println(threadLocal1.get());
            threadLocal1.set("threadLocal1");
            println(threadLocal1.get());
            println(threadLocal2.get());
            threadLocal3.set("initThreadLocal");
            println(threadLocal3.get());
            Thread childThread = new Thread(() -> {
                System.out.println("=======" + Thread.currentThread().getName() + "==========");

                println(threadLocal1.get());
                println(threadLocal2.get());
                println(threadLocal3.get());
            }, "childThread");
            childThread.start();
        }, "T1");


        Thread thread2 = new Thread(() -> {
//            System.out.println("======="+Thread.currentThread().getName()+"==========");
            println(threadLocal1.get());
            println(threadLocal1.get());
            println(threadLocal2.get());
            println(threadLocal3.get());
        }, "T2");

        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread2.start();
    }
}
