package com.sofodev.armorplus.common.registry.blocks.special.decorative;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.iface.IModdedBlock;
import com.sofodev.armorplus.common.registry.ModBlocks;
import com.sofodev.armorplus.common.registry.blocks.BlockProperties;
import com.sofodev.armorplus.common.registry.blocks.base.BlockBase;
import com.sofodev.armorplus.common.registry.items.base.special.BattleAxes;
import com.sofodev.armorplus.common.registry.items.base.special.Swords;
import com.sofodev.armorplus.common.tileentity.TileSwordDisplay;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.common.util.Utils.setRL;
import static net.minecraftforge.fml.common.registry.ForgeRegistries.ITEMS;

public class BlockSwordDisplay extends BlockBase implements IModdedBlock {

    public static final BlockProperties PROPERTIES = new BlockProperties(1000.0f, 10.0f);
    public Swords type;
    public ItemStack sword;
    protected static final AxisAlignedBB PEDESTAL_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

    public BlockSwordDisplay(Swords type) {
        super(Material.IRON, type.getName() + "_sword_display", PROPERTIES);
        this.setCreativeTab(ArmorPlus.tabArmorPlusBlocks);
        this.type = type;
    }

    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return PEDESTAL_AABB;
    }

    public ItemStack getSword() {
        return this.sword;
    }

    public BlockSwordDisplay setSword(ItemStack sword) {
        this.sword = sword;
        return this;
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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (player.isSneaking() && player.getActiveHand() == EnumHand.MAIN_HAND && player.getHeldItemMainhand().isEmpty()) {
            Item get = ITEMS.getValue(setRL(type.getName() + "_sword"));
            if (get != null) {
                if (this.sword == null || this.sword.isEmpty()) {
                    this.setSword(new ItemStack(get));
                }
                BlockEmptyDisplay empty = ModBlocks.blockEmptyDisplay;
                if (type != null && empty != null) {
                    if (world.isRemote) return true;
                    IBlockState blockState = empty.getDefaultState();
                    world.setBlockState(pos, blockState);
                    player.dropItem(getSword(), true);
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(setRL("empty_sword_display"), "", "", 0, "normal");
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        ItemStack itemstack = super.getPickBlock(state, target, world, pos, player);
        TileSwordDisplay tileTrophy = (TileSwordDisplay) world.getTileEntity(pos);
        NBTTagCompound nbttagcompound = tileTrophy.saveToNbt(new NBTTagCompound());
        if (!nbttagcompound.isEmpty()) {
            itemstack.setTagInfo("BlockEntityTag", nbttagcompound);
        }
        return itemstack;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        TileSwordDisplay te = new TileSwordDisplay();
        if (type != null) {
            te.setItem(setRL(type.getName() + "_sword"));
        }
        return te;
    }
}
