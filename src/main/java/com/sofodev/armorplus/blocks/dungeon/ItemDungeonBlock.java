/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.dungeon;

import com.sofodev.armorplus.blocks.base.BlockBase;
import com.sofodev.thedragonlib.util.LogHelper;
import net.minecraft.item.ItemBlock;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ItemDungeonBlock extends ItemBlock {

    public ItemDungeonBlock(BlockBase block) {
        super(block, new Properties());
        if (block.getRegistryName() == null) {
            LogHelper.getLogger(MODID).error("The registry name for " + block + " doesn't exist");
            return;
        }
        this.setRegistryName(block.getRegistryName());
    }

}
