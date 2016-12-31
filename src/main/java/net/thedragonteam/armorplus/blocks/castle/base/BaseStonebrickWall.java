/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class BaseStonebrickWall extends BlockWall {

    public BaseStonebrickWall(Block modelBlock) {
        super(modelBlock);
        this.setRegistryName("stone_brick_wall");
        this.setUnlocalizedName(setName("stone_brick_wall"));
    }
}
