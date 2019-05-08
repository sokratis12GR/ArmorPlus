/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.lava;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockMeltingObsidian extends BlockBreakable {

    public BlockMeltingObsidian() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(0.5f).lightValue(3).tickRandomly().sound(SoundType.STONE));
    }
}