/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

//package net.thedragonteam.armorplus.compat.minetweaker;

//@ZenClass("mods." + ArmorPlus.MODID + ".UltiTechBench")
//public class UltiTechBench {
//
//    @ZenMethod
//    public static void addShapeless(IItemStack output, IIngredient[] ingredients) {
//        MineTweakerAPI.apply(new Add(new ShapelessOreRecipe(toStack(output), toObjects(ingredients))));
//    }
//
//    @ZenMethod
//    public static void addShaped(IItemStack output, IIngredient[][] ingredients) {
//        MineTweakerAPI.apply(new Add(new ShapedOreRecipe(toStack(output), toUltiTechShapedObjects(ingredients))));
//    }
//
//    @ZenMethod
//    public static void remove(IItemStack target) {
//        MineTweakerAPI.apply(new Remove(toStack(target)));
//    }
//
//    private static class Add implements IUndoableAction {
//        IRecipe recipe;
//
//        public Add(IRecipe add) {
//            recipe = add;
//        }
//
//        @Override
//        public void apply() {
//            UltiTechBenchCraftingManager.getInstance().getRecipeList().add(recipe);
//            MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(recipe);
//        }
//
//        @Override
//        public boolean canUndo() {
//            return true;
//        }
//
//        @Override
//        public void undo() {
//            UltiTechBenchCraftingManager.getInstance().getRecipeList().remove(recipe);
//            MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(recipe);
//        }
//
//        @Override
//        public String describe() {
//            return "Adding Ulti-Tech Bench Recipe for " + recipe.getRecipeOutput().getDisplayName();
//        }
//
//        @Override
//        public String describeUndo() {
//            return "Un-adding Ulti-Tech Bench for " + recipe.getRecipeOutput().getDisplayName();
//        }
//
//        @Override
//        public Object getOverrideKey() {
//            return null;
//        }
//
//    }
//
//    private static class Remove implements IUndoableAction {
//        IRecipe recipe = null;
//        ItemStack remove;
//
//        public Remove(ItemStack rem) {
//            remove = rem;
//        }
//
//        @Override
//        public void apply() {
//
//            for (Object obj : UltiTechBenchCraftingManager.getInstance().getRecipeList()) {
//                if (obj instanceof IRecipe) {
//                    IRecipe craft = (IRecipe) obj;
//                    if (craft.getRecipeOutput().isItemEqual(remove)) {
//                        recipe = craft;
//                        UltiTechBenchCraftingManager.getInstance().getRecipeList().remove(obj);
//                        MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(recipe);
//                        break;
//                    }
//                }
//            }
//        }
//
//        @Override
//        public boolean canUndo() {
//            return recipe != null;
//        }
//
//        @Override
//        public void undo() {
//            UltiTechBenchCraftingManager.getInstance().getRecipeList().add(recipe);
//            MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(recipe);
//        }
//
//        @Override
//        public String describe() {
//            return "Removing Ulti-Tech Bench Recipe for " + remove.getDisplayName();
//        }
//
//        @Override
//        public String describeUndo() {
//            return "Un-removing Ulti-Tech Bench Recipe for " + remove.getDisplayName();
//        }
//
//        @Override
//        public Object getOverrideKey() {
//            return null;
//        }
//
//    }
//}