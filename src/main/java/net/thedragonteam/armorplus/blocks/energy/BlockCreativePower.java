/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks.energy;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.thedragonteam.armorplus.blocks.base.BaseEnergyBlock;
import net.thedragonteam.armorplus.tileentity.energy.TileEntityCreativePower;

public class BlockCreativePower extends BaseEnergyBlock {

    public BlockCreativePower() {
        super(Material.ROCK, "block_energy_creative", 100.0f, 10.0f);
    }

    @Optional.Method(modid = "tesla")
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileEntityCreativePower();
    }

    @Optional.Method(modid = "tesla")
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {

        return EnumBlockRenderType.MODEL;
    }
}