package com.sofodev.armorplus.registry.item.extra;

import com.sofodev.armorplus.api.RuneEffectType;
import com.sofodev.armorplus.registry.ModPotions;
import com.sofodev.armorplus.registry.item.armor.SetRequirement;
import org.jspecify.annotations.Nullable;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Locale;

import static com.sofodev.armorplus.registry.item.extra.Buff.NONE;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static com.sofodev.armorplus.utils.Utils.convertToSeconds;
import static net.minecraft.ChatFormatting.DARK_AQUA;

public class BuffInstance {

    private final MobEffectInstance effect;
    private final int durationTicks;
    private IBuff buff;
    private int amplifier;
    private boolean enabled;
    /** The pieces of the set the effect needs ("@..." in the entry), null for the default, see {@link SetRequirement} */
    private @Nullable SetRequirement requirement;

    public BuffInstance(boolean enabled, IBuff buff, int amplifier) {
        this(enabled ? buff : NONE, amplifier, 20);
    }

    public BuffInstance(IBuff buff, int amplifier) {
        this(buff, amplifier, 20);
    }

    public BuffInstance(boolean enabled, IBuff buff, int amplifier, int duration) {
        this(enabled ? buff : NONE, amplifier, duration);
    }

    /**
     * Creates a new Buff Instance, with the specified amplifier (starting with 0, which will be set to 1) and duration (in seconds)...
     */
    public BuffInstance(IBuff buff, int amplifier, int duration) {
        this.buff = buff;
        this.amplifier = amplifier;
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
        this.durationTicks = 20;
        this.effect = new MobEffectInstance(ModPotions.EMPTY);
        this.enabled = true;
    }

    public BuffInstance(IBuff buff) {
        this.buff = buff;
        this.amplifier = -1;
        this.durationTicks = 20;
        this.effect = new MobEffectInstance(ModPotions.EMPTY);
        this.enabled = true;
    }

    private BuffInstance(BuffInstance other, int amplifier) {
        this.buff = other.buff;
        this.amplifier = amplifier;
        this.durationTicks = other.durationTicks;
        this.effect = other.effect == null ? null : new MobEffectInstance(other.effect.getEffect(), other.durationTicks, amplifier, false, false);
        this.enabled = other.enabled;
        this.requirement = other.requirement;
    }

    /** @return a copy of this buff at the level (1 is the lowest) */
    public BuffInstance atLevel(int level) {
        return new BuffInstance(this, level - 1);
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
     * Uses the armor inventory tick function to trigger buffs
     * <p>
     * Applies Buff's effects.
     * <p>
     * If the buff is an effect it is applied even if the player already has the effect, which refreshes its duration
     * and upgrades a weaker level. The caller decides when that's needed, see RuneEvents.
     */
    @SuppressWarnings("removal")
    public void onInventoryTick(ItemStack stack, Level world, Player player) {
        buff.onInventoryTick(stack, world, player);

        if (buff.isEffect()) {
            var holder = buff.getEffect();
            if (holder == null) return;
            player.addEffect(new MobEffectInstance(holder, durationTicks, amplifier, false, false));
        }
    }

    public void hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        buff.hitEntity(stack, target, attacker);
        if (buff.isEffect()) {
            var holder = buff.getEffect();
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
        if (!(target.level() instanceof ServerLevel level)) return;
        int invulnerableTime = target.invulnerableTime;
        target.invulnerableTime = 0;
        effect.applyInstantenousEffect(level, attacker, attacker, target, amplifier, 1.0);
        target.invulnerableTime = Math.max(target.invulnerableTime, invulnerableTime);
    }

    /**
     * Returns a potion effect, might be null or empty if the buff provided is not an effect.
     */
    public MobEffectInstance getEffect() {
        var holder = buff.getEffect();
        return holder != null ? new MobEffectInstance(holder, durationTicks, amplifier, false, false) : effect;
    }

    public IBuff getBuff() {
        return buff;
    }

    public BuffInstance setBuff(IBuff buff) {
        this.buff = buff;
        return this;
    }

    /** @return the effect duration in ticks (on-hit effects of weapons use it as is) */
    public int getDurationTicks() {
        return durationTicks;
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

    public @Nullable SetRequirement getRequirement() {
        return this.requirement;
    }

    public BuffInstance setRequirement(@Nullable SetRequirement requirement) {
        this.requirement = requirement;
        return this;
    }

    public MutableComponent getTranslatedName() {
        if (this.buff instanceof EffectBuff effectBuff) return effectBuff.getDisplayName().withStyle(DARK_AQUA);
        if (this.buff instanceof RuneEffectType type) return type.getDisplayName().withStyle(DARK_AQUA);
        String name = this.buff.name().toLowerCase(Locale.ENGLISH);
        return this.buff.isEffect() ? translate(DARK_AQUA, "armorplus.effect." + name) : translate(DARK_AQUA, "armorplus.buff." + name);
    }

    @Override
    public String toString() {
        return "BuffInstance{buff=%s, amplifier=%d, potion=%s, enabled=%s}".formatted(buff, amplifier, effect, enabled);
    }
}