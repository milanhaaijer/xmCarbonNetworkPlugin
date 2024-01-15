package me.carbonnetwork.xmplugin.npc;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
public class SpawnNPCCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 0) {
                p.sendMessage("Usage: /spawnnpc <name>");
                return true;
            }

            NPCRegistry registry = CitizensAPI.getNPCRegistry();
            NPC npc = registry.createNPC(EntityType.PLAYER, args[0]);

            npc.spawn(p.getLocation());

            int NPCId = npc.getId();
            p.sendMessage("Spawned NPC with ID: " + NPCId + " and name: " + args[0]);
        }

        return true;
    }
}
