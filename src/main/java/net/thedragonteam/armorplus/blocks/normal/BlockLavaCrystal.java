/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks.normal;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.blocks.base.BaseBlock;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:46 PM.
 * - TheDragonTeam
 */
public class BlockLavaCrystal extends BaseBlock {

    public BlockLavaCrystal() {
        super(Material.ROCK, "block_lava_crystal", 2000.0F, 25.0F, "pickaxe", 3, 0.8F);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        Item item = ModItems.lavaCrystal;
        Random rand = (world instanceof World) ? ((World) world).rand : RANDOM;
        int count = quantityDropped(state, fortune, rand);
        for (int i = 0; i < count; i++) {
            ret.add(new ItemStack(item, 1, damageDropped(state)));
        }
        return ret;
    }

    @Override
    public int quantityDropped(IBlockState blockstate, int fortune, Random random) {
        return 1 + random.nextInt(1 + fortune);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Override
    public MapColor getMapColor(IBlockState state) {
        return MapColor.RED;
    }
}
