/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.tinkers.modifiers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.thedragonteam.armorplus.registry.APItems;
import net.thedragonteam.armorplus.registry.ModItems;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.tools.AbstractToolPulse;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class TiCModifiers extends AbstractToolPulse {

    private static final ItemStack infusedLavaCrystal = getItemStack(ModItems.lavaCrystal, 1);
    public static Modifier theUltimateMaterial;
    public static Modifier infusedLavaCrystalModifier;

    public void init(FMLInitializationEvent event) {
        theUltimateMaterial = registerModifier(new ModExtraModifier());
        infusedLavaCrystalModifier = registerModifier(new ModFireStorm());
        theUltimateMaterial.addItem(APItems.theUltimateMaterial, 3, 1);
        infusedLavaCrystalModifier.addRecipeMatch(new RecipeMatch.ItemCombination(2, infusedLavaCrystal));
    }
}