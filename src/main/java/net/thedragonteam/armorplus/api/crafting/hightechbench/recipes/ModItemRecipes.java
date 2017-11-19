/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.thedragonteam.armorplus.api.crafting.hightechbench.HTBShapelessOreRecipe;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;

import static net.thedragonteam.armorplus.registry.APBlocks.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ModItemRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
        manager.addRecipe(getItemStack(ultiTechBench),
            "LUUUL",
            "OWOHO",
            "OUUUO",
            "O   O",
            "O   O",
            'U', getItemStack(materials, 4),
            'W', workbench,
            'H', highTechBench,
            'O', compressedObsidian,
            'L', getItemStack(lavaCrystal, 1));
        manager.addRecipe(new HTBShapelessOreRecipe(getItemStack(materials, 4),
            "scaleEnderDragon",
            "scaleGuardian",
            "witherBone",
            "gemChargedLavaCrystal"));
    }
}
