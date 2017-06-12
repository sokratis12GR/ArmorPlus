/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.worldgen.nbt;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.thedragonteam.armorplus.ArmorPlus;

import java.util.Random;

import static net.thedragonteam.armorplus.APConfig.*;

public class StructureGenNBT implements IWorldGenerator {
    private ResourceLocation TOWER = new ResourceLocation(ArmorPlus.MODID, "tower");

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        int posX = chunkX * 16 + random.nextInt(16);
        int posZ = chunkZ * 16 + random.nextInt(16);

        BlockPos posXZ = new BlockPos(posX, 1, posZ);

        //        val biome = world.getBiomeForCoordsBody(posXZ)

        if (world instanceof WorldServer) return;

        //        BlockPos playerspawn = serverworld.provider.getSpawnPoint();

        int posY = getGroundFromAbove(world, posX, posZ);
        BlockPos basePos = new BlockPos(posX, posY, posZ);

        this.generateTower(world, random, basePos);

    }

    public void generateTower(World world, Random random, BlockPos pos) {
        if (enableTowerGen) {
            if (random.nextInt(towerGenSpawnNeedOfChance) < towerGenSpawnChance) {
                PlacementSettings settings = new PlacementSettings();
                settings.setRotation(Rotation.NONE);

                MinecraftServer server = world.getMinecraftServer();
                Template template = world.getSaveHandler().getStructureTemplateManager().getTemplate(server, TOWER);

                template.addBlocksToWorld(world, pos, settings);
            }
        }
    }

    /**
     * HELPER METHODS
     */
    // find a grass or dirt block to place the structure on
    public int getGroundFromAbove(World world, int x, int z) {
        int y = 255;
        boolean foundGround = false;
        while (!foundGround && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            // "ground" for our bush is grass or dirt
            foundGround = blockAt == Blocks.DIRT || blockAt == Blocks.GRASS || blockAt != Blocks.AIR;
        }

        return y;
    }
}