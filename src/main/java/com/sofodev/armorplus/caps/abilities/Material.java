/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.caps.abilities;

import net.minecraft.item.ItemArmor.ArmorMaterial;

import static com.sofodev.armorplus.items.armors.ArmorMaterials.*;
import static net.minecraft.item.ItemArmor.ArmorMaterial.LEATHER;

public enum Material {
    NONE(0, LEATHER),
    COAL(1, COAL_ARMOR),
    LAPIS(1, LAPIS_ARMOR),
    REDSTONE(1, REDSTONE_ARMOR),
    EMERALD(2, EMERALD_ARMOR),
    OBSIDIAN(2, OBSIDIAN_ARMOR),
    INFUSED_LAVA(2, LAVA_ARMOR),
    CHICKEN(1, CHICKEN_ARMOR),
    SLIME(1, SLIME_ARMOR),
    GUARDIAN(3, GUARDIAN_ARMOR),
    SUPER_STAR(3, SUPER_STAR_ARMOR),
    ENDER_DRAGON(3, ENDER_DRAGON_ARMOR),
    ARDITE(2, ARDITE_ARMOR),
    COBALT(2, COBALT_ARMOR),
    KNIGHT_SLIME(2, KNIGHT_SLIME_ARMOR),
    PIG_IRON(2, PIG_IRON_ARMOR),
    MANYULLYN(3, MANYULLYN_ARMOR),
    ULTIMATE(4, THE_ULTIMATE_ARMOR),
    ;

    private final byte limit;
    private final ArmorMaterial armorMaterial;

    Material(int limit, ArmorMaterial armorMaterial) {
        this.limit = (byte) limit;
        this.armorMaterial = armorMaterial;
    }

    public byte getLimit() {
        return limit;
    }

    public ArmorMaterial getArmorMaterial() {
        return armorMaterial;
    }
}