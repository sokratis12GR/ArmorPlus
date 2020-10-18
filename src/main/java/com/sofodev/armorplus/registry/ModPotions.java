package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPotions {

    public static final RegistryObject<Effect> EMPTY = register("empty", () -> new InstantEffect(EffectType.NEUTRAL, 1));

    public static RegistryObject<Effect> register(String name, Supplier<? extends Effect> sup) {
        return ArmorPlus.EFFECTS.register(name, sup);
    }
}