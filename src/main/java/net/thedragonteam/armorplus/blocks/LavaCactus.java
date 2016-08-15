package net.thedragonteam.armorplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import java.util.Random;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 8/15/2016.
 * - TheDragonTeam
 */
public class LavaCactus extends Block implements IPlantable {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
    public static final AxisAlignedBB LAVA_CACTUS_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
    public static final AxisAlignedBB LAVA_CACTUS_COLLISION_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);

    public LavaCactus() {
        super(Material.CACTUS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
        this.setTickRandomly(true);
        setUnlocalizedName("lava_cactus");
        this.setResistance(5.0F);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_BLOCKS);
        this.setHardness(1.0F);
        this.setHarvestLevel("axe", 0);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        BlockPos blockpos = pos.up();

        if (worldIn.isAirBlock(blockpos)) {
            int i;

            for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i) {
                ;
            }

            if (i < 3) {
                int j = ((Integer) state.getValue(AGE)).intValue();

                if (j == 15) {
                    worldIn.setBlockState(blockpos, this.getDefaultState());
                    IBlockState iblockstate = state.withProperty(AGE, Integer.valueOf(0));
                    worldIn.setBlockState(pos, iblockstate, 4);
                    iblockstate.neighborChanged(worldIn, blockpos, this);
                } else {
                    worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(j + 1)), 4);
                }
            }
        }
    }

    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return LAVA_CACTUS_AABB;
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        return LAVA_CACTUS_COLLISION_AABB.offset(pos);
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) ? this.canBlockStay(worldIn, pos) : false;
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
        if (!this.canBlockStay(worldIn, pos)) {
            worldIn.destroyBlock(pos, false);
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos) {
        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
            Material material = worldIn.getBlockState(pos.offset(enumfacing)).getMaterial();

            if (material.isSolid() || material == Material.LAVA) {
                return true;
            }
        }

        IBlockState state = worldIn.getBlockState(pos.down());
        return state.getBlock().canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, this) && !worldIn.getBlockState(pos.up()).getMaterial().isLiquid();
    }

    /**
     * Called When an Entity Collided with the Block
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.attackEntityFrom(DamageSource.cactus, 1.0F);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return ((Integer) state.getValue(AGE)).intValue();
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Nether;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return getDefaultState();
    }

    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{AGE});
    }
}
