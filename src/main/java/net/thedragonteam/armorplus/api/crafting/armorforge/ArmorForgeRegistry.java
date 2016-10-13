/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.armorforge;

import net.minecraft.item.ItemStack;

public class ArmorForgeRegistry {

    /**
     * Created a 3x3 shaped recipe
     *
     * @param output is the recipe's result
     * @param params are the parameters for the recipe
     *
     * @result example: AdvancedArmorForgeRegistry.addRecipe(new ItemStack(Items.Stick), "XXX", "XXX", "XXX", 'X', Items.Stick )
     */
    public static void addRecipe(ItemStack output, Object... params) {
        addShapedRecipe(output, params);
    }

    public static IRecipe addShapedRecipe(ItemStack output, Object... params) {
        return ArmorForgeCraftingManager.getInstance().addRecipe(output, params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        ArmorForgeCraftingManager.getInstance().addShapelessRecipe(output, params);
    }

    public static void addRecipe(IRecipe recipe) {
        ArmorForgeCraftingManager.getInstance().getRecipeList().add(recipe);
    }

    public static void removeRecipe(IRecipe recipe) {
        ArmorForgeCraftingManager.getInstance().getRecipeList().remove(recipe);
    }

}
