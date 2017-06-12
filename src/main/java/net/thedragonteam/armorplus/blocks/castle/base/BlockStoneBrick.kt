/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.castle.base

import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.thedragonteam.armorplus.blocks.base.BlockBase
import net.thedragonteam.armorplus.blocks.base.ToolType
import net.thedragonteam.armorplus.blocks.castle.StoneBricks
import net.thedragonteam.armorplus.iface.IModelHelper

class BlockStoneBrick(stoneBricks: StoneBricks) : BlockBase(Material.ROCK, stoneBricks.getName() + "_stone_brick", 10.0f, 5.0f, ToolType.PICKAXE, 0), IModelHelper {

    var color: MapColor = stoneBricks.mapColor

    val name: String = stoneBricks.getName() + "_stone_brick"

    override fun initModel() {
        this.initModel(this, registryName, 0, "normal")
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Suppress("OverridingDeprecatedMember")
    override fun getMapColor(state: IBlockState?, p_180659_2_: IBlockAccess?, p_180659_3_: BlockPos?): MapColor {
        return this.color
    }
}
