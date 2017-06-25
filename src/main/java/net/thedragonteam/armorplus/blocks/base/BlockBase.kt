/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.base

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.util.Utils.setName

open class BlockBase @JvmOverloads constructor(material: Material, name: String, resistance: Float = 0.0f, hardness: Float = 0.0f, tool: ToolType = ToolType.PICKAXE, harvestLevel: Int = 0, lightLevel: Float = 0.0f, lightOpacity: Int = 0, unbreakable: Boolean = false) : Block(material) {

    constructor(name: String) : this(Material.GROUND, name)

    constructor(material: Material, name: String, unbreakable: Boolean) : this(material, name, 0.0f, unbreakable)

    constructor(material: Material, name: String, resistance: Float, unbreakable: Boolean) : this(material, name, resistance, 0.0f, unbreakable)

    constructor(material: Material, name: String, resistance: Float, hardness: Float, unbreakable: Boolean) : this(material, name, resistance, hardness, ToolType.PICKAXE, unbreakable)

    constructor(material: Material, name: String, resistance: Float, hardness: Float, tool: ToolType, unbreakable: Boolean) : this(material, name, resistance, hardness, tool, 0, unbreakable)

    constructor(material: Material, name: String, resistance: Float, hardness: Float, tool: ToolType, harvestLevel: Int, unbreakable: Boolean) : this(material, name, resistance, hardness, tool, harvestLevel, 0.0f, unbreakable)

    constructor(material: Material, name: String, resistance: Float, hardness: Float, tool: ToolType, harvestLevel: Int, lightLevel: Float, unbreakable: Boolean) : this(material, name, resistance, hardness, tool, harvestLevel, lightLevel, 0, unbreakable)

    init {
        this.setRegistryName(name)
        this.unlocalizedName = setName(name)
        this.setResistance(resistance)
        this.setHardness(hardness)
        this.setHarvestLevel(tool.tool, harvestLevel)
        this.setLightLevel(lightLevel)
        this.setLightOpacity(lightOpacity)
        if (unbreakable) this.setBlockUnbreakable()
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks)
    }
}