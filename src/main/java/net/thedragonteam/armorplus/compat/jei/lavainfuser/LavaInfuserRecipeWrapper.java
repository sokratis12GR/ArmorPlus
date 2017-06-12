//package net.thedragonteam.armorplus.compat.jei.lavainfuser;

//public class LavaInfuserRecipeWrapper extends BlankRecipeWrapper implements IRecipeWrapper {
//
//    private LavaInfuserRecipe recipe;
//
//    public LavaInfuserRecipeWrapper(LavaInfuserRecipe recipe) {
//        this.recipe = recipe;
//    }
//
//    @Override
//    public void getIngredients(IIngredients ingredients) {
//        ItemStack recipeOutput = recipe.getRecipeOutput();
//
//        ingredients.setInput(ItemStack.class, recipe.input);
//        ingredients.setOutput(ItemStack.class, recipeOutput);
//    }
//}