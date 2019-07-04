package com.sofodev.armorplus.common.registry;

import com.sofodev.armorplus.common.dimension.arena.ArenaProvider;
import com.sofodev.armorplus.common.dimension.arena.BiomeArena;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.common.config.ModConfig.DimensionConfig.arenaDimensionID;
import static com.sofodev.armorplus.common.config.ModConfig.DimensionConfig.enableArenaDimension;
import static com.sofodev.armorplus.common.util.Utils.setRL;

@EventBusSubscriber(modid = MODID)
public class ModDimensions {

    public static DimensionType arenaDimension;

    public static void init() {
        if (enableArenaDimension) {
            registerDimensionTypes();
            registerDimensions();
        }
    }

    private static void registerDimensionTypes() {
        arenaDimension = DimensionType.register("arena", "_arena", arenaDimensionID, ArenaProvider.class, false);
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(arenaDimensionID, arenaDimension);
    }

    ////////////////
    //   Biomes   //
    ////////////////
    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(new BiomeArena(new Biome.BiomeProperties("Arena").setTemperature(2.0F).setRainDisabled()).setRegistryName(setRL("arena")));
    }
}
