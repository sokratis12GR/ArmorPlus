package sokratis12gr.armorplus.client.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.registry.ModBlocks;
import sokratis12gr.armorplus.registry.ModItems;

/**
 * sokratis12gr.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 7/26/2016 4:42 PM.
 */
public class ARPTab extends CreativeTabs {
    private String label;
    private int tab;

    static ItemStack iconArmorPlus;

    public static void initialize() {
        iconArmorPlus = new ItemStack(ModItems.LAVA_CHESTPLATE);
    }

    public ARPTab(int id, String modid, String label, int tab) {
        super(id, modid);
        this.label = label;
        this.tab = tab;
        setBackgroundImageName(ArmorPlus.MODID + ".png"); // Automagically has tab_ applied to it. Make sure you change the texture name.

    }

    public boolean hasSearchBar() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        switch (tab) {
            case (0):
                return new ItemStack(ModItems.LAVA_CHESTPLATE);
            case (1):
                return new ItemStack(ModItems.ENDER_DRAGON_SCALE);
            case (2):
                return new ItemStack(ModBlocks.BLOCK_LAVA_CRYSTAL);
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return getIconItemStack().getItem();
    }

    @Override
    public String getTabLabel() {
        return this.label;
    }
}