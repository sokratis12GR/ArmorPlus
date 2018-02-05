package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapedOreRecipe;

import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class UTBRecipesHelper {

    public static void registerBowRecipes(BaseCraftingManager manager, String materialA, String materialB, Item bow) {
        manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(bow),
            "    GGS",
            "  GG  S",
            " G    S",
            "G     S",
            " G    S",
            "  GG  S",
            "    GGS",
            'G', materialA,
            'S', materialB));
        manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(bow),
            "SGG    ",
            "S  GG  ",
            "S    G ",
            "S     G",
            "S    G ",
            "S  GG  ",
            "SGG    ",
            'G', materialA,
            'S', materialB));
    }

    public static void registerSwordRecipe(BaseCraftingManager manager, String material, Item sword) {
        manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(sword),
            "   E   ",
            "  ESE  ",
            " E S E ",
            "E  S  E",
            "   S   ",
            "   S   ",
            "   S   ",
            'E', material,
            'S', Items.STICK));
    }

    public static void registerBattleAxeRecipe(BaseCraftingManager manager, String material, Item sword) {
        manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(sword),
            " E   E ",
            "E  S  E",
            " EESEE ",
            "E  S  E",
            " E S E ",
            "   S   ",
            "   S   ",
            'E', material,
            'S', Items.STICK));
    }

    public static void registerEasyArmorSetRecipes(BaseCraftingManager manager, int materialMeta, Object... outputs) {
        manager.addRecipe(getItemStack(outputs[0]),
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[0]),
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[0]),
            "       ",
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[0]),
            "       ",
            "       ",
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[0]),
            "       ",
            "       ",
            "       ",
            "       ",
            "EEEEEEE",
            "E     E",
            "E     E",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[1]),
            "E     E",
            "E     E",
            "E     E",
            "EEEEEEE",
            "EEEEEEE",
            "EEEEEEE",
            "EEEEEEE",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[2]),
            "EEEEEEE",
            "EEEEEEE",
            "E     E",
            "E     E",
            "E     E",
            "E     E",
            "E     E",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[3]),
            "E     E",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[3]),
            "       ",
            "E     E",
            "E     E",
            "E     E",
            "       ",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[3]),
            "       ",
            "       ",
            "E     E",
            "E     E",
            "E     E",
            "       ",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[3]),
            "       ",
            "       ",
            "       ",
            "E     E",
            "E     E",
            "E     E",
            "       ",
            'E', getItemStack(materials, materialMeta));
        manager.addRecipe(getItemStack(outputs[3]),
            "       ",
            "       ",
            "       ",
            "       ",
            "E     E",
            "E     E",
            "E     E",
            'E', getItemStack(materials, materialMeta));
    }
}
