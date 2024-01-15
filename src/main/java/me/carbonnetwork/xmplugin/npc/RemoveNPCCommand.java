package me.carbonnetwork.xmplugin.npc;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveNPCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length == 0) {
                p.sendMessage("Usage: /removenpc <id>");
                return true;
            }

            int id = Integer.parseInt(args[0]);

            NPC npc = CitizensAPI.getNPCRegistry().getById(id);
            if (npc != null) {
                npc.destroy();
                p.sendMessage("Removed NPC");
            } else {
                p.sendMessage("NPC not found!");
            }

        }

        return true;
    }
}
