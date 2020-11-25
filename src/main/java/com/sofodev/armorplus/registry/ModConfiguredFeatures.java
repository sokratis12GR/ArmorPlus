package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.features.APOreFeatureConfig;
import com.sofodev.armorplus.utils.GlobalVars;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import static com.sofodev.armorplus.registry.ModFeatures.ORE_CONFIG;
import static com.sofodev.armorplus.registry.features.APOreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD;
import static com.sofodev.armorplus.utils.Utils.setRL;

public class ModConfiguredFeatures {

    //Configured Features
    public static final ConfiguredFeature<?, ?> ORE_LAVA_CRYSTAL = ORE_CONFIG.get()
            .withConfiguration(new APOreFeatureConfig(BASE_STONE_OVERWORLD, States.LAVA_CRYSTAL, 8))
            .range(20).square();
    public static final ConfiguredFeature<?, ?> ORE_FROST_CRYSTAL = ORE_CONFIG.get()
            .withConfiguration(new APOreFeatureConfig(BASE_STONE_OVERWORLD, States.FROST_CRYSTAL, 8))
            .range(20).square();

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, setRL("ore_lava_crystal"), ORE_LAVA_CRYSTAL);
        Registry.register(registry, setRL("ore_frost_crystal"), ORE_FROST_CRYSTAL);
    }

    public static final class States {
        public static final BlockState LAVA_CRYSTAL = GlobalVars.ORE_LAVA_CRYSTAL.getBlock().getDefaultState();
        public static final BlockState FROST_CRYSTAL = GlobalVars.ORE_FROST_CRYSTAL.getBlock().getDefaultState();
    }
}
