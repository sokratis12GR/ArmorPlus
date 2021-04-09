package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import static net.minecraft.block.AbstractBlock.Properties.copy;
import static net.minecraft.util.math.shapes.IBooleanFunction.OR;

public class StoneBrickTowerBlock extends Block {

    protected static final VoxelShape BASE = box(0.0D, 0.0D, 0.0D, 16D, 13D, 16D);
    protected static final VoxelShape C_A = box(0D, 13D, 0D, 3D, 16D, 3D);
    protected static final VoxelShape C_B = box(13D, 13D, 0D, 16D, 16D, 3D);
    protected static final VoxelShape C_C = box(0D, 13D, 13D, 3D, 16D, 16D);
    protected static final VoxelShape C_D = box(13D, 13D, 13D, 16D, 16D, 16D);
    protected static final VoxelShape V_A = VoxelShapes.join(C_A, C_B, OR);
    protected static final VoxelShape V_B = VoxelShapes.join(C_C, C_D, OR);
    protected static final VoxelShape CORNERS = VoxelShapes.join(V_A, V_B, OR);
    protected static final VoxelShape VOXEL = VoxelShapes.join(BASE, CORNERS, OR);

    public StoneBrickTowerBlock(Block block) {
        super(copy(block));
    }

    //@Override
    //public BlockRenderLayer getRenderLayer() {
    //    return BlockRenderLayer.SOLID;
    //}

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