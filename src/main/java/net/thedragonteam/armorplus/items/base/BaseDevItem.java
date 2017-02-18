/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.thedragonteam.armorplus.iface.IItemHelper;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.items.enums.DevItems;
import net.thedragonteam.thedragonlib.util.ItemStackUtils;

import javax.annotation.Nonnull;

public class BaseDevItem extends BaseItem implements IItemHelper, IModelHelper {

    private DevItems devItems;

    public BaseDevItem(DevItems devItemsIn) {
        super(devItemsIn.getName());
        this.devItems = devItemsIn;
        if (devItemsIn.hasSubTypes()) {
            this.setHasSubtypes(true);
            setMaxDamage(0);
        }
    }

    @Nonnull
    @Override
    public Item setCreativeTab(@Nonnull CreativeTabs tab) {
        return this;
    }

    @Override
    public void initModel() {
        if (devItems.hasSubTypes()) this.initModel(this, getRegistryName() + "_second", 1);
        this.initModel(this, getRegistryName(), 0);
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public void getSubItems(@Nonnull Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (devItems.hasSubTypes()) {
            subItems.add(ItemStackUtils.getItemStack(itemIn, 0));
            subItems.add(ItemStackUtils.getItemStack(itemIn, 1));
        }
    }
}
