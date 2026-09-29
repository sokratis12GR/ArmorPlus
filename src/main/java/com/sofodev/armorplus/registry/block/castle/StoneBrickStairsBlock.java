package com.sofodev.armorplus.registry.block.castle;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class StoneBrickStairsBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    public static final EnumProperty<StairsShape> SHAPE = BlockStateProperties.STAIRS_SHAPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape AABB_SLAB_TOP = StoneBrickSlabBlock.TOP_SHAPE;
    protected static final VoxelShape AABB_SLAB_BOTTOM = StoneBrickSlabBlock.BOTTOM_SHAPE;
    protected static final VoxelShape NWD_CORNER = Block.box(0, 0, 0, 8, 8, 8);
    protected static final VoxelShape SWD_CORNER = Block.box(0, 0, 8, 8, 8, 16);
    protected static final VoxelShape NWU_CORNER = Block.box(0, 8, 0, 8, 16, 8);
    protected static final VoxelShape SWU_CORNER = Block.box(0, 8, 8, 8, 16, 16);
    protected static final VoxelShape NED_CORNER = Block.box(8, 0, 0, 16, 8, 8);
    protected static final VoxelShape SED_CORNER = Block.box(8, 0, 8, 16, 8, 16);
    protected static final VoxelShape NEU_CORNER = Block.box(8, 8, 0, 16, 16, 8);
    protected static final VoxelShape SEU_CORNER = Block.box(8, 8, 8, 16, 16, 16);
    protected static final VoxelShape[] SLAB_TOP_SHAPES = makeShapes(AABB_SLAB_TOP, NWD_CORNER, NED_CORNER, SWD_CORNER, SED_CORNER);
    protected static final VoxelShape[] SLAB_BOTTOM_SHAPES = makeShapes(AABB_SLAB_BOTTOM, NWU_CORNER, NEU_CORNER, SWU_CORNER, SEU_CORNER);
    private static final int[] PALETTE_SHAPE_MAP = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};
    private final Block modelBlock;
    private final BlockState modelState;
    private final Supplier<BlockState> stateSupplier;

    public StoneBrickStairsBlock(Supplier<BlockState> state, Block block) {
        super(com.sofodev.armorplus.registry.RegistryContext.blockProperties(ofFullCopy(block).requiresCorrectToolForDrops()));
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM).setValue(SHAPE, StairsShape.STRAIGHT).setValue(WATERLOGGED, false));
        modelBlock = Blocks.AIR;
        modelState = Blocks.AIR.defaultBlockState();
        stateSupplier = state;
    }

    private static VoxelShape[] makeShapes(VoxelShape slabShape, VoxelShape nwCorner, VoxelShape neCorner, VoxelShape swCorner, VoxelShape seCorner) {
        return IntStream.range(0, 16).mapToObj(bits -> combineShapes(bits, slabShape, nwCorner, neCorner, swCorner, seCorner)).toArray(VoxelShape[]::new);
    }

    private static VoxelShape combineShapes(int bitfield, VoxelShape slabShape, VoxelShape nwCorner, VoxelShape neCorner, VoxelShape swCorner, VoxelShape seCorner) {
        VoxelShape shape = slabShape;
        if ((bitfield & 1) != 0) shape = Shapes.or(shape, nwCorner);
        if ((bitfield & 2) != 0) shape = Shapes.or(shape, neCorner);
        if ((bitfield & 4) != 0) shape = Shapes.or(shape, swCorner);
        if ((bitfield & 8) != 0) shape = Shapes.or(shape, seCorner);
        return shape;
    }

    private static StairsShape getShapeProperty(BlockState state, BlockGetter level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockState front = level.getBlockState(pos.relative(direction));
        if (isBlockStairs(front) && state.getValue(HALF) == front.getValue(HALF)) {
            Direction other = front.getValue(FACING);
            if (other.getAxis() != direction.getAxis() && isDifferentStairs(state, level, pos, other.getOpposite())) {
                return other == direction.getCounterClockWise() ? StairsShape.OUTER_LEFT : StairsShape.OUTER_RIGHT;
            }
        }

        BlockState back = level.getBlockState(pos.relative(direction.getOpposite()));
        if (isBlockStairs(back) && state.getValue(HALF) == back.getValue(HALF)) {
            Direction other = back.getValue(FACING);
            if (other.getAxis() != direction.getAxis() && isDifferentStairs(state, level, pos, other)) {
                return other == direction.getCounterClockWise() ? StairsShape.INNER_LEFT : StairsShape.INNER_RIGHT;
            }
        }

        return StairsShape.STRAIGHT;
    }

    private static boolean isDifferentStairs(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        BlockState other = level.getBlockState(pos.relative(face));
        return !isBlockStairs(other) || other.getValue(FACING) != state.getValue(FACING) || other.getValue(HALF) != state.getValue(HALF);
    }

    public static boolean isBlockStairs(BlockState state) {
        return state.getBlock() instanceof StoneBrickStairsBlock;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (state.getValue(HALF) == Half.TOP ? SLAB_TOP_SHAPES : SLAB_BOTTOM_SHAPES)[PALETTE_SHAPE_MAP[getPaletteId(state)]];
    }

    private int getPaletteId(BlockState state) {
        return state.getValue(SHAPE).ordinal() * 4 + state.getValue(FACING).get2DDataValue();
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        modelState.attack(level, pos, player);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        modelBlock.destroy(level, pos, state);
    }

    @Override
    public float getExplosionResistance() {
        return modelBlock.getExplosionResistance();
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        modelBlock.stepOn(level, pos, state, entity);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockPos pos = context.getClickedPos();
        FluidState fluid = context.getLevel().getFluidState(pos);
        BlockState state = defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection())
                .setValue(HALF, direction != Direction.DOWN && (direction == Direction.UP || context.getClickLocation().y - pos.getY() <= 0.5D) ? Half.BOTTOM : Half.TOP)
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
        return state.setValue(SHAPE, getShapeProperty(state, context.getLevel(), pos));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        Direction direction = state.getValue(FACING);
        StairsShape shape = state.getValue(SHAPE);

        switch (mirror) {
            case LEFT_RIGHT:
                if (direction.getAxis() == Direction.Axis.Z) {
                    return switch (shape) {
                        case INNER_LEFT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
                        case INNER_RIGHT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
                        case OUTER_LEFT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
                        default -> state.rotate(Rotation.CLOCKWISE_180);
                    };
                }
                break;
            case FRONT_BACK:
                if (direction.getAxis() == Direction.Axis.X) {
                    return switch (shape) {
                        case INNER_LEFT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
                        case INNER_RIGHT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
                        case OUTER_LEFT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
                        case STRAIGHT -> state.rotate(Rotation.CLOCKWISE_180);
                    };
                }
                break;
        }

        return super.mirror(state, mirror);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, SHAPE, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    private Block getModelBlock() {
        return getModelState().getBlock();
    }

    private BlockState getModelState() {
        return stateSupplier.get();
    }
}
