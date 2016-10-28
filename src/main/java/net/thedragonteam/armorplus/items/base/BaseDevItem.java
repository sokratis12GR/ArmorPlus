/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.items.DevItems;

import java.util.List;

public class BaseDevItem extends BaseItem {

    DevItems devItems;

    public BaseDevItem(DevItems devItemsIn) {
        super(devItemsIn.getName());
        this.devItems = devItemsIn;
        if (devItemsIn.hasSubTypes()) {
            this.setHasSubtypes(true);
            setMaxDamage(0);
        }
    }

    @Override
    public Item setCreativeTab(CreativeTabs tab) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        if (devItems.hasSubTypes())
            ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(getRegistryName() + "_second", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        if (devItems.hasSubTypes()) {
            subItems.add(new ItemStack(itemIn, 1, 0));
            subItems.add(new ItemStack(itemIn, 1, 1));
        }
    }
}
