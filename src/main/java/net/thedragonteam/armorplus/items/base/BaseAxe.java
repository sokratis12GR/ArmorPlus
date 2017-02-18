/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.iface.IItemHelper;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.util.Utils;

public class BaseAxe extends ItemAxe implements IItemHelper, IModelHelper {

    public String itemName;

    public BaseAxe(ToolMaterial material, String name) {
        super(material);
        this.itemName = name;
        this.setUnlocalizedName(Utils.setName(name));
        this.setRegistryName(name);
        GameRegistry.register(this);
    }

    @Override
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
