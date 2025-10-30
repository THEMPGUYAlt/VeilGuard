package com.veilguard.checks;

import org.bukkit.entity.Player;

public abstract class Check {

    private String name;
    private boolean enabled = true;

    public Check() {
    }

    public Check(String name) {
        this.name = name;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void onPlayerAction(Player player, Object... args);

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
