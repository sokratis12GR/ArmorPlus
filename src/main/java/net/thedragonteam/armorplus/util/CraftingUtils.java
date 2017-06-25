package net.thedragonteam.armorplus.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CraftingUtils {
    public static void addRecipe(int id, ResourceLocation resLoc, IRecipe recipe) {
        CraftingManager.REGISTRY.register(id, resLoc, recipe);
    }

    public static void addShapedRecipe(int id, ResourceLocation resLoc, ItemStack output, Object... shape) {
        addRecipe(id, resLoc, new ShapedOreRecipe(resLoc, output, shape));
    }

    public static void addShapelessRecipe(int id, ResourceLocation resLoc, ItemStack output, Object... shape) {
        addRecipe(id, resLoc, new ShapelessOreRecipe(resLoc, output, shape));
    }
}
