/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.items.enums.Items;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class BaseItem extends Item implements IModelHelper {

    public BaseItem(String itemName) {
        this.setRegistryName(itemName);
        this.setUnlocalizedName(setName(itemName));
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    public BaseItem(Items item) {
        this(item.getName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(getRegistryName(), 0);
    }
}
