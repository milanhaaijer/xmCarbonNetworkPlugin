package me.carbonnetwork.xmplugin.recipes;

import me.carbonnetwork.xmplugin.XmPlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class DragonEgg {

    private final XmPlugin plugin;

    public DragonEgg(XmPlugin plugin) {
        this.plugin = plugin;
    }

    public ShapedRecipe customDragonEggRecipe() {

        ItemStack resultItem = new ItemStack(Material.DRAGON_EGG);
        ShapedRecipe customRecipe = new ShapedRecipe(
                new NamespacedKey(this.plugin, "customDragonEgg"),
                resultItem);

        customRecipe.shape(" E ", "ENE", "EEE");

        customRecipe.setIngredient('E', Material.END_STONE);
        customRecipe.setIngredient('N', Material.NETHER_STAR);

        return customRecipe;
    }
}

