/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.armors;

import com.sofodev.armorplus.api.caps.abilities.MaterialType;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;
import java.util.Arrays;

import static com.sofodev.armorplus.api.caps.abilities.MaterialType.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public enum Tier implements IStringSerializable {
    TIER_0("none", 0, NONE),
    TIER_1("beginner", 8, COAL, LAPIS, REDSTONE, CHICKEN, SLIME),
    TIER_2("amateur", 16, EMERALD, OBSIDIAN, INFUSED_LAVA, ARDITE, COBALT, KNIGHT_SLIME, PIG_IRON),
    TIER_3("master", 30, GUARDIAN, SUPER_STAR, ENDER_DRAGON, MANYULLYN),
    TIER_4("grandmaster", 60, ULTIMATE),
    ;

    private final String name;

    private final int enchantability;

    private final MaterialType[] materials;

    Tier(String name, int enchantability, MaterialType... materials) {
        this.name = name;
        this.materials = materials;
        this.enchantability = enchantability;
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    public MaterialType[] getMaterials() {
        return materials;
    }

    public Tier getTier() {
        return this;
    }

    public Tier getTier(String name) {
        return Arrays.stream(values()).filter(tier -> tier.getName().equals(name)).findFirst().orElse(this);
    }

    public Tier getTier(MaterialType material) {
        return Arrays.stream(Tier.values()).filter(tier -> Arrays.stream(tier.getMaterials()).anyMatch(mat -> mat == material)).findFirst().orElse(this);
    }

    public int getEnchantability() {
        return enchantability;
    }
}
