package me.carbonnetwork.xmplugin.npc;

import me.carbonnetwork.xmplugin.XmPlugin;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Owner;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class NPCManager implements Listener {

    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final Map<UUID, Boolean> playerDialogCompleted = new HashMap<>();

    private final Map<String, Boolean> Task1Completed = new HashMap<>();
    private final Map<String, Boolean> Task2Completed = new HashMap<>();

    private final long cooldownDuration = 15000; // (adjust as needed)

    private final XmPlugin plugin;


    public NPCManager(XmPlugin plugin) {
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

            player.sendMessage("§e[NPC] §d§ldusdavid §r§7> Welkom op de server, " + player.getName() + "!");

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.sendMessage("§e[NPC] §d§ldusdavid §r§7> Veel plezier op CarbonNetwork!");
            }, 60);
            Task1Completed.put(player.getUniqueId().toString(), true);

        } else {

            Material requiredItem = Material.DIAMOND;
            int requiredAmount = 32;

            ItemStack itemInHand = player.getInventory().getItemInMainHand();

            if (itemInHand.getType() == requiredItem && itemInHand.getAmount() >= requiredAmount) {
                itemInHand.setAmount(itemInHand.getAmount() - requiredAmount);

                player.sendMessage("§e[NPC] §d§ldusdavid §r§7> Bedankt voor de diamonds!");
                player.playSound(player.getLocation(), "entity.player.burp", 1.0f, 1.0f);
            } else {
                player.sendMessage("§e[NPC] §d§ldusdavid §r§7> Breng mij 32 diamonds alsjeblieft!");
            }
        }
    }

    private boolean hasRequiredItem(Player player, Material requiredItem, int requiredAmount) {
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        return itemInHand.getType() == requiredItem && itemInHand.getAmount() == requiredAmount;
    }
}
