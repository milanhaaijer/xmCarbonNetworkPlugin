package me.carbonnetwork.xmplugin.npc.Factory.items;

import me.carbonnetwork.xmplugin.XmPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class SandwichGUI {

    private static final int returnItemID = 1;
    private static final int confirmID = 1;
    private static final int wheatID = 1;
    public static ItemStack sandwich;
    public static ItemStack wheat;
    public static ItemStack returnItem;
    public static ItemStack fillerItem;
    public static ItemStack cancelItem;
    public static ItemStack infoItem;
    public static ItemStack confirmItem;

    public static void open(Player player) {

        List<String> returnItemLore = new ArrayList<>();
        List<String> infoItemLore = new ArrayList<>();
        List<String> confirmItemLore = new ArrayList<>();

        Inventory breadGUI = Bukkit.createInventory(player, 54, "Factory");

        wheat = new ItemStack(Material.WHEAT, 64);
        sandwich = new ItemStack(Material.BREAD);

        fillerItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        cancelItem = new ItemStack(Material.BARRIER);
        returnItem = new ItemStack(Material.ARROW);
        infoItem = new ItemStack(Material.LAVA_BUCKET);
        confirmItem = new ItemStack(Material.LIME_TERRACOTTA);

        ItemMeta wheatMeta = wheat.getItemMeta();
        ItemMeta sandwichMeta = sandwich.getItemMeta();
        ItemMeta fillerItemMeta = fillerItem.getItemMeta();
        ItemMeta cancelItemMeta = cancelItem.getItemMeta();
        ItemMeta returnItemMeta = returnItem.getItemMeta();
        ItemMeta infoItemMeta = infoItem.getItemMeta();
        ItemMeta confirmItemMeta = confirmItem.getItemMeta();

        if (wheatMeta == null) wheatMeta = Bukkit.getItemFactory().getItemMeta(Material.WHEAT);
        if (sandwichMeta == null) sandwichMeta = Bukkit.getItemFactory().getItemMeta(Material.BREAD);
        if (cancelItemMeta == null) cancelItemMeta = Bukkit.getItemFactory().getItemMeta(Material.BARRIER);
        if (fillerItemMeta == null) fillerItemMeta = Bukkit.getItemFactory().getItemMeta(Material.BLACK_STAINED_GLASS_PANE);
        if (returnItemMeta == null) returnItemMeta = Bukkit.getItemFactory().getItemMeta(Material.ARROW);
        if (infoItemMeta == null) infoItemMeta = Bukkit.getItemFactory().getItemMeta(Material.LAVA_BUCKET);
        if (confirmItemMeta == null) confirmItemMeta = Bukkit.getItemFactory().getItemMeta(Material.LIME_TERRACOTTA);

        sandwichMeta.setDisplayName("§fSandwich");
        cancelItemMeta.setDisplayName("§cClose");
        fillerItemMeta.setDisplayName(" ");
        returnItemMeta.setDisplayName("§aGo Back");
        infoItemMeta.setDisplayName("§aFabricate");
        confirmItemMeta.setDisplayName("§aConfirm");

        returnItemLore.add("§7To Item Fabrication");
        infoItemLore.add("§f<-- §6Required items");
        infoItemLore.add("   §6Result item §f-->");
        confirmItemLore.add("§6Click to confirm");

        returnItemMeta.setLore(returnItemLore);
        infoItemMeta.setLore(infoItemLore);
        confirmItemMeta.setLore(confirmItemLore);

        wheatMeta.getPersistentDataContainer().set(getKey("wheatID"), PersistentDataType.INTEGER, wheatID);
        returnItemMeta.getPersistentDataContainer().set(getKey("returnItemID"), PersistentDataType.INTEGER, returnItemID);
        confirmItemMeta.getPersistentDataContainer().set(getKey("confirmItemID"), PersistentDataType.INTEGER, confirmID);

        wheat.setItemMeta(wheatMeta);
        sandwich.setItemMeta(sandwichMeta);
        fillerItem.setItemMeta(fillerItemMeta);
        cancelItem.setItemMeta(cancelItemMeta);
        returnItem.setItemMeta(returnItemMeta);
        infoItem.setItemMeta(infoItemMeta);
        confirmItem.setItemMeta(confirmItemMeta);

        setFillerItemInSlots(breadGUI, fillerItem, 0, 9);
        setFillerItemInSlots(breadGUI, fillerItem, 11, 12);
        setFillerItemInSlots(breadGUI, fillerItem, 14, 15);
        setFillerItemInSlots(breadGUI, fillerItem, 17, 30);
        setFillerItemInSlots(breadGUI, fillerItem, 32, 47);
        setFillerItemInSlots(breadGUI, fillerItem, 50, 53);

        breadGUI.setItem(49, cancelItem);
        breadGUI.setItem(48, returnItem);
        breadGUI.setItem(13, infoItem);
        breadGUI.setItem(31, confirmItem);
        breadGUI.setItem(10, wheat);
        breadGUI.setItem(16, sandwich);

        player.openInventory(breadGUI);
    }

    private static void setFillerItemInSlots(Inventory inventory, ItemStack exampleItem, int start, int end) {
        for (int i = start; i <= end; i++) {
            inventory.setItem(i, exampleItem);
        }
    }

    private static NamespacedKey getKey(String identifier) {
        return new NamespacedKey(XmPlugin.getPlugin(XmPlugin.class), identifier);
    }
}

