/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import com.sofodev.armorplus.api.crafting.base.BaseShapelessOreRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.registry.ModBlocks.blockCompressedObsidian;
import static com.sofodev.armorplus.common.registry.ModBlocks.blockLavaInfusedObsidian;
import static com.sofodev.armorplus.common.registry.ModItems.*;
import static com.sofodev.armorplus.common.registry.constants.APBlocks.*;
import static com.sofodev.armorplus.common.registry.constants.APItems.infusedLavaCrystal;
import static net.minecraft.init.Blocks.EMERALD_BLOCK;
import static net.minecraft.init.Blocks.OBSIDIAN;
import static net.minecraft.init.Items.EMERALD;
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
            "gemInfusedLavaCrystal"));
        manager.addRecipe(new BaseShapelessOreRecipe(maps[4], fragments[0], fragments[1], fragments[2], fragments[3]));
        if (recipes.enableArrowRecipes) {
            createArrowRecipes(manager, itemLavaArrow, getItemStack(infusedLavaCrystal), getItemStack(blockLavaInfusedObsidian));
            createArrowRecipes(manager, itemObsidianArrow, getItemStack(OBSIDIAN), getItemStack(blockCompressedObsidian));
            createArrowRecipes(manager, itemEmeraldArrow, getItemStack(EMERALD), getItemStack(EMERALD_BLOCK));
        }
    }

    private void createArrowRecipes(BaseCraftingManager manager, Item result, ItemStack bolt, ItemStack arrowHead) {
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(result, 8),
            "BBL  ",
            "BL   ",
            "L L  ",
            "   L ",
            "    L",
            'L', bolt,
            'B', arrowHead));
        manager.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(result, 8),
            "  LBB",
            "   LB",
            "  L L",
            " L   ",
            "L    ",
            'L', bolt,
            'B', arrowHead));
    }
}
