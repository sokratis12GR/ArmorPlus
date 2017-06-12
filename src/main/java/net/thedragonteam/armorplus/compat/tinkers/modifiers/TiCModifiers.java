/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

//package net.thedragonteam.armorplus.compat.tinkers.modifiers;

//public class TiCModifiers {
//
//    public static Modifier theUltimateMaterial = new TiCExtraModifier();
//    public static Modifier infusedLavaCrystalModifier = new ModFireStorm();
//
//    public static final ItemStack infusedLavaCrystal = getItemStack(ModItems.lavaCrystal, 1);
//
//    public TiCModifiers() {
//        theUltimateMaterial.addItem(APItems.theUltimateMaterial, 3, 1);
//        infusedLavaCrystalModifier.addRecipeMatch(new RecipeMatch.ItemCombination(2, infusedLavaCrystal));
//    }
//
//    @SideOnly(Side.CLIENT)
//    public static void initRender() {
//        ModelRegisterUtil.registerModifierModel(infusedLavaCrystalModifier, Utils.INSTANCE.setResourceLocation("models/item/modifiers/" + infusedLavaCrystalModifier.getIdentifier()));
//    }
//}