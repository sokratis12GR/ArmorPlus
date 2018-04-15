package net.thedragonteam.armorplus.api.crafting.base;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.ModHighTechItemRecipes;
import net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.ModTierTwoRecipes;
import net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.ModTinkersConstructRecipes;
import net.thedragonteam.armorplus.api.crafting.hightechbench.recipes.ModWeaponTierTwoRecipes;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes.*;
import net.thedragonteam.armorplus.api.crafting.utils.CraftingUtils;
import net.thedragonteam.armorplus.api.crafting.workbench.recipes.ModItemRecipes;
import net.thedragonteam.armorplus.api.crafting.workbench.recipes.ModOriginRecipes;
import net.thedragonteam.armorplus.api.crafting.workbench.recipes.ModSpecialMobRecipes;
import net.thedragonteam.armorplus.api.crafting.workbench.recipes.ModWeaponsTierOneRecipes;
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BaseCraftingManager {

    private static final BaseCraftingManager CHAMPION_BENCH = new BaseCraftingManager(9, "Champion Bench");
    private static final BaseCraftingManager ULTI_TECH_BENCH = new BaseCraftingManager(7, "Ulti-Tech Bench") {
        {
            new ModUltimateRecipes().addRecipes(this);
            new ModEnderDragonRecipes().addRecipes(this);
            new ModSuperStarRecipes().addRecipes(this);
            new ModGuardianRecipes().addRecipes(this);
            new ModWeaponTierThreeRecipes().addRecipes(this);
            new ModUltiTechItemRecipes().addRecipes(this);
        }
    };
    private static final BaseCraftingManager HIGH_TECH_BENCH = new BaseCraftingManager(5, "High-Tech Bench") {
        {
            new ModTierTwoRecipes().addRecipes(this);
            new ModHighTechItemRecipes().addRecipes(this);
            new ModWeaponTierTwoRecipes().addRecipes(this);
            new ModTinkersConstructRecipes().addRecipes(this);
        }
    };
    private static final BaseCraftingManager WORKBENCH = new BaseCraftingManager(3, "Workbench") {
        {
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
        return ULTI_TECH_BENCH;
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
    }

    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public BaseShapedRecipe addRecipe(ItemStack stack, Object... recipeComponents) {
        StringBuilder s = new StringBuilder();
        int index = 0, width = 0, height = 0;

        if (recipeComponents[index] instanceof String[]) {
            String[] astring = (String[]) recipeComponents[index++];

            for (String s2 : astring) {
                ++height;
                width = s2.length();
                s.append(s2);
            }
        } else {
            while (recipeComponents[index] instanceof String) {
                String s1 = (String) recipeComponents[index++];
                ++height;
                width = s1.length();
                s.append(s1);
            }
        }

        Map<Character, ItemStack> map = CraftingUtils.getCharacterItemStackMap(index, recipeComponents);

        NonNullList<ItemStack> aitemstack = NonNullList.withSize(width * height, ItemStack.EMPTY);

        IntStream.range(0, width * height).forEachOrdered(l -> {
            char c0 = s.charAt(l);
            aitemstack.set(l, map.containsKey(c0) ? map.get(c0).copy() : ItemStack.EMPTY);
        });

        BaseShapedRecipe shapedrecipes = new BaseShapedRecipe(xy, width, height, aitemstack, stack);
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