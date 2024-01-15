package me.carbonnetwork.xmplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.setFoodLevel(20);
            player.sendMessage("§aYou suddenly don't feel hungry anymore");

            return true;
        } else {
            sender.sendMessage("This command can only be used by players.");
            return false;
        }
    }

}
