/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapelessOreRecipe;
import com.sofodev.armorplus.items.materials.ItemMaterial;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.registry.ModBlocks.blockCompressedObsidian;
import static com.sofodev.armorplus.util.Utils.getBlock;
import static com.sofodev.armorplus.util.Utils.getItem;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModHighTechItemRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        ItemMaterial ultimate = (ItemMaterial) getItem("the_ultimate_material");
        manager.addRecipe(new ItemStack(getBlock("ulti_tech_bench")),
            "LUUUL",
            "OWOHO",
            "OUUUO",
            "O   O",
            "O   O",
            'U', new ItemStack(ultimate),
            'W', getBlock("workbench"),
            'H', getBlock("high_tech_bench"),
            'O', blockCompressedObsidian,
            'L', new ItemStack(getItem("infused_lava_crystal")));
        manager.addRecipe(new BaseShapelessOreRecipe(new ItemStack(ultimate),
            "scaleEnderDragon",
            "scaleGuardian",
            "witherBone",
            "gemChargedLavaCrystal"));
    }
}
