/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base.energy.tesla

import net.darkhax.tesla.api.implementation.BaseTeslaContainer
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.NonNullList
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.fml.common.Optional.Method
import net.thedragonteam.armorplus.APConfig.teslaWeaponItemNameColor
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.base.BaseAPTeslaContainerProvider
import net.thedragonteam.armorplus.items.base.BasePickaxe
import net.thedragonteam.armorplus.util.APTeslaUtils
import net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity

open class BaseTeslaPickaxe(material: Item.ToolMaterial, name: String, private var maxCapacity: Int, private var input: Int, private var output: Int) : BasePickaxe(material, name) {

    var formattingName: EnumRarity

    init {
        this.creativeTab = ArmorPlus.tabArmorplusTesla
        this.setMaxStackSize(1)
        this.formattingName = addRarity("TESLA", teslaWeaponItemNameColor, "Tesla")
    }

    override fun getRarity(stack: ItemStack): EnumRarity {
        return formattingName
    }

    @Method(modid = "tesla")
    override fun getSubItems(itemIn: Item, tab: CreativeTabs?, subItems: NonNullList<ItemStack>) {
        val powered = APTeslaUtils.createChargedStack(ItemStack(itemIn))
        val unpowered = ItemStack(itemIn)
        subItems.add(powered)
        subItems.add(unpowered)
    }

    override fun isRepairable(): Boolean {
        return true
    }

    override fun isBookEnchantable(stack: ItemStack?, book: ItemStack?): Boolean {
        return true
    }

    override fun getItemEnchantability(stack: ItemStack?): Int {
        return 30
    }

    @Method(modid = "tesla")
    override fun getDurabilityForDisplay(stack: ItemStack): Double {
        return 1 - APTeslaUtils.getStoredPower(stack).toDouble() / APTeslaUtils.getMaxCapacity(stack).toDouble()
    }

    override fun showDurabilityBar(stack: ItemStack): Boolean {
        return true
    }

    @Method(modid = "tesla")
    override fun initCapabilities(stack: ItemStack?, nbt: NBTTagCompound?): ICapabilityProvider? {
        return BaseAPTeslaContainerProvider(BaseTeslaContainer(), maxCapacity, output, input)
    }
}