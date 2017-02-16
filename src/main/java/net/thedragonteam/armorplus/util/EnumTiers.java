/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.util.EnumTiers.SubTypeMaterials.*;

public enum EnumTiers implements IStringSerializable {
    TIER_0(0, "tier_0", 0, new SubTypeMaterials[]{NONE}), //None
    TIER_1(1, "tier_1", 8, new SubTypeMaterials[]{COAL, LAPIS, REDSTONE}), //Workbench
    TIER_2(2, "tier_2", 16, new SubTypeMaterials[]{EMERALD, OBSIDIAN, LAVA}), //High-Tech Bench
    TIER_3(3, "tier_3", 30, new SubTypeMaterials[]{GUARDIAN, SUPER_STAR, ENDER_DRAGON}), //Ulti-Tech Bench
    TIER_4(4, "tier_4", 60, new SubTypeMaterials[]{ULTIMATE}), //Champion Bench
    ;

    private final int tierSlot;

    private final String name;

    private final int enchantability;

    private final SubTypeMaterials[] subTypes;

    EnumTiers(int tierSlotIn, String nameIn, int enchantability, SubTypeMaterials[] subTypesIn) {
        this.tierSlot = tierSlotIn;
        this.name = nameIn;
        this.subTypes = subTypesIn;
        this.enchantability = enchantability;
    }

    public int getTierSlot() {
        return tierSlot;
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    public SubTypeMaterials[] getSubTypes() {
        return subTypes;
    }

    public EnumTiers getTiers() {
        return this;
    }

    public int getEnchantability() {
        return enchantability;
    }

    enum SubTypeMaterials {
        NONE,
        COAL,
        LAPIS,
        REDSTONE,
        EMERALD,
        OBSIDIAN,
        LAVA,
        GUARDIAN,
        SUPER_STAR,
        ENDER_DRAGON,
        ULTIMATE,;
    }
}
