/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks.normal;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.blocks.base.BaseBlock;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.Random;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:46 PM.
 * - TheDragonTeam
 */
public class BlockLavaCrystal extends BaseBlock {

    private ItemStack drop = new ItemStack(ModItems.lavaCrystal, 1, 1);

    public BlockLavaCrystal() {
        super(Material.ROCK, "block_lava_crystal", 2000.0F, 25.0F, "pickaxe", 3, 0.8F);
    }

    public ItemStack getDrop() {
        return drop;
    }

    @Override
    public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
        return this.drop.getItem();
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state) {
        return MapColor.BLACK;
    }
}
