/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.castle.base

import net.minecraft.block.BlockStairs
import net.minecraft.block.material.MapColor
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemBlock
import net.minecraft.util.BlockRenderLayer
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.blocks.castle.StoneBricks
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.util.Utils.setName

// TODO: fix CollisionBox to mach Shape (not any time soon)
class BlockStoneBrickCorner(private val stoneBricks: StoneBricks, modelState: IBlockState) : BlockStairs(modelState), IModelHelper {

    init {
        this.unlocalizedName = setName(stoneBricks.getName() + "_stone_brick_corner")
        this.setRegistryName(stoneBricks.getName() + "_stone_brick_corner")
        this.setHardness(10.0f)
        this.setResistance(5.0f)
        this.setHarvestLevel("pickaxe", 0)
        this.setLightOpacity(255)
        GameRegistry.register(this)
        GameRegistry.register(ItemBlock(this), registryName)
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks)
    }

    @SideOnly(Side.CLIENT)
    override fun initModel() {
        this.initModel(this, registryName, 0)
    }

    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.TRANSLUCENT
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isOpaqueCube(state: IBlockState?): Boolean {
        return false
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Suppress("OverridingDeprecatedMember")
    override fun getMapColor(state: IBlockState?): MapColor {
        return this.stoneBricks.mapColor
    }
}
