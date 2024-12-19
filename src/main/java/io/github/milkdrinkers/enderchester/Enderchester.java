package io.github.milkdrinkers.enderchester;

import io.github.milkdrinkers.enderchester.config.ConfigHandler;
import io.github.milkdrinkers.enderchester.hook.BStatsHook;
import io.github.milkdrinkers.enderchester.listener.ListenerHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import space.arim.morepaperlib.MorePaperLib;

/**
 * Main class.
 */
public class Enderchester extends JavaPlugin {
    private static Enderchester instance;
    private MorePaperLib morePaperLib;
    private ConfigHandler configHandler;
    private ListenerHandler listenerHandler;

    // Hooks
    private static BStatsHook bStatsHook;

    /**
     * Gets plugin instance.
     *
     * @return the plugin instance
     */
    public static Enderchester getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        morePaperLib = new MorePaperLib(instance);
        configHandler = new ConfigHandler(instance);
        listenerHandler = new ListenerHandler(instance);

        configHandler.onLoad();
        listenerHandler.onLoad();
        bStatsHook.onLoad();
    }

    @Override
    public void onEnable() {
        configHandler.onEnable();
        listenerHandler.onEnable();
        bStatsHook.onEnable();
    }

    @Override
    public void onDisable() {
        configHandler.onDisable();
        listenerHandler.onDisable();
        bStatsHook.onDisable();
    }

    /**
     * Gets more paper lib.
     *
     * @return more paper lib
     */
    public MorePaperLib getMorePaperLib() {
        return morePaperLib;
    }

    /**
     * Gets config handler.
     *
     * @return the config handler
     */
    @NotNull
    public ConfigHandler getConfigHandler() {
        return configHandler;
    }
}
