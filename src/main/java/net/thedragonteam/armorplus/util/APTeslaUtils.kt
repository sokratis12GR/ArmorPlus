/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util

import net.darkhax.tesla.api.ITeslaConsumer
import net.darkhax.tesla.api.ITeslaHolder
import net.darkhax.tesla.api.ITeslaProducer
import net.darkhax.tesla.api.implementation.BaseTeslaContainer
import net.darkhax.tesla.capability.TeslaCapabilities
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject

import net.minecraftforge.fml.common.Optional.Method

object APTeslaUtils {

    @CapabilityInject(ITeslaConsumer::class)
    var teslaConsumer: Capability<ITeslaConsumer>? = null
    @CapabilityInject(ITeslaProducer::class)
    var teslaProducer: Capability<ITeslaProducer>? = null
    @CapabilityInject(ITeslaHolder::class)
    var teslaHolder: Capability<ITeslaHolder>? = null

    @Method(modid = "tesla")
    fun isPoweredItem(stack: ItemStack): Boolean {
        return stack.hasCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN)
    }

    @Method(modid = "tesla")
    fun getStoredPower(stack: ItemStack): Long {
        if (isPoweredItem(stack)) {
            val container = getContainer(stack)
            return getContainer(stack)!!.storedPower
        }
        return 0
    }

    @Method(modid = "tesla")
    fun getMaxCapacity(stack: ItemStack): Long {
        return if (isPoweredItem(stack)) getContainer(stack)!!.capacity else 0
    }

    @Method(modid = "tesla")
    fun addPower(stack: ItemStack, amount: Long) {
        if (isPoweredItem(stack)) getContainer(stack)!!.givePower(amount, false)
    }

    @Method(modid = "tesla")
    fun getContainer(stack: ItemStack): BaseTeslaContainer? {
        return if (isPoweredItem(stack)) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN) as BaseTeslaContainer? else null
    }

    @Method(modid = "tesla")
    fun setMaxCapacity(stack: ItemStack, amount: Long) {
        if (isPoweredItem(stack)) getContainer(stack)!!.capacity = amount
    }

    @Method(modid = "tesla")
    fun usePower(stack: ItemStack, amount: Long) {
        if (isPoweredItem(stack)) getContainer(stack)!!.takePower(amount, false)
    }

    @Method(modid = "tesla")
    fun getMaxInput(stack: ItemStack): Long {
        return if (isPoweredItem(stack)) getContainer(stack)!!.inputRate else 0
    }

    @Method(modid = "tesla")
    fun getMaxOutput(stack: ItemStack): Long {
        return if (isPoweredItem(stack)) getContainer(stack)!!.outputRate else 0
    }

    @Method(modid = "tesla")
    fun createChargedStack(stack: ItemStack): ItemStack {
        if (isPoweredItem(stack)) {
            val chargedstack = stack.copy()
            getContainer(chargedstack)!!.inputRate = APTeslaUtils.getMaxCapacity(stack)
            addPower(chargedstack, getMaxCapacity(stack))
            getContainer(chargedstack)!!.inputRate = APTeslaUtils.getMaxInput(stack)
            return chargedstack
        }
        return ItemStack.EMPTY
    }

    /**
     * Blocks
     */
    @Method(modid = "tesla")
    fun isTelsaBlock(tileEntity: TileEntity): Boolean {
        return tileEntity.hasCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN)
    }

    @Method(modid = "tesla")
    fun getContainer(tileEntity: TileEntity): BaseTeslaContainer? {
        if (isTelsaBlock(tileEntity)) {
            val container = tileEntity.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN) as BaseTeslaContainer?
            return container
        }
        return null
    }

    @Method(modid = "tesla")
    fun isConsumer(tileEntity: TileEntity): Boolean {
        return tileEntity.hasCapability(TeslaCapabilities.CAPABILITY_CONSUMER, EnumFacing.DOWN)
    }

    @Method(modid = "tesla")
    fun getMaxCapacity(tile: TileEntity): Long {
        return getContainer(tile)!!.capacity
    }

    @Method(modid = "tesla")
    fun getStoredPower(tile: TileEntity): Long {
        return getContainer(tile)!!.storedPower
    }

    @Method(modid = "tesla")
    fun getMissingPower(tileEntity: TileEntity): Long {
        return getMaxCapacity(tileEntity) - getStoredPower(tileEntity)
    }

    @Method(modid = "tesla")
    fun canAcceptPower(tile: TileEntity, amount: Long): Boolean {
        return getMissingPower(tile) >= amount
    }

    @Method(modid = "tesla")
    fun addPower(tileEntity: TileEntity, amount: Long) {
        getContainer(tileEntity)!!.givePower(amount, false)
    }
}