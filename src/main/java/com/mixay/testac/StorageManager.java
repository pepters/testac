package com.mixay.testac;

import com.sun.org.apache.xalan.internal.xsltc.dom.SingletonIterator;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class StorageManager {
    private static HashMap<UUID, PlayerData> players = new HashMap<>();
    public static void addPlayer (UUID uuid, String name) {
        players.put(uuid, new PlayerData(name));
    }
    public static PlayerData getPlayer (UUID uuid) {
        return players.get(uuid);
    }
    public static PlayerData getPlayer (String nick) {
        Player p = Bukkit.getPlayer(nick);
        if (p!=null) {
            UUID id = p.getUniqueId();
            return players.get(id);
        }
        return null;
    }
    public static void updatePlayer (UUID id, PlayerData data) {
        players.replace(id, data);
    }
    public static void removePlayer (UUID id) {
        players.remove(id);
    }
}
