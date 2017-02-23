/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.registry.APItems
import net.thedragonteam.armorplus.registry.ModBlocks
import net.thedragonteam.armorplus.registry.ModItems

import net.thedragonteam.armorplus.registry.ModItems.lava
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 7/26/2016 4:42 PM.
 * - TheDragonTeam
 */
class APTab(id: Int, modid: String, private val label: String, private val tab: Int) : CreativeTabs(id, modid) {

    init {
        this.backgroundImageName = ArmorPlus.MODID + ".png" // Automatically has tab_ applied to it. Make sure you change the texture name.
    }

    override fun hasSearchBar(): Boolean {
        return true
    }

    @SideOnly(Side.CLIENT)
    override fun getIconItemStack(): ItemStack {
        when (tab) {
            0 -> return getItemStack(APItems.lavaChestplate)
            1 -> return APItems.enderDragonScale
            2 -> return getItemStack(ModBlocks.blockLavaCrystal)
            3 -> return getItemStack(APItems.redstoneBattleAxe)
            4 -> return getItemStack(ModItems.itemTeslaRod)
        }
        return ItemStack.EMPTY
    }

    @SideOnly(Side.CLIENT)
    override fun getTabIconItem(): ItemStack {
        return iconItemStack
    }

    override fun getTabLabel(): String {
        return this.label
    }

    companion object {

        lateinit internal var iconArmorPlus: ItemStack

        fun initialize() {
            iconArmorPlus = ItemStack(lava[1])
        }
    }
}