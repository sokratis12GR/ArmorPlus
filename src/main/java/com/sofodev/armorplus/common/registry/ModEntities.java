/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.registry.entities.entityarrow.*;
import com.sofodev.armorplus.common.registry.entities.mobs.EntityEnderDragonZombie;
import com.sofodev.armorplus.common.registry.entities.mobs.EntityIceGolem;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.demonicdragon.EntityDemonicDragon;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.guardianoverlord.EntityGuardianOverlord;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.guardianoverlord.projectile.EntityFreezeBomb;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.skeletalking.projectile.EntityWitherMinion;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.skeletalking.projectile.EntityWitherling;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.ArmorPlus.instance;
import static com.sofodev.armorplus.common.util.Utils.setRL;
import static net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn;
import static net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity;

/**
 * @author Sokratis Fotkatzikis
 **/
@EventBusSubscriber(modid = MODID)
public class ModEntities {

    public static void registerEntitySettings() {
        registerSpawns();
        registerLootTables();
    }

    private static void registerSpawns() {
        if (ModConfig.EntitiesConfig.ender_dragon_zombie.enableSpawnEnd) {
            addSpawn(EntityEnderDragonZombie.class, 1, 0, 1, EnumCreatureType.MONSTER, Biomes.SKY);
        }
    }

    private static void registerLootTables() {
        LootTableList.register(EntityEnderDragonZombie.LOOT);
    }

    private static void registerTracking() {
        EntityRegistry.instance().lookupModSpawn(EntitySkeletalKing.class, true);
    }

    /**
     * Entities
     */
    private static void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, boolean hasEgg, int primaryColor, int secondaryColor) {
        ResourceLocation resourceLocation = setRL(registryName);
        if (hasEgg) {
            registerModEntity(resourceLocation, entityClass, registryName, id, instance, 64, 1, true, primaryColor, secondaryColor);
        } else {
            registerModEntity(resourceLocation, entityClass, registryName, id, instance, 64, 1, true);
        }
    }

    private static void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int primaryColor, int secondaryColor) {
        registerEntities(entityClass, registryName, id, true, primaryColor, secondaryColor);
    }

    private static void registerEntities(Class<? extends Entity> entityClass, String registryName, int id) {
        registerEntities(entityClass, registryName, id, false, 0, 0);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        int id = 0;
        registerEntities(EntityCoalArrow.class, "coal_arrow", ++id);
        registerEntities(EntityLapisArrow.class, "lapis_arrow", ++id);
        registerEntities(EntityRedstoneArrow.class, "redstone_arrow", ++id);
        registerEntities(EntityEmeraldArrow.class, "emerald_arrow", ++id);
        registerEntities(EntityObsidianArrow.class, "obsidian_arrow", ++id);
        registerEntities(EntityInfusedLavaArrow.class, "lava_arrow", ++id);
        registerEntities(EntityGuardianArrow.class, "guardian_arrow", ++id);
        registerEntities(EntitySuperStarArrow.class, "super_star_arrow", ++id);
        registerEntities(EntityEnderDragonArrow.class, "ender_dragon_arrow", ++id);
        //
        registerEntities(EntityFreezeBomb.class, "freeze_bomb", ++id);
        registerEntities(EntityWitherMinion.class, "wither_minion", ++id);
        registerEntities(EntityEnderDragonZombie.class, "ender_dragon_zombie", ++id,
            0x721164, 0x00ff00);
        registerEntities(EntityIceGolem.class, "ice_golem", ++id,
            0xffffff, 0x00ff00);
        registerEntities(EntityGuardianOverlord.class, "overlord_of_the_guardians", ++id,
            0x7ae4ff, 0x79a6ff);
        registerEntities(EntitySkeletalKing.class, "skeletal_king", ++id,
            0x665b52, 0x665b52);
        registerEntities(EntityWitherling.class, "witherling", ++id,
            0x141414, 0x655b52);
        registerEntities(EntityDemonicDragon.class, "demonic_dragon", ++id,
            0x79a6ff, 0x655b52);
    }
}