package io.github.milkdrinkers.enderchester.listener;

import io.github.milkdrinkers.enderchester.Enderchester;
import io.github.milkdrinkers.enderchester.api.event.EnderchestOpenedEvent;
import io.github.milkdrinkers.enderchester.api.event.PreEnderchestOpenedEvent;
import io.github.milkdrinkers.enderchester.api.type.OpenMethod;
import io.github.milkdrinkers.enderchester.utility.Cfg;
import io.github.milkdrinkers.enderchester.utility.Logger;
import net.kyori.adventure.key.Key;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * The type Ender chest listener.
 */
public class EnderChestListener implements Listener {
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

        if (Cfg.get().getOrDefault("check.permission", true) && !p.hasPermission("enderchester.use"))
            return false;

        if (Cfg.get().getOrDefault("check.creative", false) && p.getGameMode().equals(GameMode.CREATIVE))
            return false;

        if (Cfg.get().getOrDefault("check.blacklist.enabled", false) && Cfg.get().getStringList("check.blacklist.worlds").contains(p.getWorld().getName()))
            return false;

        if (Cfg.get().getOrDefault("check.whitelist.enabled", false) && !Cfg.get().getStringList("check.whitelist.worlds").contains(p.getWorld().getName()))
            return false;

        Enderchester.getInstance().getMorePaperLib().scheduling().regionSpecificScheduler(p.getLocation()).run(() -> {
            final boolean eventCall = new PreEnderchestOpenedEvent(p, openMethod).callEvent();
            if (!eventCall)
                return;

            p.openInventory(p.getEnderChest());
            if (Cfg.get().getOrDefault("sound.opening", true)) {
                Sound sound;
                if (Cfg.get().getString("sound.effect") != null &&
                    !Cfg.get().getString("sound.effect").isBlank() &&
                    Registry.SOUNDS.get(NamespacedKey.minecraft(Cfg.get().getString("sound.effect"))) != null) {
                    sound = Registry.SOUNDS.get(NamespacedKey.minecraft(Cfg.get().getString("sound.effect")));
                } else {
                    sound = Sound.BLOCK_ENDER_CHEST_OPEN;
                }

                p.playSound(p.getLocation(), sound, SoundCategory.BLOCKS, Cfg.get().getOrDefault("sound.volume", 1.0f), 1.0f);
            }

            new EnderchestOpenedEvent(p, openMethod).callEvent();
        });

        return true;
    }
}
