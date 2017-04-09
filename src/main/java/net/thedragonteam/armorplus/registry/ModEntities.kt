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
import net.thedragonteam.armorplus.entity.dungeon.guardian.EntityGuardianOverlord
import net.thedragonteam.armorplus.entity.dungeon.guardian.RenderGuardianOverlord
import net.thedragonteam.armorplus.entity.dungeon.guardian.projectile.EntityFreezeBomb
import net.thedragonteam.armorplus.entity.dungeon.guardian.projectile.RenderFreezeBomb
import net.thedragonteam.armorplus.entity.entityarrow.*
import net.thedragonteam.armorplus.entity.entitygolem.EntityIceGolem
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie
import net.thedragonteam.armorplus.entity.render.*
import net.thedragonteam.armorplus.util.Utils.setResourceLocation

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
    private val ICE_GOLEM = 12
    //Boss ID from 51 to 100
    private val OVERLORD_OF_THE_GUARDIANS = 51
    //Boss Projectile ID from 101 and up
    private val FREEZE_BOMB = 101

    fun init() {
        // Every entity in ArmorPlus has an ID (local to this mod)
        // Entities
        registerModEntity(setResourceLocation("ender_dragon_zombie"), EntityEnderDragonZombie::class.java, "ender_dragon_zombie", ENDER_DRAGON_ZOMBIE, instance, 64, 1, true, 0x721164, 0x00ff00)
        registerModEntity(setResourceLocation("ice_golem"), EntityIceGolem::class.java, "ice_golem", ICE_GOLEM, instance, 64, 1, true, 0xffffff, 0x00ff00)
        // Arrows
        registerModEntity(setResourceLocation("coal_arrow"), EntityCoalArrow::class.java, "coal_arrow", COAL_ARROW, instance, 64, 1, true)
        registerModEntity(setResourceLocation("lapis_arrow"), EntityLapisArrow::class.java, "lapis_arrow", LAPIS_ARROW, instance, 64, 1, true)
        registerModEntity(setResourceLocation("redstone_arrow"), EntityRedstoneArrow::class.java, "redstone_arrow", REDSTONE_ARROW, instance, 64, 1, true)
        registerModEntity(setResourceLocation("lava_arrow"), EntityLavaArrow::class.java, "lava_arrow", LAVA_ARROW, instance, 64, 1, true)
        registerModEntity(setResourceLocation("ender_dragon_arrow"), EntityEnderDragonArrow::class.java, "ender_dragon_arrow", ENDER_DRAGON_ARROW, instance, 64, 1, true)

        // Bosses
        registerModEntity(setResourceLocation("overlord_of_the_guardians"), EntityGuardianOverlord::class.java, "overlord_of_the_guardians", OVERLORD_OF_THE_GUARDIANS, instance, 80, 1, true, 0x7ae4ff, 0x79a6ff)
        // Boss Projectiles
        registerModEntity(setResourceLocation("freeze_bomb"), EntityFreezeBomb::class.java, "freeze_bomb", FREEZE_BOMB, instance, 64, 1, true)

        // The mobs wont spawn automatically if we don't define biomes to spawn in
        // but it can still be spawned manually
        if (enableEnderDragonZombieSpawnEnd)
            addSpawn(EntityEnderDragonZombie::class.java, 1, 0, 1, EnumCreatureType.MONSTER, Biomes.SKY)

        // This is the loot table for the mobs
        LootTableList.register(EntityEnderDragonZombie.LOOT)
        LootTableList.register(EntityIceGolem.LOOT)
    }

    @SideOnly(Side.CLIENT)
    fun initModels() {
        //Mobs
        registerEntityRenderingHandler<EntityEnderDragonZombie>(EntityEnderDragonZombie::class.java, IRenderFactory(::RenderEnderDragonZombie))
        registerEntityRenderingHandler<EntityIceGolem>(EntityIceGolem::class.java, IRenderFactory(::RenderIceGolem))
        //Arrows
        registerEntityRenderingHandler<EntityCoalArrow>(EntityCoalArrow::class.java, IRenderFactory(::RenderCoalArrow))
        registerEntityRenderingHandler<EntityLapisArrow>(EntityLapisArrow::class.java, IRenderFactory(::RenderLapisArrow))
        registerEntityRenderingHandler<EntityRedstoneArrow>(EntityRedstoneArrow::class.java, IRenderFactory(::RenderRedstoneArrow))
        registerEntityRenderingHandler<EntityLavaArrow>(EntityLavaArrow::class.java, IRenderFactory(::RenderLavaArrow))
        registerEntityRenderingHandler<EntityEnderDragonArrow>(EntityEnderDragonArrow::class.java, IRenderFactory(::RenderEnderDragonArrow))
        //Bosses
        registerEntityRenderingHandler<EntityGuardianOverlord>(EntityGuardianOverlord::class.java, IRenderFactory(::RenderGuardianOverlord))
        //Boss Projectiles
        registerEntityRenderingHandler<EntityFreezeBomb>(EntityFreezeBomb::class.java, IRenderFactory(::RenderFreezeBomb))
    }
}