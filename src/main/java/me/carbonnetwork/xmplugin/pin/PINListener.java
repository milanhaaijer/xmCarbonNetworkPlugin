package me.carbonnetwork.xmplugin.pin;

import me.carbonnetwork.xmplugin.XmPlugin;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.UUID;

public class PINListener implements Listener {

    private final XmPlugin plugin;

    public PINListener(XmPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = e.getClickedBlock();

            if (clickedBlock != null && clickedBlock.getType().equals(Material.BROWN_CARPET) && e.getHand() == EquipmentSlot.HAND) {

                Player clicker = e.getPlayer();
                Block carpet = e.getClickedBlock();

                if (carpet.hasMetadata("pinValue") && carpet.hasMetadata("pinSetter")) {
                    int pinValue = carpet.getMetadata("pinValue").get(0).asInt();

                    if (carpet.hasMetadata("pinSetter")) {
                        UUID pinSetterUUID = UUID.fromString(carpet.getMetadata("pinSetter").get(0).asString());

                        if (clicker.getInventory().getItemInMainHand().getType() == Material.LIME_DYE) {
                            plugin.getServer().getPlayer(pinSetterUUID).sendMessage("§dPin for " + pinValue + " successful!");
                            clicker.sendMessage("§dPin for " + pinValue + " successful!");

                            carpet.removeMetadata("pinSetter", plugin);
                            carpet.removeMetadata("pinValue", plugin);
                        } else {
                            clicker.sendMessage("§aPlease use your payment item.");
                        }

                    }

                }

            }

        }

    }

}