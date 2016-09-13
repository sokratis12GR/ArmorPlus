/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.worldgen.dimensions;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;

/**
 * net.thedragonteam.armorplus.worldgen.dimensions
 * ArmorPlus created by sokratis12GR on 8/21/2016.
 * - TheDragonTeam
 */
public class ARPWorldProvider extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return ARPDimensions.arpDimensionType;
    }

    @Override
    public String getSaveFolder() {
        return "armorplus";
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ARPChunkGenerator(worldObj);
    }
}