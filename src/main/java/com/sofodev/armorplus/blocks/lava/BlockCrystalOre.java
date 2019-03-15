/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.lava;

import com.sofodev.armorplus.blocks.BlockProperties;
import com.sofodev.armorplus.blocks.base.BlockBase;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.blocks;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockCrystalOre extends BlockBase {

    public BlockCrystalOre() {
        super(Material.ROCK, new BlockProperties(2000.0F, 25.0F, blocks.ore_lava_crystal, 0.8F));
    }

    @Override
    public void getDrops(IBlockState state, NonNullList<ItemStack> drops, World world, BlockPos pos, int fortune) {
        Item item = Utils.getItem("lava_crystal").asItem();
        Random rand;
        rand = world != null ? world.rand : RANDOM;
        int count = getItemsToDropCount(state, fortune, world, pos, rand);
        IntStream.range(0, count).mapToObj(i -> new ItemStack(item)).forEachOrdered(drops::add);
    }

    @Override
    public int getItemsToDropCount(IBlockState state, int fortune, World world, BlockPos pos, Random random) {
        return 1 + random.nextInt(1 + fortune);
    }
}
