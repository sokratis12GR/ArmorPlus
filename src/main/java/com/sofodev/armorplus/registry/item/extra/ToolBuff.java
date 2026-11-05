package com.sofodev.armorplus.registry.item.extra;

import com.sofodev.armorplus.registry.item.tool.AutoSmeltLootModifier;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

/**
 * ArmorPlus abilities of tool runes, working while the tool is held in the main hand (see
 * {@link ToolEffects}). Like {@link DeBuff} for weapons, these are kept apart from
 * the armor {@link Buff}s: they only exist for tools and can't be used as set effects. Mob effects of tool runes
 * ("minecraft:haste|2") resolve like armor effects.
 */
public enum ToolBuff implements IBuff {
    NONE,
    /** Smelts the drops of mined blocks, see {@link AutoSmeltLootModifier}. */
    AUTO_SMELT;

    @Override
    public boolean isEffect() {
        return false;
    }

    @Override
    public Holder<MobEffect> getEffect() {
        return null;
    }
}
