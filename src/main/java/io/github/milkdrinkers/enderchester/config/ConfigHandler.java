package io.github.milkdrinkers.enderchester.config;

import io.github.milkdrinkers.enderchester.Enderchester;
import io.github.milkdrinkers.enderchester.Reloadable;
import io.github.milkdrinkers.enderchester.utility.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.interfaces.InterfaceDefaultOptions;
import org.spongepowered.configurate.util.MapFactories;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A class that generates/loads {@literal &} provides access to a configuration file.
 */
public class ConfigHandler implements Reloadable {
    private final Enderchester plugin;
    private EnderChesterConfig config;

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
        final Path configPath = plugin.getDataFolder().toPath().resolve("config.yml");

        final YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
            .path(configPath)
            .indent(2)
            .nodeStyle(NodeStyle.BLOCK)
            .defaultOptions(options ->
                InterfaceDefaultOptions.defaults()
                    .shouldCopyDefaults(false)
                    .header("EnderChester by darksaid98\nFor more info see https://github.com/milkdrinkers/Enderchester")
                    .mapFactory(
                        MapFactories.insertionOrdered()
                    )
            )
            .commentsEnabled(true)
            .build();

        // Create default config
        if (!Files.exists(configPath)) {
            saveDefaultConfig(loader);
        }

        // Load existing config
        loadConfig(loader);
    }

    private void saveDefaultConfig(YamlConfigurationLoader loader) {
        final CommentedConfigurationNode rootNode = CommentedConfigurationNode.root(loader.defaultOptions());

        // Set the default config object to the node
        try {
            EnderChesterConfig config = rootNode.get(EnderChesterConfig.class);
            rootNode.set(config);
            loader.save(rootNode);
        } catch (Exception e) {
            Logger.get().error("Failed to save default config: {}", e.getMessage(), e);
        }
    }

    private void loadConfig(YamlConfigurationLoader loader) {
        try {
            final CommentedConfigurationNode rootNode = loader.load();

            // Load the config from the node
            config = rootNode.get(EnderChesterConfig.class);

            // If the config file was loaded but the object is null, use defaults
            if (config == null) {
                config = new EnderChesterConfig();
                System.out.println("Using default configuration values");
            }
        } catch (Exception e) {
            Logger.get().error("Failed to load config, using defaults options: {}", e.getMessage(), e);
            config = new EnderChesterConfig();
        }
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
    public EnderChesterConfig getConfig() {
        return config;
    }
}
