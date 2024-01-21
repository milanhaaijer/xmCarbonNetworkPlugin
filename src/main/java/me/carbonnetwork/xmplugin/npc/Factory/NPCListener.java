package me.carbonnetwork.xmplugin.npc.Factory;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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
        else if (clickedItem != null && clickedItem.isSimilar(SandwichGUI.returnItem)) {
            ChooseGUI.open(player);
        }

    }

}
