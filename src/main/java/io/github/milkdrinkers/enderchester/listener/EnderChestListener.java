package io.github.milkdrinkers.enderchester.listener;

import io.github.milkdrinkers.enderchester.Enderchester;
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

        Enderchester.getInstance().getMorePaperLib().scheduling().regionSpecificScheduler(p.getLocation()).run(() -> {
//            p.openInventory(new EnderchesterInventory(Enderchester.getInstance(), p).getInventory());
            p.openInventory(p.getEnderChest());
        });
    }

    //
    /**
     * Handles saving of custom ender chest inventory to main enderchest on close
     *
     * @param e the e
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        /*if (!e.getInventory().getType().equals(InventoryType.ENDER_CHEST))
            return;

        if (!e.getReason().equals(InventoryCloseEvent.Reason.PLAYER))
            return;

        if (!(e.getPlayer() instanceof Player p))
            return;

        if (e.getInventory().getHolder() instanceof EnderchesterInventory) {
            p.getEnderChest().setContents(e.getInventory().getContents());
        }*/
    }
}