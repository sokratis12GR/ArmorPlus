/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.lava;

import com.sofodev.armorplus.blocks.BlockProperties;
import com.sofodev.armorplus.blocks.base.BlockBase;
import net.minecraft.block.material.Material;

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.blocks;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockLavaNetherBrick extends BlockBase {

    public BlockLavaNetherBrick() {
        super(Material.ROCK, new BlockProperties(20.0f, 3.0f, blocks.lava_nether_brick, 1.0f));
    }

}