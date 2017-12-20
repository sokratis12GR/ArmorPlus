package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.blocks.castle.StoneBricks;
import net.thedragonteam.armorplus.iface.IModdedBlock;

import static net.thedragonteam.armorplus.util.Utils.setName;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BlockStoneBrickCorner extends BlockStairs implements IModdedBlock {

    private StoneBricks stoneBricks;

    public BlockStoneBrickCorner(StoneBricks stoneBricks, IBlockState modelState) {
        super(modelState);
        this.setUnlocalizedName(setName(stoneBricks.getName() + "_stone_brick_corner"));
        this.setRegistryName(stoneBricks.getName() + "_stone_brick_corner");
        this.setHardness(10.0f);
        this.setResistance(5.0f);
        this.setHarvestLevel("pickaxe", 0);
        this.setLightOpacity(255);
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
        this.stoneBricks = stoneBricks;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        this.initModel("stone_bricks", 0);
    }


    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }


    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return this.stoneBricks.getMapColor();
    }
}
