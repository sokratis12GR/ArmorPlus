/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry

import net.minecraft.entity.EnumCreatureType
import net.minecraft.init.Biomes
import net.minecraft.world.storage.loot.LootTableList
import net.minecraftforge.fml.client.registry.IRenderFactory
import net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler
import net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn
import net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.APConfig.enableEnderDragonZombieSpawnEnd
import net.thedragonteam.armorplus.ArmorPlus.instance
import net.thedragonteam.armorplus.entity.entityarrow.*
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie
import net.thedragonteam.armorplus.entity.render.*
import net.thedragonteam.armorplus.util.Utils.setRL

/**
 * net.thedragonteam.armorplus.entity
 * ArmorPlus created by sokratis12GR on 8/21/2016.
 * - TheDragonTeam
 */
object ModEntities {

    //Arrows ID 0 to 10
    private val COAL_ARROW = 0
    private val LAPIS_ARROW = 1
    private val REDSTONE_ARROW = 2
    private val LAVA_ARROW = 3
    private val ENDER_DRAGON_ARROW = 4
    //Mobs ID from 11 to 50
    private val ENDER_DRAGON_ZOMBIE = 11
    //Boss Projectile ID from 101 and up
    private val FREEZE_BOMB = 101

    fun init() {
        // Every entity in ArmorPlus has an ID (local to this mod)
        // Entities
        registerModEntity(setRL("ender_dragon_zombie"), EntityEnderDragonZombie::class.java, "ender_dragon_zombie", ENDER_DRAGON_ZOMBIE, instance, 64, 1, true, 0x721164, 0x00ff00)
        // Arrows
        registerModEntity(setRL("coal_arrow"), EntityCoalArrow::class.java, "coal_arrow", COAL_ARROW, instance, 64, 1, true)
        registerModEntity(setRL("lapis_arrow"), EntityLapisArrow::class.java, "lapis_arrow", LAPIS_ARROW, instance, 64, 1, true)
        registerModEntity(setRL("redstone_arrow"), EntityRedstoneArrow::class.java, "redstone_arrow", REDSTONE_ARROW, instance, 64, 1, true)
        registerModEntity(setRL("lava_arrow"), EntityLavaArrow::class.java, "lava_arrow", LAVA_ARROW, instance, 64, 1, true)
        registerModEntity(setRL("ender_dragon_arrow"), EntityEnderDragonArrow::class.java, "ender_dragon_arrow", ENDER_DRAGON_ARROW, instance, 64, 1, true)

        // The mobs wont spawn automatically if we don't define biomes to spawn in
        // but it can still be spawned manually
        if (enableEnderDragonZombieSpawnEnd)
            addSpawn(EntityEnderDragonZombie::class.java, 1, 0, 1, EnumCreatureType.MONSTER, Biomes.SKY)

        // This is the loot table for the mobs
        LootTableList.register(EntityEnderDragonZombie.LOOT)
    }

    @SideOnly(Side.CLIENT)
    fun initModels() {
        //Mobs
        registerEntityRenderingHandler<EntityEnderDragonZombie>(EntityEnderDragonZombie::class.java, IRenderFactory(::RenderEnderDragonZombie))
        //Arrows
        registerEntityRenderingHandler<EntityCoalArrow>(EntityCoalArrow::class.java, IRenderFactory(::RenderCoalArrow))
        registerEntityRenderingHandler<EntityLapisArrow>(EntityLapisArrow::class.java, IRenderFactory(::RenderLapisArrow))
        registerEntityRenderingHandler<EntityRedstoneArrow>(EntityRedstoneArrow::class.java, IRenderFactory(::RenderRedstoneArrow))
        registerEntityRenderingHandler<EntityLavaArrow>(EntityLavaArrow::class.java, IRenderFactory(::RenderLavaArrow))
        registerEntityRenderingHandler<EntityEnderDragonArrow>(EntityEnderDragonArrow::class.java, IRenderFactory(::RenderEnderDragonArrow))
    }
}