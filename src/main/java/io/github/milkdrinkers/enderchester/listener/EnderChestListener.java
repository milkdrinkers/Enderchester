package io.github.milkdrinkers.enderchester.listener;

import io.github.milkdrinkers.enderchester.Enderchester;
import io.github.milkdrinkers.enderchester.utility.Cfg;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
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

        openEnderChest(p, item);
        e.setCancelled(true);
    }

    /**
     * Handles opening of ender chest by right-clicking when holding the item.
     *
     * @param e the e
     */
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (!e.getAction().equals(Action.RIGHT_CLICK_AIR))
            return;

        final Player p = e.getPlayer();
        final ItemStack item = e.getItem();

        if (item == null)
            return;

        openEnderChest(p, item);
        e.setCancelled(true);
    }

    private void openEnderChest(Player p, ItemStack item) {
        if (!item.getType().equals(Material.ENDER_CHEST))
            return;

        if (Cfg.get().getOrDefault("check.permission", true) && !p.hasPermission("enderchester.use"))
            return;

        if (Cfg.get().getOrDefault("check.creative", false) && p.getGameMode().equals(GameMode.CREATIVE))
            return;

        if (Cfg.get().getOrDefault("check.blacklist.enabled", false) && Cfg.get().getStringList("check.blacklist.worlds").contains(p.getWorld().getName()))
            return;

        Enderchester.getInstance().getMorePaperLib().scheduling().regionSpecificScheduler(p.getLocation()).run(() -> {
            p.openInventory(p.getEnderChest());
            if (Cfg.get().getOrDefault("sound.opening", true))
                p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0f, 1.0f);
        });
    }
}
