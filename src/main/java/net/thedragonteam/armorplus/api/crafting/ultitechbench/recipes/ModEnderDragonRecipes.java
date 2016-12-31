/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.APConfig.enableEnderDragonArmorRecipes;

public class ModEnderDragonRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        /* Ender Dragon Armor */
        if (APConfig.enableEnderDragonArmor) {
            if (APConfig.gameMode == 0 && enableEnderDragonArmorRecipes) {
                manager.addRecipe(new ItemStack(ModItems.enderDragon[0], 1),
                        "EEEEE",
                        "E   E",
                        "     ",
                        "     ",
                        "     ",
                        'E', new ItemStack(ModItems.materials, 1, 3));
                manager.addRecipe(new ItemStack(ModItems.enderDragon[0], 1),
                        "     ",
                        "     ",
                        "EEEEE",
                        "E   E",
                        'E', new ItemStack(ModItems.materials, 1, 3));
                manager.addRecipe(new ItemStack(ModItems.enderDragon[1], 1),
                        "E   E",
                        "E   E",
                        "EEEEE",
                        "EEEEE",
                        "EEEEE",
                        'E', new ItemStack(ModItems.materials, 1, 3));
                manager.addRecipe(new ItemStack(ModItems.enderDragon[2], 1),
                        "EEEEE",
                        "EEEEE",
                        "E   E",
                        "E   E",
                        "E   E",
                        'E', new ItemStack(ModItems.materials, 1, 3));
                manager.addRecipe(new ItemStack(ModItems.enderDragon[3], 1),
                        "E   E",
                        "E   E",
                        "     ",
                        "     ",
                        "     ",
                        'E', new ItemStack(ModItems.materials, 1, 3));
                manager.addRecipe(new ItemStack(ModItems.enderDragon[3], 1),
                        "     ",
                        "     ",
                        "     ",
                        "E   E",
                        "E   E",
                        'E', new ItemStack(ModItems.materials, 1, 3));
            }
            if (APConfig.gameMode == 1 && enableEnderDragonArmorRecipes) {
                manager.addRecipe(new ItemStack(ModItems.enderDragon[0], 1),
                        "CEEEC",
                        "ES SE",
                        "     ",
                        "     ",
                        "     ",
                        'E', new ItemStack(ModItems.materials, 1, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(ModItems.enderDragon[0], 1),
                        "     ",
                        "     ",
                        "     ",
                        "CEEEC",
                        "ES SE",
                        'E', new ItemStack(ModItems.materials, 1, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(ModItems.enderDragon[1], 1),
                        "S   S",
                        "E   E",
                        "EEEEE",
                        "ECSCE",
                        "EEEEE",
                        'E', new ItemStack(ModItems.materials, 1, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(ModItems.enderDragon[2], 1),
                        "SEEES",
                        "ECSCE",
                        "E   E",
                        "E   E",
                        "E   E",
                        'E', new ItemStack(ModItems.materials, 1, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(ModItems.enderDragon[3], 1),
                        "S   S",
                        "E   E",
                        "E   E",
                        "C   C",
                        "     ",
                        'E', new ItemStack(ModItems.materials, 1, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(ModItems.enderDragon[3], 1),
                        "     ",
                        "S   S",
                        "E   E",
                        "E   E",
                        "C   C",
                        'E', new ItemStack(ModItems.materials, 1, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
            }
        }
    }
}
