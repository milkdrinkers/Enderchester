package io.github.milkdrinkers.enderchester.utility;

import com.github.milkdrinkers.Crate.Config;
import io.github.milkdrinkers.enderchester.Enderchester;
import io.github.milkdrinkers.enderchester.config.ConfigHandler;
import org.jetbrains.annotations.NotNull;

/**
 * Convenience class for accessing {@link ConfigHandler#getConfig}
 */
public abstract class Cfg {
    /**
     * Convenience method for {@link ConfigHandler#getConfig} to getConnection {@link Config}
     *
     * @return the config
     */
    @NotNull
    public static Config get() {
        return Enderchester.getInstance().getConfigHandler().getConfig();
    }
}
