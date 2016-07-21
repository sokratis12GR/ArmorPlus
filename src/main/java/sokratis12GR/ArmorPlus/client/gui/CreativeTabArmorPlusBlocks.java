package sokratis12gr.armorplus.client.gui;

//Creates your creative tab.

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.registry.ModBlocks;

public class CreativeTabArmorPlusBlocks extends CreativeTabs {

    public CreativeTabArmorPlusBlocks(String tabLabel) {
        super(tabLabel);
        setBackgroundImageName(ArmorPlus.MODID + "(NEW)" + ".png"); // Automagically has tab_ applied to it. Make sure you change the texture name.
    }

    public boolean hasSearchBar() {
        return true;
    }

    //The tab icon is what you return here.
    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(ModBlocks.BLOCK_LAVA_CRYSTAL);
    }

    @Override
    public Item getTabIconItem() {
        return new Item();
    }
}