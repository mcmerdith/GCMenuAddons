package net.mcmerdith.guildedmenu.integration;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.wargamer2010.signshop.SignShop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Level;

public class IntegrationManager {
    private List<Integration> integrations = new ArrayList<>();

    // Instance only class
    private static IntegrationManager instance;
    public static IntegrationManager instance() {
        if (instance == null) instance = new IntegrationManager();
        return instance;
    }
    private IntegrationManager() {
        register(new SignShopIntegration());
    }

    public void setup() {
        for (Integration i : integrations) {
            if (!i.setup()) {
                Bukkit.getLogger().warning(i.getClass().getSimpleName() + " failed to load");
            };
        }
    }

    public void enable() {
        for (Integration i : integrations) {
            i.onLoad();
        }
    }

    private void register(Integration i) {
        integrations.add(i);
    }

    /**
     * Gets an integration
     * @param clazz The integration to retrieve
     * @param <T> something...
     * @return The integration, if available, otherwise null
     */
    @SuppressWarnings("unchecked")
    public <T extends Integration> T get(Class<T> clazz) {
        for (Integration integration : integrations) {
            if (integration.getClass().isInstance(clazz)) {
                if (integration.isAvailable()) {
                    return (T) integration;
                }
            }
        }

        return null;
    }
}
