package com.sofodev.armorplus.registry.items.extras;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

import java.util.Locale;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static com.sofodev.armorplus.utils.Utils.convertToSeconds;
import static net.minecraft.ChatFormatting.DARK_AQUA;

public class BuffInstance {

    private final boolean instant;
    private final Potion potion;
    private IBuff buff;
    private int amplifier;
    private boolean enabled;

    public BuffInstance(IBuff buff, int amplifier) {
        this(buff, amplifier, 20, true);
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
            this.potion = new Potion(new MobEffectInstance(buff.getEffect(), convertToSeconds(duration), amplifier, false, false));
        } else {
            this.potion = Potions.EMPTY;
        }
        this.enabled = true;
    }

    /**
     * This constructor is used so that special abilities can be used directly, without the need of them to be potion abilities.
     */
    public BuffInstance(IBuff buff) {
        this.buff = buff;
        this.amplifier = -1;
        this.instant = true;
        this.potion = Potions.EMPTY;
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
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        //instant - x - true
        //!instant - y - false
        // instant -> else |
        buff.onArmorTick(stack, world, player);
        if (buff.isEffect()) {
            for (MobEffectInstance pot : this.getPotion().getEffects()) {
                if (!instant) {
                    if (!player.getActiveEffects().contains(pot)) {
                        player.addEffect(pot);
                    }
                } else {
                    player.addEffect(pot);
                }
            }
        }
    }

    public void hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        buff.hitEntity(stack, target, attacker);
        if (buff.isEffect()) {
            this.getPotion().getEffects().forEach(target::addEffect);
        }
    }

    /**
     * Returns a potion effect, might be {@link Potions#EMPTY} if the buff provided is not an effect.
     */
    public Potion getPotion() {
        return potion;
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
        return "BuffInstance{" +
                "buff=" + buff +
                ", amplifier=" + amplifier +
                ", instant=" + instant +
                ", potion=" + potion +
                ", enabled=" + enabled +
                '}';
    }
}