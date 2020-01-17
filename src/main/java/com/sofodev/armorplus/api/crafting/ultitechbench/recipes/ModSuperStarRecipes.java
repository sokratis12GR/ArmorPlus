/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.config.ModConfig.getRD;
import static com.sofodev.armorplus.common.registry.ModItems.materials;
import static com.sofodev.armorplus.common.registry.constants.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModSuperStarRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY:
                if (recipes.enableSuperStarArmorRecipes) {
                    UTBRecipesHelper.registerEasyArmorSetRecipes(manager, 2, superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots);
                }
                break;
            case EXPERT:
            case HELLISH:
                if (recipes.enableSuperStarArmorRecipes) {
                    manager.addRecipe(getItemStack(superStarHelmet),
                        "SWWNWWS",
                        "WN   NW",
                        "W     W",
                        "N     N",
                        "       ",
                        "       ",
                        "       ",
                        'W', getItemStack(materials, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarHelmet),
                        "       ",
                        "SWWNWWS",
                        "WN   NW",
                        "W     W",
                        "N     N",
                        "       ",
                        "       ",
                        'W', getItemStack(materials, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarHelmet),
                        "       ",
                        "       ",
                        "SWWNWWS",
                        "WN   NW",
                        "W     W",
                        "N     N",
                        "       ",
                        'W', getItemStack(materials, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarHelmet),
                        "       ",
                        "       ",
                        "       ",
                        "SWWNWWS",
                        "WN   NW",
                        "W     W",
                        "N     N",
                        'W', getItemStack(materials, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarChestplate),
                        "N     N",
                        "N     N",
                        "W     W",
                        "WNSNSNW",
                        "WWNHNWW",
                        "WWSNSWW",
                        "WWWWWWW",
                        'W', getItemStack(materials, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND,
                        'H', getItemStack(Items.SKULL, 1));
                    manager.addRecipe(getItemStack(superStarLeggings),
                        "SWWSWWS",
                        "WSSNSSW",
                        "N     N",
                        "W     W",
                        "N     N",
                        "W     W",
                        "N     N",
                        'W', getItemStack(materials, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarBoots),
                        "N     N",
                        "W     W",
                        "W     W",
                        "S     S",
                        "       ",
                        "       ",
                        "       ",
                        'W', getItemStack(materials, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarBoots),
                        "       ",
                        "N     N",
                        "W     W",
                        "W     W",
                        "S     S",
                        "       ",
                        "       ",
                        'W', getItemStack(materials, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarBoots),
                        "       ",
                        "       ",
                        "N     N",
                        "W     W",
                        "W     W",
                        "S     S",
                        "       ",
                        'W', getItemStack(materials, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarBoots),
                        "       ",
                        "       ",
                        "       ",
                        "N     N",
                        "W     W",
                        "W     W",
                        "S     S",
                        'W', getItemStack(materials, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                }
                break;
        }
    }
}