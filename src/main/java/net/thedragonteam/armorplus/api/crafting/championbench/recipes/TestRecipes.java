/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.championbench.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.championbench.ChampionBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

//TODO: Remove Test Recipe
public class TestRecipes {
    public void addRecipes(ChampionBenchCraftingManager manager) {
        manager.addRecipe(new ItemStack(ModItems.itemEnderDragonArrow, 1),
                "SOMERECIPE",
                "SOMERECIPE",
                "SOMERECIPE",
                "SOMERECIPE",
                "SOMERECIPE",
                "SOMERECIPE",
                "SOMERECIPE",
                "SOMERECIPE",
                "SOMERECIPE",
                "SOMERECIPE",
                'S', Items.GLOWSTONE_DUST,
                'O', Items.REDSTONE,
                'M', Items.FEATHER,
                'E', Items.TOTEM,
                'R', Items.STICK,
                'C', Items.CARROT,
                'I', Items.IRON_INGOT,
                'P', Items.DRAGON_BREATH
        );
    }
}
