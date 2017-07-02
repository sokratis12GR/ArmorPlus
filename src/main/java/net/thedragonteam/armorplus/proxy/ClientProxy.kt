/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.proxy

import net.minecraft.client.Minecraft
import net.minecraftforge.client.model.obj.OBJLoader
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.misc.CosmeticsRenderInit
import net.thedragonteam.armorplus.registry.ModBlocks
import net.thedragonteam.armorplus.registry.ModEntities
import net.thedragonteam.armorplus.registry.ModItems

@SideOnly(Side.CLIENT)
class ClientProxy : CommonProxy() {
    val MINECRAFT: Minecraft = Minecraft.getMinecraft()

    override fun preInit(event: FMLPreInitializationEvent) {
        super.preInit(event)
        OBJLoader.INSTANCE.addDomain(ArmorPlus.MODID)
        registerModels()
        MinecraftForge.EVENT_BUS.register(this)
    }

    override fun init(event: FMLInitializationEvent) {
        super.init(event)
    }

    override fun postInit(event: FMLPostInitializationEvent) {
        super.postInit(event)
        CosmeticsRenderInit()
    }

    override fun registerModels() {
        ModItems.initModels()
        ModBlocks.initModels()
        ModEntities.initModels()
    }
}