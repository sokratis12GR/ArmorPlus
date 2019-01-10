/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.crafttweaker.lavainfuser;

import com.sofodev.armorplus.api.lavainfuser.LavaInfuserManager;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static com.sofodev.armorplus.compat.crafttweaker.InputHelper.toStack;

/**
 * @author Stan
 */
@ZenClass("mods.armorplus.LavaInfuser")
public class LavaInfuser {

    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack input, double xp) {
        CraftTweakerAPI.apply(new Add(new LavaInfuserRecipe(toStack(input), toStack(output), xp)));
    }

    @ZenMethod
    public static void remove(IItemStack target) {
        CraftTweakerAPI.apply(new Remove(toStack(target)));
    }

    private static class Remove implements IAction {
        ItemStack remove;

        public Remove(ItemStack rem) {
            remove = rem;
        }

        @Override
        public void apply() {
            for (Object obj : LavaInfuserManager.getInstance().getRecipeList()) {
                if (obj instanceof LavaInfuserRecipe) {
                    LavaInfuserRecipe craft = (LavaInfuserRecipe) obj;
                    if (craft.getRecipeOutput().isItemEqual(remove)) {
                        LavaInfuserManager.getInstance().getRecipeList().remove(obj);
                        break;
                    }
                }
            }
        }

        @Override
        public String describe() {
            return "Removing Lava Infuser recipe for " + remove.getDisplayName();
        }

    }

    private static class Add implements IAction {
        private LavaInfuserRecipe recipe;

        public Add(LavaInfuserRecipe recipe) {
            this.recipe = recipe;
        }

        @Override
        public void apply() {
            LavaInfuserManager.getInstance().addInfusingRecipe(recipe);
        }

        @Override
        public String describe() {
            return "Adding Lava Infuser recipe for " + recipe.getRecipeOutput().getDisplayName();
        }
    }
}
