package com.veilguard.checks;

import com.veilguard.VeilGuard;
import com.veilguard.managers.AlertManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Random;

public class FakeOreBaitCheck extends Check {

    private final Random random = new Random();
    private final AlertManager alertManager = VeilGuard.getInstance().getAlertManager();

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onPlayerAction(Player player, Object... args) {
        sendFakeOres(player);
    }

    private void sendFakeOres(Player player) {
        Location base = player.getLocation().clone().add(random.nextInt(10)-5, -1*random.nextInt(4), random.nextInt(10)-5);

        for (int i = 0; i < 5; i++) {
            Location fakeOre = base.clone().add(random.nextInt(3), random.nextInt(3), random.nextInt(3));
            player.sendBlockChange(fakeOre, Material.DIAMOND_ORE.createBlockData());
        }
    }

    public void onBlockBreak(Player player, Location loc) {
        alertManager.flag(player, 40.0, "Mined fake ore");
    }
}
