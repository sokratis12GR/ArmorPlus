/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.lava

import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.thedragonteam.armorplus.blocks.base.BlockBase
import net.thedragonteam.armorplus.blocks.base.ToolType

class BlockLavaInfusedObsidian : BlockBase(Material.ROCK, "lava_infused_obsidian", 2000.0f, 25.0f, ToolType.PICKAXE, 2, 0.8f) {

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    override fun getMapColor(state: IBlockState?): MapColor {
        return MapColor.RED
    }
}
