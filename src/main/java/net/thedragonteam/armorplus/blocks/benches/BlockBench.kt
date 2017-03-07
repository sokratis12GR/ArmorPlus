/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.benches

import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.blocks.base.BlockBase
import net.thedragonteam.armorplus.blocks.base.ToolType
import net.thedragonteam.armorplus.iface.IModelHelper

class BlockBench(var benches: Benches) : BlockBase(Material.IRON, benches.getName(), 1000.0f, 10.0f, ToolType.PICKAXE, 2), ITileEntityProvider, IModelHelper {

    init {
        this.defaultState = this.blockState.baseState.withProperty(FACING, EnumFacing.NORTH)
    }

    override fun initModel() {
        when (benches) {
            Benches.WORKBENCH, Benches.HIGH_TECH, Benches.ULTI_TECH, Benches.CHAMPION -> this.initModel(this, registryName, 0)
            Benches.WORKBENCH_NEW -> this.initModel(this, "armorplus:workbench", 0)
        }
    }

    override fun onBlockActivated(worldIn: World?, pos: BlockPos?, state: IBlockState?, playerIn: EntityPlayer?, hand: EnumHand?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (benches == benches.bench && !worldIn!!.isRemote) {
            playerIn!!.openGui(ArmorPlus.instance, benches.guiNumber, worldIn, pos!!.x, pos.y, pos.z)
        }
        return true
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isOpaqueCube(state: IBlockState?): Boolean {
        return false
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isFullCube(state: IBlockState?): Boolean {
        return false
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getRenderType(state: IBlockState?): EnumBlockRenderType {
        return EnumBlockRenderType.MODEL
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    override fun getStateForPlacement(world: World?, pos: BlockPos?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase?, hand: EnumHand?): IBlockState {
        var iblockstate = super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand)
        iblockstate = iblockstate.withProperty(FACING, placer!!.horizontalFacing)
        return iblockstate    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).horizontalIndex
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getStateFromMeta(meta: Int): IBlockState {
        var iblockstate = this.defaultState
        iblockstate = iblockstate.withProperty(FACING, EnumFacing.getHorizontal(meta))
        return iblockstate
    }

    @Suppress("OverridingDeprecatedMember")
    override fun withRotation(state: IBlockState, rot: Rotation?): IBlockState {
        return state.withProperty(FACING, rot!!.rotate(state.getValue(FACING)))
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING)
    }

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity? {
        return benches.tileEntity
    }

    companion object {
        private val FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL)
    }
}
