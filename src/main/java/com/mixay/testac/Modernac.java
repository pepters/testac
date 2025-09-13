package com.mixay.testac;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class Modernac extends JavaPlugin {

    @Override
    public void onLoad() {
        PacketEvents.getAPI().getEventManager().registerListener(new testclass(this), PacketListenerPriority.HIGH);
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
