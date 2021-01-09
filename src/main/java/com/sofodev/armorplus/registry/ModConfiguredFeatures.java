package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.features.APOreFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;

import static com.sofodev.armorplus.config.APConfig.SERVER;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.features.APOreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD;
import static com.sofodev.armorplus.utils.Utils.setRL;

public class ModConfiguredFeatures {

    public static List<? extends Integer> lavaVeinSize = SERVER.lavaCrystalVeinList.get();
    public static List<? extends Integer> lavaOffset = SERVER.lavaCrystalOffsetList.get();
    public static List<? extends Integer> lavaRange = SERVER.lavaCrystalRangeList.get();
    public static List<? extends Integer> frostVeinSize = SERVER.frostCrystalVeinList.get();
    public static List<? extends Integer> frostOffset = SERVER.frostCrystalOffsetList.get();
    public static List<? extends Integer> frostRange = SERVER.frostCrystalRangeList.get();

    //Configured Features
    public static final ConfiguredFeature<?, ?> CF_ORE_LAVA_CR_STONE = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_LAVA_CRYSTAL_STONE,
            lavaVeinSize.get(0), lavaOffset.get(0), lavaRange.get(0));
    public static final ConfiguredFeature<?, ?> CF_ORE_LAVA_CR_OBSIDIAN = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_LAVA_CRYSTAL_OBSIDIAN,
            lavaVeinSize.get(1), lavaOffset.get(1), lavaRange.get(1));
    public static final ConfiguredFeature<?, ?> CF_ORE_LAVA_CRYSTAL = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_LAVA_CRYSTAL,
            lavaVeinSize.get(2), lavaOffset.get(2), lavaRange.get(2));
    public static final ConfiguredFeature<?, ?> CF_ORE_FROST_CR_STONE = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_FROST_CRYSTAL_STONE,
            frostVeinSize.get(0), frostOffset.get(0), frostRange.get(0));
    public static final ConfiguredFeature<?, ?> CF_ORE_FROST_CR_OBSIDIAN = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_FROST_CRYSTAL_OBSIDIAN,
            frostVeinSize.get(1), frostOffset.get(1), frostRange.get(1));
    public static final ConfiguredFeature<?, ?> CF_ORE_FROST_CRYSTAL = createOreConfiguredFeature(BASE_STONE_OVERWORLD, ORE_FROST_CRYSTAL,
            frostVeinSize.get(2), frostOffset.get(2), frostRange.get(2));

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, setRL("ore_lava_crystal"), CF_ORE_LAVA_CRYSTAL);
        Registry.register(registry, setRL("ore_lava_crystal_stone"), CF_ORE_LAVA_CR_STONE);
        Registry.register(registry, setRL("ore_lava_crystal_obsidian"), CF_ORE_LAVA_CR_OBSIDIAN);
        Registry.register(registry, setRL("ore_frost_crystal"), CF_ORE_FROST_CRYSTAL);
        Registry.register(registry, setRL("ore_frost_crystal_stone"), CF_ORE_FROST_CR_STONE);
        Registry.register(registry, setRL("ore_frost_crystal_obsidian"), CF_ORE_FROST_CR_OBSIDIAN);
    }

    /**
     * Creates a configured feature which will add a new feature to the world gen, in this instance it will creating a preset for ore world generation.
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
                .withConfiguration(new APOreFeatureConfig(replace, block.get().getDefaultState(), veinSize))
                .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(yOffset, 0, yRange)))
                .square();
    }

}
