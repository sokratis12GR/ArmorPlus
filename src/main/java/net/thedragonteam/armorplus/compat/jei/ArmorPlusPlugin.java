/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
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
import net.thedragonteam.armorplus.compat.jei.lavainfuser.InfuserRecipeMaker;
import net.thedragonteam.armorplus.compat.jei.lavainfuser.InfusingRecipeHandler;
import net.thedragonteam.armorplus.compat.jei.lavainfuser.LavaInfuserCategory;
import net.thedragonteam.armorplus.compat.jei.ultitechbench.*;
import net.thedragonteam.armorplus.compat.jei.workbench.*;
import net.thedragonteam.armorplus.container.ContainerHighTechBench;
import net.thedragonteam.armorplus.container.ContainerLavaInfuser;
import net.thedragonteam.armorplus.container.ContainerUltiTechBench;
import net.thedragonteam.armorplus.container.ContainerWorkbench;
import net.thedragonteam.armorplus.registry.APBlocks;
import net.thedragonteam.armorplus.registry.APItems;
import net.thedragonteam.armorplus.registry.ModBlocks;

import static net.thedragonteam.armorplus.api.Constants.Compat.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

@JEIPlugin
public class ArmorPlusPlugin extends BlankModPlugin {

    public static IJeiHelpers jeiHelper;

    @Override
    public void register(IModRegistry registry) {
        jeiHelper = registry.getJeiHelpers();
        IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();

        //IGuiHelper guiHelper = jeiHelper.getGuiHelper();

        registry.addRecipeCategories(
                new WBCategory(),
                new HTBCategory(),
                new UTBCategory(),
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
                new InfusingRecipeHandler()
        );

        registry.addRecipeClickArea(GuiWorkbench.class, 88, 32, 28, 23, JEI_CATEGORY_WORKBENCH);
        registry.addRecipeClickArea(GuiHighTechBench.class, 88, 40, 28, 27, JEI_CATEGORY_HIGH_TECH_BENCH);
        registry.addRecipeClickArea(GuiUltiTechBench.class, 112, 50, 28, 27, JEI_CATEGORY_ULTI_TECH_BENCH);
        registry.addRecipeClickArea(GuiLavaInfuser.class, 92, 34, 28, 27, JEI_CATEGORY_LAVA_INFUSER_INFUSING);

        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

        recipeTransferRegistry.addRecipeTransferHandler(ContainerWorkbench.class, JEI_CATEGORY_WORKBENCH, 1, 9, 10, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerHighTechBench.class, JEI_CATEGORY_HIGH_TECH_BENCH, 1, 16, 17, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerUltiTechBench.class, JEI_CATEGORY_ULTI_TECH_BENCH, 1, 25, 26, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerLavaInfuser.class, JEI_CATEGORY_LAVA_INFUSER_INFUSING, 0, 1, 3, 36);

        registry.addRecipeCategoryCraftingItem(getItemStack(APBlocks.workbench), JEI_CATEGORY_WORKBENCH);
        registry.addRecipeCategoryCraftingItem(getItemStack(APBlocks.highTechBench), JEI_CATEGORY_HIGH_TECH_BENCH);
        registry.addRecipeCategoryCraftingItem(getItemStack(APBlocks.ultiTechBench), JEI_CATEGORY_ULTI_TECH_BENCH);
        registry.addRecipeCategoryCraftingItem(getItemStack(ModBlocks.lavaInfuser), JEI_CATEGORY_LAVA_INFUSER_INFUSING);

        registry.addRecipes(WorkbenchCraftingManager.getInstance().getRecipeList());
        registry.addRecipes(HighTechBenchCraftingManager.getInstance().getRecipeList());
        registry.addRecipes(UltiTechBenchCraftingManager.getInstance().getRecipeList());
        registry.addRecipes(InfuserRecipeMaker.getFurnaceRecipes(jeiHelper));

        addDescription(registry, APItems.guardianScale, localize("armorplus.jei.guardian_scale.desc"));
        addDescription(registry, APItems.witherBone, localize("armorplus.jei.wither_bone.desc"));
        addDescription(registry, APItems.enderDragonScale, localize("armorplus.jei.ender_dragon_scale.desc"));
        addDescription(registry, APBlocks.lavaInfuser, localize("armorplus.jei.lava_infuser.desc"));
        super.register(registry);
    }

    public void addDescription(IModRegistry registry, ItemStack item, String description) {
        registry.addDescription(item, description);
    }

    public void addDescription(IModRegistry registry, Item item, int meta, String description) {
        registry.addDescription(getItemStack(item, meta), description);
    }

    public void addDescription(IModRegistry registry, Item item, String description) {
        registry.addDescription(getItemStack(item), description);
    }

    public void addDescription(IModRegistry registry, Block block, int meta, String description) {
        registry.addDescription(getItemStack(block, meta), description);
    }

    public void addDescription(IModRegistry registry, Block block, String description) {
        registry.addDescription(getItemStack(block), description);
    }
}
