package com.sofodev.armorplus.registry.blocks.castle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import static net.minecraft.block.Block.Properties.from;
import static net.minecraft.util.math.shapes.IBooleanFunction.OR;
import static net.minecraft.util.math.shapes.VoxelShapes.combineAndSimplify;

public class StoneBrickCornerBlock extends Block implements IWaterLoggable {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape NW_C = combineAndSimplify(makeCuboidShape(0, 8, 0, 8, 16, 8), makeCuboidShape(0, 0, 0, 16, 8, 16), OR);
    public static final VoxelShape NE_C = combineAndSimplify(makeCuboidShape(8, 8, 0, 16, 16, 8), makeCuboidShape(0, 0, 0, 16, 8, 16), OR);
    public static final VoxelShape SW_C = combineAndSimplify(makeCuboidShape(0, 8, 8, 8, 16, 16), makeCuboidShape(0, 0, 0, 16, 8, 16), OR);
    public static final VoxelShape SE_C = combineAndSimplify(makeCuboidShape(8, 8, 8, 16, 16, 16), makeCuboidShape(0, 0, 0, 16, 8, 16), OR);

    public StoneBrickCornerBlock(Block block) {
        super(from(block));
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.FALSE));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SW_C;
            case EAST:
                return NW_C;
            case WEST:
                return SE_C;
            case SOUTH:
                return NE_C;
        }
        return NW_C;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        IFluidState fluidstate = context.getWorld().getFluidState(blockpos);
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return facing.getAxis().isHorizontal() ? stateIn : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return super.mirror(state, mirrorIn);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @SuppressWarnings("deprecation")
    @Override
    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

}