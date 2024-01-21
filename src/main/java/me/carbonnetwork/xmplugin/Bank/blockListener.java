package me.carbonnetwork.xmplugin.Bank;

import me.carbonnetwork.xmplugin.XmPlugin;
import me.carbonnetwork.xmplugin.api.APIRequest;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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

            if (clickedBlock != null && clickedBlock.getType().equals(Material.PURPUR_STAIRS)) {
                e.setCancelled(true);

                Player player = e.getPlayer();
                BankGUI.open(player);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        String response = new APIRequest().sendGETRequest("http://api.carbonnetwork.net/players/find/?query=" + player.getName());
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
                    WithdrawGUI.open(player);
                } else if (clickedItem != null && clickedItem.isSimilar(BankGUI.depositItem)) {
                    DepositGUI.open(player);
                } else if (clickedItem != null && clickedItem.isSimilar(DepositGUI.returnItem)) {
                    BankGUI.open(player);
                } else if (clickedItem != null && clickedItem.isSimilar(WithdrawGUI.returnItem)) {
                    BankGUI.open(player);
                } else if (clickedItem != null && clickedItem.isSimilar(DepositGUI.everythingItem)) {
                    int coinCount = countCoins1InInventory(player, 1) + 10 * countCoins10InInventory(player, 1) +
                            100 * countCoins100InInventory(player, 1) + 1000 * countCoins1000InInventory(player, 1) +
                            10000 * countCoins10000InInventory(player, 1) + 100000 * countCoins100000InInventory(player, 1) +
                            1000000 * countCoins1000000InInventory(player, 1);
                    e.setCancelled(true);
                    if (coinCount == 1) {
                        player.sendMessage("You have 1 coin in your inventory");
                    } else if (coinCount != 1 && coinCount > 0) {
                        DecimalFormat formatter = new DecimalFormat("#,###");
                        String formattedCoinCount = formatter.format(coinCount);

                        // balance + money
                        String moneydata = "{\"uuid\": \"" + playerUUID + "\", \"money\": \"" + money + "\"}";
                        String url = "http://api.carbonnetwork.net/players/setmoney/";
                        int code = new APIRequest(this.plugin).sendPATCHRequest(url, moneydata);
                    }

                } else e.setCancelled(true);

            }

        }

    }
}
