package io.github.milkdrinkers.enderchester.config;

import com.github.milkdrinkers.crate.Config;
import io.github.milkdrinkers.enderchester.Enderchester;
import io.github.milkdrinkers.enderchester.Reloadable;

import javax.inject.Singleton;

/**
 * A class that generates/loads {@literal &} provides access to a configuration file.
 */
@Singleton
public class ConfigHandler implements Reloadable {
    private final Enderchester plugin;
    private Config cfg;

    /**
     * Instantiates a new Config handler.
     *
     * @param plugin the plugin instance
     */
    public ConfigHandler(Enderchester plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onLoad() {
        cfg = new Config("config", plugin.getDataFolder().getPath(), plugin.getResource("config.yml")); // Create a config file from the template in our resources folder
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    /**
     * Gets examplePlugin config object.
     *
     * @return the examplePlugin config object
     */
    public Config getConfig() {
        return cfg;
    }
}
