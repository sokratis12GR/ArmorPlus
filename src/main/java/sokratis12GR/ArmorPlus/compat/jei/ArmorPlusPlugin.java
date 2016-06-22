package sokratis12GR.ArmorPlus.compat.jei;

/**
 * sokratis12GR.ArmorPlus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 11:01 PM.
 */

import mezz.jei.api.*;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import sokratis12GR.ArmorPlus.api.Constants;
import sokratis12GR.ArmorPlus.client.gui.GuiArmorForge;
import sokratis12GR.ArmorPlus.compat.jei.armorforge.ArmorForgeRecipeCategory;
import sokratis12GR.ArmorPlus.compat.jei.armorforge.ArmorForgeShapedRecipeHandler;
import sokratis12GR.ArmorPlus.compat.jei.armorforge.ArmorForgeShapelessRecipeHandler;
import sokratis12GR.ArmorPlus.container.ContainerArmorForge;
import sokratis12GR.ArmorPlus.api.crafting.ArmorForgeCraftingManager;
import sokratis12GR.ArmorPlus.registry.ModBlocks;

import javax.annotation.Nonnull;

@JEIPlugin
public class ArmorPlusPlugin extends BlankModPlugin {
    @Override
    public void register(@Nonnull IModRegistry registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        ISubtypeRegistry nbtRegistry = jeiHelpers.getSubtypeRegistry();
        nbtRegistry.useNbtForSubtypes(
        );

        if (FluidRegistry.isUniversalBucketEnabled()) {
            nbtRegistry.useNbtForSubtypes(ForgeModContainer.getInstance().universalBucket);
        }

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