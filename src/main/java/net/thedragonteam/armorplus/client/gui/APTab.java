/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.APItems;
import net.thedragonteam.armorplus.registry.ModBlocks;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 7/26/2016 4:42 PM.
 * - TheDragonTeam
 */
public class APTab extends CreativeTabs {

    private static ItemStack iconArmorPlus;
    private int tab;
    private String label;
    public APTab(int id, String modid, String label, int tab) {
        super(id, modid);
        this.tab = tab;
        this.label = label;
        this.setBackgroundImageName(ArmorPlus.MODID + ".png");// Automatically has tab_ applied to it. Make sure you change the texture name.
    }

    public static void registerTabs() {
        iconArmorPlus = new ItemStack(APItems.lavaChestplate);
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        switch (tab) {
            case 0:
                return getItemStack(APItems.lavaChestplate);
            case 1:
                return APItems.infusedLavaCrystal;
            case 2:
                return getItemStack(ModBlocks.blockInfusedLavaCrystal);
            case 3:
                return getItemStack(APItems.superStarBattleAxe);
            case 4:
                return getItemStack(APItems.arditeChestplate);
        }
        return ItemStack.EMPTY;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return this.getIconItemStack();
    }

    @Override
    public String getTabLabel() {
        return this.label;
    }
}