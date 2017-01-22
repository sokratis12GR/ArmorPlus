/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.util.IStringSerializable;

import static net.thedragonteam.armorplus.util.EnumTiers.SubTypeMaterials.*;
import static net.thedragonteam.armorplus.util.EnumTiers.Tier.*;

public enum EnumTiers implements IStringSerializable {
    TIER_0(0, "tier_0", ZERO, new SubTypeMaterials[]{NONE}), //None
    TIER_1(1, "tier_1", ONE, new SubTypeMaterials[]{COAL, LAPIS, REDSTONE}), //Workbench
    TIER_2(2, "tier_2", TWO, new SubTypeMaterials[]{EMERALD, OBSIDIAN, LAVA}), //High-Tech Bench
    TIER_3(3, "tier_3", THREE, new SubTypeMaterials[]{GUARDIAN, SUPER_STAR, ENDER_DRAGON}), //Ulti-Tech Bench
    TIER_4(4, "tier_4", FOUR, new SubTypeMaterials[]{ULTIMATE}), //Champion Bench
    ;

    private final int tierSlot;

    private final String name;

    private final Tier tier;

    private final SubTypeMaterials[] subTypes;

    EnumTiers(int tierSlotIn, String nameIn, Tier tierIn, SubTypeMaterials[] subTypesIn) {
        this.tierSlot = tierSlotIn;
        this.name = nameIn;
        this.tier = tierIn;
        this.subTypes = subTypesIn;
    }

    public int getTierSlot() {
        return tierSlot;
    }

    @Override
    public String getName() {
        return name;
    }

    public Tier getTier() {
        return tier;
    }

    public SubTypeMaterials[] getSubTypes() {
        return subTypes;
    }

    public EnumTiers getTiers() {
        return this;
    }

    enum Tier {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,;
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
