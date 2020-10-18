package com.sofodev.armorplus.utils;

import net.minecraft.util.IItemProvider;

import static com.sofodev.armorplus.utils.Utils.getAPItem;


/**
 * A collection of initialized items, obtained via the Forge Registry for ease-of-use.
 */
public class GlobalVars {

    public static final IItemProvider LAVA_CRYSTAL = getAPItem("lava_crystals");
    public static final IItemProvider INFUSED_LAVA_CRYSTAL = getAPItem("infused_lava_crystals");
    public static final IItemProvider FROST_CRYSTAL = getAPItem("frost_crystal");
    public static final IItemProvider INFUSED_FROST_CRYSTAL = getAPItem("infused_frost_crystal");
    public static final IItemProvider CHAINMAIL = getAPItem("chainmail");
    public static final IItemProvider GUARDIAN_SCALE = getAPItem("guardian_scale");
    public static final IItemProvider WITHER_BONE = getAPItem("wither_bone");
    public static final IItemProvider ENDER_DRAGON_SCALE = getAPItem("ender_dragon_scale");
    public static final IItemProvider THE_ULTIMATE_MATERIAL = getAPItem("the_ultimate_material");

}
