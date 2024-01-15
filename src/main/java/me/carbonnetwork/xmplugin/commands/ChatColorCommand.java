package me.carbonnetwork.xmplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatColorCommand implements CommandExecutor, Listener {

    private final Map<UUID, ChatColor> playerChatColors = new HashMap<>();
    private final JavaPlugin plugin;

    public ChatColorCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("setchatcolor").setExecutor(this);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            sender.sendMessage("Usage: /setchatcolor <color>");
            return true;
        }

        ChatColor newColor;
        try {
            newColor = ChatColor.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            sender.sendMessage("§aPlease choose from these colors:\n" + getReadableColorList());
            return true;
        }

        playerChatColors.put(player.getUniqueId(), newColor);
        sender.sendMessage("§7[§9xmPlugin§7] §aChat color set to " + getReadableColor(newColor));

        return true;
    }

    private String getReadableColorList() {
        StringBuilder colors = new StringBuilder();
        ChatColor[] values = ChatColor.values();

        for (int i = 0; i < values.length; i++) {
            if (values[i] != ChatColor.RESET) {
                colors.append(getReadableColor(values[i]));

                if (i < values.length - 2) {
                    colors.append(", ");
                }
            }
        }

        return colors.toString();
    }

    private String getReadableColor(ChatColor color) {
        switch (color) {
            case BLACK:
                return "§0Black";
            case DARK_BLUE:
                return "§1Dark Blue";
            case DARK_GREEN:
                return "§2Dark Green";
            case DARK_AQUA:
                return "§3Dark Aqua";
            case DARK_RED:
                return "§4Dark Red";
            case DARK_PURPLE:
                return "§5Dark Purple";
            case GOLD:
                return "§6Gold";
            case GRAY:
                return "§7Gray";
            case DARK_GRAY:
                return "§8Dark Gray";
            case BLUE:
                return "§9Blue";
            case GREEN:
                return "§aGreen";
            case AQUA:
                return "§bAqua";
            case RED:
                return "§cRed";
            case LIGHT_PURPLE:
                return "§dLight Purple";
            case YELLOW:
                return "§eYellow";
            case WHITE:
                return "§fWhite";
            case MAGIC:
                return "§kMagic§f(Magic)";
            case BOLD:
                return "§lBold§r";
            case STRIKETHROUGH:
                return "§mStrikethrough§r";
            case UNDERLINE:
                return "§nUnderline§r";
            case ITALIC:
                return "§oItalic§r";

            default:
                return color.name();
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        ChatColor chatColor = playerChatColors.getOrDefault(player.getUniqueId(), ChatColor.RESET);

        String message = event.getMessage();
        event.setMessage(chatColor + message);
    }
}