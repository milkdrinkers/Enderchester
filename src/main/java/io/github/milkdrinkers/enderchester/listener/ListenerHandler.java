package io.github.milkdrinkers.enderchester.listener;

import io.github.milkdrinkers.enderchester.Enderchester;
import io.github.milkdrinkers.enderchester.Reloadable;

/**
 * A class to handle registration of event listeners.
 */
public class ListenerHandler implements Reloadable {
    private final Enderchester plugin;

    /**
     * Instantiates a the Listener handler.
     *
     * @param plugin the plugin instance
     */
    public ListenerHandler(Enderchester plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        // Register listeners here
        plugin.getServer().getPluginManager().registerEvents(new EnderChestListener(plugin.getConfigHandler().getConfig()), plugin);
    }

    @Override
    public void onDisable() {
    }
}
