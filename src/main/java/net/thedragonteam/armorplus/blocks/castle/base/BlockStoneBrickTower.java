package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.base.ToolType;
import net.thedragonteam.armorplus.blocks.castle.StoneBricks;
import net.thedragonteam.armorplus.iface.IModdedBlock;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BlockStoneBrickTower extends BlockBase implements IModdedBlock {

    private StoneBricks stoneBricks;

    public BlockStoneBrickTower(StoneBricks stoneBricks) {
        super(Material.ROCK, stoneBricks.getName() + "_stone_brick_tower", 10.0f, 5.0f, ToolType.PICKAXE, 0);
        this.stoneBricks = stoneBricks;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        this.initModel("stone_bricks", 0);
    }

    @SuppressWarnings("deprecation")

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return this.stoneBricks.getMapColor();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
