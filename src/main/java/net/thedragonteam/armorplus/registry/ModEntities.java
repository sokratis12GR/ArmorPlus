/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.thedragonteam.armorplus.entity.dungeon.skeletalking.EntitySkeletalKing;
import net.thedragonteam.armorplus.entity.mobs.EntityEnderDragonZombie;

import static net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn;
import static net.thedragonteam.armorplus.ModConfig.EntitiesConfig.ender_dragon_zombie;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ModEntities {

    public static void registerEntitySettings() {
        registerSpawns();
        registerLootTables();
    }

    private static void registerSpawns() {
        if (ender_dragon_zombie.enableSpawnEnd) {
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