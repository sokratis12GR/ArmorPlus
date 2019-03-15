/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.base;

import com.sofodev.armorplus.api.crafting.IRecipe;
import com.sofodev.armorplus.api.crafting.IShapedRecipe;
import com.sofodev.armorplus.api.crafting.utils.ShapedOreRecipeUtils;
import com.sofodev.armorplus.container.base.InventoryCraftingImproved;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashMap;

public class BaseShapedOreRecipe implements IRecipe, IShapedRecipe {

    private int xy;
    protected ItemStack output;
    protected Object[] input;
    protected int width = 0, height = 0;
    protected boolean mirrored = true;

    public BaseShapedOreRecipe(int xy, Block result, Object... recipe) {
        this(xy, new ItemStack(result), recipe);
    }

    public BaseShapedOreRecipe(int xy, Item result, Object... recipe) {
        this(xy, new ItemStack(result), recipe);
    }

    public BaseShapedOreRecipe(int xy, ItemStack result, Object... recipe) {
        this.xy = xy;
        output = result.copy();

        StringBuilder shape = new StringBuilder();
        int index = 0;

        if (recipe[index] instanceof Boolean) {
            mirrored = (Boolean) recipe[index];
            if (recipe[index + 1] instanceof Object[]) {
                recipe = (Object[]) recipe[index + 1];
            } else {
                index = 1;
            }
        }

        if (recipe[index] instanceof String[]) {
            String[] parts = ((String[]) recipe[index++]);

            Arrays.stream(parts).forEachOrdered(s -> {
                width = s.length();
                shape.append(s);
            });

            height = parts.length;
        } else {
            while (recipe[index] instanceof String) {
                String s = (String) recipe[index++];
                shape.append(s);
                width = s.length();
                height++;
            }
        }

        ShapedOreRecipeUtils.checkRecipeShape(width, height, shape, output, recipe);

        HashMap<Character, Object> itemMap = new HashMap<>();

        for (; index < recipe.length; index += 2) {
            Character chr = (Character) recipe[index];
            Object in = recipe[index + 1];

            if (in instanceof ItemStack) {
                itemMap.put(chr, ((ItemStack) in).copy());
            } else if (in instanceof IItemProvider) {
                itemMap.put(chr, new ItemStack((IItemProvider) in));
            } else {
                StringBuilder ret = new StringBuilder("Invalid shaped ore recipe: ");
                Arrays.stream(recipe).forEachOrdered(tmp -> ret.append(tmp).append(", "));
                ret.append(output);
                throw new RuntimeException(ret.toString());
            }
        }
        input = new Object[width * height];
        int x = 0;
        for (char chr : shape.toString().toCharArray()) {
            input[x++] = itemMap.get(chr);
        }
    }

    //  BaseShapedOreRecipe(BaseShapedRecipe recipe, Map<ItemStack, String> replacements) {
    //      output = recipe.getRecipeOutput();
    //      width = recipe.recipeWidth;
    //      height = recipe.recipeHeight;

    //      input = new Object[recipe.getInput().size()];

    //      for (int i = 0; i < input.length; i++) {
    //          ItemStack ingredient = recipe.getInput().get(i);

    //          if (ingredient.isEmpty()) continue;

    //          input[i] = recipe.getInput().get(i);

    //          for (Map.Entry<ItemStack, String> replace : replacements.entrySet()) {
    //              if (itemMatches(replace.getKey(), ingredient, true)) {
    //                  input[i] = OreDictionary.getOres(replace.getValue());
    //                  break;
    //              }
    //          }
    //      }
    //  }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCraftingImproved var1) {
        return output.copy();
    }

    /**
     * Returns the size of the recipe area
     */
    @Override
    public int getRecipeSize() {
        return input.length;
    }

    @Nonnull
    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(@Nonnull InventoryCraftingImproved inv, @Nonnull World world) {
        return ShapedOreRecipeUtils.matches(width, height, input, inv);
    }

    public BaseShapedOreRecipe setMirrored(boolean mirror) {
        mirrored = mirror;
        return this;
    }

    /**
     * Returns the input for this recipe, any mod accessing this value should never
     * manipulate the values in this array as it will effect the recipe itself.
     *
     * @return The recipes input vales.
     */
    public Object[] getInput() {
        return input;
    }

    @Override
    public int getRecipeWidth() {
        return xy;
    }

    @Override
    public int getRecipeHeight() {
        return xy;
    }
}