/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.materials

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.common.IFuelHandler
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.iface.IModelHelper

import net.thedragonteam.armorplus.util.Utils.setName

/**
 * net.thedragonteam.armorplus.items.materials
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
class LavaCrystal : Item(), IFuelHandler, IModelHelper {

    private val LAVA_CRYSTAL_NAMES = arrayOf("_normal", "_charged")

    private val BURN_TIME = intArrayOf(20000, 22000)

    init {
        this.setHasSubtypes(true)
        GameRegistry.registerFuelHandler(this)
        this.setRegistryName("lava_crystal")        // The unique name (within your mod) that identifies this item
        this.unlocalizedName = setName("lava_crystal")     // Used for localization (en_US.lang)
        GameRegistry.register(this)
        this.creativeTab = ArmorPlus.tabArmorplusItems
        this.maxDamage = 0
    }

    override fun getBurnTime(fuel: ItemStack): Int {
        if (fuel.item == this)
            LAVA_CRYSTAL_NAMES.indices
                    .asSequence()
                    .filter { fuel.itemDamage == it }
                    .forEach { return BURN_TIME[it] }
        return 0
    }

    override fun getUnlocalizedName(stack: ItemStack?): String {
        return (0..1)
                .firstOrNull { stack!!.itemDamage == it }
                ?.let { super.getUnlocalizedName(stack) + LAVA_CRYSTAL_NAMES[it] }
                ?: super.getUnlocalizedName(stack)
    }

    override fun getDurabilityForDisplay(stack: ItemStack): Double {
        return super.getDurabilityForDisplay(stack)
    }

    override fun getShareTag(): Boolean {
        return super.getShareTag()
    }

    override fun initModel() {
        LAVA_CRYSTAL_NAMES.indices.forEach { i -> this.initModel(this, registryName!!.toString() + LAVA_CRYSTAL_NAMES[i], i) }
    }

    override fun getSubItems(itemIn: Item, tab: CreativeTabs?, subItems: NonNullList<ItemStack>) {
        LAVA_CRYSTAL_NAMES.indices.mapTo(subItems) { ItemStack(itemIn, 1, it) }
    }
}
