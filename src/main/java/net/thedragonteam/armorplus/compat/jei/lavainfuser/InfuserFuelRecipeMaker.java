/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser;

import java.util.*;

public class InfuserFuelRecipeMaker {

    public static List<InfuserFuelRecipe> getFuelRecipes(IIngredientRegistry ingredientRegistry, IJeiHelpers helpers) {
        IGuiHelper guiHelper = helpers.getGuiHelper();
        IStackHelper stackHelper = helpers.getStackHelper();
        List<ItemStack> fuelStacks = ingredientRegistry.getFuels();
        Set<String> oreDictNames = new HashSet<>();
        List<InfuserFuelRecipe> fuelRecipes = new ArrayList<>(fuelStacks.size());
        for (ItemStack fuelStack : fuelStacks) {
            if (fuelStack.isEmpty()) {
                continue;
            }

            int[] oreIDs = OreDictionary.getOreIDs(fuelStack);
            if (oreIDs.length > 0) {
                for (int oreID : oreIDs) {
                    String name = OreDictionary.getOreName(oreID);
                    if (oreDictNames.contains(name)) {
                        continue;
                    }

                    oreDictNames.add(name);
                    List<ItemStack> oreDictFuels = OreDictionary.getOres(name);
                    Collection<ItemStack> oreDictFuelsSet = stackHelper.getAllSubtypes(oreDictFuels);
                    removeNoBurnTime(oreDictFuelsSet);
                    if (oreDictFuels.isEmpty()) {
                        continue;
                    }
                    int burnTime = getBurnTime(oreDictFuels.get(0));

                    fuelRecipes.add(new InfuserFuelRecipe(guiHelper, oreDictFuelsSet, burnTime));
                }
            } else {
                List<ItemStack> fuels = stackHelper.getSubtypes(fuelStack);
                removeNoBurnTime(fuels);
                if (fuels.isEmpty()) {
                    continue;
                }
                int burnTime = getBurnTime(fuels.get(0));
                fuelRecipes.add(new InfuserFuelRecipe(guiHelper, fuels, burnTime));
            }
        }
        return fuelRecipes;
    }

    private static void removeNoBurnTime(Collection<ItemStack> itemStacks) {
        itemStacks.removeIf(itemStack -> getBurnTime(itemStack) == 0);
    }

    private static int getBurnTime(ItemStack itemStack) {
        return TileEntityLavaInfuser.getItemChargeTime(itemStack);
    }
}