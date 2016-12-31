/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.ItemStackUtils.getItemStack;

/**
 * net.thedragonteam.armorplus.client.gui
 * ArmorPlus created by sokratis12GR on 7/26/2016 4:42 PM.
 * - TheDragonTeam
 */
public class APTab extends CreativeTabs {

    static ItemStack iconArmorPlus;
    private String label;
    private int tab;

    public APTab(int id, String modid, String label, int tab) {
        super(id, modid);
        this.label = label;
        this.tab = tab;
        setBackgroundImageName(ArmorPlus.MODID + ".png"); // Automatically has tab_ applied to it. Make sure you change the texture name.
    }

    public static void initialize() {
        iconArmorPlus = new ItemStack(ModItems.lava[2]);
    }

    public boolean hasSearchBar() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        switch (tab) {
            case (0):
                return getItemStack(ModItems.lava[2]);
            case (1):
                return getItemStack(materials, 3);
            case (2):
                return getItemStack(ModBlocks.blockLavaCrystal);
            case (3):
                return getItemStack(ModItems.redstoneBattleAxe);
            case (4):
                return getItemStack(ModItems.itemTeslaRod);
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return getIconItemStack();
    }

    @Override
    public String getTabLabel() {
        return this.label;
    }
}