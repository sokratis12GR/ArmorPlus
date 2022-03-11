package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.features.APOreFeature;
import com.sofodev.armorplus.registry.features.APOreFeatureConfig;
import com.sofodev.armorplus.registry.features.surface.BlackstoneValleySurfaceBuilder;
import com.sofodev.armorplus.registry.features.surface.SoulValleySurfaceBuilder;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {

    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, MODID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MODID);

    //SurfaceBuilder
    //public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> SOUL_VALLEY = createSurfaceBuilder("soul_valley", () -> new BadlandsSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> SOUL_VALLEY = createSurfaceBuilder("soul_valley",
            () -> new SoulValleySurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> POSSESSED_GROUNDS = createSurfaceBuilder("possessed_grounds",
            () -> new BlackstoneValleySurfaceBuilder(SurfaceBuilderConfig.CODEC));
    //Features
    public static final RegistryObject<Feature<APOreFeatureConfig>> ORE_CONFIG = createFeature("ap_ore_feature",
            () -> new APOreFeature(APOreFeatureConfig.CODEC));

    public static <F extends Feature<?>> RegistryObject<F> createFeature(String name, Supplier<F> feature) {
        return FEATURES.register(name, feature);
    }

    public static <F extends SurfaceBuilder<?>> RegistryObject<F> createSurfaceBuilder(String name, Supplier<F> feature) {
        return SURFACE_BUILDERS.register(name, feature);
    }
}
