/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.*;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.Constants;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.client.gui.GuiHighTechBench;
import net.thedragonteam.armorplus.client.gui.GuiWorkbench;
import net.thedragonteam.armorplus.compat.jei.hightechbench.*;
import net.thedragonteam.armorplus.compat.jei.workbench.*;
import net.thedragonteam.armorplus.container.ContainerHighTechBench;
import net.thedragonteam.armorplus.container.ContainerWorkbench;
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
                new WorkbenchRecipeCategory(),
                new HighTechBenchRecipeCategory()
        );

        registry.addRecipeHandlers(
                new WorkbenchShapedRecipeHandler(),
                new WorkbenchShapelessRecipeHandler(guiHelper),
                new WorkbenchShapelessOreRecipeHandler(guiHelper),
                new WorkbenchShapedOreRecipeHandler(),
                new HighTechBenchShapelessRecipeHandler(guiHelper),
                new HighTechBenchShapedRecipeHandler(),
                new HighTechBenchShapelessOreRecipeHandler(guiHelper),
                new HighTechBenchShapedOreRecipeHandler()
        );

        registry.addRecipeClickArea(GuiWorkbench.class, 88, 32, 28, 23, Constants.Compat.JEI_CATEGORY_WORKBENCH);
        registry.addRecipeClickArea(GuiHighTechBench.class, 88, 40, 28, 27, Constants.Compat.JEI_CATEGORY_HIGH_TECH_BENCH);

        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

        recipeTransferRegistry.addRecipeTransferHandler(ContainerWorkbench.class, Constants.Compat.JEI_CATEGORY_WORKBENCH, 1, 9, 10, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerHighTechBench.class, Constants.Compat.JEI_CATEGORY_HIGH_TECH_BENCH, 1, 16, 17, 36);

        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.arpWorkbench), Constants.Compat.JEI_CATEGORY_WORKBENCH);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.arpHighTechBench), Constants.Compat.JEI_CATEGORY_HIGH_TECH_BENCH);

        registry.addRecipes(WorkbenchCraftingManager.getInstance().getRecipeList());
        registry.addRecipes(HighTechBenchCraftingManager.getInstance().getRecipeList());
    }
}