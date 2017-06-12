/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

//package net.thedragonteam.armorplus.compat.minetweaker;

//@ZenClass("mods." + ArmorPlus.MODID + ".HighTechBench")
//public class HighTechBench {
//
//    @ZenMethod
//    public static void addShapeless(IItemStack output, IIngredient[] ingredients) {
//        MineTweakerAPI.apply(new Add(new ShapelessOreRecipe(toStack(output), toObjects(ingredients))));
//    }
//
//    @ZenMethod
//    public static void addShaped(IItemStack output, IIngredient[][] ingredients) {
//        MineTweakerAPI.apply(new Add(new ShapedOreRecipe(toStack(output), toHighTechShapedObjects(ingredients))));
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
//            HighTechBenchCraftingManager.getInstance().getRecipeList().add(recipe);
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
//            HighTechBenchCraftingManager.getInstance().getRecipeList().remove(recipe);
//            MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(recipe);
//        }
//
//        @Override
//        public String describe() {
//            return "Adding High-Tech Bench Recipe for " + recipe.getRecipeOutput().getDisplayName();
//        }
//
//        @Override
//        public String describeUndo() {
//            return "Un-adding High-Tech Bench for " + recipe.getRecipeOutput().getDisplayName();
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
//            for (Object obj : HighTechBenchCraftingManager.getInstance().getRecipeList()) {
//                if (obj instanceof IRecipe) {
//                    IRecipe craft = (IRecipe) obj;
//                    if (craft.getRecipeOutput().isItemEqual(remove)) {
//                        recipe = craft;
//                        HighTechBenchCraftingManager.getInstance().getRecipeList().remove(obj);
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
//            HighTechBenchCraftingManager.getInstance().getRecipeList().add(recipe);
//            MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(recipe);
//        }
//
//        @Override
//        public String describe() {
//            return "Removing High-Tech Bench Recipe for " + remove.getDisplayName();
//        }
//
//        @Override
//        public String describeUndo() {
//            return "Un-removing High-Tech Bench Recipe for " + remove.getDisplayName();
//        }
//
//        @Override
//        public Object getOverrideKey() {
//            return null;
//        }
//
//    }
//}