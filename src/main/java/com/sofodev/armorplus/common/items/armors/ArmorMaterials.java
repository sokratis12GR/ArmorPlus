/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.items.armors;

import net.minecraft.item.ItemArmor.ArmorMaterial;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.common.util.EnumHelperUtil.addArmorMaterial;
import static com.sofodev.armorplus.common.util.EnumHelperUtil.addVanillaArmorMaterial;
import static net.minecraft.init.SoundEvents.*;

public class ArmorMaterials {

    public ArmorMaterials() {
    }

    //Tier 1 Armors
    public static final ArmorMaterial COAL_ARMOR = addArmorMaterial("COAL", "coal_armor", 2,
        coal.armor.protectionPoints, coal.armor.toughnessPoints, Tier.TIER_1);
    public static final ArmorMaterial LAPIS_ARMOR = addArmorMaterial("LAPIS", "lapis_armor", 11,
        lapis.armor.protectionPoints, lapis.armor.toughnessPoints, Tier.TIER_1);
    public static final ArmorMaterial REDSTONE_ARMOR = addArmorMaterial("REDSTONE", "redstone_armor", 11,
        redstone.armor.protectionPoints, redstone.armor.toughnessPoints, Tier.TIER_1);
    public static final ArmorMaterial CHICKEN_ARMOR = addArmorMaterial("CHICKEN", "chicken_armor", 1,
        chicken.protectionPoints, chicken.toughnessPoints, Tier.TIER_1);
    public static final ArmorMaterial SLIME_ARMOR = addArmorMaterial("SLIME", "slime_armor", 1,
        slime.protectionPoints, slime.toughnessPoints, Tier.TIER_1);
    //Tier 2 Armors
    public static final ArmorMaterial EMERALD_ARMOR = addArmorMaterial("EMERALD", "emerald_armor", 35,
        emerald.armor.protectionPoints, emerald.armor.toughnessPoints, Tier.TIER_2);
    public static final ArmorMaterial OBSIDIAN_ARMOR = addArmorMaterial("OBSIDIAN", "obsidian_armor", 40,
        obsidian.armor.protectionPoints, obsidian.armor.toughnessPoints, Tier.TIER_2);
    public static final ArmorMaterial LAVA_ARMOR = addArmorMaterial("INFUSED_LAVA", "lava_armor", 45,
        lava.armor.protectionPoints, lava.armor.toughnessPoints, Tier.TIER_2);
    public static final ArmorMaterial ARDITE_ARMOR = addArmorMaterial("ARDITE", "ardite_armor", 55,
        ardite.protectionPoints, ardite.toughnessPoints, Tier.TIER_2);
    public static final ArmorMaterial COBALT_ARMOR = addArmorMaterial("COBALT", "cobalt_armor", 44,
        cobalt.protectionPoints, cobalt.toughnessPoints, Tier.TIER_2);
    public static final ArmorMaterial KNIGHT_SLIME_ARMOR = addArmorMaterial("KNIGHT_SLIME", "knight_slime_armor", 33,
        knight_slime.protectionPoints, knight_slime.toughnessPoints, Tier.TIER_2);
    public static final ArmorMaterial MANYULLYN_ARMOR = addArmorMaterial("MANYULLYN", "manyullyn_armor", 66,
        manyullyn.protectionPoints, manyullyn.toughnessPoints, Tier.TIER_2);
    public static final ArmorMaterial PIG_IRON_ARMOR = addArmorMaterial("PIG_IRON", "pig_iron_armor", 33,
        pig_iron.protectionPoints, pig_iron.toughnessPoints, Tier.TIER_2);
    //Tier 3 Armors
    public static final ArmorMaterial GUARDIAN_ARMOR = addArmorMaterial("GUARDIAN", "guardian_armor", 50,
        guardian.armor.protectionPoints, guardian.armor.toughnessPoints, Tier.TIER_3);
    public static final ArmorMaterial SUPER_STAR_ARMOR = addArmorMaterial("SUPER_STAR", "super_star_armor", 50,
        super_star.armor.protectionPoints, super_star.armor.toughnessPoints, Tier.TIER_3);
    public static final ArmorMaterial ENDER_DRAGON_ARMOR = addArmorMaterial("ENDER_DRAGON", "ender_dragon_armor", 60,
        ender_dragon.armor.protectionPoints, ender_dragon.armor.toughnessPoints, Tier.TIER_3);
    //Ultimate Armor
    public static final ArmorMaterial THE_ULTIMATE_ARMOR = addArmorMaterial("THE_ULTIMATE_ARMOR", "the_ultimate_armor", 160,
        ultimate.armor.protectionPoints, 1, ITEM_ARMOR_EQUIP_DIAMOND, ultimate.armor.toughnessPoints);
    //Enhanced Vanilla Armors
    public static final ArmorMaterial ENHANCED_CHAIN_ARMOR = addVanillaArmorMaterial("chainmail", 20,
        chain.protectionPoints, 15, ITEM_ARMOR_EQUIP_CHAIN, chain.toughnessPoints);
    public static final ArmorMaterial ENHANCED_IRON_ARMOR = addVanillaArmorMaterial("iron", 20,
        iron.protectionPoints, 15, ITEM_ARMOR_EQUIP_IRON, iron.toughnessPoints);
    public static final ArmorMaterial ENHANCED_GOLD_ARMOR = addVanillaArmorMaterial("gold", 10,
        gold.protectionPoints, 30, ITEM_ARMOR_EQUIP_GOLD, gold.toughnessPoints);
    public static final ArmorMaterial ENHANCED_DIAMOND_ARMOR = addVanillaArmorMaterial("diamond", 35,
        diamond.protectionPoints, 20, ITEM_ARMOR_EQUIP_DIAMOND, diamond.toughnessPoints);
}
