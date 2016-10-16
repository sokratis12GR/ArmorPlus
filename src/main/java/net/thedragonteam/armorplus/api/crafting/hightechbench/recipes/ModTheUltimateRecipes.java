/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.Utils;

import static net.thedragonteam.armorplus.ARPConfig.enableTheUltimateArmorRecipes;

public class ModTheUltimateRecipes {
    public void addRecipes(HighTechBenchCraftingManager manager) {

        /* Sets The Ultimate Armor Unbreakable */
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimateHelmet, 1));
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimateChestplate, 1));
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimateLeggings, 1));
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimateBoots, 1));

        /* The Ultimate Armor */
        if (enableTheUltimateArmorRecipes) {
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateHelmet, 1),
                    ModItems.theUltimateHelmetLeft,
                    ModItems.theUltimateHelmetMiddle,
                    ModItems.theUltimateHelmetRight,
                    ModItems.theUltimateMaterial);
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateChestplate, 1),
                    ModItems.theUltimateChestplateLeft,
                    ModItems.theUltimateChestplateMiddle,
                    ModItems.theUltimateChestplateRight,
                    ModItems.theUltimateMaterial);
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateLeggings, 1),
                    ModItems.theUltimateLeggingsLeft,
                    ModItems.theUltimateLeggingsMiddle,
                    ModItems.theUltimateLeggingsRight,
                    ModItems.theUltimateMaterial);
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateBoots, 1),
                    ModItems.theUltimateBootsLeft,
                    ModItems.theUltimateBootsMiddle,
                    ModItems.theUltimateBootsRight,
                    ModItems.theUltimateMaterial);
            //Helmet Parts
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateHelmetLeft, 1),
                    ModItems.superStarHelmet,
                    ModItems.witherBone,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateHelmetMiddle, 1),
                    ModItems.enderDragonHelmet,
                    ModItems.enderDragonScale,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateHelmetRight, 1),
                    ModItems.guardianHelmet,
                    ModItems.guardianScale,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            //Chestplate Parts
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateChestplateLeft, 1),
                    ModItems.superStarChestplate,
                    ModItems.witherBone,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateChestplateMiddle, 1),
                    ModItems.enderDragonChestplate,
                    ModItems.enderDragonScale,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateChestplateRight, 1),
                    ModItems.guardianChestplate,
                    ModItems.guardianScale,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            //Leggings Parts
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateLeggingsLeft, 1),
                    ModItems.superStarLeggings,
                    ModItems.witherBone,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateLeggingsMiddle, 1),
                    ModItems.enderDragonLeggings,
                    ModItems.enderDragonScale,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateLeggingsRight, 1),
                    ModItems.guardianLeggings,
                    ModItems.guardianScale,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            //Boots Parts
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateBootsLeft, 1),
                    ModItems.superStarBoots,
                    ModItems.witherBone,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateBootsMiddle, 1),
                    ModItems.enderDragonBoots,
                    ModItems.enderDragonScale,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateBootsRight, 1),
                    ModItems.guardianBoots,
                    ModItems.guardianScale,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
        }
    }
}
