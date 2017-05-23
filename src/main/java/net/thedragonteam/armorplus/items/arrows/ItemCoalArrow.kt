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
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.entity.entityarrow.EntityCoalArrow
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.util.ArrowUtils
import net.thedragonteam.armorplus.util.Utils.setName

class ItemCoalArrow : ItemArrow(), IModelHelper {
    init {
        this.setRegistryName("coal_arrow")
        this.unlocalizedName = setName("coal_arrow")
        GameRegistry.register(this)
        this.creativeTab = ArmorPlus.tabArmorplusWeapons
    }

    override fun initModel() = this.initModel(this, registryName, 0)

    override fun createArrow(world: World, itemstack: ItemStack, shooter: EntityLivingBase): EntityArrow = EntityCoalArrow(world, shooter)

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack?, playerIn: EntityPlayer?, tooltip: List<String>?, advanced: Boolean) {
        ArrowUtils.addArrowInformation(tooltip as MutableList<String>, "Applies Blindness", 2.0, TextFormatting.GRAY)
    }
}