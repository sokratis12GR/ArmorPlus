package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static com.sofodev.armorplus.config.APConfig.SERVER;
import static com.sofodev.armorplus.registry.ModConfiguredFeatures.*;
import static java.util.Arrays.stream;
import static net.minecraft.world.biome.Biome.Category.*;
import static net.minecraft.world.biome.Biome.RainType.SNOW;
import static net.minecraft.world.biome.Biome.TemperatureModifier.FROZEN;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class WorldGenEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        boolean isSnowy = matchesBiomes(event.getCategory(), ICY, TAIGA, EXTREME_HILLS);
        boolean isSnowing = event.getClimate().precipitation == SNOW;
        boolean isFreezing = event.getClimate().temperatureModifier == FROZEN;

        List<? extends Boolean> isLavaCrystalEnabled = SERVER.enableOreLavaWorldGen.get();
        List<? extends Boolean> isFrostCrystalEnabled = SERVER.enableOreFrostWorldGen.get();

        if (!matchesBiomes(event.getCategory(), ICY, THEEND, OCEAN, NONE)) {
            withConfiguration(event, CF_ORE_LAVA_CR_STONE, isLavaCrystalEnabled.get(0));
            withConfiguration(event, CF_ORE_LAVA_CR_OBSIDIAN, isLavaCrystalEnabled.get(1));
            withConfiguration(event, CF_ORE_LAVA_CRYSTAL, isLavaCrystalEnabled.get(2));
        }

        if (isSnowy || isSnowing || isFreezing) {
            withConfiguration(event, CF_ORE_FROST_CR_STONE, isFrostCrystalEnabled.get(0));
            withConfiguration(event, CF_ORE_FROST_CR_OBSIDIAN, isFrostCrystalEnabled.get(1));
            withConfiguration(event, CF_ORE_FROST_CRYSTAL, isFrostCrystalEnabled.get(2));
        }
    }

    private static void withConfiguration(BiomeLoadingEvent event, ConfiguredFeature<?, ?> configuredFeature, boolean enabled) {
        if (enabled) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> configuredFeature);
        }
    }

    /**
     * A quick function that checks if selected biome category matches any of the categories we specified.
     */
    private static boolean matchesBiomes(Biome.Category category, Biome.Category... categories) {
        return stream(categories).anyMatch((s) -> category.getName().equals(s.getName()));
    }

}
