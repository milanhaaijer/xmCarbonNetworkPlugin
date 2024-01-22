package me.carbonnetwork.xmplugin.npc.Factory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class itemCounter {

    public static ItemStack wheat;

    public static int countWheatInInventory(Player player, int amount) {
        int wheatCount = 0;
        wheat = new ItemStack(Material.WHEAT);

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.isSimilar(wheat)) {
                wheatCount += item.getAmount();
            }
        }

        return wheatCount;
    }

}
