package net.thedragonteam.armorplus.compat.crafttweaker.actions;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapelessOreRecipe;

import static java.lang.String.format;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toObjects;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toStack;

public class AddShapeless implements IAction {
    private BaseCraftingManager manager;
    private String name;
    private IRecipe recipe;

    public AddShapeless(BaseCraftingManager manager, IItemStack output, IIngredient[] ingredients) {
        this.manager = manager;
        this.name = manager.getName();
        this.recipe = new BaseShapelessOreRecipe(toStack(output), toObjects(ingredients));
    }

    @Override
    public void apply() {
        manager.getRecipeList().add(recipe);
    }

    @Override
    public String describe() {
        return format("Adding %s recipe for %s", recipe.getRecipeOutput().getDisplayName(), name);
    }
}
