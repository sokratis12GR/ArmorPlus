/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.castle.base

import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumBlockRenderType
import net.thedragonteam.armorplus.blocks.base.BlockBase
import net.thedragonteam.armorplus.blocks.base.ToolType
import net.thedragonteam.armorplus.blocks.castle.StoneBricks
import net.thedragonteam.armorplus.iface.IModelHelper

class BlockStoneBrickTower(stoneBricks: StoneBricks) : BlockBase(Material.ROCK, stoneBricks.getName() + "_stone_brick_tower", 10.0f, 5.0f, ToolType.PICKAXE, 0), IModelHelper {

    private val mapColor: MapColor

    init {
        this.mapColor = stoneBricks.mapColor
    }

    override fun initModel() {
        this.initModel(this, registryName, 0)
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Suppress("OverridingDeprecatedMember")
    override fun getMapColor(state: IBlockState?): MapColor {
        return this.mapColor
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
}
