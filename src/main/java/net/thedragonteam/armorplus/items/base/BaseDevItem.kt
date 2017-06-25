/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.items.enums.DevItems
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack

class BaseDevItem(private val devItems: DevItems) : BaseItem(devItems.getName()), IModelHelper {

    init {
        if (devItems.hasSubTypes()) {
            this.setHasSubtypes(true)
            maxDamage = 0
        }
        this.creativeTab = ArmorPlus.tabArmorplusItems
    }

    @SideOnly(Side.CLIENT)
    override fun initModel() {
        if (devItems.hasSubTypes()) this.initModel(registryName, "_second", "cosmetics", 1)
        this.initModel(registryName, "cosmetics", 0)
    }

    override fun getSubItems(tab: CreativeTabs?, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            if (devItems.hasSubTypes()) {
                subItems.add(getItemStack(this, 0))
                subItems.add(getItemStack(this, 1))
            }
        }
    }

}
