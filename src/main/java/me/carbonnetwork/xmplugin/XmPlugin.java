package me.carbonnetwork.xmplugin;

import me.carbonnetwork.xmplugin.npc.NPCManager;
import me.carbonnetwork.xmplugin.npc.RemoveNPCCommand;
import me.carbonnetwork.xmplugin.npc.SpawnNPCCommand;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import me.carbonnetwork.xmplugin.recipes.*;
import me.carbonnetwork.xmplugin.commands.FeedCommand;
import me.carbonnetwork.xmplugin.commands.CommandKit;
import me.carbonnetwork.xmplugin.commands.ChatColorCommand;



public final class XmPlugin extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("kit").setExecutor(new CommandKit());
        this.getCommand("feedme").setExecutor(new FeedCommand());
        this.getCommand("spawnnpc").setExecutor(new SpawnNPCCommand());
        this.getCommand("removenpc").setExecutor(new RemoveNPCCommand());
        getServer().addRecipe(new DragonEgg(this).customDragonEggRecipe());

        ChatColorCommand chatColorCommand = new ChatColorCommand(this);
        getServer().getPluginManager().registerEvents(chatColorCommand, this);

        new NPCManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().removeRecipe(new NamespacedKey(this, "customDragonEgg"));
        getServer().removeRecipe(new NamespacedKey(this, "EnchantedCobblestone"));
    }

}




