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

public class ModSuperStarRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                if (global_registry.enableSuperStarArmor && recipes.enableSuperStarArmorRecipes) {
                    registerEasyArmorSetRecipes(manager, 2, superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots);
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (global_registry.enableSuperStarArmor && recipes.enableSuperStarArmorRecipes) {
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
}