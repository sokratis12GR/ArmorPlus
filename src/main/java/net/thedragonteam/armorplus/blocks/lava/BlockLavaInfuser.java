/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.lava;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.base.BlockContainerBase;
import net.thedragonteam.armorplus.client.gui.GuiHandler;
import net.thedragonteam.armorplus.tileentity.base.TileEntityLavaInfuser;

public class BlockLavaInfuser extends BlockContainerBase {

    public BlockLavaInfuser() {
        super(Material.LAVA, new TileEntityLavaInfuser(), "lava_infuser", 10000.0D, 2.5D, BlockBase.ToolType.PICKAXE, 1);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(ArmorPlus.instance, GuiHandler.GUI_LAVA_INFUSER, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
