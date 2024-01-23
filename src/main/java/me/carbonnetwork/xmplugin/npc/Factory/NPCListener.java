package me.carbonnetwork.xmplugin.npc.Factory;

import me.carbonnetwork.xmplugin.npc.Factory.itemGUIs.RefinedDiamondGUI;
import me.carbonnetwork.xmplugin.npc.Factory.itemGUIs.SandwichGUI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NPCListener implements Listener {
    @EventHandler
    public void onRightClick(NPCRightClickEvent e) {
        Player player = e.getClicker();
        NPC npc = e.getNPC();

        if (npc.getId() != 29) return;
        ChooseGUI.open(player);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem != null && clickedItem.isSimilar(ChooseGUI.fillerItem)) {
            e.setCancelled(true);
        }
        else if (clickedItem != null && clickedItem.isSimilar(ChooseGUI.sandwich)) {
            SandwichGUI.open(player);
        }
        else if (clickedItem != null && clickedItem.isSimilar(ChooseGUI.refinedDiamond)) {
            RefinedDiamondGUI.open(player);
        }
        else if (clickedItem != null && clickedItem.isSimilar(ChooseGUI.cancelItem)) {
            player.closeInventory();
        }
        else if (clickedItem != null && clickedItem.isSimilar(ChooseGUI.placeholderItem)) {
            e.setCancelled(true);
        }
        else if (clickedItem != null && clickedItem.isSimilar(SandwichGUI.infoItem)) {
            e.setCancelled(true);
        }
        else if (clickedItem != null && clickedItem.isSimilar(SandwichGUI.returnItem)) {
            ChooseGUI.open(player);
        }
        else if (clickedItem != null && clickedItem.isSimilar(SandwichGUI.cancelItem)) {
            e.setCancelled(true);
        }
        else if (clickedItem != null && clickedItem.isSimilar(SandwichGUI.sandwich)) {
            e.setCancelled(true);
        }
        else if (clickedItem != null && clickedItem.isSimilar(SandwichGUI.wheat)) {
            e.setCancelled(true);
        }
        else if (clickedItem != null && clickedItem.isSimilar(SandwichGUI.fillerItem)) {
            e.setCancelled(true);
        }
        else if (clickedItem != null && clickedItem.isSimilar(SandwichGUI.confirmItem)) {
            int wheatcount = itemCounter.countWheatInInventory(player, 1);

            if (wheatcount < 64) {
                e.setCancelled(true);
                player.playSound(player.getLocation(), "item.shield.block", 1.0f, 1.0f);
                player.sendMessage("§cYou don't have the required materials.");
            }
            else if (wheatcount >= 64) {
                e.setCancelled(true);

                ItemStack wheatStack = new ItemStack(Material.WHEAT, 64);
                player.getInventory().removeItem(wheatStack);
                player.getInventory().addItem(SandwichGUI.sandwich);
                player.playSound(player.getLocation(), "entity.experience_orb.pickup", 1.0f, 1.0f);
            }
        }
        else if (clickedItem != null && clickedItem.isSimilar(RefinedDiamondGUI.refinedDiamond)) {
            e.setCancelled(true);
        }
        else if (clickedItem != null && clickedItem.isSimilar(RefinedDiamondGUI.diamondStack)) {
            e.setCancelled(true);
        }
        else if (clickedItem != null && clickedItem.isSimilar(RefinedDiamondGUI.diamondHalfStack)) {
            e.setCancelled(true);
        }
        else if (clickedItem != null && clickedItem.isSimilar(RefinedDiamondGUI.confirmItem)) {
            int diamondCount = itemCounter.countDiamondInInventory(player,1);

            if (diamondCount < 160) {
                e.setCancelled(true);
                player.playSound(player.getLocation(), "item.shield.block", 1.0f, 1.0f);
                player.sendMessage("§cYou don't have the required materials.");
            }
            else if (diamondCount >= 160) {
                e.setCancelled(true);

                ItemStack diamondRequired = new ItemStack(Material.DIAMOND, 160);
                player.getInventory().removeItem(diamondRequired);
                player.getInventory().addItem(RefinedDiamondGUI.refinedDiamond);
                player.playSound(player.getLocation(), "entity.experience_orb.pickup", 1.0f, 1.0f);
            }
        }
        else if (clickedItem != null && clickedItem.isSimilar(RefinedDiamondGUI.infoItem)) {
            e.setCancelled(true);
        }
        else if (clickedItem != null && clickedItem.isSimilar(RefinedDiamondGUI.returnItem)) {
            ChooseGUI.open(player);
        }
    }

}
