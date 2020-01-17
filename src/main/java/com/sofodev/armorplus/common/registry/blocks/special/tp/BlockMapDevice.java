package com.sofodev.armorplus.common.registry.blocks.special.tp;

import com.sofodev.armorplus.common.iface.IModdedBlock;
import com.sofodev.armorplus.common.registry.blocks.BlockProperties;
import com.sofodev.armorplus.common.registry.blocks.HarvestProps;
import com.sofodev.armorplus.common.registry.blocks.base.BlockBase;
import com.sofodev.armorplus.common.registry.blocks.base.ToolType;
import com.sofodev.armorplus.common.registry.items.ItemCombinedMap;
import com.sofodev.armorplus.common.tileentity.TileEntityMapDevice;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockMapDevice extends BlockBase implements IModdedBlock {

    public BlockMapDevice() {
        super(Material.PORTAL, "map_device", new BlockProperties(1000.0f, 10f, new HarvestProps(ToolType.PICKAXE, 2)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(0);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Item item = playerIn.getHeldItemMainhand().getItem();
        if (item instanceof ItemCombinedMap) {
            ItemCombinedMap map = (ItemCombinedMap) item;
            Block aboveOne = worldIn.getBlockState(pos.up()).getBlock();
            Block aboveTwo = worldIn.getBlockState(pos.up().up()).getBlock();
            if (aboveOne == Blocks.AIR && aboveTwo == Blocks.AIR) switch (map.variant) {
                case MAP_OF_OVERWORLD:
                    // worldIn.setBlockState(pos.up(), Blocks.END_PORTAL)
                    break;
                case MAP_OF_THE_NETHER:
                    worldIn.setBlockState(pos.up(), Blocks.PORTAL.getDefaultState());
                    break;
                case MAP_OF_THE_END:
                    worldIn.setBlockState(pos.up(), Blocks.END_PORTAL.getDefaultState());
                    break;
            }
        }
        //   if (worldIn.isRemote) {
        //       playerIn.openGui(ArmorPlus.instance, GUI_MAP_DEVICE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        //   }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
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
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityMapDevice();
    }
}
