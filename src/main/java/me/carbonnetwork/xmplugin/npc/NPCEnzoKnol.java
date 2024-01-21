package me.carbonnetwork.xmplugin.npc;

import me.carbonnetwork.xmplugin.XmPlugin;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NPCEnzoKnol implements Listener {

    private final XmPlugin plugin;
    private ItemStack exampleItem;
    public NPCEnzoKnol(XmPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(NPCRightClickEvent e) {
        Player player = e.getClicker();
        NPC npc = e.getNPC();

        if (npc.getId() != 21) return;
        openCustomGUI(player);
    }

    private void addGlint(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta != null) {
            itemMeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(itemMeta);
        }
    }

    private void openCustomGUI(Player player) {
        Inventory customGUI = Bukkit.createInventory(player, 54, "EnzoKnol's GUI");

        exampleItem = new ItemStack(Material.GRASS_BLOCK);

        ItemMeta itemMeta = exampleItem.getItemMeta();

        if (itemMeta == null) itemMeta = Bukkit.getItemFactory().getItemMeta(Material.GRASS_BLOCK);

        itemMeta.setDisplayName("§rTeleport");
        exampleItem.setItemMeta(itemMeta);
        addGlint(exampleItem);

        customGUI.setItem(22, exampleItem);

        player.openInventory(customGUI);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem != null && clickedItem.isSimilar(exampleItem)) {
            // Prevent taking the grass block
            event.setCancelled(true);

            // Perform teleportation
            Location teleportLocation = new Location(Bukkit.getWorld("world"), 61.5, 69, 148.5, 270, 0);
            player.teleport(teleportLocation);
        }
    }

}
