/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModBlocks;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:46 PM.
 * - TheDragonTeam
 */
public class ElectricalBlock extends Block {

    public ElectricalBlock() {
        super(Material.IRON);
        setUnlocalizedName(ArmorPlus.MODID + "." + "electrical_block");
        this.setResistance(20.0F);
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return this == ModBlocks.electricalBlock;
    }
}
