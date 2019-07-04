/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.dungeon;

import com.sofodev.armorplus.common.registry.blocks.base.BlockBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.thedragonteam.thedragonlib.util.LogHelper;

public class ItemDungeonBlock extends ItemBlock {

    public ItemDungeonBlock(BlockBase block) {
        super(block);
        if (block.getRegistryName() == null) {
            LogHelper.error("The registry name for " + block + " doesn't exist");
            return;
        }
        this.setRegistryName(block.getRegistryName());
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return null;
    }
}
