/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static com.sofodev.armorplus.api.crafting.ultitechbench.recipes.UTBRecipesHelper.registerEasyArmorSetRecipes;
import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.config.ModConfig.getRD;
import static com.sofodev.armorplus.common.registry.ModItems.materials;
import static com.sofodev.armorplus.common.registry.constants.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModEnderDragonRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                if (recipes.enableEnderDragonArmorRecipes) {
                    registerEasyArmorSetRecipes(manager, 3, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots);
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (recipes.enableEnderDragonArmorRecipes) {
                    manager.addRecipe(getItemStack(enderDragonHelmet),
                        "CEEEEEC",
                        "ES   SE",
                        "E     E",
                        "E     E",
                        "       ",
                        "       ",
                        "       ",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                    manager.addRecipe(getItemStack(enderDragonHelmet),
                        "       ",
                        "CEEEEEC",
                        "ES   SE",
                        "E     E",
                        "E     E",
                        "       ",
                        "       ",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                    manager.addRecipe(getItemStack(enderDragonHelmet),
                        "       ",
                        "       ",
                        "CEEEEEC",
                        "ES   SE",
                        "E     E",
                        "E     E",
                        "       ",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                    manager.addRecipe(getItemStack(enderDragonHelmet),
                        "       ",
                        "       ",
                        "       ",
                        "CEEEEEC",
                        "ES   SE",
                        "E     E",
                        "E     E",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                    manager.addRecipe(getItemStack(enderDragonChestplate),
                        "S     S",
                        "S     S",
                        "E     E",
                        "NEECEEN",
                        "EECSCEE",
                        "EECSCEE",
                        "NEECEEN",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL,
                        'N', Blocks.END_STONE);
                    manager.addRecipe(getItemStack(enderDragonLeggings),
                        "SEEEEES",
                        "ECCSCCE",
                        "EC   CE",
                        "E     E",
                        "E     E",
                        "E     E",
                        "E     E",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                    manager.addRecipe(getItemStack(enderDragonBoots),
                        "S     S",
                        "E     E",
                        "E     E",
                        "C     C",
                        "       ",
                        "       ",
                        "       ",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                    manager.addRecipe(getItemStack(enderDragonBoots),
                        "       ",
                        "S     S",
                        "E     E",
                        "E     E",
                        "C     C",
                        "       ",
                        "       ",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                    manager.addRecipe(getItemStack(enderDragonBoots),
                        "       ",
                        "       ",
                        "S     S",
                        "E     E",
                        "E     E",
                        "C     C",
                        "       ",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                    manager.addRecipe(getItemStack(enderDragonBoots),
                        "       ",
                        "       ",
                        "       ",
                        "S     S",
                        "E     E",
                        "E     E",
                        "C     C",
                        'E', getItemStack(materials, 3),
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                }
                break;
            }
        }
    }
}
