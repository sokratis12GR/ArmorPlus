/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.storage.loot.LootTableList;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;

import static net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn;
import static net.thedragonteam.armorplus.APConfig.enableEnderDragonZombieSpawnEnd;

/**
 * net.thedragonteam.armorplus.entity
 * ArmorPlus created by sokratis12GR on 8/21/2016.
 * - TheDragonTeam
 */
public class ModEntities {

    public static void registerEntitySettings() {
        registerSpawns();
        registerLootTables();
    }

    private static void registerSpawns() {
        if (enableEnderDragonZombieSpawnEnd) {
            addSpawn(EntityEnderDragonZombie.class, 1, 0, 1, EnumCreatureType.MONSTER, Biomes.SKY);
        }
    }

    private static void registerLootTables() {
        LootTableList.register(EntityEnderDragonZombie.Companion.getLOOT());
    }
}