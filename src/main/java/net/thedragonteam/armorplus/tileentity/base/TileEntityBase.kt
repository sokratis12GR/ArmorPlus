/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.tileentity.base

import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.util.APTeslaUtils
import net.thedragonteam.armorplus.util.TeslaForgeUnitsWrapper

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
abstract class TileEntityBase : TileEntity() {

    private var teslaWrapper: Any? = null

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        val handler = this.getItemHandler(facing as EnumFacing)
        return capability === CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && handler != null || super.hasCapability(capability, facing)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        when {
            capability === CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> {
                val handler = this.getItemHandler(facing as EnumFacing)
                if (handler != null) return handler as T?
            }
            capability === CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY -> {
                val tank = this.getFluidHandler(facing as EnumFacing)
                if (tank != null) return tank as T?
            }
            capability === CapabilityEnergy.ENERGY -> {
                val storage = this.getEnergyStorage(facing as EnumFacing)
                if (storage != null) return storage as T?
            }
            ArmorPlus.isTeslaLoaded() && (capability === APTeslaUtils.teslaConsumer || capability === APTeslaUtils.teslaProducer || capability === APTeslaUtils.teslaHolder) -> {
                val storage = this.getEnergyStorage(facing as EnumFacing)
                if (storage != null) {
                    if (this.teslaWrapper == null) this.teslaWrapper = TeslaForgeUnitsWrapper(storage)
                    return this.teslaWrapper as T?
                }
            }
        }
        return super.getCapability(capability, facing)
    }

    fun getFluidHandler(facing: EnumFacing): IFluidHandler? {
        return null
    }

    fun getEnergyStorage(facing: EnumFacing): IEnergyStorage? {
        return null
    }

    open fun getItemHandler(facing: EnumFacing): IItemHandler? {
        return null
    }
}
