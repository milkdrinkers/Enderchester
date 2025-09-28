package io.github.milkdrinkers.enderchester.listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import io.github.milkdrinkers.enderchester.Enderchester;
import io.github.milkdrinkers.enderchester.api.event.EnderchestOpenedEvent;
import io.github.milkdrinkers.enderchester.api.event.PreEnderchestOpenedEvent;
import io.github.milkdrinkers.enderchester.api.type.OpenMethod;
import io.github.milkdrinkers.enderchester.config.EnderChesterConfig;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.intellij.lang.annotations.Subst;

/**
 * The type Ender chest listener.
 */
public class EnderChestListener implements Listener {
    private final EnderChesterConfig config;

    public EnderChestListener(EnderChesterConfig config) {
        this.config = config;
    }

    /**
     * Handles opening of ender chest through clicking the item in an inventory.
     *
     * @param e the e
     */
    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.LOW)
    public void onInteract(InventoryClickEvent e) {
        if (!e.getClick().equals(ClickType.RIGHT))
            return;

        if (e.getView().getTopInventory().getType().equals(InventoryType.ENDER_CHEST))
            return;

        final Player p = (Player) e.getWhoClicked();

        final ItemStack item = e.getCurrentItem();

        if (item == null)
            return;

        if (openEnderChest(p, item, OpenMethod.INVENTORY))
            e.setCancelled(true);
    }

    /**
     * Handles opening of ender chest by right-clicking when holding the item.
     *
     * @param e the e
     */
    @SuppressWarnings("unused")
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (!e.getAction().equals(Action.RIGHT_CLICK_AIR))
            return;

        final Player p = e.getPlayer();
        final ItemStack item = e.getItem();

        if (item == null)
            return;

        if (openEnderChest(p, item, OpenMethod.HOTBAR))
            e.setCancelled(true);
    }

    private boolean openEnderChest(Player p, ItemStack item, OpenMethod openMethod) {
        if (!item.getType().equals(Material.ENDER_CHEST))
            return false;

        if (config.check.permission && !p.hasPermission("enderchester.use"))
            return false;

        if (config.check.creative && p.getGameMode().equals(GameMode.CREATIVE))
            return false;

        if (config.check.blacklist.enabled && config.check.blacklist.worlds.contains(p.getWorld().getName()))
            return false;

        if (config.check.whitelist.enabled && !config.check.whitelist.worlds.contains(p.getWorld().getName()))
            return false;

        Enderchester.getInstance().getMorePaperLib().scheduling().regionSpecificScheduler(p.getLocation()).run(() -> {
            final boolean eventCall = new PreEnderchestOpenedEvent(p, openMethod).callEvent();
            if (!eventCall)
                return;

            p.openInventory(p.getEnderChest());
            if (config.sound.opening) {
                try {
                    @Subst("minecraft:block.ender_chest.open") final String soundId = config.sound.effect;
                    final Sound sound = Sound.sound()
                        .type(Key.key(soundId))
                        .source(Sound.Source.BLOCK)
                        .volume(config.sound.volume)
                        .pitch(1.0f)
                        .build();

                    p.playSound(sound);
                } catch (RuntimeException ignored) {
                }
            }

            new EnderchestOpenedEvent(p, openMethod).callEvent();
        });

        return true;
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerJump(PlayerJumpEvent e) { // Close enderchest if starts jumping
        if (!config.check.disableMoveWhileOpen)
            return;

        if (e.isCancelled())
            return;

        if (!e.getPlayer().getOpenInventory().getTopInventory().getType().equals(InventoryType.ENDER_CHEST))
            return;

        e.getPlayer().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerJump(PlayerToggleSneakEvent e) { // Close enderchest if starts sneaking
        if (!config.check.disableMoveWhileOpen)
            return;

        if (e.isCancelled())
            return;

        if (!e.isSneaking()) // Only do on sneak initiate as players may un-sneak automatically
            return;

        if (!e.getPlayer().getOpenInventory().getTopInventory().getType().equals(InventoryType.ENDER_CHEST))
            return;

        e.getPlayer().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) { // Close enderchest if player moves
        if (!config.check.disableMoveWhileOpen)
            return;

        if (e.isCancelled())
            return;

        if (!e.hasChangedBlock() && !e.hasChangedOrientation())
            return;

        final Player p = e.getPlayer();

        if (!p.getGameMode().equals(GameMode.SURVIVAL) && !p.getGameMode().equals(GameMode.ADVENTURE))
            return;

        if (!p.getOpenInventory().getTopInventory().getType().equals(InventoryType.ENDER_CHEST))
            return;

        if (p.isGliding() || p.isFlying()) // Allow accessing while gliding
            return;

        p.closeInventory(InventoryCloseEvent.Reason.PLUGIN);
    }
}
