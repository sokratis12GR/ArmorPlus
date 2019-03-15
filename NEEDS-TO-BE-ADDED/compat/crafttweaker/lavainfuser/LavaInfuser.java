/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.crafttweaker.lavainfuser;

/**
 * @author Stan
 */
//@ZenClass("mods.armorplus.LavaInfuser")
//public class LavaInfuser {
//
//    @ZenMethod
//    public static void addRecipe(IItemStack output, IItemStack input, double xp) {
//        CraftTweakerAPI.apply(new Add(new LavaInfuserRecipe(toStack(input), toStack(output), xp)));
//    }
//
//    @ZenMethod
//    public static void remove(IItemStack target) {
//        CraftTweakerAPI.apply(new Remove(toStack(target)));
//    }
//
//    private static class Remove implements IAction {
//        ItemStack remove;
//
//        public Remove(ItemStack rem) {
//            remove = rem;
//        }
//
//        @Override
//        public void apply() {
//            LavaInfuserManager.getInstance().getRecipeList().stream().filter(
//                recipe -> recipe != null && recipe.getRecipeOutput().isItemEqual(remove)
//            ).findFirst().ifPresent(
//                recipe -> LavaInfuserManager.getInstance().getRecipeList().remove(recipe)
//            );
//        }
//
//        @Override
//        public String describe() {
//            return "Removing Lava Infuser recipe for " + remove.getDisplayName();
//        }
//
//    }
//
//    private static class Add implements IAction {
//        private LavaInfuserRecipe recipe;
//
//        public Add(LavaInfuserRecipe recipe) {
//            this.recipe = recipe;
//        }
//
//        @Override
//        public void apply() {
//            LavaInfuserManager.getInstance().addInfusingRecipe(recipe);
//        }
//
//        @Override
//        public String describe() {
//            return "Adding Lava Infuser recipe for " + recipe.getRecipeOutput().getDisplayName();
//        }
//    }
//}
//