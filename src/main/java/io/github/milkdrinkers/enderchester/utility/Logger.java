package io.github.milkdrinkers.enderchester.utility;


import io.github.milkdrinkers.enderchester.Enderchester;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jetbrains.annotations.NotNull;

/**
 * A class that provides shorthand access to {@link Enderchester#getComponentLogger}.
 */
public class Logger {
    /**
     * Get component logger. Shorthand for:
     *
     * @return the component logger {@link Enderchester#getComponentLogger}.
     */
    @NotNull
    public static ComponentLogger get() {
        return Enderchester.getInstance().getComponentLogger();
    }
}
