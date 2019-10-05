/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.armors;

import net.minecraft.item.ItemArmor.ArmorMaterial;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.common.util.EnumHelperUtil.addArmorMaterial;
import static com.sofodev.armorplus.common.util.EnumHelperUtil.addVanillaArmorMaterial;
import static net.minecraft.init.SoundEvents.*;

public class ArmorMaterials {

    public ArmorMaterials() {
    }

    //Tier 1 Armors
    public static final ArmorMaterial COAL_ARMOR = addArmorMaterial("coal_armor", 2, coal.armor, Tier.TIER_1);
    public static final ArmorMaterial LAPIS_ARMOR = addArmorMaterial("lapis_armor", 11, lapis.armor, Tier.TIER_1);
    public static final ArmorMaterial REDSTONE_ARMOR = addArmorMaterial("redstone_armor", 11, redstone.armor, Tier.TIER_1);
    public static final ArmorMaterial CHICKEN_ARMOR = addArmorMaterial("chicken_armor", 1, chicken, Tier.TIER_1);
    public static final ArmorMaterial SLIME_ARMOR = addArmorMaterial("slime_armor", 1, slime, Tier.TIER_1);
    //Tier 2 Armors
    public static final ArmorMaterial EMERALD_ARMOR = addArmorMaterial("emerald_armor", 35, emerald.armor, Tier.TIER_2);
    public static final ArmorMaterial OBSIDIAN_ARMOR = addArmorMaterial("obsidian_armor", 40, obsidian.armor, Tier.TIER_2);
    public static final ArmorMaterial LAVA_ARMOR = addArmorMaterial("lava_armor", 45, lava.armor, Tier.TIER_2);
    public static final ArmorMaterial ARDITE_ARMOR = addArmorMaterial("ardite_armor", 55, ardite, Tier.TIER_2);
    public static final ArmorMaterial COBALT_ARMOR = addArmorMaterial("cobalt_armor", 44, cobalt, Tier.TIER_2);
    public static final ArmorMaterial KNIGHT_SLIME_ARMOR = addArmorMaterial("knight_slime_armor", 33, knight_slime, Tier.TIER_2);
    public static final ArmorMaterial MANYULLYN_ARMOR = addArmorMaterial("manyullyn_armor", 66, manyullyn, Tier.TIER_2);
    public static final ArmorMaterial PIG_IRON_ARMOR = addArmorMaterial("pig_iron_armor", 33, pig_iron, Tier.TIER_2);
    //Tier 3 Armors
    public static final ArmorMaterial GUARDIAN_ARMOR = addArmorMaterial("guardian_armor", 50, guardian.armor, Tier.TIER_3);
    public static final ArmorMaterial SUPER_STAR_ARMOR = addArmorMaterial("super_star_armor", 50, super_star.armor, Tier.TIER_3);
    public static final ArmorMaterial ENDER_DRAGON_ARMOR = addArmorMaterial("ender_dragon_armor", 60, ender_dragon.armor, Tier.TIER_3);
    //Ultimate Armor
    public static final ArmorMaterial THE_ULTIMATE_ARMOR = addArmorMaterial("THE_ULTIMATE_ARMOR", "the_ultimate_armor", 160,
        ultimate.armor.protectionPoints, 1, ITEM_ARMOR_EQUIP_DIAMOND, ultimate.armor.toughnessPoints);
    //Enhanced Vanilla Armors
    public static final ArmorMaterial ENHANCED_CHAIN_ARMOR = addVanillaArmorMaterial("chainmail", 20, chain, 15, ITEM_ARMOR_EQUIP_CHAIN);
    public static final ArmorMaterial ENHANCED_IRON_ARMOR = addVanillaArmorMaterial("iron", 20, iron, 15, ITEM_ARMOR_EQUIP_IRON);
    public static final ArmorMaterial ENHANCED_GOLD_ARMOR = addVanillaArmorMaterial("gold", 10, gold, 30, ITEM_ARMOR_EQUIP_GOLD);
    public static final ArmorMaterial ENHANCED_DIAMOND_ARMOR = addVanillaArmorMaterial("diamond", 35, diamond, 20, ITEM_ARMOR_EQUIP_DIAMOND);
}
