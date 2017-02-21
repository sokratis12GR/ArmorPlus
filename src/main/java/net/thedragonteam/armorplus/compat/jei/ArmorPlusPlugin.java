/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.client.gui.GuiHighTechBench;
import net.thedragonteam.armorplus.client.gui.GuiUltiTechBench;
import net.thedragonteam.armorplus.client.gui.GuiWorkbench;
import net.thedragonteam.armorplus.compat.jei.old.hightechbench.*;
import net.thedragonteam.armorplus.compat.jei.old.ultitechbench.*;
import net.thedragonteam.armorplus.compat.jei.old.workbench.*;
import net.thedragonteam.armorplus.container.ContainerHighTechBench;
import net.thedragonteam.armorplus.container.ContainerUltiTechBench;
import net.thedragonteam.armorplus.container.ContainerWorkbench;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.api.Constants.Compat.*;

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

        registry.addRecipeCategories(
                new WBRecipeCategory(),
                new HTBRecipeCategory(),
                new UTBRecipeCategory()
        );
        registry.addRecipeHandlers(
                new WBShapedRecipeHandler(),
                new WBShapelessRecipeHandler(),
                new WBShapedOreRecipeHandler(jeiHelper),
                new WBShapelessOreRecipeHandler(jeiHelper),
                new HTBShapedRecipeHandler(),
                new HTBShapelessRecipeHandler(),
                new HTBShapelessOreRecipeHandler(jeiHelper),
                new HTBShapedOreRecipeHandler(jeiHelper),
                new UTBShapedRecipeHandler(),
                new UTBShapelessRecipeHandler(),
                new UTBShapelessOreRecipeHandler(jeiHelper),
                new UTBShapedOreRecipeHandler(jeiHelper)
        );

        registry.addRecipeClickArea(GuiWorkbench.class, 88, 32, 28, 23, JEI_CATEGORY_WORKBENCH);
        registry.addRecipeClickArea(GuiHighTechBench.class, 88, 40, 28, 27, JEI_CATEGORY_HIGH_TECH_BENCH);
        registry.addRecipeClickArea(GuiUltiTechBench.class, 112, 50, 28, 27, JEI_CATEGORY_ULTI_TECH_BENCH);

        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

        recipeTransferRegistry.addRecipeTransferHandler(ContainerWorkbench.class, JEI_CATEGORY_WORKBENCH, 1, 9, 10, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerHighTechBench.class, JEI_CATEGORY_HIGH_TECH_BENCH, 1, 16, 17, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerUltiTechBench.class, JEI_CATEGORY_ULTI_TECH_BENCH, 1, 25, 26, 36);

        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.benches[0]), JEI_CATEGORY_WORKBENCH);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.benches[1]), JEI_CATEGORY_HIGH_TECH_BENCH);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.benches[2]), JEI_CATEGORY_ULTI_TECH_BENCH);

        registry.addRecipes(WorkbenchCraftingManager.getInstance().getRecipeList());
        registry.addRecipes(HighTechBenchCraftingManager.getInstance().getRecipeList());
        registry.addRecipes(UltiTechBenchCraftingManager.getInstance().getRecipeList());

        addDescription(registry, ModItems.witherBone, "Used to craft the Super Star weapons, armor and the Ultimate Material. Dropped by The Wither Boss and by Wither Skeletons");
        addDescription(registry, ModItems.guardianScale, "Used to craft the Guardian weapons, armor and the Ultimate Material. Dropped by the Elder Guardian and by Guardians");
        addDescription(registry, ModItems.enderDragonScale, "Used to craft the Ender Dragon weapons, armor and the Ultimate Material. Dropped by the Ender Dragon and Ender Dragon Zombies");
    }

    private void addDescription(@Nonnull IModRegistry registry, Item item, String description) {
        registry.addDescription(new ItemStack(item), description);
    }
}