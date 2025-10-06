package com.sofodev.armorplus.registry.item.extra;

import com.sofodev.armorplus.utils.Utils;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;
import java.util.Random;

public enum DeBuff implements IBuff {
    NONE,
    /*Potion Effect Related*/
    WITHER,
    SLOWNESS,
    BLINDNESS,
    NAUSEA,
    POISON,
    WEAKNESS,
    MINING_FATIGUE,
    HUNGER,
    GLOWING,
    LEVITATION,
    /*Mechanical*/
    IGNITE(false) {
        @Override
        public void hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            if (!attacker.level().isClientSide && target != null) {
                target.setRemainingFireTicks((rand.nextInt(6) + 3) * 20);
            }
        }
    };

    private final boolean isEffect;
    private final Holder<MobEffect> effect;
    public Random rand = new Random();

    DeBuff(boolean isEffect) {
        this.isEffect = isEffect;
        this.effect = ForgeRegistries.MOB_EFFECTS.getHolder(Utils.mcLoc(this.name().toLowerCase(Locale.ENGLISH))).orElse(null);
    }

    DeBuff() {
        this(true);
    }

    @Override
    public boolean isEffect() {
        return isEffect;
    }

    @Override
    public Holder<MobEffect> getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return "DeBuff{" +
                "rand=" + rand +
                ", isEffect=" + isEffect +
                ", effect=" + effect +
                '}';
    }
}