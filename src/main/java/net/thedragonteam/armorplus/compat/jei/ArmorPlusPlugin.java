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
import net.thedragonteam.armorplus.compat.jei.hightechbench.*;
import net.thedragonteam.armorplus.compat.jei.ultitechbench.*;
import net.thedragonteam.armorplus.compat.jei.workbench.*;
import net.thedragonteam.armorplus.container.ContainerHighTechBench;
import net.thedragonteam.armorplus.container.ContainerUltiTechBench;
import net.thedragonteam.armorplus.container.ContainerWorkbench;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.thedragonlib.util.TextHelper;

import static net.thedragonteam.armorplus.api.Constants.Compat.*;

@JEIPlugin
public class ArmorPlusPlugin extends BlankModPlugin {

    public static IJeiHelpers jeiHelper;

    @Override
    public void register(IModRegistry registry) {
        jeiHelper = registry.getJeiHelpers();

        //IGuiHelper guiHelper = jeiHelper.getGuiHelper();

        registry.addRecipeCategories(
                new WBCategory(),
                new HTBCategory(),
                new UTBCategory()
        );
        registry.addRecipeHandlers(
                new WBShapedRecipeHandler(),
                new WBShapelessRecipeHandler(),
                new WBShapedOreRecipeHandler(jeiHelper),
                new WBShapelessOreRecipeHandler(jeiHelper),
                new HTBShapedRecipeHandler(),
                new HTBShapelessRecipeHandler(),
                new HTBShapedOreRecipeHandler(jeiHelper),
                new HTBShapelessOreRecipeHandler(jeiHelper),
                new UTBShapedRecipeHandler(),
                new UTBShapelessRecipeHandler(),
                new UTBShapedOreRecipeHandler(jeiHelper),
                new UTBShapelessOreRecipeHandler(jeiHelper)
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

        addDescription(registry, ModItems.materials, 1, TextHelper.localize("armorplus.jei.guardian_scale.desc"));
        addDescription(registry, ModItems.materials, 2, TextHelper.localize("armorplus.jei.wither_bone.desc"));
        addDescription(registry, ModItems.materials, 3, TextHelper.localize("armorplus.jei.ender_dragon_scale.desc"));
        super.register(registry);
    }

    private void addDescription(IModRegistry registry, Item item, int meta, String description) {
        registry.addDescription(new ItemStack(item, 1, meta), description);
    }
}
