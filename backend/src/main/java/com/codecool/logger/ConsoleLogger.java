package com.codecool.logger;

import java.time.LocalDateTime;

public class ConsoleLogger implements Logger {

    private String createLogEntry(String message, String type) {
        return String.format("[%s] %s: %s", LocalDateTime.now(), type, message);
    }

    @Override
    public void logError(String message) {
        System.out.println(createLogEntry(message, "ERROR"));
    }

    @Override
    public void logInfo(String message) {
        System.out.println(createLogEntry(message, "INFO"));
    }
}
