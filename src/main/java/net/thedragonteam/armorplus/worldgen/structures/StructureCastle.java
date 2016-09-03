package net.thedragonteam.armorplus.worldgen.structures;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.core.util.LogHelper;

import java.util.Random;

import static net.thedragonteam.armorplus.ARPConfig.debugMode;

public class StructureCastle extends WorldGenerator {
    /**
     * BLUEPRINTS
     **/
    // Format: int[][] { {distanceRight, distanceUp, distanceBack} }
    // Format: int[][] { {PosX, PosY, PosZ} }
    private final int[][] stoneBrickTowerPos = new int[][]
            {
                    // tower corners
                    {0, 4, 0}, {6, 4, 0},
                    {0, 4, 6}, {6, 4, 6}
            };
    private final int[][] stoneBrickPos = new int[][]
            {
                    // floor (7 x 7)
                    {0, -1, 0}, {1, -1, 0}, {2, -1, 0}, {3, -1, 0}, {4, -1, 0}, {5, -1, 0}, {6, -1, 0},
                    {0, -1, 1}, {1, -1, 1}, {2, -1, 1}, {3, -1, 1}, {4, -1, 1}, {5, -1, 1}, {6, -1, 1},
                    {0, -1, 2}, {1, -1, 2}, {2, -1, 2}, {3, -1, 2}, {4, -1, 2}, {5, -1, 2}, {6, -1, 2},
                    {0, -1, 3}, {1, -1, 3}, {2, -1, 3}, {3, -1, 3}, {4, -1, 3}, {5, -1, 3}, {6, -1, 3},
                    {0, -1, 4}, {1, -1, 4}, {2, -1, 4}, {3, -1, 4}, {4, -1, 4}, {5, -1, 4}, {6, -1, 4},
                    {0, -1, 5}, {1, -1, 5}, {2, -1, 5}, {3, -1, 5}, {4, -1, 5}, {5, -1, 5}, {6, -1, 5},
                    {0, -1, 6}, {1, -1, 6}, {2, -1, 6}, {3, -1, 6}, {4, -1, 6}, {5, -1, 6}, {6, -1, 6},

                    // upper trim (first part of roof)
                    {0, 3, 0}, {1, 3, 0}, {2, 3, 0}, {3, 3, 0}, {4, 3, 0}, {5, 3, 0}, {6, 3, 0},    // front
                    {0, 3, 6}, {1, 3, 6}, {2, 3, 6}, {3, 3, 6}, {4, 3, 6}, {5, 3, 6}, {6, 3, 6},     // back
                    {0, 3, 1}, {0, 3, 2}, {0, 3, 3}, {0, 3, 4}, {0, 3, 5},    // left
                    {6, 3, 1}, {6, 3, 2}, {6, 3, 3}, {6, 3, 4}, {6, 3, 5},    // right

                    // walls
                    //// front
                    {0, 0, 0}, {1, 0, 0}, {2, 0, 0}, {3, 0, 0}, {4, 0, 0}, {5, 0, 0}, {6, 0, 0},
                    {0, 1, 0}, {1, 1, 0}, {2, 1, 0}, {3, 1, 0}, {4, 1, 0}, {5, 1, 0}, {6, 1, 0},
                    {0, 2, 0}, {1, 2, 0}, {2, 2, 0}, {3, 2, 0}, {4, 2, 0}, {5, 2, 0}, {6, 2, 0},
                    //// right
                    {6, 0, 1}, {6, 0, 2}, {6, 0, 3}, {6, 0, 4}, {6, 0, 5}, {6, 0, 6},
                    {6, 1, 1}, {6, 1, 2}, {6, 1, 3}, {6, 1, 4}, {6, 1, 5}, {6, 1, 6},
                    {6, 2, 1}, {6, 2, 2}, {6, 2, 3}, {6, 2, 4}, {6, 2, 5}, {6, 2, 6},
                    //// back
                    {1, 0, 6}, {2, 0, 6}, {3, 0, 6}, {4, 0, 6}, {5, 0, 6}, {6, 0, 6},
                    {1, 1, 6}, {2, 1, 6}, {3, 1, 6}, {4, 1, 6}, {5, 1, 6}, {6, 1, 6},
                    {1, 2, 6}, {2, 2, 6}, {3, 2, 6}, {4, 2, 6}, {5, 2, 6}, {6, 2, 6},
                    //// left
                    {0, 0, 1}, {0, 0, 2}, {0, 0, 3}, {0, 0, 4}, {0, 0, 5}, {0, 0, 6},
                    {0, 1, 1}, {0, 1, 2}, {0, 1, 3}, {0, 1, 4}, {0, 1, 5}, {0, 1, 6},
                    {0, 2, 1}, {0, 2, 2}, {0, 2, 3}, {0, 2, 4}, {0, 2, 5}, {0, 2, 6}
            };
    private final int[] doorBottomPos = new int[]{3, 0, 0};
    private final int[] doorTopPos = new int[]{3, 1, 0};
    private final int[] chestPos = new int[]{1, 0, 3};
    private final int[] glowstonePos = new int[]{3, -1, 3};

    private final int[][] lavaPos = new int[][]
            {
                    // floor lava (9 x 9) without (7 x 7)

                    // Corner
                    {-1, -1, -1}, {-1, -1, -2}, {-2, -1, -1}, {-2, -1, -2},

                    // {-1, -1, X}, {-2, -1, X} = Negative X
                    // {7, -1, X}, {8, -1, X} = Positive X
                    {-1, -1, 0}, {-2, -1, 0}, {7, -1, 0}, {8, -1, 0},
                    {-1, -1, 1}, {-2, -1, 1}, {7, -1, 1}, {8, -1, 1},
                    {-1, -1, 2}, {-2, -1, 2}, {7, -1, 2}, {8, -1, 2},
                    {-1, -1, 3}, {-2, -1, 3}, {7, -1, 3}, {8, -1, 3},
                    {-1, -1, 4}, {-2, -1, 4}, {7, -1, 4}, {8, -1, 4},
                    {-1, -1, 5}, {-2, -1, 5}, {7, -1, 5}, {8, -1, 5},
                    {-1, -1, 6}, {-2, -1, 6}, {7, -1, 6}, {8, -1, 6},
                    {-1, -1, 7}, {-2, -1, 7}, {7, -1, 7}, {8, -1, 7},
                    {-1, -1, 8}, {-2, -1, 8}, {7, -1, 8}, {8, -1, 8},

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
                    {8, -1, -1}, {8, -1, -2}, {8, -1, 7}, {8, -1, 8},
            };

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos corner) {
        if (canSpawnHere(worldIn, corner)) {
            // figure out each IBlockState we will use
            IBlockState stoneBrickTower = ModBlocks.WHITE_STONE_BRICK_TOWER.getDefaultState();
            IBlockState stoneBrick = ModBlocks.WHITE_STONE_BRICK.getDefaultState();
            IBlockState doorLower = Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER);
            IBlockState doorUpper = Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER);
            IBlockState chest = Blocks.CHEST.getDefaultState();
            IBlockState glowstone = Blocks.GLOWSTONE.getDefaultState();
            IBlockState lava = Blocks.LAVA.getDefaultState();

            // build the layers using the arrays
            buildLayer(worldIn, corner, stoneBrickTowerPos, stoneBrickTower);
            buildLayer(worldIn, corner, stoneBrickPos, stoneBrick);
            buildLayer(worldIn, corner, lavaPos, lava);

            // place the other features LAST
            placeBlock(worldIn, corner, doorBottomPos, doorLower);
            placeBlock(worldIn, corner, doorTopPos, doorUpper);
            placeBlock(worldIn, corner, glowstonePos, glowstone);

            // I saved the chest for last
            placeBlock(worldIn, corner, chestPos, chest);

            // debug:
            if (debugMode) {
                LogHelper.info("Built a castle starting at " + corner + "!");
            }
            return true;
        } else if (debugMode) {
            LogHelper.info("Sorry, can't spawn a castle at " + corner);
        }
        return false;
    }

    // use an int[][] to place a lot of one block at once
    private void buildLayer(World world, BlockPos frontLeftCorner, int[][] blockPositions, IBlockState toPlace) {
        // iterate through the entire int[][]
        for (int[] coord : blockPositions) {
            placeBlock(world, frontLeftCorner, coord[0], coord[1], coord[2], toPlace);
        }
    }

    /**
     * Helper Method
     **/
    private void placeBlock(World world, BlockPos frontLeftCorner, int[] offsets, IBlockState toPlace) {
        placeBlock(world, frontLeftCorner, offsets[0], offsets[1], offsets[2], toPlace);
    }

    /**
     * Places a block using corner position and offsets
     **/
    private void placeBlock(World world, BlockPos frontLeftCorner, int offsetX, int offsetY, int offsetZ, IBlockState toPlace) {
        // figure out where that block is relative to the corner
        BlockPos placePos = frontLeftCorner.add(offsetX, offsetY, offsetZ);
        world.setBlockState(placePos, toPlace, 2);
    }

    private boolean canSpawnHere(World world, BlockPos posAboveGround) {
        // check all the corners to see which ones are replaceable
        boolean corner1Air = canReplace(world, posAboveGround);
        boolean corner2Air = canReplace(world, posAboveGround.add(4, 0, 0));
        boolean corner4Air = canReplace(world, posAboveGround.add(0, 0, 4));
        boolean corner3Air = canReplace(world, posAboveGround.add(4, 0, 4));

        // if Y > 20 and all corners pass the test, it's okay to spawn the structure
        return posAboveGround.getY() > 20 && corner1Air && corner2Air && corner3Air && corner4Air;
    }

    private boolean canReplace(World world, BlockPos pos) {
        Block at = world.getBlockState(pos).getBlock();
        Material material = at.getMaterial(at.getDefaultState());
        // we think it's replaceable if it's air / liquid / snow, plants, or leaves
        return material.isReplaceable() || material == Material.WOOD || material == Material.LEAVES;
    }
}