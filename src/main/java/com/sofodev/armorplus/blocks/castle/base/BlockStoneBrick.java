/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.castle.base;

import com.sofodev.armorplus.blocks.BlockProperties;
import com.sofodev.armorplus.blocks.base.BlockBase;
import com.sofodev.armorplus.blocks.castle.BrickColor;
import net.minecraft.block.material.Material;

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.blocks;


/**
 * @author Sokratis Fotkatzikis
 */
public class BlockStoneBrick extends BlockBase {

    public String name;

    public BlockStoneBrick(BrickColor brickColor) {
        super(Material.ROCK, new BlockProperties(10.0f, 5.0f, blocks.stone_bricks));
        this.name = brickColor.getName() + "_stone_brick";
    }
}
