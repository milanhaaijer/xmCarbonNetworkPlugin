package me.carbonnetwork.xmplugin.money;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
public class customMoney {
    public static ItemStack create1coinItem(int amount) {

        ItemStack coins1 = new ItemStack(Material.PAPER, amount);
        ItemMeta coins1Meta = coins1.getItemMeta();

        coins1Meta.setDisplayName("§71 Coin");
        coins1Meta.setLore(Arrays.asList("§e§m-----==[§r §6Currency Item §e§m]==-----"));

        coins1.setItemMeta(coins1Meta);

        return coins1;
    }

    public static ItemStack create10coinItem(int amount) {

        ItemStack coins10 = new ItemStack(Material.PAPER, amount);
        ItemMeta coins10Meta = coins10.getItemMeta();

        coins10Meta.setDisplayName("§810 Coins");
        coins10Meta.setLore(Arrays.asList("§e§m-----==[§r §6Currency Item §e§m]==-----"));

        coins10.setItemMeta(coins10Meta);

        return coins10;
    }

    public static ItemStack create100coinItem(int amount) {

        ItemStack coins100 = new ItemStack(Material.PAPER, amount);
        ItemMeta coins100Meta = coins100.getItemMeta();

        coins100Meta.setDisplayName("§9100 Coins");
        coins100Meta.setLore(Arrays.asList("§e§m-----==[§r §6Currency Item §e§m]==-----"));

        coins100.setItemMeta(coins100Meta);

        return coins100;
    }

    public static ItemStack create1000coinItem(int amount) {

        ItemStack coins1000 = new ItemStack(Material.PAPER, amount);
        ItemMeta coins1000Meta = coins1000.getItemMeta();

        coins1000Meta.setDisplayName("§a1000 Coins");
        coins1000Meta.setLore(Arrays.asList("§e§m-----==[§r §6Currency Item §e§m]==-----"));

        coins1000.setItemMeta(coins1000Meta);

        return coins1000;
    }

    public static ItemStack create10000coinItem(int amount) {

        ItemStack coins10000 = new ItemStack(Material.PAPER, amount);
        ItemMeta coins10000Meta = coins10000.getItemMeta();

        coins10000Meta.setDisplayName("§d10000 Coins");
        coins10000Meta.setLore(Arrays.asList("§e§m-----==[§r §6Currency Item §e§m]==-----"));

        coins10000.setItemMeta(coins10000Meta);

        return coins10000;
    }

    public static ItemStack create100000coinItem(int amount) {

        ItemStack coins100000 = new ItemStack(Material.PAPER, amount);
        ItemMeta coins100000Meta = coins100000.getItemMeta();

        coins100000Meta.setDisplayName("§6100000 Coins");
        coins100000Meta.setLore(Arrays.asList("§e§m-----==[§r §6Currency Item §e§m]==-----"));

        coins100000.setItemMeta(coins100000Meta);

        return coins100000;
    }

    public static ItemStack create1000000coinItem(int amount) {

        ItemStack coins1000000 = new ItemStack(Material.PAPER, amount);
        ItemMeta coins1000000Meta = coins1000000.getItemMeta();

        coins1000000Meta.setDisplayName("§51000000 Coins");
        coins1000000Meta.setLore(Arrays.asList("§e§m-----==[§r §6Currency Item §e§m]==-----"));

        coins1000000.setItemMeta(coins1000000Meta);

        return coins1000000;
    }
}
