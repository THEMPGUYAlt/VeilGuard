package com.veilguard.managers;

import com.veilguard.checks.Check;
import com.veilguard.VeilGuard;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CheckManager {

    private final VeilGuard plugin;
    private final List<Check> checks = new ArrayList<>();

    public CheckManager(VeilGuard plugin) {
        this.plugin = plugin;
    }

    public void registerCheck(Check check) {
        checks.add(check);
        check.onEnable();
    }

    public void unregisterCheck(Check check) {
        checks.remove(check);
        check.onDisable();
    }

    public void runChecks(Player player, Object... args) {
        for (Check check : checks) {
            if (check.isEnabled()) {
                check.onPlayerAction(player, args);
            }
        }
    }
}
