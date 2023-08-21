package com.kmsandeep.loanApplication.thread.sharedObject;


import com.kmsandeep.loanApplication.thread.dto.MyObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedObject implements Runnable {
    private MyObject myObject = new MyObject();

//    public SharedObject(MyObject myObject) {
//        this.myObject = myObject;
//    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " object: " + myObject);
    }

    public static void main(String[] args) {
//        MyObject myObject1 = new MyObject();
//        MyObject myObject2 = new MyObject();
        SharedObject sharedObject = new SharedObject();
        SharedObject sharedObject2 = new SharedObject();
        Thread thread1 = new Thread(sharedObject, "T1");
        Thread thread2 = new Thread(sharedObject2, "T2");
//        thread1.start();
//        thread2.start();

        ExecutorService executorService = Executors.newFixedThreadPool(4);


//        executorService.
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1_000_000; i++) {
            if(map.containsKey("key")){
                String val = map.remove("key");
                if(val==null){
                    System.out.println("Val in iteration "+i+" is null");
                }
            }
            else{
                map.put("key","val");
            }
        }


    }
}
