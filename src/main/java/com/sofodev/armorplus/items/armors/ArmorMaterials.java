/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.armors;

import com.sofodev.armorplus.util.EnumHelperUtil;
import net.minecraft.item.ItemArmor;

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.*;
import static net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;

public class ArmorMaterials {

    public ArmorMaterials() {
    }

    public static final ItemArmor.ArmorMaterial COAL_ARMOR = EnumHelperUtil.addArmorMaterial("COAL", "coal_armor", 2,
        coal.armor.protectionPoints, coal.armor.toughnessPoints, Tier.TIER_1);
    public static final ItemArmor.ArmorMaterial LAPIS_ARMOR = EnumHelperUtil.addArmorMaterial("LAPIS", "lapis_armor", 11,
        lapis.armor.protectionPoints, lapis.armor.toughnessPoints, Tier.TIER_1);
    public static final ItemArmor.ArmorMaterial REDSTONE_ARMOR = EnumHelperUtil.addArmorMaterial("REDSTONE", "redstone_armor", 11,
        redstone.armor.protectionPoints, redstone.armor.toughnessPoints, Tier.TIER_1);
    public static final ItemArmor.ArmorMaterial EMERALD_ARMOR = EnumHelperUtil.addArmorMaterial("EMERALD", "emerald_armor", 35,
        emerald.armor.protectionPoints, emerald.armor.toughnessPoints, Tier.TIER_2);
    public static final ItemArmor.ArmorMaterial OBSIDIAN_ARMOR = EnumHelperUtil.addArmorMaterial("OBSIDIAN", "obsidian_armor", 40,
        obsidian.armor.protectionPoints, obsidian.armor.toughnessPoints, Tier.TIER_2);
    public static final ItemArmor.ArmorMaterial LAVA_ARMOR = EnumHelperUtil.addArmorMaterial("INFUSED_LAVA", "lava_armor", 45,
        lava.armor.protectionPoints, lava.armor.toughnessPoints, Tier.TIER_2);
    public static final ItemArmor.ArmorMaterial GUARDIAN_ARMOR = EnumHelperUtil.addArmorMaterial("GUARDIAN", "guardian_armor", 50,
        guardian.armor.protectionPoints, guardian.armor.toughnessPoints, Tier.TIER_3);
    public static final ItemArmor.ArmorMaterial SUPER_STAR_ARMOR = EnumHelperUtil.addArmorMaterial("SUPER_STAR", "super_star_armor", 50,
        super_star.armor.protectionPoints, super_star.armor.toughnessPoints, Tier.TIER_3);
    public static final ItemArmor.ArmorMaterial ENDER_DRAGON_ARMOR = EnumHelperUtil.addArmorMaterial("ENDER_DRAGON", "ender_dragon_armor", 60,
        ender_dragon.armor.protectionPoints, ender_dragon.armor.toughnessPoints, Tier.TIER_3);
    public static final ItemArmor.ArmorMaterial CHICKEN_ARMOR = EnumHelperUtil.addArmorMaterial("CHICKEN", "chicken_armor", 1,
        chicken.protectionPoints, chicken.toughnessPoints, Tier.TIER_1);
    public static final ItemArmor.ArmorMaterial SLIME_ARMOR = EnumHelperUtil.addArmorMaterial("SLIME", "slime_armor", 1,
        slime.protectionPoints, slime.toughnessPoints, Tier.TIER_1);
    public static final ItemArmor.ArmorMaterial ARDITE_ARMOR = EnumHelperUtil.addArmorMaterial("ARDITE", "ardite_armor", 55,
        ardite.protectionPoints, ardite.toughnessPoints, Tier.TIER_2);
    public static final ItemArmor.ArmorMaterial COBALT_ARMOR = EnumHelperUtil.addArmorMaterial("COBALT", "cobalt_armor", 44,
        cobalt.protectionPoints, cobalt.toughnessPoints, Tier.TIER_2);
    public static final ItemArmor.ArmorMaterial KNIGHT_SLIME_ARMOR = EnumHelperUtil.addArmorMaterial("KNIGHT_SLIME", "knight_slime_armor", 33,
        knight_slime.protectionPoints, knight_slime.toughnessPoints, Tier.TIER_2);
    public static final ItemArmor.ArmorMaterial MANYULLYN_ARMOR = EnumHelperUtil.addArmorMaterial("MANYULLYN", "manyullyn_armor", 66,
        manyullyn.protectionPoints, manyullyn.toughnessPoints, Tier.TIER_2);
    public static final ItemArmor.ArmorMaterial PIG_IRON_ARMOR = EnumHelperUtil.addArmorMaterial("PIG_IRON", "pig_iron_armor", 33,
        pig_iron.protectionPoints, pig_iron.toughnessPoints, Tier.TIER_2);
    public static final ItemArmor.ArmorMaterial THE_ULTIMATE_ARMOR = EnumHelperUtil.addArmorMaterial("THE_ULTIMATE_ARMOR", "the_ultimate_armor", 160,
        ultimate.armor.protectionPoints, 1, ITEM_ARMOR_EQUIP_DIAMOND, ultimate.armor.toughnessPoints);
}
