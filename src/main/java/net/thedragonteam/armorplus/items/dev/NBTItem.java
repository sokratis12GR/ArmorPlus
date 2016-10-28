/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.dev;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.items.base.BaseItem;

import java.util.List;

public class NBTItem extends BaseItem {

    public NBTItem() {
        super("dev_item");
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        NBTTagCompound nbt;
        nbt = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();

        nbt.setInteger("Level", nbt.hasKey("Level") ? nbt.getInteger("Level") + 1 : 1);
        stack.setTagCompound(nbt);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        NBTTagCompound nbt;
        nbt = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();

        nbt.setInteger("Level", nbt.hasKey("Level") ? nbt.getInteger("Level") + 1 : 1);
        stack.setTagCompound(nbt);
        return super.onEntitySwing(entityLiving, stack);
    }

    @Override
    public Item setCreativeTab(CreativeTabs tab) {
        return null;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Level"))
            tooltip.add("Item Level: " + Integer.toString(stack.getTagCompound().getInteger("Level")));
    }
}
