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
import net.thedragonteam.armorplus.registry.ModItems;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.registry.ModItems.lava;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

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
        this.setBackgroundImageName(ArmorPlus.MODID + ".png"); // Automatically has tab_ applied to it. Make sure you change the texture name.
    }

    public static void initialize() {
        iconArmorPlus = new ItemStack(lava[1]);
    }

    public boolean hasSearchBar() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    public ItemStack getIconItemStack() {
        switch (tab) {
            case (0):
                return getItemStack(APItems.lavaChestplate);
            case (1):
                return APItems.enderDragonScale;
            case (2):
                return getItemStack(ModBlocks.blockLavaCrystal);
            case (3):
                return getItemStack(APItems.redstoneBattleAxe);
            case (4):
                return getItemStack(ModItems.itemTeslaRod);
        }
        return ItemStack.EMPTY;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    public ItemStack getTabIconItem() {
        return getIconItemStack();
    }

    @Override
    @Nonnull
    public String getTabLabel() {
        return this.label;
    }
}