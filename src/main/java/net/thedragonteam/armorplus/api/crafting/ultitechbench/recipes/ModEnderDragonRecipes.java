/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;

import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.global_registry;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.recipes;
import static net.thedragonteam.armorplus.ModConfig.getRD;
import static net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes.UTBRecipesHelper.registerEasyArmorSetRecipes;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModEnderDragonRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                if (global_registry.enableEnderDragonArmor && recipes.enableEnderDragonArmorRecipes) {
                    registerEasyArmorSetRecipes(manager, 3, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots);
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (global_registry.enableEnderDragonArmor && recipes.enableEnderDragonArmorRecipes) {
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
