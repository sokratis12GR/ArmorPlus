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
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.thedragonteam.armorplus.ArmorPlus;

import java.util.Random;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.util.Utils.setRL;

public class StructureGenNBT implements IWorldGenerator {

    private static final ResourceLocation TOWER = setRL("tower");

    /**
     * HELPER METHODS
     **/
    // find a grass or dirt block to place the structure on
    public static int getGroundFromAbove(World world, int x, int z) {
        int y = 255;
        boolean foundGround = false;
        while (!foundGround && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            // "ground" for our bush is grass or dirt
            foundGround = blockAt == Blocks.DIRT || blockAt == Blocks.GRASS || blockAt != Blocks.AIR;
        }

        return y;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        int posX = chunkX * 16 + random.nextInt(16);
        int posZ = chunkZ * 16 + random.nextInt(16);

        BlockPos posXZ = new BlockPos(posX, 1, posZ);

        Biome biome = world.getBiomeForCoordsBody(posXZ);

        if (!(world instanceof WorldServer)) return;

        WorldServer serverworld = (WorldServer) world;
        //BlockPos playerspawn = serverworld.provider.getSpawnPoint();

        int posY = getGroundFromAbove(world, posX, posZ);
        BlockPos basePos = new BlockPos(posX, posY, posZ);

        this.generateTower(serverworld, random, basePos);
    }

    public void generateTower(WorldServer world, Random random, BlockPos pos) {
        if (enableTowerGen) {
            if ((random.nextInt(towerGenSpawnNeedOfChance) < towerGenSpawnChance) || (ArmorPlus.DEV_ENVIRONMENT && (random.nextInt(100) < 10))) {
                PlacementSettings settings = new PlacementSettings();
                settings.setRotation(Rotation.NONE);
                MinecraftServer server = world.getMinecraftServer();
                Template template = world.getSaveHandler().getStructureTemplateManager().getTemplate(server, TOWER);
                template.addBlocksToWorld(world, pos, settings);
            }
        }
    }
}