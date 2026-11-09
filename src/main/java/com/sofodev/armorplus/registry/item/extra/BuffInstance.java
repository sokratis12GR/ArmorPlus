package com.sofodev.armorplus.registry.item.extra;

import com.sofodev.armorplus.registry.ModPotions;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import com.sofodev.armorplus.registry.item.armor.SetRequirement;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

import static com.sofodev.armorplus.registry.item.extra.Buff.NONE;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static com.sofodev.armorplus.utils.Utils.convertToSeconds;
import static net.minecraft.ChatFormatting.DARK_AQUA;

public class BuffInstance {

    private final boolean instant;
    private final MobEffectInstance effect;
    private final int durationTicks;
    private IBuff buff;
    private int amplifier;
    private boolean enabled;
    /** The pieces of the set the effect needs ("@..." in the entry), null for the default, see {@link SetRequirement} */
    private @Nullable SetRequirement requirement;

    public BuffInstance(boolean enabled, IBuff buff, int amplifier) {
        this(enabled ? buff : NONE, amplifier, 20, true);
    }

    public BuffInstance(IBuff buff, int amplifier) {
        this(buff, amplifier, 20, true);
    }

    public BuffInstance(boolean enabled, IBuff buff, int amplifier, int duration) {
        this(enabled ? buff : NONE, amplifier, duration, false);
    }

    public BuffInstance(IBuff buff, int amplifier, int duration) {
        this(buff, amplifier, duration, false);
    }

    /**
     * Creates a new Buff Instance, with the specified amplifier (starting with 0, which will be set to 1) and duration (in seconds)...
     */
    public BuffInstance(IBuff buff, int amplifier, int duration, boolean instant) {
        this.buff = buff;
        this.amplifier = amplifier;
        this.instant = instant;
        this.durationTicks = convertToSeconds(duration);
        if (buff.isEffect() && buff.getEffect() != null) {
            this.effect = new MobEffectInstance(buff.getEffect(), durationTicks, amplifier, false, false);
        } else {
            this.effect = null;
        }
        this.enabled = true;
    }

    /**
     * This constructor is used so that special abilities can be used directly, without the need of them to be potion abilities.
     */
    public BuffInstance(boolean enabled, IBuff buff) {
        this.buff = enabled ? buff : NONE;
        this.amplifier = -1;
        this.instant = true;
        this.durationTicks = convertToSeconds(20);
        this.effect = new MobEffectInstance(ModPotions.EMPTY.getDelegate());
        this.enabled = true;
    }

    public BuffInstance(IBuff buff) {
        this.buff = buff;
        this.amplifier = -1;
        this.instant = true;
        this.durationTicks = convertToSeconds(20);
        this.effect = new MobEffectInstance(ModPotions.EMPTY.getDelegate());
        this.enabled = true;
    }

    private BuffInstance(BuffInstance other, int amplifier) {
        this.buff = other.buff;
        this.amplifier = amplifier;
        this.instant = other.instant;
        this.durationTicks = other.durationTicks;
        this.effect = other.effect == null ? null : new MobEffectInstance(other.effect.getEffect(), other.durationTicks, amplifier, false, false);
        this.enabled = other.enabled;
        this.requirement = other.requirement;
    }

    /** @return a copy of this buff at the level (1 is the lowest) */
    public BuffInstance atLevel(int level) {
        return new BuffInstance(this, level - 1);
    }

    public static BuffInstance build(IBuff buff, int amplifier, int duration, boolean instant) {
        return new BuffInstance(buff, amplifier, duration, instant);
    }

    public static BuffInstance build(IBuff buff, int amplifier, int duration) {
        return new BuffInstance(buff, amplifier, duration);
    }

    public static BuffInstance build(IBuff buff, int amplifier) {
        return new BuffInstance(buff, amplifier);
    }

    public static BuffInstance build(IBuff buff) {
        return new BuffInstance(buff);
    }

    /**
     * Uses the {@link ArmorItem#inventoryTick(ItemStack, Level, Entity, int, boolean)} function to trigger buffs
     * <p>
     * Applies Buff's effects.
     * <p>
     * If the buff is an effect it is applied even if the player already has the effect, which refreshes its duration
     * and upgrades a weaker level. The caller decides when that's needed, see ModGlobalEvents.
     */
    @SuppressWarnings("removal")
    public void onInventoryTick(ItemStack stack, Level world, Player player) {
        buff.onInventoryTick(stack, world, player);

        if (buff.isEffect()) {
            Holder<MobEffect> holder = buff.getEffect();
            if (holder == null) return;
            player.addEffect(new MobEffectInstance(holder, durationTicks, amplifier, false, false));
        }
    }

    public void hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        buff.hitEntity(stack, target, attacker);
        if (buff.isEffect()) {
            Holder<MobEffect> holder = buff.getEffect();
            if (holder == null) return;
            if (holder.value().isInstantenous()) {
                applyInstantOnHit(holder.value(), target, attacker);
            } else {
                target.addEffect(new MobEffectInstance(holder, durationTicks, amplifier, false, false));
            }
        }
    }

    /** Applies the effect once per hit. The hit's own invulnerability frames would block its damage, so they are lifted for it. */
    private void applyInstantOnHit(MobEffect effect, LivingEntity target, LivingEntity attacker) {
        if (target.level().isClientSide()) return;
        int invulnerableTime = target.invulnerableTime;
        target.invulnerableTime = 0;
        effect.applyInstantenousEffect(attacker, attacker, target, amplifier, 1.0);
        target.invulnerableTime = Math.max(target.invulnerableTime, invulnerableTime);
    }

    /**
     * Returns a potion effect, might be {@link Potions#} if the buff provided is not an effect.
     */
    public MobEffectInstance getEffect() {
        if (effect == null && buff instanceof EffectBuff) {
            Holder<MobEffect> holder = buff.getEffect();
            return holder != null ? new MobEffectInstance(holder, durationTicks, amplifier, false, false) : null;
        }
        return effect;
    }

    /** @return the effect duration in ticks (on-hit effects of weapons use it as is) */
    public int getDurationTicks() {
        return durationTicks;
    }

    public @Nullable SetRequirement getRequirement() {
        return this.requirement;
    }

    public BuffInstance setRequirement(@Nullable SetRequirement requirement) {
        this.requirement = requirement;
        return this;
    }

    public IBuff getBuff() {
        return buff;
    }

    public BuffInstance setBuff(IBuff buff) {
        this.buff = buff;
        return this;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public BuffInstance setAmplifier(int amplifier) {
        this.amplifier = amplifier;
        return this;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public BuffInstance setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public MutableComponent getTranslatedName() {
        if (this.buff instanceof EffectBuff effectBuff) return effectBuff.getDisplayName().withStyle(DARK_AQUA);
        String name = this.buff.name().toLowerCase(Locale.ENGLISH);
        return this.buff.isEffect() ? translate(DARK_AQUA, "armorplus.effect." + name) : translate(DARK_AQUA, "armorplus.buff." + name);
    }

    @Override
    public String toString() {
        return "BuffInstance{buff=%s, amplifier=%d, instant=%s, potion=%s, enabled=%s}".formatted(buff, amplifier, instant, effect, enabled);
    }
}