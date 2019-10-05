package com.sofodev.armorplus.common.registry;

import com.sofodev.armorplus.common.dimension.arena.ArenaProvider;
import com.sofodev.armorplus.common.dimension.arena.BiomeArena;
import com.sofodev.armorplus.common.dimension.realmofinsanity.RealmBiome;
import com.sofodev.armorplus.common.dimension.realmofinsanity.RealmWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.common.config.ModConfig.DimensionConfig.*;
import static com.sofodev.armorplus.common.util.Utils.setRL;

@EventBusSubscriber(modid = MODID)
public class ModDimensions {

    public static DimensionType arenaDimension;
    public static DimensionType realmOfInsanity;

    public static void init() {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensionTypes() {
        if (enableArenaDimension) {
            arenaDimension = DimensionType.register("arena", "_arena", arenaDimensionID, ArenaProvider.class, false);
        }
        if (enableRealmOfInsanity) {
            realmOfInsanity = DimensionType.register("realm_of_insanity", "_realm", realmDimensionID, RealmWorldProvider.class, true);
        }
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(arenaDimensionID, arenaDimension);
        DimensionManager.registerDimension(realmDimensionID, realmOfInsanity);
    }

    ////////////////
    //   Biomes   //
    ////////////////
    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(new BiomeArena(new Biome.BiomeProperties("Arena").setTemperature(2.0F).setRainDisabled()).setRegistryName(setRL("arena")));
        event.getRegistry().register(new RealmBiome(new Biome.BiomeProperties("Realm Of Insanity").setTemperature(5.0F).setRainDisabled()).setRegistryName(setRL("realm_of_insanity")));
    }
}
