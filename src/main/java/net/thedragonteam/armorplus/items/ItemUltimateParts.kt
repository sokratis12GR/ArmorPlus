/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.APConfig.theUltimateArmorItemNameColor
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity
import net.thedragonteam.armorplus.util.Utils.setName

class ItemUltimateParts : Item(), IModelHelper {

    private val ULTIMATE_NAMES = arrayOf("_helmet_right", "_helmet_middle", "_helmet_left", "_chestplate_right", "_chestplate_middle", "_chestplate_left", "_leggings_right", "_leggings_middle", "_leggings_left", "_boots_right", "_boots_middle", "_boots_left")
    var formattingName: EnumRarity

    init {
        this.setHasSubtypes(true)
        this.setRegistryName("the_ultimate_part")
        this.unlocalizedName = setName("the_ultimate_part")
        GameRegistry.register(this)
        this.creativeTab = ArmorPlus.tabArmorplusItems
        this.formattingName = addRarity("ULTIMATE", theUltimateArmorItemNameColor, "Ultimate")
    }

    override fun getRarity(stack: ItemStack): EnumRarity {
        return formattingName
    }

    override fun getUnlocalizedName(stack: ItemStack?): String {
        return ULTIMATE_NAMES.indices.firstOrNull { stack!!.itemDamage == it }?.let { super.getUnlocalizedName(stack) + ULTIMATE_NAMES[it] } ?: super.getUnlocalizedName()
    }

    override fun getSubItems(itemIn: Item, tab: CreativeTabs?, subItems: NonNullList<ItemStack>) {
        ULTIMATE_NAMES.indices.mapTo(subItems) { ItemStack(itemIn, 1, it) }
    }

    override fun initModel() {
        ULTIMATE_NAMES.indices.forEach { i -> this.initModel(this, registryName!!.toString() + ULTIMATE_NAMES[i], i) }
    }
}
