package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy;
import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class StoneBrickTowerBlock extends Block {

    protected static final VoxelShape BASE = box(0.0D, 0.0D, 0.0D, 16D, 13D, 16D);
    protected static final VoxelShape C_A = box(0D, 13D, 0D, 3D, 16D, 3D);
    protected static final VoxelShape C_B = box(13D, 13D, 0D, 16D, 16D, 3D);
    protected static final VoxelShape C_C = box(0D, 13D, 13D, 3D, 16D, 16D);
    protected static final VoxelShape C_D = box(13D, 13D, 13D, 16D, 16D, 16D);
    protected static final VoxelShape V_A = Shapes.join(C_A, C_B, OR);
    protected static final VoxelShape V_B = Shapes.join(C_C, C_D, OR);
    protected static final VoxelShape CORNERS = Shapes.join(V_A, V_B, OR);
    protected static final VoxelShape VOXEL = Shapes.join(BASE, CORNERS, OR);

    public StoneBrickTowerBlock(Block block) {
        super(copy(block));
    }

    //@Override
    //public BlockRenderLayer getRenderLayer() {
    //    return BlockRenderLayer.SOLID;
    //}

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return VOXEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return VOXEL;
    }

}