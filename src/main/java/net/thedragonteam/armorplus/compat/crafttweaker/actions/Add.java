package net.thedragonteam.armorplus.compat.crafttweaker.actions;

import crafttweaker.IAction;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;

import static java.lang.String.format;

public class Add implements IAction {
    private BaseCraftingManager manager;
    private String name;
    private IRecipe recipe;

    public Add(BaseCraftingManager manager, IRecipe add) {
        this.manager = manager;
        this.name = manager.getName();
        this.recipe = add;
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
