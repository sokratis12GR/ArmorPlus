/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IItemHelper;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.items.enums.Items;
import net.thedragonteam.armorplus.util.Utils;

public class BaseItem extends Item implements IItemHelper, IModelHelper {

    public EnumRarity formattingName;
    public String itemName;
    private Items items;

    public BaseItem(String name) {
        this.itemName = name;
        this.setRegistryName(itemName);
        this.setUnlocalizedName(Utils.setName(itemName));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    public BaseItem(Items itemsIn) {
        this(itemsIn.getName());
        this.items = itemsIn;
    }

    public void initModel() {
        this.initModel(this, getRegistryName(), 0);
    }

    @Override
    public ItemStack getItemStack(ItemStack stack) {
        return stack;
    }

    @Override
    public Item getItem(Item item) {
        return item;
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(this);
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public String getName(String name) {
        return name;
    }

    @Override
    public String getName() {
        return this.itemName;
    }
}
