/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.APItems;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class APTab extends CreativeTabs {

    private int tab;
    private String label;

    public APTab(int id, String modid, String label, int tab) {
        super(id, modid);
        this.tab = tab;
        this.label = label;
        this.setBackgroundImageName(ArmorPlus.MODID + ".png");// Automatically has tab_ applied to it. Make sure you change the texture name.
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        if (tab == 0) {
            return addTabItemStack(APItems.lavaChestplate);
        } else if (tab == 1) {
            return APItems.infusedLavaCrystal;
        } else if (tab == 2) {
            return addTabItemStack(ModBlocks.blockInfusedLavaCrystal);
        } else if (tab == 3) {
            return addTabItemStack(ModItems.battleAxe[7]);
        }
        return ItemStack.EMPTY;
    }

    private ItemStack addTabItemStack(Object stack) {
        return stack instanceof Block || stack instanceof Item ? !getItemStack(stack).isEmpty() ? getItemStack(stack) : getItemStack(ModItems.lavaCrystal) : ItemStack.EMPTY;
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