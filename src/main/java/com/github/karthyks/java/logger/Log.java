package com.github.karthyks.java.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Log implements Runnable {

    private String messageToWrite;
    private LogLevel logLevel;

    public Log(String messageToWrite, LogLevel logLevel) {
        this.messageToWrite = messageToWrite;
        this.logLevel = logLevel;
    }

    @Override
    public void run() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String logTime = dateFormat.format(new Date());
        String message = logTime + " ---- " + messageToWrite;
        switch (logLevel) {
            case DEBUG:
                writeMessage("logs/debug.log", message);
                break;
            case INFO:
                writeMessage("logs/info.log", message);
                break;
            case ERROR:
                writeMessage("logs/error.log", message);
                break;
        }
    }

    private void writeMessage(String path, String message) {
        try {
            File logFile = new File(path);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
            bufferedWriter.write(message);
            bufferedWriter.write("\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum LogLevel {
        DEBUG,
        INFO,
        ERROR
    }
}