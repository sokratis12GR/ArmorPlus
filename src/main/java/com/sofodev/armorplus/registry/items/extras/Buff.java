package com.sofodev.armorplus.registry.items.extras;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

import static com.sofodev.armorplus.utils.Utils.setVanillaLocation;
import static net.minecraft.world.effect.MobEffects.WITHER;

public enum Buff implements IBuff {
    NONE,
    /*Potion Effect Related*/
    NIGHT_VISION,
    WATER_BREATHING,
    STRENGTH,
    SPEED,
    HASTE,
    JUMP_BOOST,
    REGENERATION,
    RESISTANCE,
    FIRE_RESISTANCE,
    SATURATION,
    INVISIBILITY,
    HEALTH_BOOST,
    ABSORPTION,
    SLOW_FALLING,
    /*Mechanical*/
    FLIGHT(true) {
    },
    WITHER_IMMUNITY(true) {
        @Override
        public void onArmorTick(ItemStack stack, Level world, Player player) {
            if (!world.isClientSide) {
                player.removeEffect(WITHER);
            }
        }
    },
    WATER_WEAKNESS(true) {
        @Override
        public void onArmorTick(ItemStack stack, Level world, Player player) {
            if (!world.isClientSide) {
                boolean water = player.isUnderWater();
                if (water) {
                    int supply = player.getAirSupply();
                    if (supply > 1 && supply <= player.getMaxAirSupply()) {
                        player.setAirSupply(supply / 2);
                    }
                }
            }
        }
    },
    FIRE_WEAKNESS(true) {
        @Override
        public void onArmorTick(ItemStack stack, Level world, Player player) {
            if (!world.isClientSide) {
                boolean fire = player.isOnFire();
                if (fire) {
                    player.setRemainingFireTicks(player.getRemainingFireTicks() + 5);
                }
            }
        }
    },
    NATURAL_IMMUNITY(true) {
        @Override
        public void onArmorTick(ItemStack stack, Level world, Player player) {
            if (!player.hasEffect(FIRE_RESISTANCE.getEffect())){
                player.addEffect(new MobEffectInstance(FIRE_RESISTANCE.getEffect(), 60,0, false, false));
            }
            if (!player.hasEffect(RESISTANCE.getEffect())){
                player.addEffect(new MobEffectInstance(RESISTANCE.getEffect(), 60,0, false, false));
            }
            if (!world.isClientSide && player.getRemainingFireTicks() > 0) {
                player.clearFire();
            }
        }
    },
    FIRE_EXTINGUISH(true) {
        @Override
        public void onArmorTick(ItemStack stack, Level world, Player player) {
            if (!world.isClientSide && player.getRemainingFireTicks() > 0) {
                player.clearFire();
            }
        }
    };

    private final boolean isEffect;
    private final MobEffect effect;
    private final boolean requireFullSet;

    Buff(boolean isEffect, boolean requireFullSet) {
        this.isEffect = isEffect;
        this.effect = ForgeRegistries.MOB_EFFECTS.getValue(setVanillaLocation(this.name().toLowerCase(Locale.ENGLISH)));
        this.requireFullSet = requireFullSet;
    }

    Buff(boolean requireFullSet) {
        this(false, requireFullSet);
    }

    Buff() {
        this(true, true);
    }

    @Override
    public boolean isEffect() {
        return isEffect;
    }

    @Override
    public MobEffect getEffect() {
        return effect;
    }

    @Override
    public boolean requiresFullSet() {
        return requireFullSet;
    }

    @Override
    public String toString() {
        return "Buff{" +
                "isEffect=" + isEffect +
                ", effect=" + effect +
                ", requireFullSet=" + requireFullSet +
                '}';
    }
}