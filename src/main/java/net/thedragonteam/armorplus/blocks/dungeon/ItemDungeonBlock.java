package net.thedragonteam.armorplus.blocks.dungeon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.thedragonteam.armorplus.blocks.base.BlockBase;

import java.util.Objects;

public class ItemDungeonBlock extends ItemBlock {

    public ItemDungeonBlock(BlockBase block) {
        super(block);
        this.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return null;
    }
}
