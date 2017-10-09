/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.castle.base

import net.minecraft.block.BlockStairs
import net.minecraft.block.material.MapColor
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.blocks.castle.StoneBricks
import net.thedragonteam.armorplus.iface.IModdedBlock
import net.thedragonteam.armorplus.util.Utils.setName

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
class BlockStoneBrickCorner(private val stoneBricks: StoneBricks, modelState: IBlockState) : BlockStairs(modelState), IModdedBlock {

    init {
        this.unlocalizedName = setName(stoneBricks.getName() + "_stone_brick_corner")
        this.setRegistryName(stoneBricks.getName() + "_stone_brick_corner")
        this.setHardness(10.0f)
        this.setResistance(5.0f)
        this.setHarvestLevel("pickaxe", 0)
        this.setLightOpacity(255)
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks)
    }

    @SideOnly(Side.CLIENT)
    override fun initModel() {
        this.initModel("stone_bricks", 0)
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
    override fun getMapColor(state: IBlockState?, p_180659_2_: IBlockAccess?, p_180659_3_: BlockPos?): MapColor {
        return this.stoneBricks.mapColor
    }
}
