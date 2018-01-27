package net.thedragonteam.armorplus.blocks.special;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.base.ToolType;
import net.thedragonteam.armorplus.iface.IModdedBlock;
import net.thedragonteam.armorplus.tileentity.TileEntityTrophy;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.blocks.special.TrophyType.ANY;

public class BlockTrophy extends BlockBase implements IModdedBlock {

    private TrophyType type;

    public BlockTrophy(TrophyType type) {
        super(Material.CORAL, type == ANY ? "trophy" : type.getName() + "_trophy", 20.0f, 3.0f, ToolType.PICKAXE, 1);
        this.type = type;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        TileEntityTrophy trophy = new TileEntityTrophy();
        trophy.setEntityId(type.getEntityId());
        trophy.setEntityScale(type.getScale());
        return trophy;
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
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(0, "normal");
    }
}
