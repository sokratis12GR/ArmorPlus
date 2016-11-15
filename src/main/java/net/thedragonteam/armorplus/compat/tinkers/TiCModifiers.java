/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.tinkers;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModItems;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;

public class TiCModifiers {

    public static Modifier theUltimateMaterial;
    public static Modifier chargedLavaCrystalModifier;

    public ItemStack chargedLavaCrystal = new ItemStack(ModItems.lavaCrystal, 1, 1);
    public ItemStack brick = new ItemStack(Items.BRICK, 1);

    public TiCModifiers() {
        theUltimateMaterial = new TiCExtraModifier("ultimatemod");
        theUltimateMaterial.addItem(ModItems.theUltimateMaterial);
        chargedLavaCrystalModifier = new ModFireStorm();
        chargedLavaCrystalModifier.addRecipeMatch(new RecipeMatch.ItemCombination(1, chargedLavaCrystal, brick));
    }

    @SideOnly(Side.CLIENT)
    public static void initRender() {
        ModelRegisterUtil.registerModifierModel(chargedLavaCrystalModifier, new ResourceLocation(ArmorPlus.MODID, "models/item/modifiers/firestorm"));
    }
}