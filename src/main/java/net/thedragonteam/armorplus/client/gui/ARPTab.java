/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 7/26/2016 4:42 PM.
 * - TheDragonTeam
 */
public class ARPTab extends CreativeTabs {
    static ItemStack iconArmorPlus;
    private String label;
    private int tab;

    public ARPTab(int id, String modid, String label, int tab) {
        super(id, modid);
        this.label = label;
        this.tab = tab;
        setBackgroundImageName(ArmorPlus.MODID + ".png"); // Automagically has tab_ applied to it. Make sure you change the texture name.

    }

    public static void initialize() {
        iconArmorPlus = new ItemStack(ModItems.LAVA_CHESTPLATE);
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
            case (3):
                return new ItemStack(ModItems.REDSTONE_BATTLE_AXE);
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