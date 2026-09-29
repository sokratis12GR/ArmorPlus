package com.sofodev.armorplus.registry;

import net.minecraft.core.registries.Registries;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ModPotions {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, MODID);

    public static final DeferredHolder<MobEffect, MobEffect> EMPTY = register("empty", () -> new InstantenousMobEffect(MobEffectCategory.NEUTRAL, 1));

    public static DeferredHolder<MobEffect, MobEffect> register(String name, Supplier<? extends MobEffect> sup) {
        return EFFECTS.register(name, sup);
    }
}