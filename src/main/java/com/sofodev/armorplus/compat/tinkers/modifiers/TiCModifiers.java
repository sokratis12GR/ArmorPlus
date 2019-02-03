/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.tinkers.modifiers;

import com.sofodev.armorplus.registry.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.tools.AbstractToolPulse;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class TiCModifiers extends AbstractToolPulse {

    private static final ItemStack infusedLavaCrystal = getItemStack(ModItems.itemLavaCrystal, 1);
    public static Modifier theUltimateMaterial;
    public static Modifier infusedLavaCrystalModifier;

    @Override
    public void init(FMLInitializationEvent event) {
        theUltimateMaterial = registerModifier(new ModExtraModifier());
        infusedLavaCrystalModifier = registerModifier(new ModFireStorm());
        theUltimateMaterial.addRecipeMatch(new RecipeMatch.ItemCombination(3, getItemStack(ModItems.materials, 4)));
        infusedLavaCrystalModifier.addRecipeMatch(new RecipeMatch.ItemCombination(2, infusedLavaCrystal));
    }
}