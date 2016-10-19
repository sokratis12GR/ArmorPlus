/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

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
        if (ARPConfig.recipes == 0 && enableSuperStarArmorRecipes) {
            manager.addRecipe(new ItemStack(ModItems.superStarHelmet, 1),
                    "WWWWW",
                    "WXXXW",
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    'W', ModItems.witherBone);
            manager.addRecipe(new ItemStack(ModItems.superStarHelmet, 1),
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    "WWWWW",
                    "WXXXW",
                    'W', ModItems.witherBone);
            manager.addRecipe(new ItemStack(ModItems.superStarChestplate, 1),
                    "WXXXW",
                    "WXXXW",
                    "WWWWW",
                    "WWWWW",
                    "WWWWW",
                    'W', ModItems.witherBone);
            manager.addRecipe(new ItemStack(ModItems.superStarLeggings, 1),
                    "WWWWW",
                    "WWWWW",
                    "WXXXW",
                    "WXXXW",
                    "WXXXW",
                    'W', ModItems.witherBone);
            manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "WXXXW",
                    "WXXXW",
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    'W', ModItems.witherBone);
            manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    "WXXXW",
                    "WXXXW",
                    'W', ModItems.witherBone);
        }
        if (ARPConfig.recipes == 1 && enableSuperStarArmorRecipes) {
            manager.addRecipe(new ItemStack(ModItems.superStarHelmet, 1),
                    "SWWWS",
                    "WNXNW",
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            manager.addRecipe(new ItemStack(ModItems.superStarHelmet, 1),
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    "SWWWS",
                    "WNXNW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            manager.addRecipe(new ItemStack(ModItems.superStarChestplate, 1),
                    "NXXXN",
                    "WXXXW",
                    "WWWWW",
                    "WSNSW",
                    "WWWWW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            manager.addRecipe(new ItemStack(ModItems.superStarLeggings, 1),
                    "SWWWS",
                    "WSNSW",
                    "WXXXW",
                    "WXXXW",
                    "NXXXN",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "NXXXN",
                    "WXXXW",
                    "WXXXW",
                    "SXXXS",
                    "XXXXX",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "XXXXX",
                    "NXXXN",
                    "WXXXW",
                    "WXXXW",
                    "SXXXS",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
        }
    }
}
