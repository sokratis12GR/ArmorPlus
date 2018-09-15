package net.thedragonteam.armorplus.blocks.dungeon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
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
