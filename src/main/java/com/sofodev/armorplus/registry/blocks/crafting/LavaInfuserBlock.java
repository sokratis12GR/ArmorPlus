package com.sofodev.armorplus.registry.blocks.crafting;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import static net.minecraft.util.math.shapes.IBooleanFunction.OR;
import static net.minecraftforge.common.ToolType.PICKAXE;

public class LavaInfuserBlock extends AbstractInfuserBlock {

    protected static final VoxelShape BOTTOM_P1 = VoxelShapes.join(Block.box(2, 0, 2, 14, 1, 14),
            Block.box(1, 1, 1, 15, 2, 15), OR);
    protected static final VoxelShape BOTTOM_P2 = VoxelShapes.join(Block.box(2, 2, 2, 14, 4, 14),
            Block.box(1, 4, 1, 15, 5, 15), OR);
    protected static final VoxelShape BOTTOM_P3 = VoxelShapes.join(BOTTOM_P1, BOTTOM_P2, OR);
    protected static final VoxelShape BOTTOM = VoxelShapes.join(BOTTOM_P3,
            Block.box(3, 5, 3, 13, 6, 13), OR);
    //Middle
    protected static final VoxelShape MIDDLE_P1 = VoxelShapes.join(Block.box(4, 6, 4, 6, 13, 6),
            Block.box(4, 6, 12, 6, 13, 10), OR);
    protected static final VoxelShape MIDDLE_P2 = VoxelShapes.join(Block.box(12, 6, 4, 10, 13, 6),
            Block.box(12, 6, 4, 12, 13, 6), OR);
    protected static final VoxelShape MIDDLE = VoxelShapes.join(MIDDLE_P1, MIDDLE_P2, OR);
    protected static final VoxelShape BOT_MIDDLE = VoxelShapes.join(BOTTOM, MIDDLE, OR);
    protected static final VoxelShape TOP_P1 = VoxelShapes.join(Block.box(3, 13, 3, 13, 14, 13),
            Block.box(6, 14, 6, 10, 15, 10), OR);
    protected static final VoxelShape TOP_P2 = VoxelShapes.join(TOP_P1,
            Block.box(7, 15, 7, 9, 15.5, 9), OR);
    protected static final VoxelShape TOP = VoxelShapes.join(TOP_P1, TOP_P2, OR);
    protected static final VoxelShape FULL = VoxelShapes.join(BOT_MIDDLE, TOP, OR);


    public LavaInfuserBlock() {
        super(Properties.of(Material.STONE).harvestTool(PICKAXE).strength(2.5F, 10000.0F)
                .dynamicShape().harvestLevel(0));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return FULL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return FULL;
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader worldIn) {
        return new LavaInfuserTile();
    }

    /**
     * Interface for handling interaction with blocks that impliment AbstractFurnaceBlock. Called in onBlockActivated
     * inside AbstractFurnaceBlock.
     */
    @Override
    protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
        TileEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof LavaInfuserTile) {
            player.openMenu((INamedContainerProvider) tileentity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.getValue(LIT)) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY();
            double d2 = (double) pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D) {
                worldIn.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = stateIn.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : d4;
            double d6 = rand.nextDouble() * 6.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : d4;
            worldIn.addParticle(ParticleTypes.LARGE_SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            worldIn.addParticle(ParticleTypes.DRIPPING_LAVA, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }
}