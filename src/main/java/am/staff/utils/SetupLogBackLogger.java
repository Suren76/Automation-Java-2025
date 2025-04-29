package am.staff.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.pattern.CompositeConverter;
import org.slf4j.LoggerFactory;

import static org.slf4j.Logger.ROOT_LOGGER_NAME;

public final class SetupLogBackLogger {
    private SetupLogBackLogger() {}

    public static String DEFAULT_LOG_LEVEL = "INFO";

    public static void setupLogbackLoggerDefaultPattern() {
        ch.qos.logback.classic.Logger rootLogger;
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        PatternLayout layout = new PatternLayout();
        layout.setContext(context);
        layout.getInstanceConverterMap().put("colorLevel", ColorLevelConverter::new);
        layout.setPattern("%d{HH:mm:ss.SSS} [%thread] %colorLevel(%-5level) %logger{36} - %msg%n");
        layout.start();

        LayoutWrappingEncoder<ILoggingEvent> encoder = new LayoutWrappingEncoder<>();
        encoder.setContext(context);
        encoder.setLayout(layout);

        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setContext(context);
        consoleAppender.setEncoder(encoder);
        consoleAppender.start();

        rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ROOT_LOGGER_NAME);

        rootLogger.detachAndStopAllAppenders();
        rootLogger.addAppender(consoleAppender);
        rootLogger.setLevel(Level.toLevel(getLogLevel()));
    }

    private static String getLogLevel() {
        return System.getProperty("DEBUG") != null ?
                System.getProperty("DEBUG") :
                DEFAULT_LOG_LEVEL;
    }

    private static class ColorLevelConverter extends CompositeConverter<ILoggingEvent> {
        private static final String RESET = "\u001B[0m";

        private static final String RED = "\u001B[31m";
        private static final String GREEN = "\u001B[32m";
        private static final String YELLOW = "\u001B[33m";
        private static final String BLUE = "\u001B[34m";

        @Override
        protected String transform(ILoggingEvent event, String in) {
            return switch (event.getLevel().toInt()) {
                case Level.DEBUG_INT -> GREEN + in + RESET; // green
                case Level.INFO_INT -> BLUE + in + RESET; // blue
                case Level.WARN_INT -> YELLOW + in + RESET; // yellow
                case Level.ERROR_INT -> RED + in + RESET; // red
                default -> event.getLevel().levelStr;
            };
        }
    }
}
