/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.lava

import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.thedragonteam.armorplus.blocks.base.BlockBase
import net.thedragonteam.armorplus.blocks.base.ToolType
import net.thedragonteam.armorplus.iface.IModelHelper

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 8/15/2016.
 * - TheDragonTeam
 */
class LavaNetherBrick : BlockBase(Material.ROCK, "lava_nether_brick", 20.0f, 3.0f, ToolType.PICKAXE, 1, 1.0f), IModelHelper {
    init {
        this.defaultState = this.blockState.baseState.withProperty(FACING, EnumFacing.NORTH)
    }

    override fun isNormalCube(state: IBlockState, world: IBlockAccess?, pos: BlockPos?): Boolean {
        return true
    }

    override fun getStateForPlacement(worldIn: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand?): IBlockState {
        var iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer, hand)
        iblockstate = iblockstate.withProperty(FACING, placer.horizontalFacing)
        return iblockstate
    }

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

    override fun getMapColor(state: IBlockState?, p_180659_2_: IBlockAccess?, p_180659_3_: BlockPos?): MapColor {
        return MapColor.NETHERRACK
    }

    companion object {
        val FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL)!!
    }

    override fun initModel() {
        this.initModel(registryName, "lava", 0)
    }
}