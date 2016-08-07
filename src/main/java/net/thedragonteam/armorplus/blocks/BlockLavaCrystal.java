package net.thedragonteam.armorplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.Random;

/**
 * sokratis12gr.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:46 PM.
 */
public class BlockLavaCrystal extends Block {

    private Item drop;

    public BlockLavaCrystal() {
        super(Material.ROCK);
        this.drop = ModItems.LAVA_CRYSTAL;
        setUnlocalizedName("BlockLavaCrystal");
        this.setResistance(2000.0F);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_BLOCKS);
        this.setHardness(25.0F);
        this.setHarvestLevel("pickaxe", 3);
    }

    @Override
    public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
        return this.drop;
    }
}
