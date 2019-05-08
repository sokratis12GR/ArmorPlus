/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.normal;

import com.sofodev.armorplus.common.registry.blocks.BlockProperties;
import com.sofodev.armorplus.common.registry.blocks.base.BlockBase;
import net.minecraft.block.material.Material;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.blocks;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockCompressedObsidian extends BlockBase {

    public BlockCompressedObsidian() {
        super(Material.ROCK, new BlockProperties(2000.0f, 50.0f, blocks.block_compressed_obsidian));
    }
}
