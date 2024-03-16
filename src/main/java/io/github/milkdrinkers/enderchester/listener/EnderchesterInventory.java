package io.github.milkdrinkers.enderchester.listener;

import io.github.milkdrinkers.enderchester.Enderchester;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

/**
 * The class Enderchester inventory, used for emulating a fake ender chest with this plugin as its holder.
 */
public class EnderchesterInventory implements InventoryHolder {
    /**
     * The Inventory.
     */
    public final Inventory inventory;

    /**
     * Instantiates a new custom Ender chest inventory.
     *
     * @param enderchester the enderchester
     * @param p            the p
     */
    public EnderchesterInventory(Enderchester enderchester, HumanEntity p) {
        inventory = enderchester.getServer().createInventory(this, InventoryType.ENDER_CHEST, InventoryType.ENDER_CHEST.defaultTitle());
        inventory.setContents(p.getEnderChest().getContents());
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
