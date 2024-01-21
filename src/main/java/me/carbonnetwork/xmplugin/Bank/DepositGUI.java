package me.carbonnetwork.xmplugin.Bank;

import me.carbonnetwork.xmplugin.api.APIRequest;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepositGUI implements Listener {

    public static ItemStack everythingItem;
    public static ItemStack specificItem;
    public static ItemStack returnItem;

    public static void open(Player player) {

        String response = new APIRequest().sendGETRequest("http://api.carbonnetwork.net/players/find/?query=" + player.getName());
        if (response.startsWith("{")) {
            JSONObject json = new JSONObject(response);
            JSONArray data = json.getJSONArray("data");

            int money = data.getJSONObject(0).getInt("money");
            DecimalFormat format = new DecimalFormat("#,###");
            String formattedmoney = format.format(money);


            List<String> returnItemLore = new ArrayList<>();

            Inventory depositGUI;

            depositGUI = Bukkit.createInventory(player, 36, "§8[BANK] Bank Deposit");

            everythingItem = new ItemStack(Material.CHEST, 64);
            specificItem = new ItemStack(Material.OAK_SIGN);
            returnItem = new ItemStack(Material.ARROW);

            ItemMeta everythingItemMeta = everythingItem.getItemMeta();
            ItemMeta specificItemMeta = specificItem.getItemMeta();
            ItemMeta returnItemMeta = returnItem.getItemMeta();

            if (everythingItemMeta == null) everythingItemMeta = Bukkit.getItemFactory().getItemMeta(Material.CHEST);
            if (specificItemMeta == null) specificItemMeta = Bukkit.getItemFactory().getItemMeta(Material.OAK_SIGN);
            if (returnItemMeta == null) returnItemMeta = Bukkit.getItemFactory().getItemMeta(Material.ARROW);

            everythingItemMeta.setDisplayName("§aEverything in your inventory");
            specificItemMeta.setDisplayName("§aSpecific amount");
            returnItemMeta.setDisplayName("§aGo back");

            everythingItemMeta.setLore(Arrays.asList("§7Current balance: §6" + formattedmoney));

            returnItemLore.add("§7To Personal Bank Account");

            returnItemMeta.setLore(returnItemLore);

            everythingItem.setItemMeta(everythingItemMeta);
            specificItem.setItemMeta(specificItemMeta);
            returnItem.setItemMeta(returnItemMeta);

            for (int i = 0; i < depositGUI.getSize(); i++) {
                depositGUI.setItem(i, BankGUI.fillerItem);
            }

            depositGUI.setItem(31, returnItem);
            depositGUI.setItem(11, everythingItem);
            depositGUI.setItem(15, specificItem);

            player.openInventory(depositGUI);

        } else player.sendMessage("§aAn error occurred while trying to open your bank account.");

    }

}

