package com.mixay.testac;

import com.github.retrooper.packetevents.protocol.player.User;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import java.text.MessageFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class PitchYawCheck {
    private static final Logger logger = Bukkit.getLogger();
    private static final BukkitScheduler scheduler = Bukkit.getScheduler();

    public static void checkpitchyaw(User player, float pitch, float yaw, Modernac plugin) {
        if (pitch > 90F || pitch < -90F || Float.isInfinite(pitch) || Float.isNaN(pitch)) {
            // impossible action, delayed ban
            long bandelay = ThreadLocalRandom.current().nextInt(5, 30) * 20L;
            logger.info(MessageFormat.format("Invalid pitch for player {0} ({1})", player.getName(), pitch));
            // ban task
            if (!StorageManager.isinqueue(player.getUUID())) {
                StorageManager.addtoqueue(player.getUUID());
                scheduler.runTaskLater(plugin, new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + player.getName());
                        StorageManager.removefromqueue(player.getUUID());
                    }
                }, bandelay);
            }
        }
    }
}
