package net.mcmerdith.guildedmenu.integration;

import org.bukkit.Bukkit;

public abstract class Integration {
    private String pluginName;
    private String[] configs;

    private boolean pluginInstalled = false;
    private boolean loadSuccess = false;

    private boolean hasConfigs() {
        return configs.length != 0;
    }

    private boolean isPluginAvailable = false;

    public Integration(String pluginName) {
        this(pluginName, null);
    }

    public Integration(String pluginName, String[] configs) {
        this.pluginName = pluginName;
        this.configs = configs;
    }

    /**
     * Check if the integration can be used
     *
     * @return If the integration can be used
     */
    public boolean isAvailable() {
        isPluginAvailable = Bukkit.getPluginManager().isPluginEnabled(pluginName);
        return isPluginAvailable && loadSuccess;
    }

    /**
     * Check if the integration is installed on the server and load any config data
     *
     * @return True if the load succeeded OR the plugin is not installed, false if the plugin was installed and the load failed
     */
    boolean setup() {
        pluginInstalled = Bukkit.getPluginManager().getPlugin(pluginName) != null;
        if (pluginInstalled) {
            loadSuccess = loadOrGenerateConfigs();
            return loadSuccess;
        } else {
            return true;
        }
    }

    /**
     * Run when server is loading. A
     *
     * @return
     */
    abstract boolean onLoad();

    boolean loadOrGenerateConfigs() {
        if (!hasConfigs()) return true;
        return true;
    }
}
