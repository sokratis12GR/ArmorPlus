/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapelessOreRecipe;

import static com.sofodev.armorplus.registry.APBlocks.*;
import static com.sofodev.armorplus.registry.ModBlocks.blockCompressedObsidian;
import static com.sofodev.armorplus.registry.ModItems.itemLavaCrystal;
import static com.sofodev.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
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
