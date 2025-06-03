package util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogUtils {
    private static final String ACCESS_LOG = "resources/logs/access.log";
    private static final String SENSITIVE_LOG = "resources/logs/sensitive_actions.log";

    public static void logAccess(String user, boolean success, String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(ACCESS_LOG, true))) {
            out.printf("[%s] %s - %s: %s%n", LocalDateTime.now(), user, success ? "SUCCESS" : "FAIL", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logSensitiveAction(String user, String action) {
        try (PrintWriter out = new PrintWriter(new FileWriter(SENSITIVE_LOG, true))) {
            String logLine = String.format("[%s] %s - ACTION: %s", LocalDateTime.now(), user, action);
            String hash = Integer.toHexString(logLine.hashCode());
            out.println(logLine + " - HASH: " + hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
