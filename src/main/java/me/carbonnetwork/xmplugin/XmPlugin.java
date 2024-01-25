package me.carbonnetwork.xmplugin;

import me.carbonnetwork.xmplugin.Bank.blockListener;
import me.carbonnetwork.xmplugin.api.APIRequest;
import me.carbonnetwork.xmplugin.commands.moneycommands.*;
import me.carbonnetwork.xmplugin.npc.*;
import me.carbonnetwork.xmplugin.npc.Factory.NPCListener;
import me.carbonnetwork.xmplugin.pin.PINCommand;
import me.carbonnetwork.xmplugin.pin.PINListener;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import me.carbonnetwork.xmplugin.recipes.*;
import me.carbonnetwork.xmplugin.commands.FeedCommand;
import me.carbonnetwork.xmplugin.commands.CommandKit;
import me.carbonnetwork.xmplugin.commands.ChatColorCommand;

public final class XmPlugin extends JavaPlugin {

    private Player setter;
    public String APIToken = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("kit").setExecutor(new CommandKit());
        this.getCommand("feedme").setExecutor(new FeedCommand());
        this.getCommand("spawnnpc").setExecutor(new SpawnNPCCommand());
        this.getCommand("removenpc").setExecutor(new RemoveNPCCommand());

        this.getCommand("givecoins1").setExecutor(new givePlayerCoins1());
        this.getCommand("givecoins10").setExecutor(new givePlayerCoins10());
        this.getCommand("givecoins100").setExecutor(new givePlayerCoins100());
        this.getCommand("givecoins1000").setExecutor(new givePlayerCoins1000());
        this.getCommand("givecoins10000").setExecutor(new givePlayerCoins10000());
        this.getCommand("givecoins100000").setExecutor(new givePlayerCoins100000());
        this.getCommand("givecoins1000000").setExecutor(new givePlayerCoins1000000());

        getServer().addRecipe(new DragonEgg(this).customDragonEggRecipe());

        ChatColorCommand chatColorCommand = new ChatColorCommand(this);
        getServer().getPluginManager().registerEvents(chatColorCommand, this);

        new NPCdusdavid(this);

        getServer().getPluginManager().registerEvents(new NPCNotch(this), this);
        getServer().getPluginManager().registerEvents(new NPCEnzoKnol(this), this);
        getServer().getPluginManager().registerEvents(new NPCListener(), this);
        getServer().getPluginManager().registerEvents(new blockListener(this), this);


        NPCNotch NPCNotch = new NPCNotch(this);
        NPCNotch.setupNPC();

        new APIRequest(this).login();

        // Pin system:
        getCommand("pin").setExecutor(new PINCommand(this));
        getServer().getPluginManager().registerEvents(new PINListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().removeRecipe(new NamespacedKey(this, "customDragonEgg"));
    }

    public String getAPIToken() {
        return APIToken;
    }

    public void setAPIToken(String APIToken) {
        this.APIToken = APIToken;
    }


    public void setPinValue(Block carpet, int pinValue, Player setter) {
        carpet.setMetadata("pinValue", new FixedMetadataValue(this, pinValue));
        if (setter != null) {
            carpet.setMetadata("pinSetter", new FixedMetadataValue(this, setter.getUniqueId()));
        }
    }
}