package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.features.APOreFeature;
import com.sofodev.armorplus.registry.features.APOreFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.FEATURES;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {

    //Features
    public static final RegistryObject<Feature<APOreFeatureConfig>> ORE_CONFIG = createFeature("ap_ore_feature", () -> new APOreFeature(APOreFeatureConfig.CODEC));

    public static void registerFeatures() {
        ORE_CONFIG.isPresent();
    }

    public static <F extends Feature<?>> RegistryObject<F> createFeature(String name, Supplier<F> feature) {
        return FEATURES.register(name, feature);
    }
}
