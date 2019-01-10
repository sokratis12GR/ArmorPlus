/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.base;

import com.sofodev.armorplus.iface.IModelHelper;
import com.sofodev.armorplus.items.enums.Cosmetics;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemCosmetic extends ItemBase implements IModelHelper {

    private final Cosmetics cosmetics;

    public ItemCosmetic(Cosmetics cosmetics) {
        super(cosmetics.getName());
        this.cosmetics = cosmetics;
        if (cosmetics.hasSubTypes()) {
            this.setHasSubtypes(true);
            this.setMaxDamage(0);
        }
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        if (cosmetics.hasSubTypes()) this.initModel(getRegistryName(), "_second", "cosmetics", 1);
        this.initModel(getRegistryName(), "cosmetics", 0);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab) && cosmetics.hasSubTypes()) {
            items.add(getItemStack(this, 0));
            items.add(getItemStack(this, 1));
        }
    }

}
