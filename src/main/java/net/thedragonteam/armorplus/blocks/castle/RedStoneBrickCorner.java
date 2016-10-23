/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.castle;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.castle.base.BaseCastleBlock;

public class RedStoneBrickCorner extends BaseCastleBlock {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<RedStoneBrickCorner.EnumHalf> HALF = PropertyEnum.<RedStoneBrickCorner.EnumHalf>create("half", RedStoneBrickCorner.EnumHalf.class);
    public static final PropertyEnum<RedStoneBrickCorner.EnumShape> SHAPE = PropertyEnum.<RedStoneBrickCorner.EnumShape>create("shape", RedStoneBrickCorner.EnumShape.class);

    public RedStoneBrickCorner() {
        super("red_stone_brick_corner");
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, RedStoneBrickCorner.EnumHalf.BOTTOM).withProperty(SHAPE, RedStoneBrickCorner.EnumShape.STRAIGHT));
        this.setLightOpacity(255);
        setRegistryName("red_stone_brick_corner");
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state) {
        return MapColor.RED;
    }

    private static boolean isDifferentStairs(IBlockState p_185704_0_, IBlockAccess p_185704_1_, BlockPos p_185704_2_, EnumFacing p_185704_3_) {
        IBlockState iblockstate = p_185704_1_.getBlockState(p_185704_2_.offset(p_185704_3_));
        return !isRedStoneBrickCorner(iblockstate) || iblockstate.getValue(FACING) != p_185704_0_.getValue(FACING) || iblockstate.getValue(HALF) != p_185704_0_.getValue(HALF);
    }

    public static boolean isRedStoneBrickCorner(IBlockState state) {
        return state.getBlock() instanceof RedStoneBrickCorner;
    }

    private static RedStoneBrickCorner.EnumShape getStairsShape(IBlockState p_185706_0_, IBlockAccess p_185706_1_, BlockPos p_185706_2_) {
        EnumFacing enumfacing = (EnumFacing) p_185706_0_.getValue(FACING);
        IBlockState iblockstate = p_185706_1_.getBlockState(p_185706_2_.offset(enumfacing));

        if (isRedStoneBrickCorner(iblockstate) && p_185706_0_.getValue(HALF) == iblockstate.getValue(HALF)) {
            EnumFacing enumfacing1 = (EnumFacing) iblockstate.getValue(FACING);

            if (enumfacing1.getAxis() != ((EnumFacing) p_185706_0_.getValue(FACING)).getAxis() && isDifferentStairs(p_185706_0_, p_185706_1_, p_185706_2_, enumfacing1.getOpposite())) {
                if (enumfacing1 == enumfacing.rotateYCCW()) {
                    return RedStoneBrickCorner.EnumShape.OUTER_LEFT;
                }

                return RedStoneBrickCorner.EnumShape.OUTER_RIGHT;
            }
        }

        IBlockState iblockstate1 = p_185706_1_.getBlockState(p_185706_2_.offset(enumfacing.getOpposite()));

        if (isRedStoneBrickCorner(iblockstate1) && p_185706_0_.getValue(HALF) == iblockstate1.getValue(HALF)) {
            EnumFacing enumfacing2 = (EnumFacing) iblockstate1.getValue(FACING);

            if (enumfacing2.getAxis() != ((EnumFacing) p_185706_0_.getValue(FACING)).getAxis() && isDifferentStairs(p_185706_0_, p_185706_1_, p_185706_2_, enumfacing2)) {
                if (enumfacing2 == enumfacing.rotateYCCW()) {
                    return RedStoneBrickCorner.EnumShape.INNER_LEFT;
                }

                return RedStoneBrickCorner.EnumShape.INNER_RIGHT;
            }
        }

        return RedStoneBrickCorner.EnumShape.STRAIGHT;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
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
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if (state.getValue(HALF) == RedStoneBrickCorner.EnumHalf.TOP) {
            i |= 4;
        }

        i = i | 5 - ((EnumFacing) state.getValue(FACING)).getIndex();
        return i;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(SHAPE, getStairsShape(state, worldIn, pos));
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
        RedStoneBrickCorner.EnumShape blockstairs$enumshape = (RedStoneBrickCorner.EnumShape) state.getValue(SHAPE);

        switch (mirrorIn) {
            case LEFT_RIGHT:

                if (enumfacing.getAxis() == EnumFacing.Axis.Z) {
                    switch (blockstairs$enumshape) {
                        case OUTER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, RedStoneBrickCorner.EnumShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, RedStoneBrickCorner.EnumShape.OUTER_LEFT);
                        case INNER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, RedStoneBrickCorner.EnumShape.INNER_LEFT);
                        case INNER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, RedStoneBrickCorner.EnumShape.INNER_RIGHT);
                        default:
                            return state.withRotation(Rotation.CLOCKWISE_180);
                    }
                }

                break;
            case FRONT_BACK:

                if (enumfacing.getAxis() == EnumFacing.Axis.X) {
                    switch (blockstairs$enumshape) {
                        case OUTER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, RedStoneBrickCorner.EnumShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, RedStoneBrickCorner.EnumShape.OUTER_LEFT);
                        case INNER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, RedStoneBrickCorner.EnumShape.INNER_RIGHT);
                        case INNER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, RedStoneBrickCorner.EnumShape.INNER_LEFT);
                        case STRAIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180);
                    }
                }
        }

        return super.withMirror(state, mirrorIn);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING, HALF, SHAPE});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState().withProperty(HALF, (meta & 4) > 0 ? RedStoneBrickCorner.EnumHalf.TOP : RedStoneBrickCorner.EnumHalf.BOTTOM);
        iblockstate = iblockstate.withProperty(FACING, EnumFacing.getFront(5 - (meta & 3)));
        return iblockstate;
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        IBlockState iblockstate = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
        iblockstate = iblockstate.withProperty(FACING, placer.getHorizontalFacing()).withProperty(SHAPE, RedStoneBrickCorner.EnumShape.STRAIGHT);
        return facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double) hitY <= 0.5D) ? iblockstate.withProperty(HALF, RedStoneBrickCorner.EnumHalf.BOTTOM) : iblockstate.withProperty(HALF, RedStoneBrickCorner.EnumHalf.TOP);

    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        if (net.minecraftforge.common.ForgeModContainer.disableStairSlabCulling)
            return super.doesSideBlockRendering(state, world, pos, face);

        if (state.isOpaqueCube())
            return true;

        state = this.getActualState(state, world, pos);

        RedStoneBrickCorner.EnumHalf half = state.getValue(HALF);
        EnumFacing side = state.getValue(FACING);
        RedStoneBrickCorner.EnumShape shape = state.getValue(SHAPE);
        if (face == EnumFacing.UP) return half == RedStoneBrickCorner.EnumHalf.TOP;
        if (face == EnumFacing.DOWN) return half == RedStoneBrickCorner.EnumHalf.BOTTOM;
        if (shape == RedStoneBrickCorner.EnumShape.OUTER_LEFT || shape == RedStoneBrickCorner.EnumShape.OUTER_RIGHT)
            return false;
        if (face == side) return true;
        if (shape == RedStoneBrickCorner.EnumShape.INNER_LEFT && face.rotateY() == side) return true;
        if (shape == RedStoneBrickCorner.EnumShape.INNER_RIGHT && face.rotateYCCW() == side) return true;
        return false;
    }

    public static enum EnumHalf implements IStringSerializable {
        TOP("top"),
        BOTTOM("bottom");

        private final String name;

        private EnumHalf(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }
    }

    public static enum EnumShape implements IStringSerializable {
        STRAIGHT("straight"),
        INNER_LEFT("inner_left"),
        INNER_RIGHT("inner_right"),
        OUTER_LEFT("outer_left"),
        OUTER_RIGHT("outer_right");

        private final String name;

        private EnumShape(String name) {
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
