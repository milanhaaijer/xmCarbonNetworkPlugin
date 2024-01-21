package me.carbonnetwork.xmplugin.Bank;

import me.carbonnetwork.xmplugin.XmPlugin;
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

public class WithdrawGUI implements Listener {

    public static ItemStack everythingItem;
    public static ItemStack specificItem;
    public static ItemStack returnItem;

    public static void open(Player player, XmPlugin plugin) {

        String response = new APIRequest(plugin).sendGETRequest("http://api.carbonnetwork.net/players/find/?query=" + player.getName());
        if (response.startsWith("{")) {
            JSONObject json = new JSONObject(response);
            JSONArray data = json.getJSONArray("data");

            int money = data.getJSONObject(0).getInt("money");
            DecimalFormat format = new DecimalFormat("#,###");
            String formattedmoney = format.format(money);

            List<String> returnItemLore = new ArrayList<>();

            Inventory withdrawGUI;

            withdrawGUI = Bukkit.createInventory(player, 36, "§8[BANK] Bank Withdrawal");

            everythingItem = new ItemStack(Material.DROPPER, 64);
            specificItem = new ItemStack(Material.OAK_SIGN);
            returnItem = new ItemStack(Material.ARROW);

            ItemMeta everythingItemMeta = everythingItem.getItemMeta();
            ItemMeta specificItemMeta = specificItem.getItemMeta();
            ItemMeta returnItemMeta = returnItem.getItemMeta();

            if (everythingItemMeta == null) everythingItemMeta = Bukkit.getItemFactory().getItemMeta(Material.DROPPER);
            if (specificItemMeta == null) specificItemMeta = Bukkit.getItemFactory().getItemMeta(Material.OAK_SIGN);
            if (returnItemMeta == null) returnItemMeta = Bukkit.getItemFactory().getItemMeta(Material.ARROW);

            everythingItemMeta.setDisplayName("§aEverything in the account");
            specificItemMeta.setDisplayName("§aSpecific amount");
            returnItemMeta.setDisplayName("§aGo back");

            everythingItemMeta.setLore(Arrays.asList("§7Current balance: §6" + formattedmoney));

            returnItemLore.add("§7To Personal Bank Account");

            returnItemMeta.setLore(returnItemLore);

            everythingItem.setItemMeta(everythingItemMeta);
            specificItem.setItemMeta(specificItemMeta);
            returnItem.setItemMeta(returnItemMeta);

            for (int i = 0; i < withdrawGUI.getSize(); i++) {
                withdrawGUI.setItem(i, BankGUI.fillerItem);
            }

            withdrawGUI.setItem(31, returnItem);
            withdrawGUI.setItem(11, everythingItem);
            withdrawGUI.setItem(15, specificItem);

            player.openInventory(withdrawGUI);

        } else player.sendMessage("§aAn error occurred while trying to open your bank account.");

    }

}
