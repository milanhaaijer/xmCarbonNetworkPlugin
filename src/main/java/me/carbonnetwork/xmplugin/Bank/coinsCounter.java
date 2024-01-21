package me.carbonnetwork.xmplugin.Bank;

import me.carbonnetwork.xmplugin.money.customMoney;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class coinsCounter {

    public static int countCoins1InInventory(Player player, int amount) {
        int coin1Count = 0;

        for (ItemStack item : player.getInventory().getContents()) {
           if (item != null && item.isSimilar(customMoney.create1coinItem(amount))) {
              coin1Count += item.getAmount();
           }
        }

        return coin1Count;
    }

    public static int countCoins10InInventory(Player player, int amount) {
        int coin10Count = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.isSimilar(customMoney.create10coinItem(amount))) {
                coin10Count += item.getAmount();
            }
        }

        return coin10Count;
    }

    public static int countCoins100InInventory(Player player, int amount) {
        int coin100Count = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.isSimilar(customMoney.create100coinItem(amount))) {
                coin100Count += item.getAmount();
            }
        }

        return coin100Count;
    }

    public static int countCoins1000InInventory(Player player, int amount) {
        int coin1000Count = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.isSimilar(customMoney.create1000coinItem(amount))) {
                coin1000Count += item.getAmount();
            }
        }

        return coin1000Count;
    }

    public static int countCoins10000InInventory(Player player, int amount) {
        int coin10000Count = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.isSimilar(customMoney.create10000coinItem(amount))) {
                coin10000Count += item.getAmount();
            }
        }

        return coin10000Count;
    }

    public static int countCoins100000InInventory(Player player, int amount) {
        int coin100000Count = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.isSimilar(customMoney.create100000coinItem(amount))) {
                coin100000Count += item.getAmount();
            }
        }

        return coin100000Count;
    }

    public static int countCoins1000000InInventory(Player player, int amount) {
        int coin1000000Count = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.isSimilar(customMoney.create1000000coinItem(amount))) {
                coin1000000Count += item.getAmount();
            }
        }

        return coin1000000Count;
    }
}
