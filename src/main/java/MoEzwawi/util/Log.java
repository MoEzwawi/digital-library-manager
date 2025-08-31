package MoEzwawi.util;

import java.io.IOException;
import java.util.logging.*;

/**
 * <p>Simple logging utility for the application.</p>
 * <p>Wraps java.util.logging to centralize log behavior.</p>
 * <p>Configures the application logger to write all messages to a file (library.log).</p>
 * <p>Disables parent handlers to suppress default console output.</p>
 */
public final class Log {

    private static final Logger LOGGER = Logger.getLogger("DigitalLibrary");

    static {
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        try {
            FileHandler fileHandler = new FileHandler("library_log.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize log file.");
        }
    }

    private Log() { }

    public static void info(String message) {
        LOGGER.log(Level.INFO, message);
    }

    public static void warn(String message, Throwable t) {
        LOGGER.log(Level.WARNING, message, t);
    }

    public static void error(String message, Throwable t) {
        LOGGER.log(Level.SEVERE, message, t);
    }
}