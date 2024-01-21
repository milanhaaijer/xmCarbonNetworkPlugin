package me.carbonnetwork.xmplugin.commands.moneycommands;

import me.carbonnetwork.xmplugin.money.customMoney;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class givePlayerCoins100000 implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /givecoins100000 <amount>");
            return true;
        }

        int amount;
        try {
            amount = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage("Invalid amount. Please provide a valid number.");
            return true;
        }

        player.getInventory().addItem(customMoney.create100000coinItem(amount));
        return true;
    }
}
