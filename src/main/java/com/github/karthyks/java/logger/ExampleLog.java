package com.github.karthyks.java.logger;

public class ExampleLog {
    public static void main(String[] args) throws InterruptedException {
        JLog.d("ExampleLog", "main: Logging debug...");
        Thread.sleep(1000);
        JLog.i("ExampleLog", "main: Logging info...");
        Thread.sleep(1000);
        JLog.e("ExampleLog", "main: Logging error...");
        Thread.sleep(1000);
        JLog.d("ExampleLog", "main: Logging debug...");
        Thread.sleep(1000);
        JLog.i("ExampleLog", "main: Logging info...");
        Thread.sleep(1000);
        JLog.e("ExampleLog", "main: Logging error...");
        Thread.sleep(1000);
    }
}
