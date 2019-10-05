package com.sofodev.armorplus.common.dimension.realmofinsanity;

import com.sofodev.armorplus.common.registry.ModBlocks;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEndDecorator;
import net.minecraft.world.biome.BiomeVoidDecorator;

public class RealmBiome extends Biome {

    public RealmBiome(Biome.BiomeProperties properties) {
        super(properties);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWitherSkeleton.class, 10, 2, 2));
        this.topBlock = ModBlocks.blockCompressedObsidian.getDefaultState();
        this.fillerBlock = ModBlocks.blockCompressedObsidian.getDefaultState();
        this.decorator = new BiomeVoidDecorator();
    }
}