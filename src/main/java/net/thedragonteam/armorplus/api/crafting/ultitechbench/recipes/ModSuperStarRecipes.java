/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.enableSuperStarArmor;
import static net.thedragonteam.armorplus.APConfig.enableSuperStarArmorRecipes;
import static net.thedragonteam.armorplus.APConfig.getRD;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModSuperStarRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        switch (getRD()) {
            case EASY:
                if (enableSuperStarArmor && enableSuperStarArmorRecipes) {
                    manager.addRecipe(getItemStack(superStarHelmet),
                            "WWWWW",
                            "W   W",
                            "     ",
                            "     ",
                            "     ",
                            'W', getItemStack(materials, 2));
                    manager.addRecipe(getItemStack(superStarHelmet),
                            "     ",
                            "     ",
                            "     ",
                            "WWWWW",
                            "W   W",
                            'W', getItemStack(materials, 2));
                    manager.addRecipe(getItemStack(superStarChestplate),
                            "W   W",
                            "W   W",
                            "WWWWW",
                            "WWWWW",
                            "WWWWW",
                            'W', getItemStack(materials, 2));
                    manager.addRecipe(getItemStack(superStarLeggings),
                            "WWWWW",
                            "WWWWW",
                            "W   W",
                            "W   W",
                            "W   W",
                            'W', getItemStack(materials, 2));
                    manager.addRecipe(getItemStack(superStarBoots),
                            "W   W",
                            "W   W",
                            "     ",
                            "     ",
                            "     ",
                            'W', getItemStack(materials, 2));
                    manager.addRecipe(getItemStack(superStarBoots),
                            "     ",
                            "     ",
                            "     ",
                            "W   W",
                            "W   W",
                            'W', getItemStack(materials, 2));
                }
                break;
            case EXPERT:
            case HELLISH:
                if (enableSuperStarArmor && enableSuperStarArmorRecipes) {
                    manager.addRecipe(getItemStack(superStarHelmet),
                            "SWWWS",
                            "WN NW",
                            "     ",
                            "     ",
                            "     ",
                            'W', getItemStack(materials, 2),
                            'N', Items.NETHER_STAR,
                            'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarHelmet),
                            "     ",
                            "     ",
                            "     ",
                            "SWWWS",
                            "WN NW",
                            'W', getItemStack(materials, 2),
                            'N', Items.NETHER_STAR,
                            'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarChestplate),
                            "N   N",
                            "W   W",
                            "WWWWW",
                            "WSNSW",
                            "WWWWW",
                            'W', getItemStack(materials, 2),
                            'N', Items.NETHER_STAR,
                            'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarLeggings),
                            "SWWWS",
                            "WSNSW",
                            "W   W",
                            "W   W",
                            "N   N",
                            'W', getItemStack(materials, 2),
                            'N', Items.NETHER_STAR,
                            'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarBoots),
                            "N   N",
                            "W   W",
                            "W   W",
                            "S   S",
                            "     ",
                            'W', getItemStack(materials, 2),
                            'N', Items.NETHER_STAR,
                            'S', Blocks.SOUL_SAND);
                    manager.addRecipe(getItemStack(superStarBoots),
                            "     ",
                            "N   N",
                            "W   W",
                            "W   W",
                            "S   S",
                            'W', getItemStack(materials, 2),
                            'N', Items.NETHER_STAR,
                            'S', Blocks.SOUL_SAND);
                }
                break;
        }
    }
}