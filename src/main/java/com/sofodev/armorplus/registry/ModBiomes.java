package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.biomes.DummyBiome;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MODID);

    public static final RegistryObject<Biome> FROZEN_PLAINS = register("frozen_plains", DummyBiome::build);
    public static final RegistryObject<Biome> VALLEY_OF_SOULS = register("valley_of_souls", DummyBiome::build);
    public static final RegistryObject<Biome> POSSESSED_GROUNDS = register("possessed_grounds", DummyBiome::build);

    public static RegistryObject<Biome> register(String name, Supplier<? extends Biome> sup) {
        return BIOMES.register(name, sup);
    }
}