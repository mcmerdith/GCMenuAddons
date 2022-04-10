package net.mcmerdith.guildedmenu;

import net.mcmerdith.guildedmenu.integration.IntegrationManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GuildedMenu extends JavaPlugin {
    private static GuildedMenu instance;
    public static GuildedMenu plugin() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;

        setupIntegrations();
    }

    @Override
    public void onEnable() {
        IntegrationManager.instance().enable();
        super.onEnable();
    }

    private void setupIntegrations() {
        IntegrationManager.instance().setup();
    }
}
