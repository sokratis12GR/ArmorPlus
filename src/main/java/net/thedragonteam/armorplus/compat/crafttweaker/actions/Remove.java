package net.thedragonteam.armorplus.compat.crafttweaker.actions;

import crafttweaker.IAction;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;
import net.thedragonteam.armorplus.compat.crafttweaker.CTArmorPlusPlugin;

import java.util.List;

import static java.lang.String.format;

public class Remove implements IAction {
    private final String name;
    private ItemStack remove;
    private List<IRecipe> recipes;

    public Remove(BaseCraftingManager manager, ItemStack remove) {
        this.recipes = manager.getRecipeList();
        this.name = manager.getName();
        this.remove = remove;
    }

    @Override
    public void apply() {
        CTArmorPlusPlugin.removeRecipe(recipes, remove);
    }

    @Override
    public String describe() {
        return format("Removing %s recipe for %s", remove.getDisplayName(), name);
    }
}