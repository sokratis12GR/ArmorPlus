/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.tinkers.modifiers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.registry.APItems;
import net.thedragonteam.armorplus.registry.ModItems;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;

import static net.thedragonteam.armorplus.util.Utils.setRL;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class TiCModifiers {

    public static Modifier theUltimateMaterial = new TiCExtraModifier();
    public static Modifier infusedLavaCrystalModifier = new ModFireStorm();

    public static final ItemStack infusedLavaCrystal = getItemStack(ModItems.lavaCrystal, 1);

    public TiCModifiers() {
        theUltimateMaterial.addItem(APItems.theUltimateMaterial, 3, 1);
        infusedLavaCrystalModifier.addRecipeMatch(new RecipeMatch.ItemCombination(2, infusedLavaCrystal));
    }

    @SideOnly(Side.CLIENT)
    public static void initRender() {
        ModelRegisterUtil.registerModifierModel(infusedLavaCrystalModifier, setRL("models/item/modifiers/" + infusedLavaCrystalModifier.getIdentifier()))
        ;
    }
}