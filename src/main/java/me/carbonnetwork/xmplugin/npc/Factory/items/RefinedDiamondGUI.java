package me.carbonnetwork.xmplugin.npc.Factory.items;

import me.carbonnetwork.xmplugin.XmPlugin;
import me.carbonnetwork.xmplugin.npc.Factory.ChooseGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class RefinedDiamondGUI {

    private static final int confirmID = 2;
    private static final int refinedDiamondID = 2;
    private static final int diamondStackID = 2;
    private static final int diamondHalfStackID = 2;
    private static final int returnItemID = 2;
    public static ItemStack diamondStack;
    public static ItemStack diamondHalfStack;
    public static ItemStack refinedDiamond;
    public static ItemStack confirmItem;
    public static ItemStack returnItem;
    public static ItemStack infoItem;

    public static void open(Player player) {

        List<String> returnItemLore = new ArrayList<>();
        List<String> infoItemLore = new ArrayList<>();
        List<String> confirmItemLore = new ArrayList<>();

        Inventory refinedDiamondGUI = Bukkit.createInventory(player, 54, "Factory");

        diamondStack = new ItemStack(Material.DIAMOND, 64);
        diamondHalfStack = new ItemStack(Material.DIAMOND, 32);
        refinedDiamond = new ItemStack(Material.DIAMOND);
        confirmItem = new ItemStack(Material.LIME_TERRACOTTA);
        returnItem = new ItemStack(Material.ARROW);
        infoItem = new ItemStack(Material.LAVA_BUCKET);

        ItemMeta diamondStackMeta = diamondStack.getItemMeta();
        ItemMeta diamondHalfStackMeta = diamondHalfStack.getItemMeta();
        ItemMeta refinedDiamondMeta = refinedDiamond.getItemMeta();
        ItemMeta confirmItemMeta = confirmItem.getItemMeta();
        ItemMeta returnItemMeta = returnItem.getItemMeta();
        ItemMeta infoItemMeta = infoItem.getItemMeta();

        if (diamondStackMeta == null) diamondStackMeta = Bukkit.getItemFactory().getItemMeta(Material.DIAMOND);
        if (diamondHalfStackMeta == null) diamondHalfStackMeta = Bukkit.getItemFactory().getItemMeta(Material.DIAMOND);
        if (refinedDiamondMeta == null) refinedDiamondMeta = Bukkit.getItemFactory().getItemMeta(Material.DIAMOND);
        if (returnItemMeta == null) returnItemMeta = Bukkit.getItemFactory().getItemMeta(Material.ARROW);
        if (infoItemMeta == null) infoItemMeta = Bukkit.getItemFactory().getItemMeta(Material.LAVA_BUCKET);

        confirmItemMeta.setDisplayName("§aConfirm");
        refinedDiamondMeta.setDisplayName("§bRefined Diamond");
        returnItemMeta.setDisplayName("§aGo Back");
        infoItemMeta.setDisplayName("§aFabricate");

        refinedDiamond.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1);

        returnItemLore.add("§7To Item Fabrication");
        infoItemLore.add("§f<-- §6Required items");
        infoItemLore.add("   §6Result item §f-->");
        confirmItemLore.add("§6Click to confirm");

        returnItemMeta.setLore(returnItemLore);
        infoItemMeta.setLore(infoItemLore);
        confirmItemMeta.setLore(confirmItemLore);

        refinedDiamondMeta.getPersistentDataContainer().set(getKey("refinedDiamondID"), PersistentDataType.INTEGER, refinedDiamondID);
        confirmItemMeta.getPersistentDataContainer().set(getKey("confirmItemID"), PersistentDataType.INTEGER, confirmID);
        diamondStackMeta.getPersistentDataContainer().set(getKey("diamondStackID"), PersistentDataType.INTEGER, diamondStackID);
        diamondHalfStackMeta.getPersistentDataContainer().set(getKey("diamondHalfStackID"), PersistentDataType.INTEGER, diamondHalfStackID);
        returnItemMeta.getPersistentDataContainer().set(getKey("returnItemID"), PersistentDataType.INTEGER, returnItemID);

        diamondStack.setItemMeta(diamondStackMeta);
        diamondHalfStack.setItemMeta(diamondHalfStackMeta);
        refinedDiamond.setItemMeta(refinedDiamondMeta);
        confirmItem.setItemMeta(confirmItemMeta);
        returnItem.setItemMeta(returnItemMeta);
        infoItem.setItemMeta(infoItemMeta);

        for (int i = 0; i < refinedDiamondGUI.getSize(); i++) {
            refinedDiamondGUI.setItem(i, ChooseGUI.fillerItem);
        }

        refinedDiamondGUI.setItem(49, ChooseGUI.cancelItem);
        refinedDiamondGUI.setItem(48, returnItem);
        refinedDiamondGUI.setItem(13, infoItem);
        refinedDiamondGUI.setItem(31, confirmItem);
        refinedDiamondGUI.setItem(10, diamondStack);
        refinedDiamondGUI.setItem(11, diamondStack);
        refinedDiamondGUI.setItem(19, diamondHalfStack);
        refinedDiamondGUI.setItem(16, refinedDiamond);

        player.openInventory(refinedDiamondGUI);

    }

    private static NamespacedKey getKey(String identifier) {
        return new NamespacedKey(XmPlugin.getPlugin(XmPlugin.class), identifier);
    }
}
