package com.sofodev.armorplus.common.dimension.arena;

import com.sofodev.armorplus.common.registry.ModBlocks;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEndDecorator;

public class BiomeArena extends Biome {

    public BiomeArena(Biome.BiomeProperties properties) {
        super(properties);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWitherSkeleton.class, 10, 2, 2));
        this.topBlock = ModBlocks.blockCompressedObsidian.getDefaultState();
        this.fillerBlock = ModBlocks.blockCompressedObsidian.getDefaultState();
        this.decorator = new BiomeEndDecorator();
    }
}