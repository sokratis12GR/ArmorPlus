/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.worldgen.nbt;

import com.sofodev.armorplus.DevUtils;
import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.util.Utils;
import com.sofodev.armorplus.common.util.WorldGenUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Random;

import static net.minecraftforge.common.BiomeDictionary.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public class StructureGenNBT implements IWorldGenerator {

    public static final ResourceLocation TOWER = Utils.setRL("tower");
    public static final ResourceLocation ENDER_DUNGEON_FLOOR_1 = Utils.setRL("ender_dungeon_floor_1");

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        int posX = WorldGenUtils.getExactRandPos(chunkX, random);
        int posZ = WorldGenUtils.getExactRandPos(chunkZ, random);

        BlockPos posXZ = new BlockPos(posX, 1, posZ);

        Biome biome = world.getBiomeForCoordsBody(posXZ);

        if (!(world instanceof WorldServer)) return;

        WorldServer serverworld = (WorldServer) world;

        int posY = WorldGenUtils.getGroundFromAbove(world, posX, posZ);
        BlockPos basePos = new BlockPos(posX, posY, posZ);

        if (ModConfig.WorldGenConfig.tower.shouldOnlyGenerateInTheOverworld) {
            if (world.provider.getDimension() == 0) {
                generateTowerWithChecks(biome, serverworld, random, basePos);
            }
        } else {
            generateTowerWithChecks(biome, serverworld, random, basePos);
        }
    }

    public void generateTowerWithChecks(Biome biome, WorldServer serverworld, Random random, BlockPos basePos) {
        getBiomes(Type.HOT).forEach(hotBiome -> {
            boolean isBiomeEligible = checkEligibility(hotBiome);
            if (isBiomeEligible && biome == hotBiome) {
                this.generateTower(serverworld, random, basePos);
            }
        });
    }

    public boolean checkEligibility(Biome hotBiome) {
        return !hasType(hotBiome, Type.SAVANNA) && !hasType(hotBiome, Type.JUNGLE);
    }

    public void generateTower(WorldServer world, Random random, BlockPos pos) {
        if (ModConfig.WorldGenConfig.tower.enable && ((random.nextInt(ModConfig.WorldGenConfig.tower.chanceNeededForSpawning) < ModConfig.WorldGenConfig.tower.spawnChance) || (DevUtils.enableTowerDevEnv() && (random.nextInt(100) < 10)))) {
            PlacementSettings settings = new PlacementSettings();
            settings.setRotation(Rotation.NONE);
            MinecraftServer server = world.getMinecraftServer();
            Template template = world.getSaveHandler().getStructureTemplateManager().getTemplate(server, TOWER);
            template.addBlocksToWorld(world, pos, settings);
            if (ModConfig.DebugConfig.debugMode || DevUtils.enableTowerDevEnv()) {
                LogHelper.info("Tower generated at: " + pos);
            }
        }
    }
}