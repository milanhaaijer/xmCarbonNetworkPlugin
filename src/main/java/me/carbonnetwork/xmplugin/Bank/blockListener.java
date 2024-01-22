package me.carbonnetwork.xmplugin.Bank;

import me.carbonnetwork.xmplugin.XmPlugin;
import me.carbonnetwork.xmplugin.api.APIRequest;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.UUID;

import static me.carbonnetwork.xmplugin.Bank.coinsCounter.*;

public class blockListener implements Listener {

    private final XmPlugin plugin;

    public blockListener(XmPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = e.getClickedBlock();

            if (clickedBlock != null && clickedBlock.getType().equals(Material.PURPUR_STAIRS) && e.getHand() == EquipmentSlot.HAND) {
                e.setCancelled(true);

                Player player = e.getPlayer();
                BankGUI.open(player, this.plugin);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        String response = new APIRequest(this.plugin).sendGETRequest("http://api.carbonnetwork.net/players/find/?query=" + player.getName());
        if (response.startsWith("{")) {
            JSONObject json = new JSONObject(response);
            JSONArray data = json.getJSONArray("data");

            int money = data.getJSONObject(0).getInt("money");

            UUID playerUUID = player.getUniqueId();
            ItemStack clickedItem = e.getCurrentItem();



            if (e.getView().getTitle().contains("[BANK]")) {
                if (clickedItem != null && clickedItem.isSimilar(BankGUI.fillerItem)) {
                    e.setCancelled(true);
                } else if (clickedItem != null && clickedItem.isSimilar(BankGUI.closeItem)) {
                    player.closeInventory();
                } else if (clickedItem != null && clickedItem.isSimilar(BankGUI.withdrawItem)) {
                    WithdrawGUI.open(player, this.plugin);
                } else if (clickedItem != null && clickedItem.isSimilar(BankGUI.depositItem)) {
                    bankGUI.openDepositGUI();
                } else if (clickedItem != null && clickedItem.isSimilar(DepositGUI.returnItem)) {
                    BankGUI.open(player, this.plugin);
                } else if (clickedItem != null && clickedItem.isSimilar(WithdrawGUI.returnItem)) {
                    BankGUI.open(player, this.plugin);
                } else if (clickedItem != null && clickedItem.isSimilar(DepositGUI.everythingItem)) {
                    int coinCount = countCoins1InInventory(player, 1) + 10 * countCoins10InInventory(player, 1) +
                            100 * countCoins100InInventory(player, 1) + 1000 * countCoins1000InInventory(player, 1) +
                            10000 * countCoins10000InInventory(player, 1) + 100000 * countCoins100000InInventory(player, 1) +
                            1000000 * countCoins1000000InInventory(player, 1);
                    e.setCancelled(true);


                    if (coinCount > 0) {
                        DecimalFormat formatter = new DecimalFormat("#,###");
                        String formattedCoinCount = formatter.format(coinCount);

                        // balance + money
                        int newBalance = money + coinCount;
                        String moneyData = "{\"uuid\": \"" + playerUUID + "\", \"money\": \"" + newBalance + "\"}";
                        String url = "http://api.carbonnetwork.net/players/setmoney/";
                        String updateMoney = new APIRequest(this.plugin).sendPATCHRequest(url, moneyData);

                        // Implement a way to remove coins from inventory


                        // Implement a way to refresh the GUI
                        player.closeInventory();
                    } else if (coinCount == 0) {
                        player.playSound(player.getLocation(), "item.shield.block", 1.0f, 1.0f);
                        player.sendMessage("§cYou don't have any coins in your inventory");
                        player.closeInventory();
                    }

                } else e.setCancelled(true);

            }

        }

    }

}
