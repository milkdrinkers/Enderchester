package io.github.milkdrinkers.enderchester.api.event;

import io.github.milkdrinkers.enderchester.api.type.OpenMethod;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a enderchest is opened by a player through the plugin.
 */
@SuppressWarnings("unused")
public class EnderchestOpenedEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private final OpenMethod openMethod;

    public EnderchestOpenedEvent(Player player, OpenMethod openMethod) {
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
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
