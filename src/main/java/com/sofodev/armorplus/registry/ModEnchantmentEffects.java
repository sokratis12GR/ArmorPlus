package com.sofodev.armorplus.registry;

import com.mojang.serialization.MapCodec;
import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.enchantment.FuriousEnchantmentEffect;
import com.sofodev.armorplus.registry.enchantment.LifeStealEnchantmentEffect;
import com.sofodev.armorplus.registry.enchantment.SoulStealerEnchantmentEffect;
import com.sofodev.armorplus.registry.enchantment.UnknownEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ArmorPlus.MODID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> SOUL_STEALER =
            ENTITY_ENCHANTMENT_EFFECTS.register("soul_stealer", () -> SoulStealerEnchantmentEffect.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> FURY =
            ENTITY_ENCHANTMENT_EFFECTS.register("furious", () -> FuriousEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> LIFE_STEAL =
            ENTITY_ENCHANTMENT_EFFECTS.register("life_steal", () -> LifeStealEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> UNKNOWN =
            ENTITY_ENCHANTMENT_EFFECTS.register("unknown", () -> UnknownEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> SOUL_HARDEN =
            ENTITY_ENCHANTMENT_EFFECTS.register("soul_harden", () -> SoulStealerEnchantmentEffect.CODEC);

    public static void register(IEventBus bus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(bus);
    }
}
