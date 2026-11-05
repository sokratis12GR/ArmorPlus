package com.sofodev.armorplus.registry.block.special;

import com.mojang.serialization.MapCodec;
import com.sofodev.armorplus.registry.RegistryContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
    private static final VoxelShape SHAPE = Shapes.or(Block.column(14.0, 0.0, 3.0), Block.column(8.0, 3.0, 11.0), Block.column(12.0, 11.0, 14.0));

    public EmpoweringPedestalBlock() {
        super(RegistryContext.blockProperties(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).noOcclusion()));
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
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.isEmpty() || !(level.getBlockEntity(pos) instanceof EmpoweringPedestalBlockEntity pedestal)) return InteractionResult.TRY_WITH_EMPTY_HAND;
        if (!pedestal.getItem().isEmpty()) return InteractionResult.TRY_WITH_EMPTY_HAND;
        if (!level.isClientSide()) {
            pedestal.setItem(stack.copyWithCount(1));
            if (!player.hasInfiniteMaterials()) stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
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
