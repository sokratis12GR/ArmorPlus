package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapedOreRecipe;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

class HTBRecipesHelper {

    static void createEasyArmorSetRecipes(BaseCraftingManager manager, String material, Object... outputs) {
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[0]),
            "EEEEE",
            "E   E",
            "     ",
            "     ",
            "     ",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[0]),
            "     ",
            "EEEEE",
            "E   E",
            "     ",
            "     ",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[0]),
            "     ",
            "     ",
            "EEEEE",
            "E   E",
            "     ",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[0]),
            "     ",
            "     ",
            "     ",
            "EEEEE",
            "E   E",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[1]),
            "E   E",
            "E   E",
            "EEEEE",
            "EEEEE",
            "EEEEE",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[2]),
            "EEEEE",
            "E   E",
            "E   E",
            "E   E",
            "E   E",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[3]),
            "     ",
            "     ",
            "E   E",
            "E   E",
            "E   E",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[3]),
            "     ",
            "E   E",
            "E   E",
            "E   E",
            "     ",
            'E', material));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[3]),
            "E   E",
            "E   E",
            "E   E",
            "     ",
            "     ",
            'E', material));
    }

    static void createExpertArmorSetRecipes(BaseCraftingManager manager, String materialA, String materialB, Object... outputs) {
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[0]),
            "EEeEE",
            "e   e",
            "e   e",
            "     ",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[0]),
            "     ",
            "EEeEE",
            "e   e",
            "e   e",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[0]),
            "     ",
            "     ",
            "EEeEE",
            "e   e",
            "e   e",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[1]),
            "e   e",
            "e   e",
            "EEEEE",
            "EeEeE",
            "EEEEE",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[2]),
            "EEEEE",
            "EeeeE",
            "e   e",
            "e   e",
            "e   e",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[3]),
            "E   E",
            "E   E",
            "e   e",
            "     ",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[3]),
            "     ",
            "E   E",
            "E   E",
            "e   e",
            "     ",
            'E', materialA,
            'e', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(outputs[3]),
            "     ",
            "     ",
            "E   E",
            "E   E",
            "e   e",
            'E', materialA,
            'e', materialB));
    }

    static void createSwordRecipes(BaseCraftingManager manager, Object output, String material) {
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(output),
            "    E",
            "    E",
            "    E",
            "    E",
            "    S",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(output),
            "   E ",
            "   E ",
            "   E ",
            "   E ",
            "   S ",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(output),
            "  E  ",
            "  E  ",
            "  E  ",
            "  E  ",
            "  S  ",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(output),
            " E   ",
            " E   ",
            " E   ",
            " E   ",
            " S   ",
            'E', material,
            'S', "stickWood"));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(output),
            "E    ",
            "E    ",
            "E    ",
            "E    ",
            "S    ",
            'E', material,
            'S', "stickWood"));
    }

    static void createBattleAxeRecipe(BaseCraftingManager manager, Object output, String material) {
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(output),
            "E   E",
            "EESEE",
            "E S E",
            "  S  ",
            "  S  ",
            'E', material,
            'S', "stickWood"));
    }

    static void createBowRecipe(BaseCraftingManager manager, Object output, String material) {
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(output),
            "  EES",
            " E  S",
            "E   S",
            " E  S",
            "  EES",
            'E', material,
            'S', "string"));
        manager.addRecipe(new BaseShapedOreRecipe(5, getItemStack(output),
            "SEE  ",
            "S  E ",
            "S   E",
            "S  E ",
            "SEE  ",
            'E', material,
            'S', "string"));
    }
}
