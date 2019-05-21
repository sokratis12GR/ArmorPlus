package com.sofodev.armorplus.common.registry;

import com.sofodev.armorplus.common.dimension.arena.ArenaProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

import static com.sofodev.armorplus.common.config.ModConfig.DimensionConfig.arenaDimensionID;
import static com.sofodev.armorplus.common.config.ModConfig.DimensionConfig.enableArenaDimension;

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
}
