package com.sofodev.armorplus.registry.block.special;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Four of these around a Rune Altar complete its ritual circle, see {@link com.sofodev.armorplus.registry.rune.RuneRitual}.
 * Using it with an item places one of it on an empty pedestal, using it again takes it back.
 */
public class EmpoweringPedestalBlock extends BaseEntityBlock {

    public static final MapCodec<EmpoweringPedestalBlock> CODEC = simpleCodec(properties -> new EmpoweringPedestalBlock());
    private static final VoxelShape SHAPE = Shapes.or(Block.box(1.0, 0.0, 1.0, 15.0, 3.0, 15.0), Block.box(4.0, 3.0, 4.0, 12.0, 11.0, 12.0), Block.box(2.0, 11.0, 2.0, 14.0, 14.0, 14.0));

    public EmpoweringPedestalBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).noOcclusion());
    }

    @Override
    public MapCodec<EmpoweringPedestalBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock()) && level.getBlockEntity(pos) instanceof EmpoweringPedestalBlockEntity pedestal) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), pedestal.getItem());
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.isEmpty() || !(level.getBlockEntity(pos) instanceof EmpoweringPedestalBlockEntity pedestal)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        if (!pedestal.getItem().isEmpty()) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        if (!level.isClientSide()) {
            pedestal.setItem(stack.copyWithCount(1));
            if (!player.hasInfiniteMaterials()) stack.shrink(1);
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof EmpoweringPedestalBlockEntity pedestal) || pedestal.getItem().isEmpty()) return InteractionResult.PASS;
        if (!level.isClientSide()) {
            player.getInventory().placeItemBackInInventory(pedestal.getItem());
            pedestal.setItem(ItemStack.EMPTY);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EmpoweringPedestalBlockEntity(pos, state);
    }
}
