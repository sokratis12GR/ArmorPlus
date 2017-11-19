package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.thedragonteam.armorplus.api.crafting.hightechbench.HTBShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

class HTBRecipesHelper {

    static void createEasyArmorSetRecipes(HighTechBenchCraftingManager manager, String material, Object... outputs) {
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[0]),
            "EEEEE",
            "E   E",
            "     ",
            "     ",
            "     ",
            'E', material));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[0]),
            "     ",
            "EEEEE",
            "E   E",
            "     ",
            "     ",
            'E', material));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[0]),
            "     ",
            "     ",
            "EEEEE",
            "E   E",
            "     ",
            'E', material));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[0]),
            "     ",
            "     ",
            "     ",
            "EEEEE",
            "E   E",
            'E', material));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[1]),
            "E   E",
            "E   E",
            "EEEEE",
            "EEEEE",
            "EEEEE",
            'E', material));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[2]),
            "EEEEE",
            "E   E",
            "E   E",
            "E   E",
            "E   E",
            'E', material));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[3]),
            "     ",
            "     ",
            "E   E",
            "E   E",
            "E   E",
            'E', material));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[3]),
            "     ",
            "E   E",
            "E   E",
            "E   E",
            "     ",
            'E', material));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[3]),
            "E   E",
            "E   E",
            "E   E",
            "     ",
            "     ",
            'E', material));
    }

    static void createExpertArmorSetRecipes(HighTechBenchCraftingManager manager, String materialA, String materialB, Object... outputs) {
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[0]),
            "EEeEE",
            "e   e",
            "e   e",
            "     ",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[0]),
            "     ",
            "EEeEE",
            "e   e",
            "e   e",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[0]),
            "     ",
            "     ",
            "EEeEE",
            "e   e",
            "e   e",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[1]),
            "e   e",
            "e   e",
            "EEEEE",
            "EeEeE",
            "EEEEE",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[2]),
            "EEEEE",
            "EeeeE",
            "e   e",
            "e   e",
            "e   e",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[3]),
            "E   E",
            "E   E",
            "e   e",
            "     ",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[3]),
            "     ",
            "E   E",
            "E   E",
            "e   e",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(outputs[3]),
            "     ",
            "     ",
            "E   E",
            "E   E",
            "e   e",
            'E', materialA,
            'e', materialB));
    }

    static void createSwordRecipes(HighTechBenchCraftingManager manager, Object output, String material) {
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(output),
            "    E",
            "    E",
            "    E",
            "    E",
            "    S",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(output),
            "   E ",
            "   E ",
            "   E ",
            "   E ",
            "   S ",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(output),
            "  E  ",
            "  E  ",
            "  E  ",
            "  E  ",
            "  S  ",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(output),
            " E   ",
            " E   ",
            " E   ",
            " E   ",
            " S   ",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(output),
            "E    ",
            "E    ",
            "E    ",
            "E    ",
            "S    ",
            'E', material,
            'S', "stickWood"));
    }

    static void createBattleAxeRecipe(HighTechBenchCraftingManager manager, Object output, String material) {
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(output),
            "E   E",
            "EESEE",
            "E S E",
            "  S  ",
            "  S  ",
            'E', material,
            'S', "stickWood"));
    }

    static void createBowRecipe(HighTechBenchCraftingManager manager, Object output, String material) {
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(output),
            "  EES",
            " E  S",
            "E   S",
            " E  S",
            "  EES",
            'E', material,
            'S', "string"));
        manager.addRecipe(new HTBShapedOreRecipe(getItemStack(output),
            "SEE  ",
            "S  E ",
            "S   E",
            "S  E ",
            "SEE  ",
            'E', material,
            'S', "string"));
    }
}
