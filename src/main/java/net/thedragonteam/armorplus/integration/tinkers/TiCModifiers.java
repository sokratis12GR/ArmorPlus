/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.integration.tinkers;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.registry.ModItems;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.modifiers.Modifier;

public class TiCModifiers {

    public static Modifier lavaCrystalModifier;
    public static Modifier chargedLavaCrystalModifier;

    ItemStack chargedLavaCrystal = new ItemStack(ModItems.lavaCrystal, 1, 1);
    ItemStack obsidian = new ItemStack(Items.BRICK, 1);

    public TiCModifiers() {

        lavaCrystalModifier = new TiCExtraModifier("ultimatemod");
        lavaCrystalModifier.addItem(ModItems.theUltimateMaterial);
        chargedLavaCrystalModifier = new ModFireStorm();
        chargedLavaCrystalModifier.addRecipeMatch(new RecipeMatch.ItemCombination(1, chargedLavaCrystal, obsidian));
    }

}