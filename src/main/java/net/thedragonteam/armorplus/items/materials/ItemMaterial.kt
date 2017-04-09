/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.materials

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.iface.IModelHelper

import net.thedragonteam.armorplus.util.Utils.setName

class ItemMaterial : Item(), IModelHelper {

    private val MATERIAL_NAMES = arrayOf("_chainmail", "_guardian_scale", "_wither_bone", "_ender_dragon_scale", "_the_ultimate_material")

    init {
        setRegistryName("material")
        unlocalizedName = setName("material")
        setHasSubtypes(true)
        GameRegistry.register(this)
        creativeTab = ArmorPlus.tabArmorplusItems
    }

    //0 = Chainmail
    //1 = Guardian Scale
    //2 = Wither Bone
    //3 = Ender Dragon Scale
    //4 = The Ultimate Material
    override fun getUnlocalizedName(stack: ItemStack?): String {
        return MATERIAL_NAMES.indices
                .firstOrNull { stack!!.itemDamage == it }
                ?.let { super.getUnlocalizedName(stack) + MATERIAL_NAMES[it] }
                ?: super.getUnlocalizedName(stack)
    }

    override fun getSubItems(itemIn: Item, tab: CreativeTabs?, subItems: NonNullList<ItemStack>) {
        MATERIAL_NAMES.indices.mapTo(subItems) { ItemStack(itemIn, 1, it) }
    }

    override fun initModel() {
        MATERIAL_NAMES.indices.forEach { i -> this.initModel(this, registryName!!.toString() + MATERIAL_NAMES[i], i) }
    }
}
