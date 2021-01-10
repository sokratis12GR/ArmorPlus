package com.sofodev.armorplus.registry.blocks;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class APBlockItem extends BlockItem {

    public APBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    public APBlockItem(Block blockIn) {
        super(blockIn, new Properties().group(ArmorPlus.AP_BLOCK_GROUP));
    }
}