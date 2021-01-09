package com.sofodev.armorplus.registry.items.tools.properties.tool;

import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Rarity;

import java.util.Arrays;

import static com.sofodev.armorplus.registry.items.APRarity.*;
import static com.sofodev.armorplus.registry.items.extras.DeBuff.*;
import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolProperties.*;

public enum APToolMaterial implements IAPTool {
    COAL_MAT(COAL.getRarity(), COAL_PROP, new BuffInstance(BLINDNESS, 0, 10)),
    REDSTONE_MAT(REDSTONE.getRarity(), REDSTONE_PROP, new BuffInstance(MINING_FATIGUE, 1, 10)),
    LAPIS_MAT(LAPIS.getRarity(), LAPIS_PROP, new BuffInstance(NAUSEA, 0, 10)),
    EMERALD_MAT(EMERALD.getRarity(), EMERALD_PROP, new BuffInstance(SLOWNESS, 0, 20)),
    OBSIDIAN_MAT(OBSIDIAN.getRarity(), OBSIDIAN_PROP, new BuffInstance(WEAKNESS, 2, 20)),
    INFUSED_LAVA_MAT(INFUSED_LAVA.getRarity(), INFUSED_LAVA_PROP, new BuffInstance(IGNITE)),
    GUARDIAN_MAT(GUARDIAN.getRarity(), GUARDIAN_PROP, new BuffInstance(WEAKNESS, 1, 4), new BuffInstance(NAUSEA, 1, 20)),
    SUPER_STAR_MAT(SUPER_STAR.getRarity(), SUPER_STAR_PROP, new BuffInstance(WITHER, 1, 4), new BuffInstance(GLOWING, 0, 20)),
    ENDER_DRAGON_MAT(ENDER_DRAGON.getRarity(), ENDER_DRAGON_PROP, new BuffInstance(WITHER, 3, 4), new BuffInstance(SLOWNESS, 1, 20)),
    SLAYER_MAT(SLAYER.getRarity(), SLAYER_PROP),
    ;
    //TODO: Implement Enhanced Vanilla Tools
    //TODO: Implement Soul Gathering System

    private final IItemTier properties;
    private final BuffInstance[] buffs;
    private final Rarity rarity;

    APToolMaterial(Rarity rarity, IItemTier properties, BuffInstance... buffs) {
        this.rarity = rarity;
        this.properties = properties;
        this.buffs = buffs;
    }

    @Override
    public IItemTier get() {
        return properties;
    }

    @Override
    public BuffInstance[] getBuffInstances() {
        return buffs;
    }

    @Override
    public String getName() {
        return this.name().toLowerCase().replace("_mat", "");
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return "APToolMaterial{" +
                "properties=" + properties +
                ", buffs=" + Arrays.toString(buffs) +
                ", rarity=" + rarity +
                '}';
    }
}