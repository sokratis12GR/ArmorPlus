package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jei.recipes.BrokenCraftingRecipeException;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.thedragonteam.armorplus.api.crafting.IRecipe;

import java.util.List;

import static mezz.jei.api.ingredients.VanillaTypes.ITEM;

public class JEIUtils {

    public static class XYProperties {
        private final int xPos;
        private final int yPos;
        private final int height;
        private final int width;

        public XYProperties(int xPos, int yPos, int height, int width) {
            this.xPos = xPos;
            this.yPos = yPos;
            this.height = height;
            this.width = width;
        }

        public XYProperties(int xPos, int yPos, int xy) {
            this(xPos, yPos, xy, xy);
        }
    }

    public static void setRecipe(IRecipeLayout rL, IRecipeWrapper rW, IIngredients in, ICraftingGridHelper cGH, XYProperties prop, int inputSlot, int outputSlot) {
        IGuiItemStackGroup guiItemStacks = rL.getItemStacks();

        guiItemStacks.init(outputSlot, false, prop.xPos, prop.yPos);

        for (int y = 0; y < prop.height; ++y) {
            for (int x = 0; x < prop.width; ++x) {
                int index = inputSlot + x + (y * prop.height);
                guiItemStacks.init(index, true, x * 18, y * 18);
            }
        }

        if (rW instanceof ICustomCraftingRecipeWrapper) {
            ICustomCraftingRecipeWrapper customWrapper = (ICustomCraftingRecipeWrapper) rW;
            customWrapper.setRecipe(rL, in);
            return;
        }

        List<List<ItemStack>> inputs = in.getInputs(ITEM);
        List<List<ItemStack>> outputs = in.getOutputs(ITEM);

        if (rW instanceof IShapedCraftingRecipeWrapper) {
            IShapedCraftingRecipeWrapper wrapper = (IShapedCraftingRecipeWrapper) rW;
            cGH.setInputs(guiItemStacks, inputs, wrapper.getWidth(), wrapper.getHeight());
        } else {
            cGH.setInputs(guiItemStacks, inputs);
            rL.setShapeless();
        }

        if (!outputs.isEmpty()) {
            guiItemStacks.set(outputSlot, outputs.get(0));
        }
    }

    public static void getIngredients(IIngredients ingredients, IRecipe recipe, NonNullList<ItemStack> recipeItems) {
        ItemStack recipeOutput = recipe.getRecipeOutput();
        try {
            ingredients.setInputs(ITEM, recipeItems);
            if (!recipeOutput.isEmpty()) {
                ingredients.setOutput(ITEM, recipeOutput);
            }
        } catch (RuntimeException e) {
            String info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, recipeItems, recipeOutput);
            throw new BrokenCraftingRecipeException(info, e);
        }
    }


    public static void getIngredients(IIngredients ingredients, IRecipe recipe, IJeiHelpers jeiHelpers, List inputItems) {
        IStackHelper stackHelper = jeiHelpers.getStackHelper();
        ItemStack recipeOutput = recipe.getRecipeOutput();

        try {
            List<List<ItemStack>> inputs = stackHelper.expandRecipeItemStackInputs(inputItems);
            ingredients.setInputLists(ITEM, inputs);
            if (!recipeOutput.isEmpty()) {
                ingredients.setOutput(ITEM, recipeOutput);
            }
        } catch (RuntimeException e) {
            String info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, inputItems, recipeOutput);
            throw new BrokenCraftingRecipeException(info, e);
        }
    }
}
