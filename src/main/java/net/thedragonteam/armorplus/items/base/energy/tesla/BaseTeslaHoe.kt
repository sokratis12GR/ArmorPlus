/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base.energy.tesla

import net.darkhax.tesla.api.implementation.BaseTeslaContainer
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.fml.common.Optional.Method
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.base.BaseAPTeslaContainerProvider
import net.thedragonteam.armorplus.items.base.BaseHoe
import net.thedragonteam.armorplus.util.APTeslaUtils

open class BaseTeslaHoe(material: Item.ToolMaterial, name: String, private var maxCapacity: Int, private var input: Int, private var output: Int) : BaseHoe(material, name) {

    lateinit var formattingName: EnumRarity
    private val cost = 10

    init {
        this.creativeTab = ArmorPlus.tabArmorplusTesla
        this.setMaxStackSize(1)
    }

    override fun getRarity(stack: ItemStack): EnumRarity = formattingName

    override fun onItemUse(player: EntityPlayer, worldIn: World, posIn: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        APTeslaUtils.usePower(player.getHeldItem(hand), cost.toLong())
        return super.onItemUse(player, worldIn, posIn, hand, facing, hitX, hitY, hitZ)
    }

    @Method(modid = "tesla")
    override fun getSubItems(itemIn: Item, tab: CreativeTabs?, subItems: NonNullList<ItemStack>) {
        val powered = APTeslaUtils.createChargedStack(ItemStack(itemIn))
        val unpowered = ItemStack(itemIn)
        subItems.add(powered)
        subItems.add(unpowered)
    }

    override fun isRepairable(): Boolean = true

    override fun isBookEnchantable(stack: ItemStack?, book: ItemStack?): Boolean = true

    override fun getItemEnchantability(stack: ItemStack?): Int = 30

    @Method(modid = "tesla")
    override fun getDurabilityForDisplay(stack: ItemStack): Double = 1 - APTeslaUtils.getStoredPower(stack).toDouble() / APTeslaUtils.getMaxCapacity(stack).toDouble()

    override fun showDurabilityBar(stack: ItemStack): Boolean = true

    @Method(modid = "tesla")
    override fun initCapabilities(stack: ItemStack?, nbt: NBTTagCompound?): ICapabilityProvider? = BaseAPTeslaContainerProvider(BaseTeslaContainer(), maxCapacity, output, input)
}
