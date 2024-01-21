package me.carbonnetwork.xmplugin.npc;

import me.carbonnetwork.xmplugin.XmPlugin;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Owner;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class NPCdusdavid implements Listener {


    private final Map<String, Boolean> Task1Completed = new HashMap<>();
    private final Map<String, Boolean> Task2Completed = new HashMap<>();
    private final Map<String, Boolean> Task3Completed = new HashMap<>();
    private final Map<UUID, Integer> wheatCropsBrokenMap = new HashMap<>();
    private final XmPlugin plugin;


    public NPCdusdavid(XmPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        setupNPC();
    }

    private void setupNPC() {
        // Retrieve the NPC from the registry if it already exists
        NPCRegistry npcRegistry = CitizensAPI.getNPCRegistry();
        NPC npc = CitizensAPI.getNPCRegistry().getById(3); // Replace the number with the ID of your existing NPC

        if (npc == null) {
            plugin.getLogger().warning("NPC not found with ID 3. Make sure to create it first.");
            return;
        }

        String ownerName = "xmPlugin";
        npc.getOrAddTrait(Owner.class).setOwner(ownerName);
    }

    @EventHandler
    public void onRightClick(NPCRightClickEvent event) {
        Player player = event.getClicker();
        NPC npc = event.getNPC();

        if (npc.getId() != 3) return;

        if (!Task1Completed.containsKey(player.getUniqueId().toString())) {

            player.sendMessage("§e[NPC] §d§ldusdavid §r§7> Welcome to the server, " + player.getName() + "!");

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.sendMessage("§e[NPC] §d§ldusdavid §r§7> Have fun!");
            }, 60);
            Task1Completed.put(player.getUniqueId().toString(), true);

        } else if (Task1Completed.containsKey(player.getUniqueId().toString()) && !Task2Completed.containsKey(player.getUniqueId().toString())){

            Material requiredItem = Material.DIAMOND;
            int requiredAmount = 32;

            ItemStack itemInHand = player.getInventory().getItemInMainHand();

            if (itemInHand.getType() == requiredItem && itemInHand.getAmount() >= requiredAmount) {
                itemInHand.setAmount(itemInHand.getAmount() - requiredAmount);

                player.sendMessage("§e[NPC] §d§ldusdavid §r§7> Thanks for the diamonds!");
                player.playSound(player.getLocation(), "entity.player.burp", 1.0f, 1.0f);
                Task2Completed.put(player.getUniqueId().toString(), true);
            } else {
                player.sendMessage("§e[NPC] §d§ldusdavid §r§7> Bring me 32 diamonds please");
            }
        } else if (Task1Completed.containsKey(player.getUniqueId().toString()) && Task2Completed.containsKey(player.getUniqueId().toString()) && !Task3Completed.containsKey(player.getUniqueId().toString())) {
            if (isMatureWheatCrop(player.getLocation().getBlock())) {
                incrementWheatCropsBroken(player);
            }

            int requiredWheatCrops = 8;
            int wheatCropsBroken = wheatCropsBrokenMap.getOrDefault(player.getUniqueId() , 0);

            if (wheatCropsBroken >= requiredWheatCrops) {
                player.sendMessage("§e[NPC] §d§ldusdavid §r§7> Well done! You have collected 8 wheat");
                player.playSound(player.getLocation(), "entity.firework_rocket.launch", 1.0f, 1.0f);
                Task3Completed.put(player.getUniqueId().toString(), true);
            } else {
                player.sendMessage("§e[NPC] §d§ldusdavid §r§7> Collect " + (requiredWheatCrops - wheatCropsBroken) + " more wheat");
            }
        }
    }

    private boolean hasRequiredItem(Player player, Material requiredItem, int requiredAmount) {
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        return itemInHand.getType() == requiredItem && itemInHand.getAmount() == requiredAmount;
    }

    @EventHandler
    public void onWheatCropBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if (isMatureWheatCrop(block)) {
            incrementWheatCropsBroken(player);
        }
    }

    private boolean isMatureWheatCrop(Block block) {
        Material blockType = block.getType();

        if (blockType == Material.WHEAT) {
            BlockData blockData = block.getBlockData();

            return blockData.getAsString().contains("age=7");
        }
        return false;
    }

    private void incrementWheatCropsBroken(Player player) {
        UUID playerUUID = player.getUniqueId();
        int wheatCropsBroken = wheatCropsBrokenMap.getOrDefault(playerUUID, 0);
        wheatCropsBroken++;
        wheatCropsBrokenMap.put(playerUUID, wheatCropsBroken);
    }
}
