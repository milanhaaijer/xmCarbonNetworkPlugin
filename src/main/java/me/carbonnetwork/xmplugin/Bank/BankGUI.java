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
import java.util.Arrays;

public class BankGUI implements Listener {

    private final XmPlugin plugin;

    public BankGUI(XmPlugin plugin) {
        this.plugin = plugin;
    }

    public static ItemStack fillerItem;
    public static ItemStack depositItem;
    public static ItemStack withdrawItem;
    public static ItemStack closeItem;

    public static void open(Player player, XmPlugin plugin) {

        String response = new APIRequest(plugin).sendGETRequest("http://api.carbonnetwork.net/players/find/?query=" + player.getName());
        if (response.startsWith("{")) {
            JSONObject json = new JSONObject(response);
            JSONArray data = json.getJSONArray("data");

            int money = data.getJSONObject(0).getInt("money");
            DecimalFormat format = new DecimalFormat("#,###");
            String formattedmoney = format.format(money);

            Inventory bankGUI;

            bankGUI = Bukkit.createInventory(player, 36, "§8[BANK] Personal Bank Account");

            fillerItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            depositItem = new ItemStack(Material.CHEST);
            withdrawItem = new ItemStack(Material.DROPPER);
            closeItem = new ItemStack(Material.BARRIER);

            ItemMeta fillerItemMeta = fillerItem.getItemMeta();
            ItemMeta depositItemMeta = depositItem.getItemMeta();
            ItemMeta withdrawItemMeta = withdrawItem.getItemMeta();
            ItemMeta closeItemMeta = closeItem.getItemMeta();

            if (fillerItemMeta == null)
                fillerItemMeta = Bukkit.getItemFactory().getItemMeta(Material.BLACK_STAINED_GLASS_PANE);
            if (depositItemMeta == null) depositItemMeta = Bukkit.getItemFactory().getItemMeta(Material.CHEST);
            if (withdrawItemMeta == null) withdrawItemMeta = Bukkit.getItemFactory().getItemMeta(Material.DROPPER);
            if (closeItemMeta == null) closeItemMeta = Bukkit.getItemFactory().getItemMeta(Material.BARRIER);

            fillerItemMeta.setDisplayName(" ");
            depositItemMeta.setDisplayName("§aDeposit Coins");
            withdrawItemMeta.setDisplayName("§aWithdraw Coins");
            closeItemMeta.setDisplayName("§cClose");

            depositItemMeta.setLore(Arrays.asList("§7Current balance: §6" + formattedmoney));
            withdrawItemMeta.setLore(Arrays.asList("§7Current balance: §6" + formattedmoney));

            fillerItem.setItemMeta(fillerItemMeta);
            depositItem.setItemMeta(depositItemMeta);
            withdrawItem.setItemMeta(withdrawItemMeta);
            closeItem.setItemMeta(closeItemMeta);

            for (int i = 0; i < bankGUI.getSize(); i++) {
                bankGUI.setItem(i, fillerItem);
            }

            bankGUI.setItem(11, depositItem);
            bankGUI.setItem(15, withdrawItem);
            bankGUI.setItem(31, closeItem);

            player.openInventory(bankGUI);

        } else player.sendMessage("§aAn error occurred while trying to open your bank account.");

    }

}
