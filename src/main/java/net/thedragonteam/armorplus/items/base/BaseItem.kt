/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base

import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.iface.IItemHelper
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.items.enums.Items
import net.thedragonteam.armorplus.util.Utils

open class BaseItem(var itemName: String) : Item(), IItemHelper, IModelHelper {

    var formattingName: EnumRarity? = null

    init {
        this.setRegistryName(itemName)
        this.unlocalizedName = Utils.setName(itemName)
        GameRegistry.register(this)
        this.creativeTab = ArmorPlus.tabArmorplusItems
    }

    constructor(itemsIn: Items) : this(itemsIn.getName()) {}

    override fun initModel() {
        this.initModel(this, registryName, 0)
    }

    override fun getItemStack(stack: ItemStack): ItemStack {
        return stack
    }

    override fun getItem(item: Item): Item {
        return item
    }

    override fun getItemStack(): ItemStack {
        return ItemStack(this)
    }

    override fun getItem(): Item {
        return this
    }

    override fun getName(name: String): String {
        return name
    }

    override fun getName(): String {
        return this.itemName
    }
}
