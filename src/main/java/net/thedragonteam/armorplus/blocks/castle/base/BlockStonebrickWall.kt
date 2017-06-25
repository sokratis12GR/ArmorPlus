/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.castle.base

import net.minecraft.block.BlockWall
import net.minecraft.block.material.MapColor
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.util.Utils.setName

class BlockStonebrickWall(private val stoneBrick: BlockStoneBrick) : BlockWall(stoneBrick), IModelHelper {

    init {
        this.setRegistryName(stoneBrick.name + "_wall")
        this.unlocalizedName = setName(stoneBrick.name + "_wall")
        this.setResistance(10f)
        this.setHardness(5f)
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getMapColor(state: IBlockState?, p_180659_2_: IBlockAccess?, p_180659_3_: BlockPos?): MapColor {
        return this.stoneBrick.color
    }

    @SideOnly(Side.CLIENT)
    override fun getSubBlocks(tab: CreativeTabs?, list: NonNullList<ItemStack>) {
        list.add(ItemStack(this, 1))
    }

    override fun initModel() {
        this.initModel(registryName, "stone_bricks", 0)
    }
}

