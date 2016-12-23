/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.worldgen.structures;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.storage.loot.LootTableList;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Random;

import static net.thedragonteam.armorplus.APConfig.isDebugMode;
import static net.thedragonteam.armorplus.util.StructureUtils.*;

public class StructureCastle extends WorldGenerator {
    /**
     * BLUEPRINTS
     **/
    // Format: int[][] { {distanceRight, distanceUp, distanceBack} }
    // Format: int[][] { {PosX, PosY, PosZ} }
    private final int[][] stoneBrickPos = new int[][]
            {
                    // upper trim (first part of roof)
                    {0, 3, 0}, {1, 3, 0}, {2, 3, 0}, {3, 3, 0}, {4, 3, 0}, {5, 3, 0}, {6, 3, 0},    // front
                    {0, 3, 6}, {1, 3, 6}, {2, 3, 6}, {3, 3, 6}, {4, 3, 6}, {5, 3, 6}, {6, 3, 6},     // back
                    {0, 3, 1}, {0, 3, 2}, {0, 3, 3}, {0, 3, 4}, {0, 3, 5},    // left
                    {6, 3, 1}, {6, 3, 2}, {6, 3, 3}, {6, 3, 4}, {6, 3, 5},    // right
            };
    private final int[] doorBottomPos = new int[]{3, 0, 0};
    private final int[] doorTopPos = new int[]{3, 1, 0};
    private final int[] chestPos = new int[]{1, 0, 3};
    private final int[] glowstonePos = new int[]{3, -1, 3};

    private final int[][] lavaPos = new int[][]
            {
                    // Corner
                    {-1, -1, -1}, {-1, -1, -2}, {-2, -1, -1},

                    // {-1, -1, POS_X}, {-2, -1, POS_X} = Negative POS_X
                    // {7, -1, POS_X}, {8, -1, POS_X} = Positive POS_X
                    {-1, -1, 0}, {-2, -1, 0}, {7, -1, 0}, {8, -1, 0},
                    {-1, -1, 1}, {-2, -1, 1}, {7, -1, 1}, {8, -1, 1},
                    {-1, -1, 2}, {-2, -1, 2}, {7, -1, 2}, {8, -1, 2},
                    {-1, -1, 3}, {-2, -1, 3}, {7, -1, 3}, {8, -1, 3},
                    {-1, -1, 4}, {-2, -1, 4}, {7, -1, 4}, {8, -1, 4},
                    {-1, -1, 5}, {-2, -1, 5}, {7, -1, 5}, {8, -1, 5},
                    {-1, -1, 6}, {-2, -1, 6}, {7, -1, 6}, {8, -1, 6},
                    {-1, -1, 7}, {-2, -1, 7}, {7, -1, 7}, {8, -1, 7},
                    {-1, -1, 8},

                    // {0, -1, -1}, {0, -1, -2} = Negative X
                    // {0, -1, 7}, {0, -1, 8} = Positive X
                    {0, -1, -1}, {0, -1, -2}, {0, -1, 7}, {0, -1, 8},
                    {1, -1, -1}, {1, -1, -2}, {1, -1, 7}, {1, -1, 8},
                    {2, -1, -1}, {2, -1, -2}, {2, -1, 7}, {2, -1, 8},
                    {3, -1, -1}, {3, -1, -2}, {3, -1, 7}, {3, -1, 8},
                    {4, -1, -1}, {4, -1, -2}, {4, -1, 7}, {4, -1, 8},
                    {5, -1, -1}, {5, -1, -2}, {5, -1, 7}, {5, -1, 8},
                    {6, -1, -1}, {6, -1, -2}, {6, -1, 7}, {6, -1, 8},
                    {7, -1, -1}, {7, -1, -2}, {7, -1, 7}, {7, -1, 8},
                    {8, -1, -1},
            };
    private final int[][] stonePos = new int[][]
            {
                    // Corner
                    {-1, -2, -1}, {-1, -2, -2}, {-2, -2, -1},

                    // {-1, -1, POS_X}, {-2, -1, POS_X} = Negative POS_X
                    // {7, -1, POS_X}, {8, -1, POS_X} = Positive POS_X
                    {-1, -2, 0}, {-2, -2, 0}, {7, -2, 0}, {8, -2, 0},
                    {-1, -2, 1}, {-2, -2, 1}, {7, -2, 1}, {8, -2, 1},
                    {-1, -2, 2}, {-2, -2, 2}, {7, -2, 2}, {8, -2, 2},
                    {-1, -2, 3}, {-2, -2, 3}, {7, -2, 3}, {8, -2, 3},
                    {-1, -2, 4}, {-2, -2, 4}, {7, -2, 4}, {8, -2, 4},
                    {-1, -2, 5}, {-2, -2, 5}, {7, -2, 5}, {8, -2, 5},
                    {-1, -2, 6}, {-2, -2, 6}, {7, -2, 6}, {8, -2, 6},
                    {-1, -2, 7}, {-2, -2, 7}, {7, -2, 7}, {8, -2, 7},
                    {-1, -2, 8},

                    // {0, -1, -1}, {0, -1, -2} = Negative X
                    // {0, -1, 7}, {0, -1, 8} = Positive X
                    {0, -2, -1}, {0, -2, -2}, {0, -2, 7}, {0, -2, 8},
                    {1, -2, -1}, {1, -2, -2}, {1, -2, 7}, {1, -2, 8},
                    {2, -2, -1}, {2, -2, -2}, {2, -2, 7}, {2, -2, 8},
                    {3, -2, -1}, {3, -2, -2}, {3, -2, 7}, {3, -2, 8},
                    {4, -2, -1}, {4, -2, -2}, {4, -2, 7}, {4, -2, 8},
                    {5, -2, -1}, {5, -2, -2}, {5, -2, 7}, {5, -2, 8},
                    {6, -2, -1}, {6, -2, -2}, {6, -2, 7}, {6, -2, 8},
                    {7, -2, -1}, {7, -2, -2}, {7, -2, 7}, {7, -2, 8},
                    {8, -2, -1},

                    // Corner
                    {-2, -1, -2}, {-3, -1, -2}, {-2, -1, -3},
                    {8, -1, -2}, {9, -1, -2}, {8, -1, -3},
                    {-2, -1, 8}, {-3, -1, 8}, {-2, -1, 9},
                    {8, -1, 8}, {8, -1, 9}, {9, -1, 8},

                    // Sides
                    //// Front
                    {-1, -1, -3}, {0, -1, -3}, {1, -1, -3}, {2, -1, -3}, {3, -1, -3}, {4, -1, -3}, {5, -1, -3}, {6, -1, -3}, {7, -1, -3},
                    //// Right
                    {-3, -1, -1}, {-3, -1, 0}, {-3, -1, 1}, {-3, -1, 2}, {-3, -1, 3}, {-3, -1, 4}, {-3, -1, 5}, {-3, -1, 6}, {-3, -1, 7},
                    //// Left
                    {9, -1, -1}, {9, -1, 0}, {9, -1, 1}, {9, -1, 2}, {9, -1, 3}, {9, -1, 4}, {9, -1, 5}, {9, -1, 6}, {9, -1, 7},
                    //// Back
                    {-1, -1, 9}, {0, -1, 9}, {1, -1, 9}, {2, -1, 9}, {3, -1, 9}, {4, -1, 9}, {5, -1, 9}, {6, -1, 9}, {7, -1, 9},
            };

    private final int[][] stoneFencePos = new int[][]
            {
                    // Corner
                    {-2, 0, -2}, {-3, 0, -2}, {-2, 0, -3},
                    {8, 0, -2}, {9, 0, -2}, {8, 0, -3},
                    {-2, 0, 8}, {-3, 0, 8}, {-2, 0, 9},
                    {8, 0, 8}, {8, 0, 9}, {9, 0, 8},

                    // Sides
                    //// Front
                    {-1, 0, -3}, {0, 0, -3}, {1, 0, -3}, {2, 0, -3}, {3, 0, -3}, {4, 0, -3}, {5, 0, -3}, {6, 0, -3}, {7, 0, -3},
                    //// Right
                    {-3, 0, -1}, {-3, 0, 0}, {-3, 0, 1}, {-3, 0, 2}, {-3, 0, 3}, {-3, 0, 4}, {-3, 0, 5}, {-3, 0, 6}, {-3, 0, 7},
                    //// Left
                    {9, 0, -1}, {9, 0, 0}, {9, 0, 1}, {9, 0, 2}, {9, 0, 3}, {9, 0, 4}, {9, 0, 5}, {9, 0, 6}, {9, 0, 7},
                    //// Back
                    {-1, 0, 9}, {0, 0, 9}, {1, 0, 9}, {2, 0, 9}, {3, 0, 9}, {4, 0, 9}, {5, 0, 9}, {6, 0, 9}, {7, 0, 9},
            };


    @Override
    public boolean generate(World worldIn, Random rand, BlockPos corner) {
        if (canSpawnHere(worldIn, corner)) {
            // figure out each IBlockState we will use
            IBlockState stoneBrickTower = ModBlocks.whiteStoneBrickTower.getDefaultState();
            IBlockState stoneBrick = ModBlocks.whiteStoneBrick.getDefaultState();
            IBlockState doorLower = Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER);
            IBlockState doorUpper = Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER);
            IBlockState chest = Blocks.CHEST.getDefaultState();
            IBlockState glowstone = Blocks.GLOWSTONE.getDefaultState();
            IBlockState lava = Blocks.LAVA.getDefaultState();
            IBlockState stone = Blocks.STONEBRICK.getDefaultState();
            IBlockState stoneFence = Blocks.COBBLESTONE_WALL.getDefaultState();

            // build the layers using the arrays
            placeLayer(worldIn, corner, stoneBrickPos, stoneBrick);
            placeLayer(worldIn, corner, lavaPos, lava);
            placeLayer(worldIn, corner, stonePos, stone);
            placeLayer(worldIn, corner, stoneFencePos, stoneFence);

            placeWall(worldIn, corner, stoneBrick, 6, 2, 0);
            placeWall(worldIn, corner, stoneBrick, 6, 2, 6);
            placeFloor(worldIn, corner, stone, 6, 6);

            // Corners
            placeBlock(worldIn, corner, 0, 4, 0, stoneBrick);
            placeBlock(worldIn, corner, 0, 4, 6, stoneBrick);
            placeBlock(worldIn, corner, 6, 4, 0, stoneBrick);
            placeBlock(worldIn, corner, 6, 4, 6, stoneBrick);
            // place the other features LAST
            placeBlock(worldIn, corner, doorBottomPos, doorLower);
            placeBlock(worldIn, corner, doorTopPos, doorUpper);
            placeBlock(worldIn, corner, glowstonePos, glowstone);

            placeBlock(worldIn, corner, chestPos, chest);
            TileEntity tileEntityOne = getTileEntity(worldIn, corner, chestPos);
            placeTileEntity(worldIn, corner, chestPos, tileEntityOne);

            if (tileEntityOne instanceof TileEntityChest)
                ((TileEntityChest) tileEntityOne).setLootTable(LootTableList.CHESTS_END_CITY_TREASURE, rand.nextLong());

            // debug:
            if (isDebugMode())
                LogHelper.info("Built a castle starting at " + corner + "!");
            return true;
        } else if (isDebugMode())
            LogHelper.info("Sorry, can't spawn a castle at " + corner);
        return false;
    }
}