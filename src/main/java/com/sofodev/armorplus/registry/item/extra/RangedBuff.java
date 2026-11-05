package com.sofodev.armorplus.registry.item.extra;

import com.sofodev.armorplus.events.RangedRuneEvents;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

/**
 * ArmorPlus abilities of ranged runes, stored on the arrows a bow or crossbow fires (see
 * {@link RangedRuneEvents}). Like {@link ToolBuff} for tools, these only exist for bows and
 * crossbows. Mob effects of ranged runes ("minecraft:poison|2") are applied to the mobs the arrows hit.
 */
public enum RangedBuff implements IBuff {
    NONE(false),
    PIERCING(true),
    VOLLEY(true),
    RICOCHET(true),
    LONG_SHOT(true),
    MARKED(true),
    QUICK_DRAW(true),
    AMMO_SAVER(true),
    RETRIEVAL(false),
    SEEKER(false),
    ENDER_SHOT(false);

    private final boolean leveled;

    RangedBuff(boolean leveled) {
        this.leveled = leveled;
    }

    @Override
    public boolean isEffect() {
        return false;
    }

    @Override
    public Holder<MobEffect> getEffect() {
        return null;
    }

    @Override
    public boolean isLeveled() {
        return leveled;
    }
}
