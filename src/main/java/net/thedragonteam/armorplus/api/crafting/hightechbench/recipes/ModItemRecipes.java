/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapelessOreRecipe;

import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.ItemStackUtils.getItemStack;

public class ModItemRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
        manager.addRecipe(getItemStack(benches[2]),
                "LUUL",
                "OWHO",
                "OUUO",
                "O  O",
                'U', getItemStack(materials, 4),
                'W', benches[0],
                'H', benches[1],
                'O', compressedObsidian,
                'L', getItemStack(lavaCrystal, 1));
        manager.addRecipe(new ShapelessOreRecipe(getItemStack(materials, 4),
                "scaleEnderDragon",
                "scaleGuardian",
                "witherBone",
                "gemChargedLavaCrystal"));
    }
}
