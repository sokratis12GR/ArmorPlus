package net.thedragonteam.armorplus.api.crafting;

/**
 * Used to mark a recipe that shape matters so that the recipe
 * book and auto crafting picks the correct shape.
 * Note: These methods can't be named 'getHeight' or 'getWidth' due to obfusication issues.
 */
public interface IShapedRecipe extends IRecipe
{
    int getRecipeWidth();
    int getRecipeHeight();
}