package com.sofodev.armorplus.registry.items.tools.properties.mace;

import com.sofodev.armorplus.registry.items.tools.properties.tool.APToolMaterial;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;

import java.util.Locale;

import static com.sofodev.armorplus.registry.items.tools.properties.mace.APMaceType.*;
import static com.sofodev.armorplus.registry.items.tools.properties.mace.DestructionShape.PLUS;
import static com.sofodev.armorplus.registry.items.tools.properties.mace.DestructionShape.SQUARE;
import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolMaterial.*;


public enum APMaceMaterial implements IAPMace {
    COAL_MACE(COAL_MAT, LIGHT, 5, 1),
    REDSTONE_MACE(REDSTONE_MAT, LIGHT, 5, 2),
    LAPIS_MACE(LAPIS_MAT, LIGHT, 5, 2),
    EMERALD_MACE(EMERALD_MAT, NORMAL, 10, 4, PLUS),
    OBSIDIAN_MACE(OBSIDIAN_MAT, NORMAL, 10, 4, PLUS),
    INFUSED_LAVA_MACE(INFUSED_LAVA_MAT, NORMAL, 12, 6, PLUS),
    GUARDIAN_MACE(GUARDIAN_MAT, HEAVY, 15, 9, PLUS),
    SUPER_STAR_MACE(SUPER_STAR_MAT, HEAVY, 15, 9, PLUS),
    ENDER_DRAGON_MACE(ENDER_DRAGON_MAT, HEAVY, 17, 10, SQUARE),
    SLAYER_MACE(SLAYER_MAT, HEAVY, 20, 12, SQUARE),
    ;

    private final Tier material;
    private final Rarity rarity;
    private final APMaceType type;
    private final int cooldown;
    private final int range;
    private final boolean hasAOEDestruction;
    private final DestructionShape shape;

    APMaceMaterial(APToolMaterial material, APMaceType type, int cooldown, int range) {
        this(material, type, cooldown, range, false, SQUARE);
    }

    APMaceMaterial(APToolMaterial material, APMaceType type, int cooldown, int range, DestructionShape shape) {
        this(material, type, cooldown, range, true, shape);
    }

    APMaceMaterial(APToolMaterial material, APMaceType type, int cooldown, int range, boolean hasAOE, DestructionShape shape) {
        this.material = material.get();
        this.rarity = material.getRarity();
        this.type = type;
        this.cooldown = cooldown;
        this.range = range;
        this.hasAOEDestruction = hasAOE;
        this.shape = shape;
    }

    @Override
    public Tier get() {
        return this.material;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    public APMaceType getType() {
        return type;
    }

    @Override
    public String getName() {
        return this.name().toLowerCase(Locale.ENGLISH).replace("_mace", "");
    }

    @Override
    public int cooldown() {
        return this.cooldown;
    }

    @Override
    public int destructionRange() {
        return this.range;
    }

    @Override
    public boolean hasAOEDestruction() {
        return this.hasAOEDestruction;
    }

    @Override
    public DestructionShape getShape() {
        return this.shape;
    }

    @Override
    public String toString() {
        return "APMaceMaterial{" +
                "material=" + material +
                ", rarity=" + rarity +
                ", type=" + type +
                ", cooldown=" + cooldown +
                ", range=" + range +
                ", hasAOEDestruction=" + hasAOEDestruction +
                ", shape=" + shape +
                '}';
    }
}
