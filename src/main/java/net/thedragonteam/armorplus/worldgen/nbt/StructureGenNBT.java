/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.worldgen.nbt;

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
import static net.thedragonteam.armorplus.DevUtils.enableTowerDevEnv;
import static net.thedragonteam.armorplus.ModConfig.DebugConfig.debugMode;
import static net.thedragonteam.armorplus.ModConfig.WorldGenConfig.tower;
import static net.thedragonteam.armorplus.util.Utils.setRL;
import static net.thedragonteam.armorplus.util.WorldGenUtils.*;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class StructureGenNBT implements IWorldGenerator {

    public static final ResourceLocation TOWER = setRL("tower");

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        int posX = x16(chunkX) + random.nextInt(CHUNK_SIZE);
        int posZ = x16(chunkZ) + random.nextInt(CHUNK_SIZE);

        BlockPos posXZ = new BlockPos(posX, 1, posZ);

        Biome biome = world.getBiomeForCoordsBody(posXZ);

        if (!(world instanceof WorldServer)) return;

        WorldServer serverworld = (WorldServer) world;

        int posY = getGroundFromAbove(world, posX, posZ);
        BlockPos basePos = new BlockPos(posX, posY, posZ);

        if (tower.shouldOnlyGenerateInTheOverworld) {
            if (world.provider.getDimension() == 0) {
                getBiomes(Type.HOT).forEach(hotBiome -> {
                    boolean isBiomeEligible = (!hasType(hotBiome, Type.SAVANNA) && !hasType(hotBiome, Type.JUNGLE));
                    if (isBiomeEligible && biome == hotBiome) {
                        this.generateTower(serverworld, random, basePos);
                    }
                });
            }
        } else {
            getBiomes(Type.HOT).forEach(hotBiome -> {
                boolean isBiomeEligible = (!hasType(hotBiome, Type.SAVANNA) && !hasType(hotBiome, Type.JUNGLE));
                if (isBiomeEligible && biome == hotBiome) {
                    this.generateTower(serverworld, random, basePos);
                }
            });
        }
    }

    public void generateTower(WorldServer world, Random random, BlockPos pos) {
        if (tower.enable && ((random.nextInt(tower.chanceNeededForSpawning) < tower.spawnChance) || (enableTowerDevEnv() && (random.nextInt(100) < 10)))) {
            PlacementSettings settings = new PlacementSettings();
            settings.setRotation(Rotation.NONE);
            MinecraftServer server = world.getMinecraftServer();
            Template template = world.getSaveHandler().getStructureTemplateManager().getTemplate(server, TOWER);
            template.addBlocksToWorld(world, pos, settings);
            if (debugMode || enableTowerDevEnv()) {
                LogHelper.info("Tower generated at: " + pos);
            }
        }
    }
}