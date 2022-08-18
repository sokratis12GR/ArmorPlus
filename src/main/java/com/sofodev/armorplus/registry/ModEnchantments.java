package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.enchantments.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ModEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);

    public static final RegistryObject<Enchantment> FURY = register("furious", FuriousEnchantment::new);
    public static final RegistryObject<Enchantment> LIFE_STEAL = register("life_steal", LifeStealEnchantment::new);
    public static final RegistryObject<Enchantment> SOUL_STEALER = register("soul_stealer", SoulStealerEnchantment::new);
    public static final RegistryObject<Enchantment> UNKNOWN = register("unknown", UnknownEnchantment::new);
    public static final RegistryObject<Enchantment> SOUL_HARDEN = register("soul_harden", SoulHardenEnchantment::new);

    public static RegistryObject<Enchantment> register(String name, Supplier<? extends Enchantment> sup) {
        return ENCHANTMENTS.register(name, sup);
    }
}