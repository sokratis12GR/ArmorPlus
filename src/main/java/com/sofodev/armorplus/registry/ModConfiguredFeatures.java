package com.sofodev.armorplus.registry;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.registries.RegistryObject;

import static com.sofodev.armorplus.config.ArmorPlusConfig.*;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraft.data.worldgen.features.OreFeatures.DEEPSLATE_ORE_REPLACEABLES;
import static net.minecraft.data.worldgen.features.OreFeatures.STONE_ORE_REPLACEABLES;

//TODO: FIX WORLD GEN
public class ModConfiguredFeatures {

    //Configured Features
    public static ConfiguredFeature<?, ?> ORE_LAVA_CR_STONE_CF;
    public static ConfiguredFeature<?, ?> ORE_LAVA_CR_OBSIDIAN_CF;
    public static ConfiguredFeature<?, ?> ORE_LAVA_CRYSTAL_CF;
    public static ConfiguredFeature<?, ?> ORE_FROST_CR_STONE_CF;
    public static ConfiguredFeature<?, ?> ORE_FROST_CR_OBSIDIAN_CF;
    public static ConfiguredFeature<?, ?> ORE_FROST_CRYSTAL_CF;
    //PlacedFeatures
    public static PlacedFeature ORE_LAVA_CR_STONE_PF;
    public static PlacedFeature ORE_LAVA_CR_OBSIDIAN_PF;
    public static PlacedFeature ORE_LAVA_CRYSTAL_PF;
    public static PlacedFeature ORE_FROST_CR_STONE_PF;
    public static PlacedFeature ORE_FROST_CR_OBSIDIAN_PF;
    public static PlacedFeature ORE_FROST_CRYSTAL_PF;

//    public static final SurfaceBuilderConfig CONFIG_SOUL_VALLEY = new SurfaceBuilderConfig(SOUL_SOIL.defaultBlockState(), SOUL_SOIL.defaultBlockState(), SOUL_SAND.defaultBlockState());
//    public static final SurfaceBuilderConfig CONFIG_POSSESSED_GROUNDS = new SurfaceBuilderConfig(BASALT.defaultBlockState(), BLACKSTONE.defaultBlockState(), GRAVEL.defaultBlockState());

//    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CONFIGURED_SOUL_VALLEY = createConfiguredSurfaceBuilder(ModFeatures.SOUL_VALLEY.get(), CONFIG_SOUL_VALLEY);
//    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CONFIGURED_POSSESSED_GROUNDS = createConfiguredSurfaceBuilder(ModFeatures.POSSESSED_GROUNDS.get(), CONFIG_POSSESSED_GROUNDS);

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registryConfiguredFeatures = BuiltinRegistries.CONFIGURED_FEATURE;
        ORE_LAVA_CR_STONE_CF = Registry.register(registryConfiguredFeatures, setRL("ore_lava_crystal_cf"), createOreConfiguredFeature(STONE_ORE_REPLACEABLES, ORE_LAVA_CRYSTAL_STONE, oreLavaCrystalStone.veinSize.get(), oreLavaCrystalStone.exposure.get()));
        ORE_LAVA_CR_OBSIDIAN_CF = Registry.register(registryConfiguredFeatures, setRL("ore_lava_crystal_stone_cf"), createOreConfiguredFeature(DEEPSLATE_ORE_REPLACEABLES, ORE_LAVA_CRYSTAL_OBSIDIAN, oreLavaCrystalObsidian.veinSize.get(), oreLavaCrystalObsidian.exposure.get()));
        ORE_LAVA_CRYSTAL_CF = Registry.register(registryConfiguredFeatures, setRL("ore_lava_crystal_obsidian_cf"), createOreConfiguredFeature(DEEPSLATE_ORE_REPLACEABLES, ORE_LAVA_CRYSTAL, oreLavaCrystalCompressed.veinSize.get(), oreLavaCrystalCompressed.exposure.get()));
        ORE_FROST_CR_STONE_CF = Registry.register(registryConfiguredFeatures, setRL("ore_frost_crystal_cf"), createOreConfiguredFeature(STONE_ORE_REPLACEABLES, ORE_FROST_CRYSTAL_STONE, oreFrostCrystalStone.veinSize.get(), oreFrostCrystalStone.exposure.get()));
        ORE_FROST_CR_OBSIDIAN_CF = Registry.register(registryConfiguredFeatures, setRL("ore_frost_crystal_stone_cf"), createOreConfiguredFeature(DEEPSLATE_ORE_REPLACEABLES, ORE_FROST_CRYSTAL_OBSIDIAN, oreFrostCrystalObsidian.veinSize.get(), oreFrostCrystalObsidian.exposure.get()));
        ORE_FROST_CRYSTAL_CF = Registry.register(registryConfiguredFeatures, setRL("ore_frost_crystal_obsidian_cf"), createOreConfiguredFeature(DEEPSLATE_ORE_REPLACEABLES, ORE_FROST_CRYSTAL, oreFrostCrystalCompressed.veinSize.get(), oreFrostCrystalCompressed.exposure.get()));
        Registry<PlacedFeature> registryPlacedFeatures = BuiltinRegistries.PLACED_FEATURE;
        ORE_LAVA_CR_STONE_PF = Registry.register(registryPlacedFeatures, setRL("ore_lava_crystal_pf"), createOrePlacedFeature(ORE_LAVA_CR_STONE_CF, 25, oreLavaCrystalStone.minY.get(), oreLavaCrystalStone.maxY.get()));
        ORE_LAVA_CR_OBSIDIAN_PF = Registry.register(registryPlacedFeatures, setRL("ore_lava_crystal_stone_pf"), createOrePlacedFeature(ORE_LAVA_CR_OBSIDIAN_CF, 20, oreLavaCrystalObsidian.minY.get(), oreLavaCrystalObsidian.maxY.get()));
        ORE_LAVA_CRYSTAL_PF = Registry.register(registryPlacedFeatures, setRL("ore_lava_crystal_obsidian_pf"), createOrePlacedFeature(ORE_LAVA_CRYSTAL_CF, 15, oreLavaCrystalCompressed.minY.get(), oreLavaCrystalCompressed.maxY.get()));
        ORE_FROST_CR_STONE_PF = Registry.register(registryPlacedFeatures, setRL("ore_frost_crystal_pf"), createOrePlacedFeature(ORE_FROST_CR_STONE_CF, 25, oreFrostCrystalStone.minY.get(), oreFrostCrystalStone.maxY.get()));
        ORE_FROST_CR_OBSIDIAN_PF = Registry.register(registryPlacedFeatures, setRL("ore_frost_crystal_stone_pf"), createOrePlacedFeature(ORE_FROST_CR_OBSIDIAN_CF, 20, oreFrostCrystalObsidian.minY.get(), oreFrostCrystalObsidian.maxY.get()));
        ORE_FROST_CRYSTAL_PF = Registry.register(registryPlacedFeatures, setRL("ore_frost_crystal_obsidian_pf"), createOrePlacedFeature(ORE_FROST_CRYSTAL_CF, 15, oreFrostCrystalCompressed.minY.get(), oreFrostCrystalCompressed.maxY.get()));
    }

    /**
     * Creates a configured feature which will add a new feature to the world gen, in this instance it will be creating a preset for ore world generation.
     * <p>
     * The filling block will be determined by the specified {@link RuleTest} in {@param replace}, and the filler will be our block of choice {@param block}.
     * We can determine the size of the veins that can appear by tweaking the {@param veinSize} accordingly.
     * Then we set the minimal offset (Y coordinate) that the ore generation can occur via {@param yOffset}.
     * And finally we can adjust the maximum length (Y coordinate) that our ore generation can occur by changing the {@param exposure}.
     *
     * @return The finished ConfiguredFeature entry.
     */
    private static ConfiguredFeature<?, ?> createOreConfiguredFeature(RuleTest replace, RegistryObject<Block> block, int veinSize, double exposure) {
        return Feature.ORE.configured(new OreConfiguration(
                replace, // The blocks this ore can replace.
                block.get().defaultBlockState(), // The ore block to place.
                veinSize, // The size of the vein. Do not do less than 3 or else it places nothing.
                (float) exposure // % of exposed ore block will not generate if touching air.
        ));
    }

    private static PlacedFeature createOrePlacedFeature(ConfiguredFeature<?, ?> cf, int attemptCount, int minY, int maxY) {
        return cf.placed(
                CountPlacement.of(attemptCount), // How many attempts per chunk to spawn this feature.
                InSquarePlacement.spread(), // Randomizes the x/z so it is in a random 0-15 spot in the chunk.
                HeightRangePlacement.uniform( // Equal chance for any height in the following range:
                        VerticalAnchor.aboveBottom(minY), // Bottom of spawn range starts 20 blocks above world bottom.
                        VerticalAnchor.belowTop(maxY)), // Top of the spawn range starts 50 blocks below world max height.
                BiomeFilter.biome() // Needed to allow the feature to spawn in biomes properly.
        );
    }

}
