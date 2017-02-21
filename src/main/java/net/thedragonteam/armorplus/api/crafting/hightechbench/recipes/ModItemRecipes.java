/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapelessOreRecipe;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.util.ItemStackUtils.getItemStack;

public class ModItemRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
        manager.addRecipe(new ShapedOreRecipe(getItemStack(ModBlocks.benches[2]),
                "LUUL",
                "OWHO",
                "OUUO",
                "O  O",
                'U', ModItems.theUltimateMaterial,
                'W', ModBlocks.benches[0],
                'H', ModBlocks.benches[1],
                'O', ModBlocks.compressedObsidian,
                'L', "gemChargedLavaCrystal"));
        manager.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.theUltimateMaterial, 1),
                "scaleEnderDragon",
                "scaleGuardian",
                "witherBone",
                "gemChargedLavaCrystal"));
    }
}
