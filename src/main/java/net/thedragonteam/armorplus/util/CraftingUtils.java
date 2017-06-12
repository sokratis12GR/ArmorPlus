package net.thedragonteam.armorplus.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by sokratis12GR on 6/11/2017.
 */
public class CraftingUtils {
    public static void addRecipe(ResourceLocation id, IRecipe recipe) {
        CraftingManager.func_193372_a(id, recipe);
    }

    public static void addShapedRecipe(ResourceLocation id, ItemStack output, Object... shape) {
        addRecipe(id, new ShapedOreRecipe(id, output, shape));
    }

    public static void addShapelessRecipe(ResourceLocation id, ItemStack output, Object... shape) {
        addRecipe(id, new ShapelessOreRecipe(id, output, shape));
    }
}
