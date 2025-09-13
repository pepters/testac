package com.mixay.testac;

import java.util.HashSet;
import java.util.UUID;

public class StorageManager {
    private static HashSet <UUID> queuedforban = new HashSet<>();
    private static HashSet <UUID> swungplayers = new HashSet<>();
    public static void addtoqueue (UUID uuid) {
        queuedforban.add(uuid);
    }
    public static void flushqueue () {
        queuedforban.clear();
    }
    public static void removefromqueue (UUID uuid) {
        queuedforban.remove(uuid);
    }
    public static boolean isinqueue (UUID uuid) {
        return queuedforban.contains(uuid);
    }
    public static boolean playerswung (UUID uuid) {
        return swungplayers.contains(uuid);
    }
    public static void addswung (UUID uuid) {
        swungplayers.add(uuid);
    }
    public static void finishswing (UUID uuid) {
        swungplayers.remove(uuid);
    }
}
