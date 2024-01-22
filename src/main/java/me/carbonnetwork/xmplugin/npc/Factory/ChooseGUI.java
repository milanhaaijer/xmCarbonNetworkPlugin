package me.carbonnetwork.xmplugin.npc.Factory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
public class ChooseGUI {

    public static ItemStack sandwich;
    public static ItemStack refinedDiamond;
    public static ItemStack fillerItem;
    public static ItemStack placeholderItem;
    public static ItemStack cancelItem;

    public static void open(Player player) {

        List<String> sandwichLore = new ArrayList<>();
        List<String> refinedDiamondLore = new ArrayList<>();

        Inventory chooseGUI;

        chooseGUI = Bukkit.createInventory(player, 54, "Fabricate");

        sandwich = new ItemStack(Material.BREAD);
        refinedDiamond = new ItemStack(Material.DIAMOND);
        fillerItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        placeholderItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        cancelItem = new ItemStack(Material.BARRIER);

        ItemMeta sandwichMeta = sandwich.getItemMeta();
        ItemMeta refinedDiamondMeta = refinedDiamond.getItemMeta();
        ItemMeta fillerItemMeta = fillerItem.getItemMeta();
        ItemMeta placeholderItemMeta = placeholderItem.getItemMeta();
        ItemMeta cancelItemMeta = cancelItem.getItemMeta();

        if (sandwichMeta == null) sandwichMeta = Bukkit.getItemFactory().getItemMeta(Material.BREAD);
        if (refinedDiamondMeta == null) refinedDiamondMeta = Bukkit.getItemFactory().getItemMeta(Material.DIAMOND);
        if (cancelItemMeta == null) cancelItemMeta = Bukkit.getItemFactory().getItemMeta(Material.BARRIER);
        if (fillerItemMeta == null) fillerItemMeta = Bukkit.getItemFactory().getItemMeta(Material.BLACK_STAINED_GLASS_PANE);
        if (placeholderItemMeta == null) placeholderItemMeta = Bukkit.getItemFactory().getItemMeta(Material.WHITE_STAINED_GLASS_PANE);

        sandwichMeta.setDisplayName("§r§fSandwich");
        refinedDiamondMeta.setDisplayName("§bRefined Diamond");
        cancelItemMeta.setDisplayName("§cClose");
        fillerItemMeta.setDisplayName(" ");
        placeholderItemMeta.setDisplayName(" ");

        sandwichLore.add("§7Requires 64 Wheat");
        refinedDiamondLore.add("§7Requires 160 Diamonds");

        sandwichMeta.setLore(sandwichLore);
        refinedDiamondMeta.setLore(refinedDiamondLore);

        sandwich.setItemMeta(sandwichMeta);
        refinedDiamond.setItemMeta(refinedDiamondMeta);
        cancelItem.setItemMeta(cancelItemMeta);
        fillerItem.setItemMeta(fillerItemMeta);
        placeholderItem.setItemMeta(placeholderItemMeta);

        for (int i = 0; i < chooseGUI.getSize(); i++) {
            chooseGUI.setItem(i, fillerItem);
        }

        int paddingSides = 1;
        int paddingTop = 1;
        int paddingBottom = 4;

        int startSlot = (paddingTop * 9) + paddingSides;

        for (int y = 0; y < 6 - paddingTop - paddingBottom; y++) { // Rows minus top and bottom padding
            for (int x = 0; x < 7; x++) { // 7 columns
                int slot = startSlot + y * 9 + x;
                chooseGUI.setItem(slot, placeholderItem);
            }
        }

        chooseGUI.setItem(49, cancelItem);
        chooseGUI.setItem(10, sandwich);
        chooseGUI.setItem(11, refinedDiamond);

        player.openInventory(chooseGUI);
    }

}
