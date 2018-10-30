/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapelessOreRecipe;

import static net.thedragonteam.armorplus.registry.APBlocks.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.blockCompressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.itemLavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ModHighTechItemRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        manager.addRecipe(getItemStack(ultiTechBench),
            "LUUUL",
            "OWOHO",
            "OUUUO",
            "O   O",
            "O   O",
            'U', getItemStack(materials, 4),
            'W', workbench,
            'H', highTechBench,
            'O', blockCompressedObsidian,
            'L', getItemStack(itemLavaCrystal, 1));
        manager.addRecipe(new BaseShapelessOreRecipe(getItemStack(materials, 4),
            "scaleEnderDragon",
            "scaleGuardian",
            "witherBone",
            "gemChargedLavaCrystal"));
    }
}
