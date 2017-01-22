/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.client.gui.GuiHighTechBench;
import net.thedragonteam.armorplus.client.gui.GuiLavaInfuser;
import net.thedragonteam.armorplus.client.gui.GuiUltiTechBench;
import net.thedragonteam.armorplus.client.gui.GuiWorkbench;
import net.thedragonteam.armorplus.compat.jei.hightechbench.*;
import net.thedragonteam.armorplus.compat.jei.lavainfuser.*;
import net.thedragonteam.armorplus.compat.jei.ultitechbench.*;
import net.thedragonteam.armorplus.compat.jei.workbench.*;
import net.thedragonteam.armorplus.container.ContainerHighTechBench;
import net.thedragonteam.armorplus.container.ContainerLavaInfuser;
import net.thedragonteam.armorplus.container.ContainerUltiTechBench;
import net.thedragonteam.armorplus.container.ContainerWorkbench;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import static mezz.jei.plugins.jei.JEIInternalPlugin.ingredientRegistry;
import static net.thedragonteam.armorplus.api.Constants.Compat.*;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

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
                new UTBCategory(),
                new LavaInfuserFuelCategory(),
                new LavaInfuserCategory()
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
                new UTBShapelessOreRecipeHandler(jeiHelper),
                new InfusingRecipeHandler(),
                new InfuserFuelRecipeHandler()
        );

        registry.addRecipeClickArea(GuiWorkbench.class, 88, 32, 28, 23, JEI_CATEGORY_WORKBENCH);
        registry.addRecipeClickArea(GuiHighTechBench.class, 88, 40, 28, 27, JEI_CATEGORY_HIGH_TECH_BENCH);
        registry.addRecipeClickArea(GuiUltiTechBench.class, 112, 50, 28, 27, JEI_CATEGORY_ULTI_TECH_BENCH);
        registry.addRecipeClickArea(GuiLavaInfuser.class, 92, 34, 28, 27, JEI_CATEGORY_LAVA_INFUSER_INFUSING, JEI_CATEGORY_LAVA_INFUSER_FUEL);

        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

        recipeTransferRegistry.addRecipeTransferHandler(ContainerWorkbench.class, JEI_CATEGORY_WORKBENCH, 1, 9, 10, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerHighTechBench.class, JEI_CATEGORY_HIGH_TECH_BENCH, 1, 16, 17, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerUltiTechBench.class, JEI_CATEGORY_ULTI_TECH_BENCH, 1, 25, 26, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerLavaInfuser.class, JEI_CATEGORY_LAVA_INFUSER_INFUSING, 0, 1, 3, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerLavaInfuser.class, JEI_CATEGORY_LAVA_INFUSER_FUEL, 1, 1, 3, 36);

        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.benches[0]), JEI_CATEGORY_WORKBENCH);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.benches[1]), JEI_CATEGORY_HIGH_TECH_BENCH);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.benches[2]), JEI_CATEGORY_ULTI_TECH_BENCH);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.lavaInfuser), JEI_CATEGORY_LAVA_INFUSER_INFUSING, JEI_CATEGORY_LAVA_INFUSER_FUEL);

        registry.addRecipes(WorkbenchCraftingManager.getInstance().getRecipeList());
        registry.addRecipes(HighTechBenchCraftingManager.getInstance().getRecipeList());
        registry.addRecipes(UltiTechBenchCraftingManager.getInstance().getRecipeList());
        registry.addRecipes(InfuserRecipeMaker.getFurnaceRecipes(jeiHelper));
        registry.addRecipes(InfuserFuelRecipeMaker.getFuelRecipes(ingredientRegistry, jeiHelper));

        addDescription(registry, ModItems.materials, 1, localize("armorplus.jei.guardian_scale.desc"));
        addDescription(registry, ModItems.materials, 2, localize("armorplus.jei.wither_bone.desc"));
        addDescription(registry, ModItems.materials, 3, localize("armorplus.jei.ender_dragon_scale.desc"));
        addDescription(registry, ModBlocks.lavaInfuser, localize("armorplus.jei.lava_infuser.desc"));
        super.register(registry);
    }

    private void addDescription(IModRegistry registry, Item item, int meta, String description) {
        registry.addDescription(new ItemStack(item, 1, meta), description);
    }

    private void addDescription(IModRegistry registry, Item item, String description) {
        registry.addDescription(new ItemStack(item, 1), description);
    }

    private void addDescription(IModRegistry registry, Block block, int meta, String description) {
        registry.addDescription(new ItemStack(block, 1, meta), description);
    }

    private void addDescription(IModRegistry registry, Block block, String description) {
        registry.addDescription(new ItemStack(block, 1), description);
    }
}
