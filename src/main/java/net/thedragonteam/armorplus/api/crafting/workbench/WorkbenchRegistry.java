/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.workbench;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.base.IRecipe;

public class WorkbenchRegistry {

    /**
     * Created a 3x3 shaped recipe
     *
     * @param output is the recipe's result
     * @param params are the parameters for the recipe
     *
     * @result example: WorkbenchRegistry.addRecipe(new ItemStack(Items.Stick), "XXX", "XXX", "XXX", 'X', Items.Stick )
     */
    public static void addRecipe(ItemStack output, Object... params) {
        addShapedRecipe(output, params);
    }

    public static IRecipe addShapedRecipe(ItemStack output, Object... params) {
        return WorkbenchCraftingManager.getInstance().addRecipe(output, params);
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        WorkbenchCraftingManager.getInstance().addShapelessRecipe(output, params);
    }

    /**
     * @param recipe {@link WorkbenchCraftingManager#addRecipe(IRecipe)}
     */
    public static void addRecipe(IRecipe recipe) {
        WorkbenchCraftingManager.getInstance().getRecipeList().add(recipe);
    }

    /**
     * @param recipe {@link WorkbenchCraftingManager#removeRecipe(IRecipe)}
     */
    public static void removeRecipe(IRecipe recipe) {
        WorkbenchCraftingManager.getInstance().getRecipeList().remove(recipe);
    }

}
