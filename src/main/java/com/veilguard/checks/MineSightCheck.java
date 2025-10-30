package com.veilguard.checks;

import com.veilguard.VeilGuard;
import com.veilguard.managers.AlertManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class MineSightCheck extends Check {

    private final AlertManager alertManager = VeilGuard.getInstance().getAlertManager();
    private final Map<Player, Villager> villagers = new HashMap<>();

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {
        villagers.values().forEach(Villager::remove);
        villagers.clear();
    }

    @Override
    public void onPlayerAction(Player player, Object... args) {
        spawnVillagerBait(player);
    }

    private void spawnVillagerBait(Player player) {
        Location loc = player.getLocation().clone().add(2,0,2);
        Villager villager = player.getWorld().spawn(loc, Villager.class);
        villager.setInvisible(true);
        villager.setInvulnerable(true);
        villager.setAI(false);
        villager.setCustomName("Bait");
        villagers.put(player, villager);

        new BukkitRunnable() {
            int ticks = 0;
            @Override
            public void run() {
                ticks++;
                if (ticks > 200 || !villager.isValid()) {
                    villager.remove();
                    villagers.remove(player);
                    cancel();
                } else if (player.getLocation().distanceSquared(villager.getLocation()) < 100) {
                    alertManager.flag(player, 50.0, "Looking at villager through blocks");
                }
            }
        }.runTaskTimer(VeilGuard.getInstance(), 0, 20);
    }
}
