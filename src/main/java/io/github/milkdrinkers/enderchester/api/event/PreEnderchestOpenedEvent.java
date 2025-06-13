package io.github.milkdrinkers.enderchester.api.event;

import io.github.milkdrinkers.enderchester.api.type.OpenMethod;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired before a enderchest is opened by a player through the plugin. Cancelling the event stops the plugin from opening the enderchest for the player.
 */
@SuppressWarnings("unused")
public class PreEnderchestOpenedEvent extends Event implements Cancellable {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private boolean cancelled;
    private final Player player;
    private final OpenMethod openMethod;

    public PreEnderchestOpenedEvent(Player player, OpenMethod openMethod) {
        this.player = player;
        this.openMethod = openMethod;
    }

    public Player getPlayer() {
        return player;
    }

    public OpenMethod getOpenMethod() {
        return openMethod;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
