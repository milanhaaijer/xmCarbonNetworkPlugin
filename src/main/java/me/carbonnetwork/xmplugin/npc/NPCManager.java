package me.carbonnetwork.xmplugin.npc;

import me.carbonnetwork.xmplugin.XmPlugin;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Owner;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCManager implements Listener {

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
        NPC npc = event.getNPC();

        // Check if the right-clicked NPC is the one you want to handle
        int yourNPCId = 3;
        if (npc.getId() == yourNPCId) {
            event.getClicker().sendMessage("§e[NPC] §ddusdavid§f: He daar schattig douwdruppeltje!");
        }
    }
}
