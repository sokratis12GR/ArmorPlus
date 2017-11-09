package net.thedragonteam.armorplus.api.crafting.utils;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.IRecipe;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class CraftingUtils {

    /**
     * Adds a shapeless crafting recipe to the the game.
     */
    public static void addShapelessRecipe(List<ItemStack> list, Object... recipeComponents) {
        Arrays.stream(recipeComponents).forEachOrdered(object -> {
            if (object instanceof ItemStack) {
                list.add(((ItemStack) object).copy());
            } else if (object instanceof Item) {
                list.add(new ItemStack((Item) object));
            } else {
                assert object instanceof Block : "Invalid shapeless recipe: unknown type " + object.getClass().getName() + "!";

                list.add(new ItemStack((Block) object));
            }
        });
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     */
    public static ItemStack findMatchingRecipe(List<IRecipe> recipes, InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe recipe : recipes) {
            if (recipe.matches(craftMatrix, worldIn)) {
                return Optional.of(recipe).map(irecipe -> irecipe.getCraftingResult(craftMatrix)).orElse(ItemStack.EMPTY);
            }
        }
        return Optional.<IRecipe>empty().map(irecipe -> irecipe.getCraftingResult(craftMatrix)).orElse(ItemStack.EMPTY);
    }


    public static NonNullList<ItemStack> getRemainingItems(List<IRecipe> recipes, InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe irecipe : recipes) {
            if (irecipe.matches(craftMatrix, worldIn)) {
                return irecipe.getRemainingItems(craftMatrix);
            }
        }

        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(craftMatrix.getSizeInventory(), ItemStack.EMPTY);

        IntStream.range(0, nonnulllist.size()).forEachOrdered(i -> nonnulllist.set(i, craftMatrix.getStackInSlot(i)));

        return nonnulllist;
    }
}
