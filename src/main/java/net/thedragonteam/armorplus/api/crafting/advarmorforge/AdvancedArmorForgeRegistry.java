/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.advarmorforge;

import net.minecraft.item.ItemStack;

public class AdvancedArmorForgeRegistry {

    /**
     * Created a 4x4 shaped recipe
     *
     * @param output is the recipe's result
     * @param params are the parameters for the recipe
     *
     * @result example: AdvancedArmorForgeRegistry.addRecipe(new ItemStack(Items.Stick), "XXXX", "XXXX", "XXXX", "XXXX", 'X', Items.Stick )
     */
    public static void addRecipe(ItemStack output, Object... params) {
        addShapedRecipe(output, params);
    }

    public static IRecipe addShapedRecipe(ItemStack output, Object... params) {
        return AdvancedArmorForgeCraftingManager.getInstance().addRecipe(output, params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        AdvancedArmorForgeCraftingManager.getInstance().addShapelessRecipe(output, params);
    }

    public static void addRecipe(IRecipe recipe) {
        AdvancedArmorForgeCraftingManager.getInstance().getRecipeList().add(recipe);
    }

    public static void removeRecipe(IRecipe recipe) {
        AdvancedArmorForgeCraftingManager.getInstance().getRecipeList().remove(recipe);
    }
}
