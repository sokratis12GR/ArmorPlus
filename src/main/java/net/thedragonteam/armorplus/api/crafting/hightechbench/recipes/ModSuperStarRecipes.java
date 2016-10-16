/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.enableSuperStarArmorRecipes;

public class ModSuperStarRecipes {
    public void addRecipes(HighTechBenchCraftingManager manager) {
        /* Super Star Armor */
        if (ARPConfig.recipes == 0 && enableSuperStarArmorRecipes) {
            manager.addRecipe( new ItemStack(ModItems.superStarHelmet, 1),
                    "WWWW",
                    "WNNW",
                    "XXXX",
                    "XXXX",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
            manager.addRecipe( new ItemStack(ModItems.superStarHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "WWWW",
                    "WNNW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
            manager.addRecipe(new ItemStack(ModItems.superStarChestplate, 1),
                    "WXXW",
                    "WWWW",
                    "WNNW",
                    "WWWW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
            manager.addRecipe(new ItemStack(ModItems.superStarLeggings, 1),
                    "WWWW",
                    "WNNW",
                    "WXXW",
                    "WXXW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
            manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "WXXW",
                    "NXXN",
                    "XXXX",
                    "XXXX",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
            manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "XXXX",
                    "XXXX",
                    "WXXW",
                    "NXXN",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
        }
        if (ARPConfig.recipes == 1 && enableSuperStarArmorRecipes) {
            manager.addRecipe( new ItemStack(ModItems.superStarHelmet, 1),
                    "SWWS",
                    "WNNW",
                    "XXXX",
                    "XXXX",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            manager.addRecipe( new ItemStack(ModItems.superStarHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "SWWS",
                    "WNNW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            manager.addRecipe(new ItemStack(ModItems.superStarChestplate, 1),
                    "SXXS",
                    "WWWW",
                    "SNNS",
                    "WWWW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            manager.addRecipe(new ItemStack(ModItems.superStarLeggings, 1),
                    "SWWS",
                    "WNNW",
                    "WXXW",
                    "SXXS",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "SXXS",
                    "WXXW",
                    "NXXN",
                    "XXXX",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            manager.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "XXXX",
                    "SXXS",
                    "WXXW",
                    "NXXN",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
        }
    }
}
