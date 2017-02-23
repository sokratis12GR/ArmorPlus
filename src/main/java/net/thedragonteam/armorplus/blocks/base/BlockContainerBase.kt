/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.base

import net.minecraft.block.Block
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.item.ItemBlock
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.util.Utils.setName

class BlockContainerBase(materialIn: Material, private val tileEntity: TileEntity, name: String, resistance: Double, hardness: Double, toolType: ToolType, harvestLevel: Int) : Block(materialIn), ITileEntityProvider, IModelHelper {

    init {
        this.setRegistryName(name)
        this.unlocalizedName = setName(name)
        this.setResistance(resistance.toFloat())
        this.setHardness(hardness.toFloat())
        this.setHarvestLevel(toolType.tool, harvestLevel)
        GameRegistry.register(this)
        GameRegistry.register(ItemBlock(this), registryName)
    }

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity? {
        return this.tileEntity
    }

    override fun initModel() {
        this.initModel(this, registryName, 0)
    }
}
