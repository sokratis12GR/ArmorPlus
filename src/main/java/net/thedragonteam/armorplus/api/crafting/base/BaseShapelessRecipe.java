package net.thedragonteam.armorplus.api.crafting.base;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.utils.ShapelessRecipeUtils;
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved;

import javax.annotation.Nonnull;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BaseShapelessRecipe implements IRecipe {

    public final NonNullList<ItemStack> input;
    /**
     * Is the ItemStack that you get when craft the recipe.
     */
    private final ItemStack recipeOutput;

    public BaseShapelessRecipe(ItemStack output, NonNullList<ItemStack> inputList) {
        this.recipeOutput = output;
        this.input = inputList;
    }

    @Override
    @Nonnull
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(@Nonnull InventoryCraftingImproved inv, @Nonnull World worldIn) {
        return ShapelessRecipeUtils.matches(input, inv);
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCraftingImproved inv) {
        return this.recipeOutput.copy();
    }

    /**
     * Returns the size of the recipe area
     */
    @Override
    public int getRecipeSize() {
        return this.input.size();
    }

    public NonNullList<ItemStack> getInput() {
        return input;
    }
}
