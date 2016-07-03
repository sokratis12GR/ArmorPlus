package sokratis12GR.ArmorPlus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import sokratis12GR.ArmorPlus.ArmorPlus;

/**
 * sokratis12GR.ArmorPlus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:46 PM.
 */
public class MetalOre extends Block {

    public MetalOre() {
        super(Material.ROCK);
        setUnlocalizedName("MetalOre");
        this.setResistance(20.0F);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_BLOCKS);
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe", 1);
    }
}
