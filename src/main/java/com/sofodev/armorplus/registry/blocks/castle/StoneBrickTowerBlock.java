package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import static net.minecraft.block.Block.Properties.from;
import static net.minecraft.util.math.shapes.IBooleanFunction.OR;

public class StoneBrickTowerBlock extends Block {

    protected static final VoxelShape BASE = makeCuboidShape(0.0D, 0.0D, 0.0D, 16D, 13D, 16D);
    protected static final VoxelShape C_A = makeCuboidShape(0D, 13D, 0D, 3D, 16D, 3D);
    protected static final VoxelShape C_B = makeCuboidShape(13D, 13D, 0D, 16D, 16D, 3D);
    protected static final VoxelShape C_C = makeCuboidShape(0D, 13D, 13D, 3D, 16D, 16D);
    protected static final VoxelShape C_D = makeCuboidShape(13D, 13D, 13D, 16D, 16D, 16D);
    protected static final VoxelShape V_A = VoxelShapes.combineAndSimplify(C_A, C_B, OR);
    protected static final VoxelShape V_B = VoxelShapes.combineAndSimplify(C_C, C_D, OR);
    protected static final VoxelShape CORNERS = VoxelShapes.combineAndSimplify(V_A, V_B, OR);
    protected static final VoxelShape VOXEL = VoxelShapes.combineAndSimplify(BASE, CORNERS, OR);

    public StoneBrickTowerBlock(Block block) {
        super(from(block));
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VOXEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VOXEL;
    }

}