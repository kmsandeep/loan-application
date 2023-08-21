package com.kmsandeep.loanApplication.thread;

public class StopRunnable implements Runnable{
    private boolean stopRequested = false;

    public synchronized void requestStop(){
        this.stopRequested = true;
    }

    public synchronized boolean isStopRequested() {
        return stopRequested;
    }

    @Override
    public void run() {
            sleep(5000);
        while (!isStopRequested()){
            System.out.println("Running :"+ Thread.currentThread().getName());
            sleep(1000);
        }

    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        StopRunnable stopRunnable = new StopRunnable();
        Thread thread = new Thread(stopRunnable,"stopRunnable");
        thread.setDaemon(true);
        thread.start();
        sleep(5000);
        System.out.println("Stop requested");
        stopRunnable.requestStop();
    }
}
