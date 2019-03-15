/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.v2;

import com.sofodev.armorplus.blocks.BlockProperties;
import com.sofodev.armorplus.blocks.base.BlockBase;
import net.minecraft.block.material.Material;

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.blocks;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockMetal extends BlockBase {

    public BlockMetal(Metals metals) {
        super(Material.IRON, new BlockProperties(20.0f, 5.0f, blocks.block_metal));
    }
}

