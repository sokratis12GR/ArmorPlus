package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.features.APOreFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;

import static com.sofodev.armorplus.config.APConfig.COMMON;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.features.APOreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD;
import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraft.block.Blocks.*;

public class ModConfiguredFeatures {

    //Configured Features
    public static final ConfiguredFeature<?, ?> CF_ORE_LAVA_CR_STONE = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_LAVA_CRYSTAL_STONE,
            COMMON.oreLavaCrystalStone.veinSize.get(), COMMON.oreLavaCrystalStone.offset.get(), COMMON.oreLavaCrystalStone.range.get());
    public static final ConfiguredFeature<?, ?> CF_ORE_LAVA_CR_OBSIDIAN = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_LAVA_CRYSTAL_OBSIDIAN,
            COMMON.oreLavaCrystalObsidian.veinSize.get(), COMMON.oreLavaCrystalObsidian.offset.get(), COMMON.oreLavaCrystalObsidian.range.get());
    public static final ConfiguredFeature<?, ?> CF_ORE_LAVA_CRYSTAL = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_LAVA_CRYSTAL,
            COMMON.oreLavaCrystalCompressed.veinSize.get(), COMMON.oreLavaCrystalCompressed.offset.get(), COMMON.oreLavaCrystalCompressed.range.get());
    public static final ConfiguredFeature<?, ?> CF_ORE_FROST_CR_STONE = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_FROST_CRYSTAL_STONE,
            COMMON.oreFrostCrystalStone.veinSize.get(), COMMON.oreFrostCrystalStone.offset.get(), COMMON.oreFrostCrystalStone.range.get());
    public static final ConfiguredFeature<?, ?> CF_ORE_FROST_CR_OBSIDIAN = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_FROST_CRYSTAL_OBSIDIAN,
            COMMON.oreFrostCrystalObsidian.veinSize.get(), COMMON.oreFrostCrystalObsidian.offset.get(), COMMON.oreFrostCrystalObsidian.range.get());
    public static final ConfiguredFeature<?, ?> CF_ORE_FROST_CRYSTAL = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_FROST_CRYSTAL,
            COMMON.oreFrostCrystalCompressed.veinSize.get(), COMMON.oreFrostCrystalCompressed.offset.get(), COMMON.oreFrostCrystalCompressed.range.get());

    public static final SurfaceBuilderConfig CONFIG_SOUL_VALLEY = new SurfaceBuilderConfig(SOUL_SOIL.defaultBlockState(), SOUL_SOIL.defaultBlockState(), SOUL_SAND.defaultBlockState());
    public static final SurfaceBuilderConfig CONFIG_POSSESSED_GROUNDS = new SurfaceBuilderConfig(BASALT.defaultBlockState(), BLACKSTONE.defaultBlockState(), GRAVEL.defaultBlockState());

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CONFIGURED_SOUL_VALLEY = createConfiguredSurfaceBuilder(ModFeatures.SOUL_VALLEY.get(), CONFIG_SOUL_VALLEY);
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CONFIGURED_POSSESSED_GROUNDS = createConfiguredSurfaceBuilder(ModFeatures.POSSESSED_GROUNDS.get(), CONFIG_POSSESSED_GROUNDS);

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registryConfiguredFeatures = WorldGenRegistries.CONFIGURED_FEATURE;
        Registry.register(registryConfiguredFeatures, setRL("ore_lava_crystal"), CF_ORE_LAVA_CRYSTAL);
        Registry.register(registryConfiguredFeatures, setRL("ore_lava_crystal_stone"), CF_ORE_LAVA_CR_STONE);
        Registry.register(registryConfiguredFeatures, setRL("ore_lava_crystal_obsidian"), CF_ORE_LAVA_CR_OBSIDIAN);
        Registry.register(registryConfiguredFeatures, setRL("ore_frost_crystal"), CF_ORE_FROST_CRYSTAL);
        Registry.register(registryConfiguredFeatures, setRL("ore_frost_crystal_stone"), CF_ORE_FROST_CR_STONE);
        Registry.register(registryConfiguredFeatures, setRL("ore_frost_crystal_obsidian"), CF_ORE_FROST_CR_OBSIDIAN);
        Registry<ConfiguredSurfaceBuilder<?>> registryConfiguredSurfaceBuilder = WorldGenRegistries.CONFIGURED_SURFACE_BUILDER;
        Registry.register(registryConfiguredSurfaceBuilder, setRL("soul_valley"), CONFIGURED_SOUL_VALLEY);
        Registry.register(registryConfiguredSurfaceBuilder, setRL("possessed_grounds"), CONFIGURED_POSSESSED_GROUNDS);
    }

    /**
     * Creates a configured feature which will add a new feature to the world gen, in this instance it will be creating a preset for ore world generation.
     * <p>
     * The filling block will be determined by the specified {@link RuleTest} in {@param replace}, and the filler will be our block of choice {@param block}.
     * We can determine the size of the veins that can appear by tweaking the {@param veinSize} accordingly.
     * Then we set the minimal offset (Y coordinate) that the ore generation can occur via {@param yOffset}.
     * And finally we can adjust the maximum length (Y coordinate) that our ore generation can occur by changing the {@param yRange}.
     *
     * @return The finished ConfiguredFeature entry.
     */
    private static ConfiguredFeature<?, ?> createOreConfiguredFeature(RuleTest replace, RegistryObject<Block> block, int veinSize, int yOffset, int yRange) {
        return ModFeatures.ORE_CONFIG.get()
                .configured(new APOreFeatureConfig(replace, block.get().defaultBlockState(), veinSize))
                .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(yOffset, 0, yRange)))
                .squared();
    }

    private static ConfiguredSurfaceBuilder<SurfaceBuilderConfig> createConfiguredSurfaceBuilder(SurfaceBuilder<SurfaceBuilderConfig> builder, SurfaceBuilderConfig config) {
        return builder.configured(config);
    }

}
