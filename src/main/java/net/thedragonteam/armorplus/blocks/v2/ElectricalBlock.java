/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks.v2;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.thedragonteam.armorplus.registry.ModBlocks;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:46 PM.
 * - TheDragonTeam
 */
public class ElectricalBlock extends BaseMetalBlock {

    public ElectricalBlock() {
        super("electrical_block");
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return this == ModBlocks.electricalBlock;
    }
}
