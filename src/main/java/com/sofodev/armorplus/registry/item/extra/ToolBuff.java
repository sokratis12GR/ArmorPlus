package com.sofodev.armorplus.registry.item.extra;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

/**
 * ArmorPlus abilities of tool runes, working while the tool is held in the main hand (see
 * {@link com.sofodev.armorplus.registry.item.tool.ToolEffects}). Like {@link DeBuff} for weapons, these are kept apart from
 * the armor {@link Buff}s: they only exist for tools and can't be used as set effects. Mob effects of tool runes
 * ("minecraft:haste|2") resolve like armor effects.
 */
public enum ToolBuff implements IBuff {
    NONE,
    /** Smelts mined blocks using the "smeltingRecipes" config, see AutoSmeltingEvents. */
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
