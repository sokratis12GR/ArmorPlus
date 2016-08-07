package net.thedragonteam.armorplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedragonteam.armorplus.ArmorPlus;

/**
 * Created by Socrates on 4/19/2016.
 */
public class CompressedObsidian extends Block {

    public CompressedObsidian() {
        super(Material.ROCK);
        setUnlocalizedName("CompressedObsidian");
        this.setResistance(2000.0F);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_BLOCKS);
        this.setHardness(50.0F);
        this.setHarvestLevel("pickaxe", 3);
    }
}
