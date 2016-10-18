/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapelessOreRecipe;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

public class ModItemRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
        manager.addRecipe(new ItemStack(ModBlocks.arpUltiTechBench, 1),
                "LUUL",
                "OWHO",
                "OUUO",
                "OXXO",
                'U', ModItems.theUltimateMaterial,
                'W', ModBlocks.arpWorkbench,
                'H', ModBlocks.arpHighTechBench,
                'O', ModBlocks.compressedObsidian,
                'L', new ItemStack(ModItems.lavaCrystal, 1, 1));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.theUltimateMaterial, 1),
                ModItems.enderDragonScale,
                ModItems.guardianScale,
                ModItems.witherBone,
                new ItemStack(ModItems.lavaCrystal, 1, 1)));
    }
}
