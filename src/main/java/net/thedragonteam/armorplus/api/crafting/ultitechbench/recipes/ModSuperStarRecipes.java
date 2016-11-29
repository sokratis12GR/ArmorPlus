/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.enableSuperStarArmorRecipes;

public class ModSuperStarRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        /* Super Star Armor */
        if (ARPConfig.enableSuperStarArmor) {
            if (ARPConfig.recipes == 0 && enableSuperStarArmorRecipes) {
                manager.addRecipe(new ItemStack(ModItems.superStarHelmet, 1),
                        "WWWWW",
                        "W   W",
                        "     ",
                        "     ",
                        "     ",
                        'W', new ItemStack(ModItems.materials, 1, 2));
                manager.addRecipe(new ItemStack(ModItems.superStarHelmet, 1),
                        "     ",
                        "     ",
                        "     ",
                        "WWWWW",
                        "W   W",
                        'W', new ItemStack(ModItems.materials, 1, 2));
                manager.addRecipe(new ItemStack(ModItems.superStarChestplate, 1),
                        "W   W",
                        "W   W",
                        "WWWWW",
                        "WWWWW",
                        "WWWWW",
                        'W', new ItemStack(ModItems.materials, 1, 2));
                manager.addRecipe(new ItemStack(ModItems.superStarLeggings, 1),
                        "WWWWW",
                        "WWWWW",
                        "W   W",
                        "W   W",
                        "W   W",
                        'W', new ItemStack(ModItems.materials, 1, 2));
                manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                        "W   W",
                        "W   W",
                        "     ",
                        "     ",
                        "     ",
                        'W', new ItemStack(ModItems.materials, 1, 2));
                manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                        "     ",
                        "     ",
                        "     ",
                        "W   W",
                        "W   W",
                        'W', new ItemStack(ModItems.materials, 1, 2));
            }
            if (ARPConfig.recipes == 1 && enableSuperStarArmorRecipes) {
                manager.addRecipe(new ItemStack(ModItems.superStarHelmet, 1),
                        "SWWWS",
                        "WN NW",
                        "     ",
                        "     ",
                        "     ",
                        'W', new ItemStack(ModItems.materials, 1, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(ModItems.superStarHelmet, 1),
                        "     ",
                        "     ",
                        "     ",
                        "SWWWS",
                        "WN NW",
                        'W', new ItemStack(ModItems.materials, 1, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(ModItems.superStarChestplate, 1),
                        "N   N",
                        "W   W",
                        "WWWWW",
                        "WSNSW",
                        "WWWWW",
                        'W', new ItemStack(ModItems.materials, 1, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(ModItems.superStarLeggings, 1),
                        "SWWWS",
                        "WSNSW",
                        "W   W",
                        "W   W",
                        "N   N",
                        'W', new ItemStack(ModItems.materials, 1, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                        "N   N",
                        "W   W",
                        "W   W",
                        "S   S",
                        "     ",
                        'W', new ItemStack(ModItems.materials, 1, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                        "     ",
                        "N   N",
                        "W   W",
                        "W   W",
                        "S   S",
                        'W', new ItemStack(ModItems.materials, 1, 2),
                        'N', Items.NETHER_STAR,
                        'S', Blocks.SOUL_SAND);
            }
        }
    }
}