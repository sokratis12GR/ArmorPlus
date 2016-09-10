/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.*;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.advarmorforge.AdvancedArmorForgeCraftingManager;
import net.thedragonteam.armorplus.api.crafting.armorforge.ArmorForgeCraftingManager;
import net.thedragonteam.armorplus.client.gui.GuiAdvancedArmorForge;
import net.thedragonteam.armorplus.client.gui.GuiArmorForge;
import net.thedragonteam.armorplus.compat.jei.advarmorforge.AdvArmorForgeRecipeCategory;
import net.thedragonteam.armorplus.compat.jei.advarmorforge.AdvArmorForgeShapedRecipeHandler;
import net.thedragonteam.armorplus.compat.jei.advarmorforge.AdvArmorForgeShapelessRecipeHandler;
import net.thedragonteam.armorplus.compat.jei.armorforge.ArmorForgeRecipeCategory;
import net.thedragonteam.armorplus.compat.jei.armorforge.ArmorForgeShapedRecipeHandler;
import net.thedragonteam.armorplus.compat.jei.armorforge.ArmorForgeShapelessRecipeHandler;
import net.thedragonteam.armorplus.container.ContainerAdvancedArmorForge;
import net.thedragonteam.armorplus.container.ContainerArmorForge;
import net.thedragonteam.armorplus.registry.ModBlocks;

import javax.annotation.Nonnull;

/**
 * net.thedragonteam.armorplus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:01 PM.
 * - TheDragonTeam
 */
@JEIPlugin
public class ArmorPlusPlugin extends BlankModPlugin {

    public static IJeiHelpers jeiHelper;

    @Override
    public void register(@Nonnull IModRegistry registry) {
        jeiHelper = registry.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelper.getGuiHelper();

        registry.addRecipeCategories(
                new ArmorForgeRecipeCategory(),
                new AdvArmorForgeRecipeCategory()
        );

        registry.addRecipeHandlers(
                new ArmorForgeShapedRecipeHandler(),
                new ArmorForgeShapelessRecipeHandler(guiHelper),
                new AdvArmorForgeShapedRecipeHandler(),
                new AdvArmorForgeShapelessRecipeHandler(guiHelper)
        );

        registry.addRecipeClickArea(GuiArmorForge.class, 88, 32, 28, 23, Constants.Compat.JEI_CATEGORY_ARMOR_FORGE);
        registry.addRecipeClickArea(GuiAdvancedArmorForge.class, 88, 40, 28, 27, Constants.Compat.JEI_CATEGORY_ADVANCED_ARMOR_FORGE);

        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

        recipeTransferRegistry.addRecipeTransferHandler(ContainerArmorForge.class, Constants.Compat.JEI_CATEGORY_ARMOR_FORGE, 1, 9, 10, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerAdvancedArmorForge.class, Constants.Compat.JEI_CATEGORY_ADVANCED_ARMOR_FORGE, 1, 16, 17, 36);

        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.armorForge), Constants.Compat.JEI_CATEGORY_ARMOR_FORGE);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.advancedArmorForge), Constants.Compat.JEI_CATEGORY_ADVANCED_ARMOR_FORGE);

        registry.addRecipes(ArmorForgeCraftingManager.getInstance().getRecipeList());
        registry.addRecipes(AdvancedArmorForgeCraftingManager.getInstance().getRecipeList());
    }
}