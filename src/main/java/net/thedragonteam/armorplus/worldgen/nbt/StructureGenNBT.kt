/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.worldgen.nbt

import net.minecraft.init.Blocks
import net.minecraft.util.ResourceLocation
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldServer
import net.minecraft.world.chunk.IChunkGenerator
import net.minecraft.world.chunk.IChunkProvider
import net.minecraft.world.gen.structure.template.PlacementSettings
import net.minecraftforge.fml.common.IWorldGenerator
import net.thedragonteam.armorplus.APConfig.*
import net.thedragonteam.armorplus.ArmorPlus
import java.util.*

class StructureGenNBT : IWorldGenerator {

    override fun generate(random: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider) {

        val posX = chunkX * 16 + random.nextInt(16)
        val posZ = chunkZ * 16 + random.nextInt(16)

        @Suppress("UNUSED_VARIABLE")
        val posXZ = BlockPos(posX, 1, posZ)

        //        val biome = world.getBiomeForCoordsBody(posXZ)

        if (world !is WorldServer) return

        //        BlockPos playerspawn = serverworld.provider.getSpawnPoint();

        val posY = getGroundFromAbove(world, posX, posZ)
        val basePos = BlockPos(posX, posY, posZ)

        this.generateTower(world, random, basePos)

    }

    fun generateTower(world: WorldServer, random: Random, pos: BlockPos) {
        if (enableTowerGen) {
            if (random.nextInt(towerGenSpawnNeedOfChance) < towerGenSpawnChance) {
                val settings = PlacementSettings()
                settings.rotation = Rotation.NONE

                val server = world.minecraftServer
                val template = world.saveHandler.structureTemplateManager.getTemplate(server, TOWER)

                template.addBlocksToWorld(world, pos, settings)
            }
        }
    }

    /**
     * HELPER METHODS
     */
    // find a grass or dirt block to place the structure on
    fun getGroundFromAbove(world: World, x: Int, z: Int): Int {
        var y = 255
        var foundGround = false
        while (!foundGround && y-- >= 0) {
            val blockAt = world.getBlockState(BlockPos(x, y, z)).block
            // "ground" for our bush is grass or dirt
            foundGround = blockAt === Blocks.DIRT || blockAt === Blocks.GRASS || blockAt !== Blocks.AIR
        }

        return y
    }

    private val TOWER = ResourceLocation(ArmorPlus.MODID, "tower")
}