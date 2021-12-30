package com.sofodev.armorplus.registry.blocks;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class APBlockItem extends BlockItem {

    public APBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    public APBlockItem(Block blockIn) {
        super(blockIn, new Properties().tab(ArmorPlus.AP_BLOCK_GROUP));
    }
}