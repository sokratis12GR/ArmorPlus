package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UTBShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;

import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class UTBRecipesHelper {

    public static void registerBowRecipes(UltiTechBenchCraftingManager manager, String materialA, String materialB, Item bow) {
        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(bow),
            "    GGS",
            "  GG  S",
            " G    S",
            "G     S",
            " G    S",
            "  GG  S",
            "    GGS",
            'G', materialA,
            'S', materialB));
        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(bow),
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

    public static void registerSwordRecipe(UltiTechBenchCraftingManager manager, String material, Item sword) {
        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(sword),
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

    public static void registerBattleAxeRecipe(UltiTechBenchCraftingManager manager, String material, Item sword) {
        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(sword),
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

    public static void registerEasyArmorSetRecipes(UltiTechBenchCraftingManager manager, int materialMeta, Object... outputs) {
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
