/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Items;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EASY;
import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EXPERT;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModEnderDragonRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        /* Ender Dragon Armor */
        if (enableEnderDragonArmor) {
            if (getRD() == EASY && enableEnderDragonArmorRecipes) {
                manager.addRecipe(getItemStack(enderDragonHelmet),
                        "EEEEE",
                        "E   E",
                        "     ",
                        "     ",
                        "     ",
                        'E', getItemStack(materials, 3));
                manager.addRecipe(getItemStack(enderDragonHelmet),
                        "     ",
                        "     ",
                        "EEEEE",
                        "E   E",
                        'E', getItemStack(materials, 3));
                manager.addRecipe(getItemStack(enderDragonChestplate),
                        "E   E",
                        "E   E",
                        "EEEEE",
                        "EEEEE",
                        "EEEEE",
                        'E', getItemStack(materials, 3));
                manager.addRecipe(getItemStack(enderDragonLeggings),
                        "EEEEE",
                        "EEEEE",
                        "E   E",
                        "E   E",
                        "E   E",
                        'E', getItemStack(materials, 3));
                manager.addRecipe(getItemStack(enderDragonBoots),
                        "E   E",
                        "E   E",
                        "     ",
                        "     ",
                        "     ",
                        'E', getItemStack(materials, 3));
                manager.addRecipe(getItemStack(enderDragonBoots),
                        "     ",
                        "     ",
                        "     ",
                        "E   E",
                        "E   E",
                        'E', getItemStack(materials, 3));
            }
            if (getRD() == EXPERT && enableEnderDragonArmorRecipes) {
                manager.addRecipe(getItemStack(enderDragonHelmet),
                        "CEEEC",
                        "ES SE",
                        "     ",
                        "     ",
                        "     ",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(getItemStack(enderDragonHelmet),
                        "     ",
                        "     ",
                        "     ",
                        "CEEEC",
                        "ES SE",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(getItemStack(enderDragonChestplate),
                        "S   S",
                        "E   E",
                        "EEEEE",
                        "ECSCE",
                        "EEEEE",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(getItemStack(enderDragonLeggings),
                        "SEEES",
                        "ECSCE",
                        "E   E",
                        "E   E",
                        "E   E",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(getItemStack(enderDragonBoots),
                        "S   S",
                        "E   E",
                        "E   E",
                        "C   C",
                        "     ",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(getItemStack(enderDragonBoots),
                        "     ",
                        "S   S",
                        "E   E",
                        "E   E",
                        "C   C",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
            }
        }
    }
}
