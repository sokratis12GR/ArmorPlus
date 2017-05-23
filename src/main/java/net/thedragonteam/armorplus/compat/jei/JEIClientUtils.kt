package net.thedragonteam.armorplus.compat.jei

import mezz.jei.api.IModRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.thedragonlib.util.ItemStackUtils

@SideOnly(Side.CLIENT)
class JEIClientUtils {

    companion object {
        fun addDescription(registry: IModRegistry, item: ItemStack, description: String) = registry.addDescription(item, description)

        fun addDescription(registry: IModRegistry, item: Item, meta: Int, description: String) = registry.addDescription(ItemStackUtils.getItemStack(item, meta), description)

        fun addDescription(registry: IModRegistry, item: Item, description: String) = registry.addDescription(ItemStackUtils.getItemStack(item), description)

        fun addDescription(registry: IModRegistry, block: Block, meta: Int, description: String) = registry.addDescription(ItemStackUtils.getItemStack(block, meta), description)

        fun addDescription(registry: IModRegistry, block: Block, description: String) = registry.addDescription(ItemStackUtils.getItemStack(block), description)
    }
}