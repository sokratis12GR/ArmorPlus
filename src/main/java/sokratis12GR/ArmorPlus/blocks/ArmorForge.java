package sokratis12GR.ArmorPlus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.registry.ModItems;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * sokratis12GR.ArmorPlus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:46 PM.
 */
public class ArmorForge extends Block {

    public ArmorForge() {
        super(Material.IRON);
        setUnlocalizedName("ArmorForge");
        this.setResistance(1000.0F);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_BLOCKS);
        this.setHardness(10.0F);
        this.setHarvestLevel("pickaxe", 2);
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
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
}
