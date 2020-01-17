package com.sofodev.armorplus.common.registry.blocks.special.tp;

import com.sofodev.armorplus.common.registry.items.ItemCombinedMap;
import com.sofodev.armorplus.common.tileentity.TilePortal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static com.sofodev.armorplus.common.registry.items.ItemCombinedMap.Variants.MAP;
import static com.sofodev.armorplus.common.util.Utils.setRL;

public class BlockPortal extends BlockContainer {
    private final ItemCombinedMap.Variants variant;
    public int dimensionID;
    //protected static final AxisAlignedBB END_PORTAL_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

    public BlockPortal(ItemCombinedMap.Variants variant) {
        super(Material.PORTAL);
        this.variant = variant;
        this.dimensionID = variant.getType() != null ? variant.getType().getId() : 0;
        String name = "portal_to_" + variant.getName().replace("map_of_", "");
        this.setRegistryName(setRL(name));
        this.setTranslationKey(name);
        this.setHardness(-1.0F);
        this.setResistance(6000000.0F);
        this.setLightLevel(1.0F);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("USE THIS AT OWN RISK, UNSTABLE.. BEST TO IGNORE");
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    /**
     * @deprecated call via {@link IBlockState#shouldSideBeRendered(IBlockAccess, BlockPos, EnumFacing)} whenever
     * possible. Implementing/overriding is fine.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        return !iblockstate.isOpaqueCube() && block != Blocks.END_GATEWAY;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TilePortal) {
            int i = ((TilePortal) tileentity).getParticleAmount();

            for (int j = 0; j < i; ++j) {
                double xChunk = (float) pos.getX() + rand.nextFloat();
                double yChunk = (float) pos.getY() + rand.nextFloat();
                double zChunk = (float) pos.getZ() + rand.nextFloat();
                double xSpeed = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                double ySpeed = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                double zSpeed = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                int randMulti = rand.nextInt(2) * 2 - 1;

                if (rand.nextBoolean()) {
                    zChunk = (double) pos.getZ() + 0.5D + 0.25D * (double) randMulti;
                    zSpeed = rand.nextFloat() * 2.0F * (float) randMulti;
                } else {
                    xChunk = (double) pos.getX() + 0.5D + 0.25D * (double) randMulti;
                    xSpeed = rand.nextFloat() * 2.0F * (float) randMulti;
                }

                worldIn.spawnParticle(EnumParticleTypes.PORTAL, xChunk, yChunk, zChunk, xSpeed, ySpeed, zSpeed);
            }
        }
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.BLACK;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    //TODO: DOESN'T WORK ATM.. CRASHES ON DIMENSION CHANGE.
    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
     // if (variant.getType() == null || entity.dimension == dimensionID) return;
     // int overworldID = DimensionType.OVERWORLD.getId();
     // //requirements: Basic requirements in order for the entity to be able to teleport
     // boolean requirements = !entity.isRiding() && !entity.isBeingRidden() && entity.isNonBoss() && entity.getEntityBoundingBox().intersects(state.getBoundingBox(world, pos).offset(pos));
     // //#teleportFROMOverwolrd: You can only teleport to a given dimension from the overworld.
     // boolean teleportFROMOverworld = dimensionID != overworldID && entity.dimension == overworldID;
     // boolean teleportTOOverworld = dimensionID == overworldID && entity.dimension != overworldID || dimensionID != overworldID && entity.dimension == dimensionID;
     // //Teleport to dimension from the overworld
     // if (!world.isRemote && requirements) {
     //     if (teleportFROMOverworld) {
     //         //   if (entity.getServer() != null) {
     //         //      entity.getServer().commandManager.executeCommand(entity, "/aptp " + dimensionID);
     //         // }
     //         entity.changeDimension(dimensionID);
     //     }
     //     if (teleportTOOverworld) {
     //         //  if (entity.getServer() != null) {
     //         //      entity.getServer().commandManager.executeCommand(entity, "/aptp " + dimensionID);
     //         //  }
     //         entity.changeDimension(overworldID);
     //     }
     // }
    }

    // End Portal Version
    //  @Override
    //  @SideOnly(Side.CLIENT)
    //  public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    //      double xCord = (float) pos.getX() + rand.nextFloat();
    //      double yCord = (float) pos.getY() + 0.8F;
    //      double zCord = (float) pos.getZ() + rand.nextFloat();
    //      double xSpeed = 0.0D;
    //      double ySpeed = 0.0D;
    //      double zSpeed = 0.0D;
    //      worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, xCord, yCord, zCord, xSpeed, ySpeed, zSpeed);
    //  }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        ItemStack itemstack = super.getPickBlock(state, target, world, pos, player);
        TilePortal portal = (TilePortal) world.getTileEntity(pos);
        NBTTagCompound nbttagcompound = portal.saveToNbt(new NBTTagCompound());
        if (!nbttagcompound.isEmpty()) {
            itemstack.setTagInfo("DimID", nbttagcompound);
        }
        return itemstack;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        TilePortal portal = new TilePortal();
        if (variant == MAP || variant.getType() == null) {
            portal.setDimensionID(0);
        } else {
            portal.setDimensionID(dimensionID);
        }
        return portal;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        TilePortal portal = new TilePortal();
        if (variant == MAP || variant.getType() == null) {
            portal.setDimensionID(0);
        } else {
            portal.setDimensionID(dimensionID);
        }
        return portal;
    }

    //  @Override
    //  @SideOnly(Side.CLIENT)
    //  public void initModel() {
    //      this.initModel(setRL("ap_portal"));
    //  }
}