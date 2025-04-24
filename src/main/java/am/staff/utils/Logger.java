package am.staff.utils;

import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Logger {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    enum LogLevel{
        DEBUG(Level.DEBUG),
        INFO(Level.INFO),
        WARNING(Level.WARN),
        ERROR(Level.ERROR);

        private static String RESET = "\u001B[0m";

        private static String RED = "\u001B[31m";
        private static String GREEN = "\u001B[32m";
        private static String YELLOW = "\u001B[33m";
        private static String BLUE = "\u001B[34m";

        private Level logLevel;

        LogLevel(Level logLevel) {
            this.logLevel = logLevel;
        }

        public String getLogLevelNameColored() {
            return addColorToLogLevelName(this);
        }

        private static String addColorToLogLevelName(LogLevel logLevel) {
            return "%s%s%s".formatted(
                    logLevelToColor(logLevel),
                    logLevel,
                    RESET
            );
        };

        private static String logLevelToColor(LogLevel logLevel) {
            return Map.of(
                    LogLevel.DEBUG, GREEN,
                    LogLevel.ERROR, RED,
                    LogLevel.INFO, BLUE,
                    LogLevel.WARNING, YELLOW
            ).get(logLevel);
        }
    }

    public static void info(String message) {
        log(LogLevel.INFO, message);
    }

    public static void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public static void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }

    private static void log(LogLevel logType, String message) {
        logger.atLevel(logType.logLevel).log(message);
    }

    private static String formatMessage(String dateAndTime, LogLevel logLevel, String message) {
        String logStringTemplate =
                (
                        "%s | ".formatted(dateAndTime) +
                        "[%s] | ".formatted(logLevel.getLogLevelNameColored()) +
                        "%s".formatted(message)
                );
        return logStringTemplate;
    }

    private static String formatMessage(LogLevel logLevel, String message) {
        return formatMessage(getCurrentDate(), logLevel, message);
    }

    private static String getCurrentDate() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
