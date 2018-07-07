package net.thedragonteam.armorplus.compat.crafttweaker.actions;

import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapedOreRecipe;

import static java.lang.String.format;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toStack;

public class AddShaped implements IAction {
    private BaseCraftingManager manager;
    private String name;
    private IRecipe recipe;

    public AddShaped(BaseCraftingManager manager, int size, IItemStack output, Object[] ingredients) {
        this.manager = manager;
        this.name = manager.getName();
        this.recipe = new BaseShapedOreRecipe(size, toStack(output), ingredients);
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
