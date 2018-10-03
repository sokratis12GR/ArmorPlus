package net.thedragonteam.armorplus.blocks.lava;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.blocks.base.ToolType;
import net.thedragonteam.armorplus.client.gui.GuiHandler;
import net.thedragonteam.armorplus.iface.IModdedBlock;
import net.thedragonteam.armorplus.tileentity.TileLavaInfuser;
import net.thedragonteam.thedragonlib.util.ItemStackUtils;

import java.util.Random;

import static net.minecraft.util.EnumFacing.*;
import static net.thedragonteam.armorplus.ArmorPlus.tabArmorplusBlocks;
import static net.thedragonteam.armorplus.registry.ModBlocks.lavaInfuser;
import static net.thedragonteam.armorplus.registry.ModBlocks.lavaInfuserInfusing;
import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BlockLavaInfuser extends BlockContainer implements IModdedBlock {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    private static boolean keepInventory;
    private boolean isInfusing;

    public BlockLavaInfuser(String name, boolean isInfusing) {
        super(Material.ROCK);
        this.isInfusing = isInfusing;
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, NORTH));
        this.setRegistryName(setRL(name));
        this.setTranslationKey(setName(name));
        this.setResistance(10000.0F);
        this.setHardness(2.5F);
        this.setHarvestLevel(ToolType.PICKAXE.getTool(), 1);
        this.setCreativeTab(tabArmorplusBlocks);
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;

        if (active) {
            worldIn.setBlockState(pos, getNeededState(lavaInfuserInfusing, iblockstate), 3);
            worldIn.setBlockState(pos, getNeededState(lavaInfuserInfusing, iblockstate), 3);
        } else {
            worldIn.setBlockState(pos, getNeededState(lavaInfuser, iblockstate), 3);
            worldIn.setBlockState(pos, getNeededState(lavaInfuser, iblockstate), 3);
        }

        keepInventory = false;

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    public static IBlockState getNeededState(Block block, IBlockState other) {
        return block.getDefaultState().withProperty(FACING, other.getValue(FACING));
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemStackUtils.getItem(lavaInfuser);
    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        if (world != null && state != null && pos != null) {
            this.setDefaultFacing(world, pos, state);
        }
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);

            if (enumfacing == NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
                enumfacing = SOUTH;
            } else if (enumfacing == SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
                enumfacing = NORTH;
            } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
                enumfacing = EAST;
            } else if (enumfacing == EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (this.isInfusing) {
            EnumFacing enumfacing = stateIn.getValue(FACING);
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = (double) pos.getZ() + 0.5D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            if (rand.nextDouble() < 0.1D) {
                worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
            this.spawnParticles(worldIn, d0, d1, d2, d4, enumfacing);
        }
    }

    private void spawnParticles(World worldIn, double d0, double d1, double d2, double d4, EnumFacing facing) {
        double xSpeed = facing == WEST ? -0.5 : facing == EAST ? 0.5 : facing == NORTH || facing == SOUTH ? 0.0 : 0.0;
        double zSpeed = facing == WEST || facing == EAST ? 0.0 : facing == NORTH ? -0.5 : facing == SOUTH ? 0.5 : 0.0;
        worldIn.spawnParticle(EnumParticleTypes.LAVA, d0, d1 + 1, d2 + d4, xSpeed, 0.5, zSpeed);
        worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1 + 1, d2 + d4, xSpeed, 0.5, zSpeed);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1 + 1, d2 + d4, xSpeed, 0.5, zSpeed);
    }

    /**
     * Called when the block is right clicked by a player.
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(ArmorPlus.instance, GuiHandler.GUI_LAVA_INFUSER, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileLavaInfuser();
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileLavaInfuser) {
                ((TileLavaInfuser) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileLavaInfuser) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileLavaInfuser) tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return super.getPickBlock(state, target, world, pos, player);
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    @Override
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.byIndex(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        this.initModel(0);
    }
}