/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.entity.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.common.entity.mobs.EntityEnderDragonZombie;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import static net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn;

/**
 * @author Sokratis Fotkatzikis
 **/
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
}