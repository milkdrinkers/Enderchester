package io.github.milkdrinkers.enderchester.hook;

import io.github.milkdrinkers.enderchester.Enderchester;
import org.bstats.bukkit.Metrics;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * A hook to interface with <a href="https://github.com/Bastian/bstats-metrics">BStats</a>.
 */
public class BStatsHook implements Hook {
    private final static int BSTATS_ID = 24181; // Signup to BStats and register your new plugin here: https://bstats.org/getting-started, replace the id with you new one!
    private final Enderchester plugin;
    private @Nullable Metrics hook;

    /**
     * Instantiates a new BStats hook.
     *
     * @param plugin the plugin instance
     */
    public BStatsHook(Enderchester plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        // Catch startup errors for bstats
        try {
            setHook(new Metrics(plugin, BSTATS_ID));
        } catch (Exception ignored) {
            setHook(null);
        }
    }

    @Override
    public void onDisable() {
        getHook().shutdown();
        setHook(null);
    }

    /**
     * Check if the BStats hook is loaded and ready for use.
     *
     * @return whether the BStats metrics hook is loaded or not
     */
    public boolean isHookLoaded() {
        return hook != null;
    }

    /**
     * Gets BStats metrics instance. Should only be used following {@link #isHookLoaded()}.
     *
     * @return instance
     */
    public Metrics getHook() {
        if (!isHookLoaded())
            throw new IllegalStateException("Attempted to access BStats metrics instance hook when it is unavailable!");

        return hook;
    }

    /**
     * Sets the BStats metrics instance.
     *
     * @param hook The BStats metrics instance {@link Metrics}
     */
    @ApiStatus.Internal
    private void setHook(@Nullable Metrics hook) {
        this.hook = hook;
    }
}
