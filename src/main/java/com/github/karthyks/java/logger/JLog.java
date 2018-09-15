package com.github.karthyks.java.logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JLog {
    public static final String SEPARATOR = " *** ";
    private static ExecutorService logExecutor = Executors.newSingleThreadExecutor();
    private static ExecutorService logPoolExecutor = Executors.newFixedThreadPool(1);
    private static BlockingQueue<Log> logQueue = new ArrayBlockingQueue<>(100);

    static {
        File logsDir = new File("logs");
        File debugFile = new File("logs/debug.log");
        File infoFile = new File("logs/info.log");
        File errorFile = new File("logs/error.log");
        try {
            if (!logsDir.exists()) {
                logsDir.mkdir();
            }
            if (!debugFile.exists()) {
                debugFile.createNewFile();
            }
            if (!infoFile.exists()) {
                infoFile.createNewFile();
            }
            if (!errorFile.exists()) {
                errorFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logExecutor.execute(() -> {
            while (true) {
                try {
                    logPoolExecutor.execute(logQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void log(Log.LogLevel logLevel, String message) {
        logQueue.add(new Log(message, logLevel));
    }

    public static void d(String className, String log) {
        log(Log.LogLevel.DEBUG, className + SEPARATOR + log);
    }

    public static void i(String className, String log) {
        log(Log.LogLevel.INFO, className + SEPARATOR + log);
    }

    public static void e(String className, String log) {
        log(Log.LogLevel.ERROR, className + SEPARATOR + log);
    }
}
