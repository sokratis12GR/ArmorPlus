package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.WBShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class WBRecipesHelper {

    private static WBShapedOreRecipe createShapedRecipe(Object output, Object... recipe) {
        return new WBShapedOreRecipe(getItemStack(output), recipe);
    }

    public static void createArmorRecipes(WorkbenchCraftingManager manager, String material, Object... outputs) {
        manager.addRecipes(
            createShapedRecipe(outputs[0], "   ", "CCC", "C C", 'C', material),
            createShapedRecipe(outputs[0], "   ", "CCC", "C C", 'C', material),
            createShapedRecipe(outputs[0], "CCC", "C C", "   ", 'C', material),
            createShapedRecipe(outputs[1], "C C", "CCC", "CCC", 'C', material),
            createShapedRecipe(outputs[2], "CCC", "C C", "C C", 'C', material),
            createShapedRecipe(outputs[3], "   ", "C C", "C C", 'C', material),
            createShapedRecipe(outputs[3], "C C", "C C", "   ", 'C', material)
        );
    }

    public static void createSwordRecipe(WorkbenchCraftingManager manager, String material, Object output) {
        manager.addRecipe(createShapedRecipe(output, " C ", " C ", " S ", 'C', material, 'S', "stickWood"));
    }

    public static void createBattleAxeRecipe(WorkbenchCraftingManager manager, String material, Object output) {
        manager.addRecipe(createShapedRecipe(output, "C C", "CSC", " S ", 'C', material, 'S', "stickWood"));
    }

    public static void createBowRecipes(WorkbenchCraftingManager manager, String material, Object output) {
        manager.addRecipes(
            createShapedRecipe(output, " CS", "C S", " CS", 'C', material, 'S', "string"),
            createShapedRecipe(output, "SC ", "S C", "SC ", 'C', material, 'S', "string")
        );
    }
}
