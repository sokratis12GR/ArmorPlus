package com.sofodev.armorplus.registry.items.extras;

import com.sofodev.armorplus.registry.ModPotions;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Locale;

import static com.sofodev.armorplus.registry.items.extras.Buff.NONE;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static com.sofodev.armorplus.utils.Utils.convertToSeconds;
import static net.minecraft.ChatFormatting.DARK_AQUA;

public class BuffInstance {

    private final boolean instant;
    private final MobEffectInstance effect;
    private IBuff buff;
    private int amplifier;
    private boolean enabled;

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
        if (buff.isEffect() && buff.getEffect() != null) {
            this.effect = new MobEffectInstance(buff.getEffect(), convertToSeconds(duration), amplifier, false, false);
        } else {
            this.effect = new MobEffectInstance(ModPotions.EMPTY.get());
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
        this.effect = new MobEffectInstance(ModPotions.EMPTY.get());
        this.enabled = true;
    }

    public BuffInstance(IBuff buff) {
        this.buff = buff;
        this.amplifier = -1;
        this.instant = true;
        this.effect = new MobEffectInstance(ModPotions.EMPTY.get());
        this.enabled = true;
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
     * Uses the {@link ArmorItem#onArmorTick(ItemStack, Level, Player)} function to trigger buffs
     * <p>
     * Applies Buff's effects.
     * <p>
     * If the buff is an effect it will be either applied instantly (even if you already have the effect))
     */
    @SuppressWarnings("removal")
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        //instant - x - true
        //!instant - y - false
        // instant -> else |
        buff.onArmorTick(stack, world, player);
        if (buff.isEffect()) {
                if (!instant) {
                    List<MobEffectInstance> playerEffects = player.getActiveEffects().stream().toList();
                    if (!playerEffects.isEmpty()) {
                        for (MobEffectInstance instance : playerEffects){
                            if (instance.getEffect().getDisplayName().toString().equals(effect.getEffect().getDisplayName().toString())) continue;
                            player.addEffect(effect);
                        }
                    }
                } else {
                    player.addEffect(effect);
                }
        }
    }

    public void hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        buff.hitEntity(stack, target, attacker);
        if (buff.isEffect()) {
            target.addEffect(effect);
        }
    }

    /**
     * Returns a potion effect, might be {@link Potions#EMPTY} if the buff provided is not an effect.
     */
    public MobEffectInstance getEffect() {
        return effect;
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
        String name = this.buff.name().toLowerCase(Locale.ENGLISH);
        return this.buff.isEffect() ? translate(DARK_AQUA, "armorplus.effect." + name) : translate(DARK_AQUA, "armorplus.buff." + name);
    }

    @Override
    public String toString() {
        return "BuffInstance{buff=%s, amplifier=%d, instant=%s, potion=%s, enabled=%s}".formatted(buff, amplifier, instant, effect, enabled);
    }
}