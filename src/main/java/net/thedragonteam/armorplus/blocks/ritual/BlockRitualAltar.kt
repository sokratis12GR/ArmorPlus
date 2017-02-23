/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.ritual

import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.item.EntityItem
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.thedragonteam.armorplus.blocks.base.BlockBase
import net.thedragonteam.armorplus.registry.ModItems
import net.thedragonteam.armorplus.tileentity.TileEntityRitualAltar
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack
import net.thedragonteam.thedragonlib.util.LogHelper.info

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
open class BlockRitualAltar : BlockBase(Material.ROCK, "ritual_altar", 10F, 10F), ITileEntityProvider {

    lateinit private var ritualAltar: TileEntityRitualAltar

    override fun getBoundingBox(state: IBlockState?, source: IBlockAccess?, pos: BlockPos?): AxisAlignedBB {
        return NOT_CONNECTED_AABB
    }

    override fun onEntityWalk(worldIn: World?, pos: BlockPos?, entityIn: Entity?) {
        for (item in ModItems.templates)
            if (entityIn is EntityItem && entityIn.entityItem.item == item) {
                ritualAltar.isItemValid(getItemStack(item))
                info("Is Item Valid: " + ritualAltar.isItemValid(getItemStack(item)))
            }

        super.onEntityWalk(worldIn, pos, entityIn)
    }

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity {
        return TileEntityRitualAltar()
    }

    companion object {
        protected val NOT_CONNECTED_AABB = AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.875, 0.9375)
    }
}
