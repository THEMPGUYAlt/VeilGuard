package com.veilguard.managers;

import org.bukkit.entity.Player;
import com.veilguard.VeilGuard;

public class AlertManager {

    private final VeilGuard plugin;

    public AlertManager(VeilGuard plugin) {
        this.plugin = plugin;
    }

    public void flag(Player player, double weight, String reason) {
        double current = plugin.getConfig().getDouble("settings.alert-threshold");
        if (weight >= current) {
            plugin.getServer().broadcastMessage("[VeilGuard] " + player.getName() + " flagged: " + reason);
            if (weight >= plugin.getConfig().getDouble("settings.autoban-threshold")) {
                String cmd = plugin.getConfig().getString("settings.punishment-command").replace("%player%", player.getName());
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), cmd);
            }
        }
    }
}
