package com.kmsandeep.loanApplication.thread.synchronize;

public class SyncObject {
    private static int countStatic;
    private int count;

    public synchronized static int incrementAndGetCountStatic() {
        return countStatic++;
    }

    public synchronized static void incrementCountStatic() {
        countStatic++;
    }

    public synchronized int incrementAndGetCount() {
        return count++;
    }

    public synchronized void incrementCount() {
        count++;
    }

    public static int getCountStatic() {
        return countStatic;
    }

    public int getCount() {
        return count;
    }
}
