/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.jei;

/**
 * sokratis12gr.armorplus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:01 PM.
 */

import mezz.jei.api.*;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.armorforge.ArmorForgeCraftingManager;
import net.thedragonteam.armorplus.client.gui.GuiArmorForge;
import net.thedragonteam.armorplus.compat.jei.armorforge.ArmorForgeRecipeCategory;
import net.thedragonteam.armorplus.compat.jei.armorforge.ArmorForgeShapedRecipeHandler;
import net.thedragonteam.armorplus.compat.jei.armorforge.ArmorForgeShapelessRecipeHandler;
import net.thedragonteam.armorplus.container.ContainerArmorForge;
import net.thedragonteam.armorplus.registry.ModBlocks;

import javax.annotation.Nonnull;

;

@JEIPlugin
public class ArmorPlusPlugin extends BlankModPlugin {
    @Override
    public void register(@Nonnull IModRegistry registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        ISubtypeRegistry nbtRegistry = jeiHelpers.getSubtypeRegistry();
        nbtRegistry.useNbtForSubtypes(
        );

        registry.addRecipeCategories(
                new ArmorForgeRecipeCategory(guiHelper)
        );

        registry.addRecipeHandlers(
                new ArmorForgeShapedRecipeHandler(),
                new ArmorForgeShapelessRecipeHandler(guiHelper)
        );

        registry.addRecipeClickArea(GuiArmorForge.class, 88, 32, 28, 23, Constants.Compat.JEI_CATEGORY_ARMOR_FORGE);

        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

        recipeTransferRegistry.addRecipeTransferHandler(ContainerArmorForge.class, Constants.Compat.JEI_CATEGORY_ARMOR_FORGE, 1, 9, 10, 36);

        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.ARMOR_FORGE), Constants.Compat.JEI_CATEGORY_ARMOR_FORGE);

        registry.addRecipes(ArmorForgeCraftingManager.getInstance().getRecipeList());
    }
}