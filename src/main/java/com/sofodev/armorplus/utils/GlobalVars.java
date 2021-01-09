package com.sofodev.armorplus.utils;

import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.extensions.IForgeBlock;

import static com.sofodev.armorplus.utils.Utils.getAPBlock;
import static com.sofodev.armorplus.utils.Utils.getAPItem;


/**
 * A collection of initialized items, obtained via the Forge Registry for ease-of-use.
 */
public class GlobalVars {

    //General Items & Materials
    public static final IItemProvider LAVA_CRYSTAL = getAPItem("lava_crystal");
    public static final IItemProvider INFUSED_LAVA_CRYSTAL = getAPItem("infused_lava_crystal");
    public static final IItemProvider FROST_CRYSTAL = getAPItem("frost_crystal");
    public static final IItemProvider INFUSED_FROST_CRYSTAL = getAPItem("infused_frost_crystal");
    public static final IItemProvider CHAINMAIL = getAPItem("chainmail");
    public static final IItemProvider GUARDIAN_SCALE = getAPItem("guardian_scale");
    public static final IItemProvider WITHER_BONE = getAPItem("wither_bone");
    public static final IItemProvider ENDER_DRAGON_SCALE = getAPItem("ender_dragon_scale");
    public static final IItemProvider THE_ULTIMATE_MATERIAL = getAPItem("the_ultimate_material");

    //Blocks
    public static final IForgeBlock ORE_LAVA_CRYSTAL = getAPBlock("ore_lava_crystal");
    public static final IForgeBlock ORE_FROST_CRYSTAL = getAPBlock("ore_frost_crystal");

    //Equipment
    //SS
    public static final IItemProvider SUPER_STAR_HEAD = getAPItem("super_star_leggings");
    public static final IItemProvider SUPER_STAR_CHEST = getAPItem("super_star_chestplate");
    public static final IItemProvider SUPER_STAR_LEGS = getAPItem("super_star_leggings");
    public static final IItemProvider SUPER_STAR_FEET = getAPItem("super_star_boots");
    //G
    public static final IItemProvider GUARDIAN_HEAD = getAPItem("guardian_leggings");
    public static final IItemProvider GUARDIAN_CHEST = getAPItem("guardian_chestplate");
    public static final IItemProvider GUARDIAN_LEGS = getAPItem("guardian_leggings");
    public static final IItemProvider GUARDIAN_FEET = getAPItem("guardian_boots");
    //ED
    public static final IItemProvider ENDER_DRAGON_HEAD = getAPItem("ender_dragon_leggings");
    public static final IItemProvider ENDER_DRAGON_CHEST = getAPItem("ender_dragon_chestplate");
    public static final IItemProvider ENDER_DRAGON_LEGS = getAPItem("ender_dragon_leggings");
    public static final IItemProvider ENDER_DRAGON_FEET = getAPItem("ender_dragon_boots");

    public static void registerAfterEverything() {

    }
}
