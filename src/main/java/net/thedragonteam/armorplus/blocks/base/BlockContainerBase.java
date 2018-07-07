package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.iface.IModdedBlock;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BlockContainerBase extends Block implements IModdedBlock {
    private final TileEntity tileEntity;

    public BlockContainerBase(Material materialIn, TileEntity tileEntity, String name, double resistance, double hardness, ToolType toolType, int harvestLevel) {
        super(materialIn);
        this.tileEntity = tileEntity;
        this.setRegistryName(setRL(name));
        this.setUnlocalizedName(setName(name));
        this.setResistance((float) resistance);
        this.setHardness((float) hardness);
        this.setHarvestLevel(toolType.getTool(), harvestLevel);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return tileEntity;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(0);
    }
}
