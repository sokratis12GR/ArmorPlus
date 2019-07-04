/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.registry.ModBlocks;
import com.sofodev.armorplus.common.registry.ModItems;
import com.sofodev.armorplus.common.registry.constants.APItems;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.sofodev.armorplus.common.registry.constants.APItems.infusedLavaCrystal;
import static com.sofodev.armorplus.common.util.Utils.setRL;
import static net.minecraftforge.fml.common.registry.ForgeRegistries.BLOCKS;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
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
    public ItemStack createIcon() {
        if (tab == 0) {
            return addTabItemStack(APItems.lavaChestplate);
        } else if (tab == 1) {
            return infusedLavaCrystal;
        } else if (tab == 2) {
            return addTabItemStack(ModBlocks.blockInfusedLavaCrystal);
        } else if (tab == 3) {
            return addTabItemStack(APItems.lavaBattleAxe);
        } else if (tab == 4) {
            return addTabItemStack(ModItems.pickaxe[5]);
        } else if (tab == 5) {
            return addTabItemStack(BLOCKS.getValue(setRL("trophy")));
        }
        return ItemStack.EMPTY;
    }

    private ItemStack addTabItemStack(Object stack) {
        return stack instanceof Block || stack instanceof Item ? getLavaCrystal(stack) : ItemStack.EMPTY;
    }

    private ItemStack getLavaCrystal(Object stack) {
        return !getItemStack(stack).isEmpty() ? getItemStack(stack) : getItemStack(ModItems.itemLavaCrystal);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIcon() {
        return this.createIcon();
    }

    @Override
    public String getTabLabel() {
        return this.label;
    }
}