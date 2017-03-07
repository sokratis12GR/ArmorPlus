/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base

import net.minecraft.item.Item
import net.minecraft.item.ItemPickaxe
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.util.Utils

open class BasePickaxe(material: Item.ToolMaterial, var itemName: String) : ItemPickaxe(material), IModelHelper {

    init {
        this.unlocalizedName = Utils.setName(itemName)
        this.setRegistryName(itemName)
        GameRegistry.register(this)
    }

    override fun initModel() {
        this.initModel(this, registryName, 0)
    }
}
