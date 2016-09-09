/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.worldgen.dimensions;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.thedragonteam.armorplus.ArmorPlus;

import static net.thedragonteam.armorplus.ARPConfig.arpDimensionId;

/**
 * net.thedragonteam.armorplus.worldgen.dimensions
 * ArmorPlus created by sokratis12GR on 8/21/2016.
 * - TheDragonTeam
 */
public class ARPDimensions {

    public static DimensionType arpDimensionType;

    public static void init() {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensionTypes() {
        arpDimensionType = DimensionType.register(ArmorPlus.MODID, "_arp", arpDimensionId, ARPWorldProvider.class, false);
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(arpDimensionId, arpDimensionType);
    }
}
