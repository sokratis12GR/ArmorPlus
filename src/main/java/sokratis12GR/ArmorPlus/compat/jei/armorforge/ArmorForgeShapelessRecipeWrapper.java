package sokratis12GR.ArmorPlus.compat.jei.armorforge;

import mezz.jei.api.IGuiHelper;
import mezz.jei.plugins.vanilla.crafting.AbstractShapelessRecipeWrapper;
import net.minecraft.item.ItemStack;
import sokratis12GR.ArmorPlus.api.crafting.ShapelessRecipes;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * sokratis12GR.ArmorPlus.compat.jei.armorforge
 * ArmorPlus created by sokratis12GR on 6/22/2016 7:04 PM.
 */
public class ArmorForgeShapelessRecipeWrapper extends AbstractShapelessRecipeWrapper {

    @Nonnull
    private final ShapelessRecipes recipe;

    public ArmorForgeShapelessRecipeWrapper(@Nonnull IGuiHelper guiHelper, @Nonnull ShapelessRecipes recipe) {
        super(guiHelper);
        this.recipe = recipe;
        for (Object input : this.recipe.recipeItems) {
            if (input instanceof ItemStack) {
                ItemStack itemStack = (ItemStack) input;
                if (itemStack.stackSize != 1) {
                    itemStack.stackSize = 1;
                }
            }
        }
    }

    @Nonnull
    @Override
    public List<ItemStack> getInputs() {
        return recipe.recipeItems;
    }

    @Nonnull
    @Override
    public List<ItemStack> getOutputs() {
        return Collections.singletonList(recipe.getRecipeOutput());
    }
}