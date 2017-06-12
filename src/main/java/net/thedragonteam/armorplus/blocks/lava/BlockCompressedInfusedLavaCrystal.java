package net.thedragonteam.armorplus.blocks.lava;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.base.ToolType;
import net.thedragonteam.armorplus.iface.IModelHelper;

public class BlockCompressedInfusedLavaCrystal extends BlockBase implements IModelHelper {

    public BlockCompressedInfusedLavaCrystal() {
        super(Material.IRON, "block_compressed_infused_lava_crystal", 1000, 5.0F, ToolType.PICKAXE, 3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(this, getRegistryName(), 0, "normal");
    }
}
