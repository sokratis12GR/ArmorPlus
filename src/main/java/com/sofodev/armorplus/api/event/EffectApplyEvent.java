package com.sofodev.armorplus.api.event;

import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import org.jspecify.annotations.Nullable;

/**
 * Fired on {@code NeoForge.EVENT_BUS} (server side only) before an ArmorPlus effect is applied. Canceling it skips this
 * one application; the effect is tried again on the next tick or hit. See {@link RuneApplyEvent} and
 * {@link SetEffectApplyEvent}.
 */
public abstract class EffectApplyEvent extends Event implements ICancellableEvent {

    public enum Context {
        /** Worn armor, once a second: {@link #getEntity()} is the player, the stack is empty. */
        WORN,
        /** The tool held in the main hand, once a second: {@link #getEntity()} is the player. */
        HELD,
        /** A melee or arrow hit: {@link #getEntity()} is the attacker or shooter (may be a mob), {@link #getTarget()} the entity hit. */
        HIT
    }

    private final LivingEntity entity;
    private final BuffInstance effect;
    private final Context context;
    private final ItemStack stack;
    private final @Nullable LivingEntity target;

    protected EffectApplyEvent(LivingEntity entity, BuffInstance effect, Context context, ItemStack stack, @Nullable LivingEntity target) {
        this.entity = entity;
        this.effect = effect;
        this.context = context;
        this.stack = stack;
        this.target = target;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public BuffInstance getEffect() {
        return effect;
    }

    public Context getContext() {
        return context;
    }

    /** {@return the held tool or weapon or the bow or crossbow that fired the arrow, empty for {@link Context#WORN}} */
    public ItemStack getStack() {
        return stack;
    }

    /** {@return the entity hit, only for {@link Context#HIT}} */
    public @Nullable LivingEntity getTarget() {
        return target;
    }
}
