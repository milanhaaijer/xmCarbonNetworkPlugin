package me.carbonnetwork.xmplugin.pin;

import me.carbonnetwork.xmplugin.XmPlugin;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PINCommand implements CommandExecutor {

    private final XmPlugin plugin;

    public PINCommand(XmPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
                try {
                    int pinValue = Integer.parseInt(args[1]);

                    // Check if the target block is a brown carpet
                    Block targetBlock = player.getTargetBlock(null, 5);
                    if (targetBlock.getType() == Material.BROWN_CARPET) {
                        plugin.setPinValue(targetBlock, pinValue, player);
                        player.sendMessage("§aPin value set successfully!");
                    } else {
                        player.sendMessage("§cYou must be looking at brown carpet to set a pin value.");
                    }
                } catch (NumberFormatException e) {
                    player.sendMessage("§cInvalid pin value. Please use /pin set <number>");
                }
            } else {
                player.sendMessage("§cInvalid command. Usage: /pin set <number>");
            }

            return true;
        }

        return false;
    }

}
