package com.kmsandeep.loanApplication.thread.blockingQueue;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.stream.Stream;

public class BlockingQueueEx {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        BlockingQueueEx blockingQueueEx = new BlockingQueueEx();
        Thread thread = new Thread(blockingQueueEx.task());
//        thread.wait();
        thread.start();
//        blockingQueue.
    }

    private Runnable task() {
        return () -> {
            System.out.println("Before Thread: " + Thread.currentThread().getName());
            Integer finalStr = Stream.iterate(0, i -> i + 1)
                    .parallel()
                    .limit(100000)
//                    .map(String::valueOf)
//                    .peek(System.out::println)
//                    .map(Optional::ofNullable)
//                    .map(Optional::get)
                    .reduce(0, Integer::sum, Integer::sum);
            System.out.println(finalStr);
            System.out.println("After Thread: " + Thread.currentThread().getName());
        };
    }
}


