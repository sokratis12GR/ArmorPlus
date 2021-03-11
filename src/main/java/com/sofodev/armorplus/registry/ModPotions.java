package com.sofodev.armorplus.registry;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPotions {

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);

    public static final RegistryObject<Effect> EMPTY = register("empty", () -> new InstantEffect(EffectType.NEUTRAL, 1));

    public static RegistryObject<Effect> register(String name, Supplier<? extends Effect> sup) {
        return EFFECTS.register(name, sup);
    }
}