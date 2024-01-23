package me.carbonnetwork.xmplugin.npc.Factory.items;

import me.carbonnetwork.xmplugin.XmPlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class refinedDiamondItem {

    private static final int refinedDiamondID = 3;
    public static ItemStack createRefinedDiamond() {
        ItemStack refinedDiamond = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta = refinedDiamond.getItemMeta();

        // Add enchantment
        refinedDiamond.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);

        // Add HIDE_ENCHANTS flag to hide enchantments in lore
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        // Set display name and lore
        itemMeta.setDisplayName("§bRefined Diamond");

        itemMeta.getPersistentDataContainer().set(getKey("refinedDiamondID"), PersistentDataType.INTEGER, refinedDiamondID);

        refinedDiamond.setItemMeta(itemMeta);

        return refinedDiamond;
    }

    private static NamespacedKey getKey(String identifier) {
        return new NamespacedKey(XmPlugin.getPlugin(XmPlugin.class), identifier);
    }
}
