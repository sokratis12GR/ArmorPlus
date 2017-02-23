/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.misc


import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.RenderPlayerEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries
import java.util.*

class CosmeticsRenderInit {
    init {
        ThreadCosmeticsFetcher()
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun onPlayerRender(event: RenderPlayerEvent.Pre) {
        if (!COSMETICS_FOR_PEOPLE_LIST.isEmpty())
            for ((key, value) in COSMETICS_FOR_PEOPLE_LIST) {
                val playerName = event.entityPlayer.name
                if (key.equals(playerName, ignoreCase = true)) {
                    //Render the special Item/Block
                    value.render(event.entityPlayer, event.partialRenderTick)
                    break
                }
            }
    }

    companion object {

        val COSMETICS_FOR_PEOPLE_LIST = HashMap<String, RenderCosmetics>()

        fun parse(properties: Properties) {
            for (key in properties.stringPropertyNames()) {
                val values = properties.getProperty(key).split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (values.isNotEmpty()) {
                    val itemName = values[0]

                    var meta: Int
                    try {
                        meta = Integer.parseInt(values[1])
                    } catch (e: NumberFormatException) {
                        meta = 0
                    }

                    var stack = ItemStack.EMPTY
                    //Get the Item from the String
                    val resLoc = ResourceLocation(itemName)
                    when {
                        Item.REGISTRY.containsKey(resLoc) -> stack = ItemStack(ForgeRegistries.ITEMS.getValue(resLoc)!!, 1, meta)
                        Block.REGISTRY.containsKey(resLoc) -> stack = ItemStack(ForgeRegistries.BLOCKS.getValue(resLoc)!!, 1, meta)
                    }

                    //Add a new Special Renderer to the list
                    if (!stack.isEmpty) {
                        COSMETICS_FOR_PEOPLE_LIST.put(key, RenderCosmetics(stack))
                    }
                }
            }
        }
    }

}