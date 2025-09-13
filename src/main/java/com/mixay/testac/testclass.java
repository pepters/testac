package com.mixay.testac;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.UserDisconnectEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.client.*;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class testclass implements PacketListener {
    Modernac plugin;
    Logger logger;
    public testclass (Modernac pl) {
        this.plugin = pl;
        this.logger = Bukkit.getLogger();
    }
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        User player = event.getUser();
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_ROTATION) {
            WrapperPlayClientPlayerRotation packet = new WrapperPlayClientPlayerRotation(event);
            float pitch = packet.getPitch();
            float yaw = packet.getYaw();
            PitchYawCheck.checkpitchyaw(player, pitch, yaw, plugin);
        }
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION) {
            WrapperPlayClientPlayerPositionAndRotation packet = new WrapperPlayClientPlayerPositionAndRotation(event);
            float pitch = packet.getPitch();
            float yaw = packet.getYaw();
            PitchYawCheck.checkpitchyaw(player, pitch, yaw, plugin);
        }
        if (event.getPacketType() == PacketType.Play.Client.ANIMATION) {
            WrapperPlayClientAnimation packet = new WrapperPlayClientAnimation(event);
            StorageManager.addswung(player.getUUID());
        }
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity packet = new WrapperPlayClientInteractEntity(event);
            if (packet.getAction() != WrapperPlayClientInteractEntity.InteractAction.ATTACK) return;
            if (!StorageManager.playerswung(player.getUUID())) {
                logger.info("Player hasnt swung");
            }
            StorageManager.finishswing(player.getUUID());
        }
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_FLYING) {
            WrapperPlayClientPlayerFlying packet = new WrapperPlayClientPlayerFlying(event);
            Player p = event.getPlayer();
            // we will check for server ground
            final boolean onground = packet.isOnGround();
            final boolean mathGround = SpigotConversionUtil.fromBukkitLocation(p.getLocation()).getY() % 0.015625D == 0.0D;
            final boolean serverGround = true;
            if (onground && !mathGround && !serverGround) {
                // ну все дальше уже незя

            }
        }
    }
    @Override
    public void onUserDisconnect (UserDisconnectEvent event) {
        User player = event.getUser();
        if (StorageManager.playerswung(player.getUUID())) {
            StorageManager.finishswing(player.getUUID());
        }
    }
}
