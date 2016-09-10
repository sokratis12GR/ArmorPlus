/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.Random;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:46 PM.
 * - TheDragonTeam
 */
public class BlockLavaCrystal extends Block {

    private ItemStack drop = new ItemStack(ModItems.lavaCrystal, 1, 1);

    public BlockLavaCrystal() {
        super(Material.ROCK);
        setUnlocalizedName(ArmorPlus.MODID + "." + "block_lava_crystal");
        this.setResistance(2000.0F);
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
        this.setHardness(25.0F);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(0.8F);
    }

    public ItemStack getDrop() {
        return drop;
    }

    @Override
    public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
        return this.drop.getItem();
    }
}
