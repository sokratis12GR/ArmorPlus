package com.sofodev.armorplus.common.registry.items;

import com.sofodev.armorplus.common.iface.IModdedItem;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.sofodev.armorplus.common.util.Utils.setRL;

public class ItemBlockMissingTexture extends ItemBlock implements IModdedItem {

    public ItemBlockMissingTexture(Block block) {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(setRL("missing"), 0);
    }
}
