package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.ModFeatures;
import com.sofodev.armorplus.registry.features.APOreFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.features.APOreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD;
import static java.util.Arrays.stream;
import static net.minecraft.world.biome.Biome.Category.*;
import static net.minecraft.world.biome.Biome.RainType.SNOW;
import static net.minecraft.world.biome.Biome.TemperatureModifier.FROZEN;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class WorldGenEvents {

    //TODO:
    //  - All 3 variants can be found in the Overworld
    //  - Stone variants Y:12 up to Y:20
    //  - Obsidian variants Y:6 up to Y:10
    //  - Compressed Obsidian variants Y:0 up to Y:4
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        boolean isSnowy = matchesBiomes(event.getCategory(), ICY, TAIGA, EXTREME_HILLS);
        boolean isSnowing = event.getClimate().precipitation == SNOW;
        boolean isFreezing = event.getClimate().temperatureModifier == FROZEN;

        if (!matchesBiomes(event.getCategory(), ICY, THEEND, OCEAN, NONE)) {
            addOresToWorldGen(event, BASE_STONE_OVERWORLD, ORE_LAVA_CRYSTAL_STONE, 5, 12, 20);
            addOresToWorldGen(event, BASE_STONE_OVERWORLD, ORE_LAVA_CRYSTAL_OBSIDIAN, 4, 6, 10);
            addOresToWorldGen(event, BASE_STONE_OVERWORLD, ORE_LAVA_CRYSTAL, 3, 0, 4);
        }

        if (isSnowy || isSnowing || isFreezing) {
            addOresToWorldGen(event, BASE_STONE_OVERWORLD, ORE_FROST_CRYSTAL_STONE, 5, 12, 20);
            addOresToWorldGen(event, BASE_STONE_OVERWORLD, ORE_FROST_CRYSTAL_OBSIDIAN, 4, 6, 10);
            addOresToWorldGen(event, BASE_STONE_OVERWORLD, ORE_FROST_CRYSTAL, 3, 0, 4);
        }
    }

    /**
     * Creates a configured feature which will add a new feature to the world gen, in this instance it will creating a preset for ore world generation.
     * <p>
     * The filling block will be determined by the specified {@link RuleTest} in {@param replace}, and the filler will be our block of choice {@param block}.
     * We can determine the size of the veins that can appear by tweaking the {@param veinSize} accordingly.
     * Then we set the minimal offset (Y coordinate) that the ore generation can occur via {@param yOffset}.
     * And finally we can adjust the maximum length (Y coordinate) that our ore generation can occur by changing the {@param yRange}.
     */
    private static void addOresToWorldGen(BiomeLoadingEvent event, RuleTest replace, RegistryObject<Block> block, int veinSize, int yOffset, int yRange) {
        ConfiguredFeature<?, ?> cf = ModFeatures.ORE_CONFIG.get()
                .withConfiguration(new APOreFeatureConfig(replace, block.get().getDefaultState(), veinSize))
                .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(yOffset, 0, yRange)))
                .square();

        event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> cf);
    }

    /**
     * A quick function that checks if selected biome category matches any of the categories we specified.
     */
    private static boolean matchesBiomes(Biome.Category category, Biome.Category... categories) {
        return stream(categories).anyMatch((s) -> category.getName().equals(s.getName()));
    }

}
