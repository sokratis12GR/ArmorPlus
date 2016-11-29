/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.thedragonteam.armorplus.util.StructureUtils.Direction.NEGATIVE_XZ;
import static net.thedragonteam.armorplus.util.StructureUtils.Direction.POSITIVE_XZ;

public class StructureUtils {

    public static void buildWall(World worldIn, BlockPos corner, IBlockState block, int maxPos, int maxPosY, int posIn, StructureUtils.Direction direction) {
        int pos;
        int posY;
        switch (direction) {
            case POSITION_X:
                for (posY = 0; posY <= maxPosY; posY++)
                    for (pos = 0; pos <= maxPos; pos++) {
                        int[] stoneBrick = new int[]{
                                pos, posY, posIn
                        };
                        placeBlock(worldIn, corner, stoneBrick, block);
                    }
                break;
            case POSITION_Z:
                for (posY = 0; posY <= maxPosY; posY++)
                    for (pos = 0; pos <= maxPos; pos++) {
                        int[] stoneBrick = new int[]{
                                posIn, posY, pos
                        };
                        placeBlock(worldIn, corner, stoneBrick, block);
                    }
                break;
        }
    }

    /**
     * Builds a floor at y level -1 with starting points x = 0 and z = 0
     */
    public static void buildFloor(World worldIn, BlockPos corner, IBlockState block, int maxPosX, int maxPosZ) {
        buildFloor(worldIn, corner, block, 0, 0, maxPosX, maxPosZ);
    }

    /**
     * Builds a floor at y level -1
     */
    public static void buildFloor(World worldIn, BlockPos corner, IBlockState block, int posX, int posZ, int maxPosX, int maxPosZ) {
        buildFloor(worldIn, corner, block, posX, -1, posZ, maxPosX, maxPosZ);
    }

    public static void buildFloor(World worldIn, BlockPos corner, IBlockState block, int posX, int posY, int posZ, int maxPosX, int maxPosZ) {
        buildFloor(worldIn, corner, block, posX, posY, posZ, maxPosX, maxPosZ, true);
    }

    public static void buildFloor(World worldIn, BlockPos corner, IBlockState block, int posX, int posY, int posZ, int maxPosX, int maxPosZ, boolean isPositive) {
        if (isPositive) {
            buildFloor(worldIn, corner, block, posX, posY, posZ, maxPosX, maxPosZ, POSITIVE_XZ);
        } else {
            buildFloor(worldIn, corner, block, posX, posY, posZ, maxPosX, maxPosZ, NEGATIVE_XZ);
        }
    }

    /**
     * Generates a floor in the world
     *
     * @param worldIn   the world that the structure is in
     * @param corner    the starting point or "corner" of the structure
     * @param block     the block that will be filling the positions
     * @param posX      the x starting point
     * @param posY      the y level
     * @param posZ      the z starting point
     * @param maxPosX   the end position of x
     * @param maxPosZ   the end position of Z
     * @param direction the direction that the floor will be generating
     */
    public static void buildFloor(World worldIn, BlockPos corner, IBlockState block, int posX, int posY, int posZ, int maxPosX, int maxPosZ, StructureUtils.Direction direction) {
        switch (direction) {
            case POSITIVE_XZ:
                for (int z = posZ; z <= maxPosZ; z++)
                    for (int x = posX; x <= maxPosX; x++) {
                        int[] stoneBrickFloor = new int[]{
                                x, posY, z
                        };
                        placeBlock(worldIn, corner, stoneBrickFloor, block);
                    }
                break;
            case NEGATIVE_XZ:
                for (int z = posZ; z <= maxPosZ; z--)
                    for (int x = posX; x <= maxPosX; x--) {
                        int[] stoneBrickFloor = new int[]{
                                x, posY, z
                        };
                        placeBlock(worldIn, corner, stoneBrickFloor, block);
                    }
                break;
            case POSITIVE_Z_NEGATIVE_X:
                for (int z = posZ; z <= maxPosZ; z++)
                    for (int x = posX; x <= maxPosX; x--) {
                        int[] stoneBrickFloor = new int[]{
                                x, posY, z
                        };
                        placeBlock(worldIn, corner, stoneBrickFloor, block);
                    }
                break;
            case POSITIVE_X_NEGATIVE_Z:
                for (int z = posZ; z <= maxPosZ; z--)
                    for (int x = posX; x <= maxPosX; x++) {
                        int[] stoneBrickFloor = new int[]{
                                x, posY, z
                        };
                        placeBlock(worldIn, corner, stoneBrickFloor, block);
                    }
                break;
        }
    }

    // use an int[][] to place a lot of one block at once
    public static void buildLayer(World world, BlockPos frontLeftCorner, int[][] blockPositions, IBlockState toPlace) {
        // iterate through the entire int[][]
        for (int[] coord : blockPositions) {
            placeBlock(world, frontLeftCorner, coord[0], coord[1], coord[2], toPlace);
        }
    }

    /**
     * Helper Methods
     **/
    public static void placeTileEntity(World world, BlockPos frontLeftCorner, int[] offsets, TileEntity toPlace) {
        placeTileEntity(world, frontLeftCorner, offsets[0], offsets[1], offsets[2], toPlace);
    }

    public static void placeTileEntity(World world, BlockPos frontLeftCorner, int offsetX, int offsetY, int offsetZ, TileEntity toPlace) {
        // figure out where that block is relative to the corner
        BlockPos placePos = frontLeftCorner.add(offsetX, offsetY, offsetZ);
        world.setTileEntity(placePos, toPlace);
    }

    public static TileEntity getTileEntity(World world, BlockPos frontLeftCorner, int[] offsets) {
        return getTileEntity(world, frontLeftCorner, offsets[0], offsets[1], offsets[2]);
    }

    public static TileEntity getTileEntity(World world, BlockPos frontLeftCorner, int offsetX, int offsetY, int offsetZ) {
        // figure out where that block is relative to the corner
        BlockPos placePos = frontLeftCorner.add(offsetX, offsetY, offsetZ);
        return world.getTileEntity(placePos);
    }


    public static void placeBlock(World world, BlockPos frontLeftCorner, int[] offsets, IBlockState toPlace) {
        placeBlock(world, frontLeftCorner, offsets[0], offsets[1], offsets[2], toPlace);
    }

    /**
     * Places a block using corner position and offsets
     **/
    public static void placeBlock(World world, BlockPos frontLeftCorner, int offsetX, int offsetY, int offsetZ, IBlockState toPlace) {
        // figure out where that block is relative to the corner
        BlockPos placePos = frontLeftCorner.add(offsetX, offsetY, offsetZ);
        world.setBlockState(placePos, toPlace, 2);
    }

    public static boolean canSpawnHere(World world, BlockPos posAboveGround) {
        // check all the corners to see which ones are replaceable
        boolean corner1Air = canReplace(world, posAboveGround);
        boolean corner2Air = canReplace(world, posAboveGround.add(4, 0, 0));
        boolean corner4Air = canReplace(world, posAboveGround.add(0, 0, 4));
        boolean corner3Air = canReplace(world, posAboveGround.add(4, 0, 4));

        // if Y > 20 and all corners pass the test, it's okay to spawn the structure
        return posAboveGround.getY() > 20 && corner1Air && corner2Air && corner3Air && corner4Air;
    }

    public static boolean canReplace(World world, BlockPos pos) {
        Block at = world.getBlockState(pos).getBlock();
        Material material = at.getMaterial(at.getDefaultState());
        // we think it's replaceable if it's air / liquid / snow, plants, or leaves
        return material.isReplaceable() || material == Material.WOOD || material == Material.LEAVES;
    }

    public enum Direction {
        POSITIVE_XZ,
        NEGATIVE_XZ,
        POSITIVE_X_NEGATIVE_Z,
        POSITIVE_Z_NEGATIVE_X,
        POSITION_X,
        POSITION_Z;
    }
}
