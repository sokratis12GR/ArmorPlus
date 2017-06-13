/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.items.enums.Items;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class BaseItem extends Item implements IModelHelper {

    public BaseItem(String itemName) {
        this.setRegistryName(itemName);
        this.setUnlocalizedName(setName(itemName));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    public BaseItem(Items item) {
        this(item.getName());
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return ArmorPlus.tabArmorplusItems;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(this, getRegistryName(), 0);
    }
}
