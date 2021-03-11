package com.sofodev.armorplus.registry.items.extras;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sofodev.armorplus.utils.Utils.setVanillaLocation;
import static net.minecraft.potion.Effects.WITHER;

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
        public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
            if (!world.isClientSide) {
                player.removeEffect(WITHER);
            }
        }
    },
    WATER_WEAKNESS(true),
    FIRE_WEAKNESS(true),
    NATURAL_IMMUNITY(true) {
        @Override
        public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
            FIRE_RESISTANCE.onArmorTick(stack, world, player);
            RESISTANCE.onArmorTick(stack, world, player);
        }
    };

    private final boolean isEffect;
    private final Effect effect;
    private final boolean requireFullSet;

    Buff(boolean isEffect, boolean requireFullSet) {
        this.isEffect = isEffect;
        this.effect = ForgeRegistries.POTIONS.getValue(setVanillaLocation(this.name().toLowerCase()));
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
    public Effect getEffect() {
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