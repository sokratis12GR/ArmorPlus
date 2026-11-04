package com.sofodev.armorplus.api.event;

import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

/**
 * Fired before the effect of a socketed rune is applied: armor runes ({@link Context#WORN}), tool runes
 * ({@link Context#HELD}, and {@link Context#HIT} for their on-hit debuffs) and weapon runes ({@link Context#HIT}).
 * Cancelable. Set effects fire {@link SetEffectApplyEvent} instead.
 */
public class RuneApplyEvent extends EffectApplyEvent {

    public RuneApplyEvent(LivingEntity entity, BuffInstance effect, Context context, ItemStack stack, @Nullable LivingEntity target) {
        super(entity, effect, context, stack, target);
    }
}
