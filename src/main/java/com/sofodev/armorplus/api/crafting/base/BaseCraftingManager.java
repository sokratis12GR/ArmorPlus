/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.base;

import com.google.common.collect.Lists;
import com.sofodev.armorplus.api.crafting.IRecipe;
import com.sofodev.armorplus.api.crafting.hightechbench.recipes.ModHighTechItemRecipes;
import com.sofodev.armorplus.api.crafting.hightechbench.recipes.ModTierTwoRecipes;
import com.sofodev.armorplus.api.crafting.hightechbench.recipes.ModTinkersConstructRecipes;
import com.sofodev.armorplus.api.crafting.hightechbench.recipes.ModWeaponTierTwoRecipes;
import com.sofodev.armorplus.api.crafting.ultitechbench.recipes.*;
import com.sofodev.armorplus.api.crafting.utils.CraftingUtils;
import com.sofodev.armorplus.api.crafting.workbench.recipes.ModItemRecipes;
import com.sofodev.armorplus.api.crafting.workbench.recipes.ModOriginRecipes;
import com.sofodev.armorplus.api.crafting.workbench.recipes.ModSpecialMobRecipes;
import com.sofodev.armorplus.api.crafting.workbench.recipes.ModWeaponsTierOneRecipes;
import com.sofodev.armorplus.common.container.base.InventoryCraftingImproved;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.common.registry.ModItems.fragments;
import static com.sofodev.armorplus.common.registry.ModItems.maps;

/**
 * @author Sokratis Fotkatzikis
 */
public abstract class BaseCraftingManager {

    public static final BaseCraftingManager CHAMPION_BENCH = new BaseCraftingManager(9, "Champion Bench") {
        @Override
        public void setRecipes() {
            //None
        }
    };
    public static final BaseCraftingManager ULT_TECH_BENCH = new BaseCraftingManager(7, "Ulti-Tech Bench") {

        @Override
        public void setRecipes() {
            new ModUltimateRecipes().addRecipes(this);
            new ModEnderDragonRecipes().addRecipes(this);
            new ModSuperStarRecipes().addRecipes(this);
            new ModGuardianRecipes().addRecipes(this);
            new ModWeaponTierThreeRecipes().addRecipes(this);
            new ModUltiTechItemRecipes().addRecipes(this);
        }
    };
    public static final BaseCraftingManager HIGH_TECH_BENCH = new BaseCraftingManager(5, "High-Tech Bench") {

        @Override
        public void setRecipes() {
            new ModTierTwoRecipes().addRecipes(this);
            new ModHighTechItemRecipes().addRecipes(this);
            new ModWeaponTierTwoRecipes().addRecipes(this);
            new ModTinkersConstructRecipes().addRecipes(this);
        }
    };

    public static final BaseCraftingManager WORKBENCH = new BaseCraftingManager(3, "Workbench") {
        @Override
        public void setRecipes() {
            new ModItemRecipes().addRecipes(this);
            new ModOriginRecipes().addRecipes(this);
            new ModSpecialMobRecipes().addRecipes(this);
            new ModWeaponsTierOneRecipes().addRecipes(this);
        }
    };

    public static BaseCraftingManager getCBInstance() {
        return CHAMPION_BENCH;
    }

    public static BaseCraftingManager getUTBInstance() {
        return ULT_TECH_BENCH;
    }

    public static BaseCraftingManager getHTBInstance() {
        return HIGH_TECH_BENCH;
    }

    public static BaseCraftingManager getWBInstance() {
        return WORKBENCH;
    }

    private final List<IRecipe> recipes = Lists.newArrayList();
    private int xy;
    private String name;

    public BaseCraftingManager(int xy, String name) {
        this.xy = xy;
        this.name = name;
        this.recipes.sort((pCompare1, pCompare2) -> Integer.compare(pCompare2.getRecipeSize(), pCompare1.getRecipeSize()));
        this.setRecipes();
    }

    public abstract void setRecipes();

    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public BaseShapedRecipe addRecipe(ItemStack stack, Object... recipeComponents) {
        StringBuilder shape = new StringBuilder();
        int index = 0;
        int width = 0;
        int height = 0;

        if (recipeComponents[index] instanceof String[]) {
            String[] components = (String[]) recipeComponents[index++];

            for (String component : components) {
                ++height;
                width = component.length();
                shape.append(component);
            }
        } else {
            while (recipeComponents[index] instanceof String) {
                String components = (String) recipeComponents[index++];
                ++height;
                width = components.length();
                shape.append(components);
            }
        }

        Map<Character, ItemStack> map = CraftingUtils.getCharacterItemStackMap(index, recipeComponents);

        NonNullList<ItemStack> recipeShape = NonNullList.withSize(width * height, ItemStack.EMPTY);

        IntStream.range(0, width * height).forEachOrdered(l -> {
            char c0 = shape.charAt(l);
            recipeShape.set(l, map.containsKey(c0) ? map.get(c0).copy() : ItemStack.EMPTY);
        });

        BaseShapedRecipe shapedrecipes = new BaseShapedRecipe(xy, width, height, recipeShape, stack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    public String getName() {
        return name;
    }

    /**
     * Adds a shapeless crafting recipe to the the game.
     */
    public void addShapelessRecipe(ItemStack stack, Object... recipeComponents) {
        NonNullList<ItemStack> list = NonNullList.create();
        CraftingUtils.addShapelessRecipe(list, recipeComponents);
        this.recipes.add(new BaseShapelessRecipe(stack, list));
    }

    /**
     * Adds an IRecipe to the list of crafting recipes.
     */
    public void addRecipe(IRecipe recipe) {
        this.recipes.add(recipe);
    }

    /**
     * Adds an IRecipe to the list of crafting recipes.
     */
    public void addRecipes(IRecipe... recipes) {
        Arrays.stream(recipes).forEach(this::addRecipe);
    }

    /**
     * Removes an IRecipe to the list of crafting recipes.
     */
    public void removeRecipe(IRecipe recipe) {
        this.recipes.remove(recipe);
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     */
    public ItemStack findMatchingRecipe(InventoryCraftingImproved craftMatrix, World worldIn) {
        return CraftingUtils.findMatchingRecipe(recipes, craftMatrix, worldIn);
    }

    public NonNullList<ItemStack> getRemainingItems(InventoryCraftingImproved craftMatrix, World worldIn) {
        return CraftingUtils.getRemainingItems(recipes, craftMatrix, worldIn);
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}