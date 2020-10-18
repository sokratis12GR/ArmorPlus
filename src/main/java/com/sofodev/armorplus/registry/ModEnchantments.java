package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.enchantments.EnhancedEnchantment;
import com.sofodev.armorplus.registry.enchantments.FuriousEnchantment;
import com.sofodev.armorplus.registry.enchantments.LifeStealEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEnchantments {

    public static final RegistryObject<Enchantment> FURY = register("furious", FuriousEnchantment::new);
    public static final RegistryObject<Enchantment> LIFE_STEAL = register("life_steal", LifeStealEnchantment::new);
    public static final RegistryObject<Enchantment> ENHANCED = register("enhanced", EnhancedEnchantment::new);

    public static RegistryObject<Enchantment> register(String name, Supplier<? extends Enchantment> sup) {
        return ArmorPlus.ENCHANTMENTS.register(name, sup);
    }
}