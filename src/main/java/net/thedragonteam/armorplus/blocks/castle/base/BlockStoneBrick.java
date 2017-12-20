package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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
public class BlockStoneBrick extends BlockBase implements IModdedBlock {

    private MapColor color;

    private String name;

    public BlockStoneBrick(StoneBricks stoneBricks) {
        super(Material.ROCK, stoneBricks.getName() + "_stone_brick", 10.0f, 5.0f, ToolType.PICKAXE, 0);
        this.color = stoneBricks.getMapColor();
        this.name = stoneBricks.getName();
    }

    public String getName() {
        return this.name;
    }

    public MapColor getColor() {
        return color;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        this.initModel(0, "normal");
    }


    @SuppressWarnings("deprecation")
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return this.color;
    }
}
