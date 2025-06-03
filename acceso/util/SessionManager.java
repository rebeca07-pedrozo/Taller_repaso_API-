package util;

import java.util.*;

public class SessionManager {
    private static final Map<String, Boolean> activeSessions = new HashMap<>();
    private static final Map<String, Integer> failCount = new HashMap<>();
    private static final Map<String, Long> blockedUsers = new HashMap<>();

    public static void activateUser(String username) {
        activeSessions.put(username, true);
    }

    public static void deactivateUser(String username) {
        activeSessions.remove(username);
    }

    public static boolean isUserActive(String username) {
        return activeSessions.containsKey(username);
    }

    public static void recordFailure(String username) {
        int count = failCount.getOrDefault(username, 0) + 1;
        failCount.put(username, count);
        if (count >= 3) {
            blockedUsers.put(username, System.currentTimeMillis() + 30000); // 30 seg
        }
    }

    public static void resetFailures(String username) {
        failCount.remove(username);
        blockedUsers.remove(username);
    }

    public static boolean isBlocked(String username) {
        Long time = blockedUsers.get(username);
        if (time == null) return false;
        if (System.currentTimeMillis() > time) {
            blockedUsers.remove(username);
            return false;
        }
        return true;
    }
}
