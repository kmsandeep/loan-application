package com.kmsandeep.loanApplication.thread.executorService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ExecutorServiceEx {
    public static void main(String[] args) throws IOException {
        List<String> tasks = List
                .of("Read", "Write", "Play", "Dance",
                        "Cook", "Walk", "Cry", "Run", "Fly", "Spy", "Read", "Write", "Play", "Dance",
                        "Cook", "Walk", "Cry", "Run", "Fly", "Spy");
        System.out.println(tasks.size());
        int n = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            List<Future<String>> futures = executorService.invokeAll(
                    tasks.stream()
                            .map(entry -> callable(String.valueOf(entry)))
                            .collect(Collectors.toList())
            );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        tasks.stream()
                .map(str -> executorService.submit(callable(str)))
                .forEach(ExecutorServiceEx::printFuture);


        executorService.shutdown();
    }

    private static void printFuture(Future<String> future) {
        System.out.println(future.isDone());
        try {
            System.out.println(" get --> " + future.get());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
        System.out.println(future.isDone());
        System.out.println();
    }

    private static Runnable runnable(String msg) {
        return () -> System.out.println(Thread.currentThread().getName() + " Runnable Task: " + msg);
    }

    private static Callable<String> callable(String msg) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " Callable Task: " + msg);
            return msg;
        };
    }
}
