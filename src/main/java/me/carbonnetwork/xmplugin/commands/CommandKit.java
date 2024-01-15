package me.carbonnetwork.xmplugin.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandKit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;


            ItemStack stone_sword = new ItemStack(Material.STONE_SWORD);

            ItemStack stone_pickaxe = new ItemStack(Material.STONE_PICKAXE);

            ItemStack stone_axe = new ItemStack(Material.STONE_AXE);

            ItemStack stone_shovel = new ItemStack(Material.STONE_SHOVEL);

            ItemStack stone_hoe = new ItemStack(Material.STONE_HOE);

            player.getInventory().addItem(stone_sword, stone_pickaxe, stone_axe, stone_shovel, stone_hoe);

            player.sendMessage("§aThis will help you start your quest to greatness");
        }

        return true;
    }
}
