/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.entity.dungeon.guardian.EntityGuardianOverlord;
import net.thedragonteam.armorplus.entity.dungeon.guardian.RenderGuardianOverlord;
import net.thedragonteam.armorplus.entity.dungeon.guardian.projectile.EntityFreezeBomb;
import net.thedragonteam.armorplus.entity.dungeon.guardian.projectile.RenderFreezeBomb;
import net.thedragonteam.armorplus.entity.entityarrow.*;
import net.thedragonteam.armorplus.entity.entitygolem.EntityIceGolem;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;
import net.thedragonteam.armorplus.entity.render.*;

import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;
import static net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn;
import static net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity;
import static net.thedragonteam.armorplus.APConfig.enableEnderDragonZombieSpawnEnd;
import static net.thedragonteam.armorplus.ArmorPlus.instance;
import static net.thedragonteam.armorplus.util.Utils.setResourceLocation;

/**
 * net.thedragonteam.armorplus.entity
 * ArmorPlus created by sokratis12GR on 8/21/2016.
 * - TheDragonTeam
 */
public class ModEntities {

    //Arrows ID 0 to 10
    public static int COAL_ARROW = 0;
    public static int LAPIS_ARROW = 1;
    public static int REDSTONE_ARROW = 2;
    public static int LAVA_ARROW = 3;
    public static int ENDER_DRAGON_ARROW = 4;
    //Mobs ID from 11 to 50
    public static int ENDER_DRAGON_ZOMBIE = 11;
    public static int ICE_GOLEM = 12;
    //Boss ID from 51 to 100
    public static int OVERLORD_OF_THE_GUARDIANS = 51;
    //Boss Projectile ID from 101 and up
    public static int FREEZE_BOMB = 101;

    public static void init() {
        // Every entity in ArmorPlus has an ID (local to this mod)
        // Entities
       registerModEntity(setResourceLocation("ender_dragon_zombie"), EntityEnderDragonZombie.class, "ender_dragon_zombie", ENDER_DRAGON_ZOMBIE, instance, 64, 1, true, 0x721164, 0x00ff00);
       registerModEntity(setResourceLocation("ice_golem"), EntityIceGolem.class, "ice_golem", ICE_GOLEM, instance, 64, 1, true, 0xffffff, 0x00ff00);
       // Arrows
       registerModEntity(setResourceLocation("coal_arrow"), EntityCoalArrow.class, "coal_arrow", COAL_ARROW, instance, 64, 1, true);
       registerModEntity(setResourceLocation("lapis_arrow"), EntityLapisArrow.class, "lapis_arrow", LAPIS_ARROW, instance, 64, 1, true);
       registerModEntity(setResourceLocation("redstone_arrow"), EntityRedstoneArrow.class, "redstone_arrow", REDSTONE_ARROW, instance, 64, 1, true);
       registerModEntity(setResourceLocation("lava_arrow"), EntityLavaArrow.class, "lava_arrow", LAVA_ARROW, instance, 64, 1, true);
       registerModEntity(setResourceLocation("ender_dragon_arrow"), EntityEnderDragonArrow.class, "ender_dragon_arrow", ENDER_DRAGON_ARROW, instance, 64, 1, true);

        // Bosses
        registerModEntity(setResourceLocation("overlord_of_the_guardians"), EntityGuardianOverlord.class, "overlord_of_the_guardians", OVERLORD_OF_THE_GUARDIANS, instance, 80, 1, true, 0x7ae4ff, 0x79a6ff);
        // Boss Projectiles
        registerModEntity(setResourceLocation("freeze_bomb"), EntityFreezeBomb.class, "freeze_bomb", FREEZE_BOMB, instance, 64, 1, true);

        // The mobs wont spawn automatically if we don't define biomes to spawn in
        // but it can still be spawned manually
       if (enableEnderDragonZombieSpawnEnd)
           addSpawn(EntityEnderDragonZombie.class, 1, 0, 1, EnumCreatureType.MONSTER, Biomes.SKY);

        // This is the loot table for the mobs
        LootTableList.register(EntityEnderDragonZombie.Companion.getLOOT());
        LootTableList.register(EntityIceGolem.Companion.getLOOT());
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        //Mobs
       registerEntityRenderingHandler(EntityEnderDragonZombie.class, RenderEnderDragonZombie::new);
       //Arrows
       registerEntityRenderingHandler(EntityCoalArrow.class, RenderCoalArrow::new);
       registerEntityRenderingHandler(EntityLapisArrow.class, RenderLapisArrow::new);
       registerEntityRenderingHandler(EntityRedstoneArrow.class, RenderRedstoneArrow::new);
       registerEntityRenderingHandler(EntityLavaArrow.class, RenderLavaArrow::new);
       registerEntityRenderingHandler(EntityEnderDragonArrow.class, RenderEnderDragonArrow::new);
        //Bosses
        registerEntityRenderingHandler(EntityGuardianOverlord.class, RenderGuardianOverlord::new);
        //Boss Projectiles
        registerEntityRenderingHandler(EntityFreezeBomb.class, RenderFreezeBomb::new);
    }
}