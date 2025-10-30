package com.veilguard;

import com.veilguard.managers.CheckManager;
import com.veilguard.managers.AlertManager;
import com.veilguard.checks.FakeOreBaitCheck;
import com.veilguard.checks.MineSightCheck;
import org.bukkit.plugin.java.JavaPlugin;

public class VeilGuard extends JavaPlugin {

    private static VeilGuard instance;
    private CheckManager checkManager;
    private AlertManager alertManager;
    private boolean protocolLibAvailable = false;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        protocolLibAvailable = getServer().getPluginManager().getPlugin("ProtocolLib") != null;
        if (protocolLibAvailable) {
            getLogger().info("ProtocolLib detected! Advanced packet checks enabled.");
        } else {
            getLogger().info("ProtocolLib not detected. Some checks will use reflection.");
        }

        alertManager = new AlertManager(this);
        checkManager = new CheckManager(this);

        checkManager.registerCheck(new FakeOreBaitCheck());
        checkManager.registerCheck(new MineSightCheck());

        getLogger().info("VeilGuard has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("VeilGuard has been disabled.");
    }

    public static VeilGuard getInstance() {
        return instance;
    }

    public CheckManager getCheckManager() {
        return checkManager;
    }

    public AlertManager getAlertManager() {
        return alertManager;
    }

    public boolean isProtocolLibAvailable() {
        return protocolLibAvailable;
    }
}
