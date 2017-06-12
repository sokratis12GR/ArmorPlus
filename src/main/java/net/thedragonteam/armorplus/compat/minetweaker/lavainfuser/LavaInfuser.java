//package net.thedragonteam.armorplus.compat.minetweaker.lavainfuser;

//@ZenClass("mods." + ArmorPlus.MODID + ".LavaInfuser")
//public class LavaInfuser {
//
//    @ZenMethod
//    public static void addRecipe(IItemStack output, IItemStack input, double xp) {
//        MineTweakerAPI.apply(new Add(new LavaInfuserRecipe(toStack(input), toStack(output), xp)));
//    }
//
//    @ZenMethod
//    public static void remove(IItemStack target) {
//        MineTweakerAPI.apply(new Remove(toStack(target)));
//    }
//
//    private static class Remove implements IUndoableAction {
//        private LavaInfuserRecipe recipe = null;
//        ItemStack remove;
//
//        public Remove(ItemStack rem) {
//            remove = rem;
//        }
//
//        @Override
//        public void apply() {
//            for (Object obj : LavaInfuserManager.getInstance().getRecipeList()) {
//                if (obj instanceof LavaInfuserRecipe) {
//                    LavaInfuserRecipe craft = (LavaInfuserRecipe) obj;
//                    if (craft.getRecipeOutput().isItemEqual(remove)) {
//                        recipe = craft;
//                        LavaInfuserManager.getInstance().getRecipeList().remove(obj);
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
//            LavaInfuserManager.getInstance().getRecipeList().add(recipe);
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
//
//    private static class Add implements IUndoableAction {
//        private LavaInfuserRecipe recipe;
//
//        public Add(LavaInfuserRecipe recipe) {
//            this.recipe = recipe;
//        }
//
//        @Override
//        public void apply() {
//            LavaInfuserManager.getInstance().addInfusing(recipe);
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
//            LavaInfuserManager.getInstance().removeRecipe(recipe);
//            MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(recipe);
//        }
//
//        @Override
//        public String describe() {
//            return "Adding lava infuser recipe for " + recipe.getRecipeOutput().getDisplayName();
//        }
//
//        @Override
//        public String describeUndo() {
//            return "Removing lava infuser recipe for " + recipe.getRecipeOutput().getDisplayName();
//        }
//
//        @Override
//        public Object getOverrideKey() {
//            return null;
//        }
//    }
//}
//