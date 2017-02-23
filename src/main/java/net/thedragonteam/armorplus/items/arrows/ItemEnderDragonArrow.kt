/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.arrows

import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.projectile.EntityArrow
import net.minecraft.item.ItemArrow
import net.minecraft.item.ItemStack
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.entity.entityarrow.EntityEnderDragonArrow
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.util.ArrowUtils
import net.thedragonteam.armorplus.util.Utils.setName

class ItemEnderDragonArrow : ItemArrow(), IModelHelper {
    init {
        this.setRegistryName("ender_dragon_arrow")
        this.unlocalizedName = setName("ender_dragon_arrow")
        GameRegistry.register(this)
        this.creativeTab = ArmorPlus.tabArmorplusWeapons
    }

    override fun initModel() {
        this.initModel(this, registryName, 0)
    }

    override fun createArrow(world: World, itemstack: ItemStack, shooter: EntityLivingBase): EntityArrow {
        return EntityEnderDragonArrow(world, shooter)
    }

    override fun addInformation(stack: ItemStack?, playerIn: EntityPlayer?, tooltip: List<String>?, advanced: Boolean) {
        ArrowUtils.addArrowInformation(tooltip as MutableList<String>, "Applies Wither 4", 8.5, TextFormatting.DARK_PURPLE)
    }
}