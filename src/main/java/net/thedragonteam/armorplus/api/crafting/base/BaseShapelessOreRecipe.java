package net.thedragonteam.armorplus.api.crafting.base;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.utils.ShapelessOreRecipeUtils;
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BaseShapelessOreRecipe implements IRecipe {
    protected ItemStack output;
    protected NonNullList<Object> input = NonNullList.create();

    public BaseShapelessOreRecipe(Block result, Object... recipe) {
        this(new ItemStack(result), recipe);
    }

    public BaseShapelessOreRecipe(Item result, Object... recipe) {
        this(new ItemStack(result), recipe);
    }

    public BaseShapelessOreRecipe(ItemStack result, Object... recipe) {
        output = result.copy();
        ShapelessOreRecipeUtils.createRecipe(output, input, recipe);
    }

    BaseShapelessOreRecipe(BaseShapelessRecipe recipe, Map<ItemStack, String> replacements) {
        output = recipe.getRecipeOutput();

        recipe.input.stream().map(
                ingredient -> replacements.entrySet().stream().filter(
                        replace -> OreDictionary.itemMatches(replace.getKey(), ingredient, false)
                ).findFirst().<Object>map(
                        replace -> OreDictionary.getOres(replace.getValue())
                ).orElse(ingredient)
        ).forEachOrdered(finalObj -> input.add(finalObj));
    }

    /**
     * Returns the size of the recipe area
     */
    @Override
    public int getRecipeSize() {
        return input.size();
    }

    @Override
    @Nonnull
    public ItemStack getRecipeOutput() {
        return output;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCraftingImproved var1) {
        return output.copy();
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(@Nonnull InventoryCraftingImproved inv, @Nonnull World world) {
        return ShapelessOreRecipeUtils.matches(input, inv);
    }

    /**
     * Returns the input for this recipe, any mod accessing this value should never
     * manipulate the values in this array as it will effect the recipe itself.
     *
     * @return The recipes input vales.
     */
    public NonNullList<Object> getInput() {
        return this.input;
    }

}