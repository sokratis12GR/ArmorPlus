/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base

import net.minecraft.item.Item
import net.minecraft.item.ItemSpade
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.util.Utils

open class BaseShovel(material: Item.ToolMaterial, var itemName: String) : ItemSpade(material), IModelHelper {

    init {
        this.setRegistryName(itemName)
        this.unlocalizedName = Utils.setName(itemName)
        GameRegistry.register(this)
    }

    override fun initModel() = this.initModel(this, registryName, 0)
}
