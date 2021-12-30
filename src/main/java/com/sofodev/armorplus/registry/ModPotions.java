package com.sofodev.armorplus.registry;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPotions {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);

//    public static final RegistryObject<MobEffect> EMPTY = register("empty", () -> new MobEffect(MobEffectCategory.NEUTRAL, 1));
    public static final RegistryObject<MobEffect> EMPTY = register("empty", () -> new InstantenousMobEffect(MobEffectCategory.NEUTRAL, 1));

    public static RegistryObject<MobEffect> register(String name, Supplier<? extends MobEffect> sup) {
        return EFFECTS.register(name, sup);
    }
}