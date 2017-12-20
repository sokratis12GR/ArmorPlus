/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.thedragonteam.armorplus.entity.dungeon.wither.EntitySkeletalKing;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;

import static net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn;
import static net.thedragonteam.armorplus.APConfig.enableEnderDragonZombieSpawnEnd;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
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
        LootTableList.register(EntityEnderDragonZombie.LOOT);
    }

    private static void registerTracking() {
        EntityRegistry.instance().lookupModSpawn(EntitySkeletalKing.class, true);
    }
}