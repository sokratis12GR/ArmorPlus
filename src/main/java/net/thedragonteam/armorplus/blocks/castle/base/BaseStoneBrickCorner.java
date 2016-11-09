/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BaseBlock;
import net.thedragonteam.armorplus.blocks.castle.StoneBricks;

public class BaseStoneBrickCorner extends BaseBlock {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<BaseStoneBrickCorner.EnumHalf> HALF = PropertyEnum.create("half", BaseStoneBrickCorner.EnumHalf.class);
    public static final PropertyEnum<BaseStoneBrickCorner.EnumShape> SHAPE = PropertyEnum.create("shape", BaseStoneBrickCorner.EnumShape.class);

    public MapColor mapColor;

    public BaseStoneBrickCorner(StoneBricks stoneBricks) {
        super(Material.ROCK, stoneBricks.getName() + "_stone_brick_corner", 10.0F, 5.0F, "pickaxe", 0, 255);
        this.mapColor = stoneBricks.getMapColor();
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, BaseStoneBrickCorner.EnumHalf.BOTTOM).withProperty(SHAPE, BaseStoneBrickCorner.EnumShape.STRAIGHT));
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state) {
        return mapColor;
    }

    private static boolean isDifferentStairs(IBlockState blockState, IBlockAccess blockAccess, BlockPos blockPos, EnumFacing enumFacing) {
        IBlockState iblockstate = blockAccess.getBlockState(blockPos.offset(enumFacing));
        return !isBaseStoneBrickCorner(iblockstate) || iblockstate.getValue(FACING) != blockState.getValue(FACING) || iblockstate.getValue(HALF) != blockState.getValue(HALF);
    }

    public static boolean isBaseStoneBrickCorner(IBlockState state) {
        return state.getBlock() instanceof BaseStoneBrickCorner;
    }

    private static BaseStoneBrickCorner.EnumShape getStairsShape(IBlockState blockState, IBlockAccess blockAccess, BlockPos blockPos) {
        EnumFacing enumfacing = blockState.getValue(FACING);
        IBlockState iblockstate = blockAccess.getBlockState(blockPos.offset(enumfacing));

        if (isBaseStoneBrickCorner(iblockstate) && blockState.getValue(HALF) == iblockstate.getValue(HALF)) {
            EnumFacing enumfacing1 = iblockstate.getValue(FACING);

            if (enumfacing1.getAxis() != blockState.getValue(FACING).getAxis() && isDifferentStairs(blockState, blockAccess, blockPos, enumfacing1.getOpposite()))
                return enumfacing1 == enumfacing.rotateYCCW() ? EnumShape.OUTER_LEFT : EnumShape.OUTER_RIGHT;
        }

        IBlockState iblockstate1 = blockAccess.getBlockState(blockPos.offset(enumfacing.getOpposite()));

        if (isBaseStoneBrickCorner(iblockstate1) && blockState.getValue(HALF) == iblockstate1.getValue(HALF)) {
            EnumFacing enumfacing2 = iblockstate1.getValue(FACING);

            if (enumfacing2.getAxis() != blockState.getValue(FACING).getAxis() && isDifferentStairs(blockState, blockAccess, blockPos, enumfacing2))
                return enumfacing2 == enumfacing.rotateYCCW() ? EnumShape.INNER_LEFT : EnumShape.INNER_RIGHT;
        }

        return BaseStoneBrickCorner.EnumShape.STRAIGHT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if (state.getValue(HALF) == BaseStoneBrickCorner.EnumHalf.TOP) i |= 4;

        i = i | 5 - state.getValue(FACING).getIndex();
        return i;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(SHAPE, getStairsShape(state, worldIn, pos));
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        EnumFacing enumfacing = state.getValue(FACING);
        BaseStoneBrickCorner.EnumShape blockstairs$enumshape = state.getValue(SHAPE);

        switch (mirrorIn) {
            case LEFT_RIGHT:

                if (enumfacing.getAxis() == EnumFacing.Axis.Z) {
                    switch (blockstairs$enumshape) {
                        case OUTER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BaseStoneBrickCorner.EnumShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BaseStoneBrickCorner.EnumShape.OUTER_LEFT);
                        case INNER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BaseStoneBrickCorner.EnumShape.INNER_LEFT);
                        case INNER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BaseStoneBrickCorner.EnumShape.INNER_RIGHT);
                        default:
                            return state.withRotation(Rotation.CLOCKWISE_180);
                    }
                }

                break;
            case FRONT_BACK:

                if (enumfacing.getAxis() == EnumFacing.Axis.X) {
                    switch (blockstairs$enumshape) {
                        case OUTER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BaseStoneBrickCorner.EnumShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BaseStoneBrickCorner.EnumShape.OUTER_LEFT);
                        case INNER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BaseStoneBrickCorner.EnumShape.INNER_RIGHT);
                        case INNER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BaseStoneBrickCorner.EnumShape.INNER_LEFT);
                        case STRAIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180);
                    }
                }
        }

        return super.withMirror(state, mirrorIn);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, HALF, SHAPE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState().withProperty(HALF, (meta & 4) > 0 ? BaseStoneBrickCorner.EnumHalf.TOP : BaseStoneBrickCorner.EnumHalf.BOTTOM);
        iblockstate = iblockstate.withProperty(FACING, EnumFacing.getFront(5 - (meta & 3)));
        return iblockstate;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack) {
        IBlockState iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer, stack);
        iblockstate = iblockstate.withProperty(FACING, placer.getHorizontalFacing()).withProperty(SHAPE, BaseStoneBrickCorner.EnumShape.STRAIGHT);
        return facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double) hitY <= 0.5D) ? iblockstate.withProperty(HALF, BaseStoneBrickCorner.EnumHalf.BOTTOM) : iblockstate.withProperty(HALF, BaseStoneBrickCorner.EnumHalf.TOP);

    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        if (ForgeModContainer.disableStairSlabCulling) return super.doesSideBlockRendering(state, world, pos, face);
        else if (state.isOpaqueCube())
            return true;

        state = this.getActualState(state, world, pos);

        BaseStoneBrickCorner.EnumHalf half = state.getValue(HALF);
        EnumFacing side = state.getValue(FACING);
        BaseStoneBrickCorner.EnumShape shape = state.getValue(SHAPE);
        switch (face) {
            case UP:
                return half == EnumHalf.TOP;
            case DOWN:
                return half == EnumHalf.BOTTOM;
        }
        return (shape == EnumShape.OUTER_LEFT || shape == EnumShape.OUTER_RIGHT || face == side || shape == EnumShape.INNER_LEFT && face.rotateY() == side || shape == EnumShape.STRAIGHT) || shape == EnumShape.INNER_RIGHT && face.rotateYCCW() == side;
    }

    public enum EnumHalf implements IStringSerializable {
        TOP("top"),
        BOTTOM("bottom");

        private final String name;

        EnumHalf(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }
    }

    public enum EnumShape implements IStringSerializable {
        STRAIGHT("straight"),
        INNER_LEFT("inner_left"),
        INNER_RIGHT("inner_right"),
        OUTER_LEFT("outer_left"),
        OUTER_RIGHT("outer_right");

        private final String name;

        EnumShape(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }
    }
}
