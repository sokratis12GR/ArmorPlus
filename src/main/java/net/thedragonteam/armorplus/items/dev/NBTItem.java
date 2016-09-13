/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.dev;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
        if (stack.hasTagCompound()) {
            nbt = stack.getTagCompound();
        } else {
            nbt = new NBTTagCompound();
        }

        if (nbt.hasKey("Level")) {
            nbt.setInteger("Level", nbt.getInteger("Level") + 1);
        } else {
            nbt.setInteger("Level", 1);
        }
        stack.setTagCompound(nbt);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        NBTTagCompound nbt;
        if (stack.hasTagCompound()) {
            nbt = stack.getTagCompound();
        } else {
            nbt = new NBTTagCompound();
        }

        if (nbt.hasKey("Level")) {
            nbt.setInteger("Level", nbt.getInteger("Level") + 1);
        } else {
            nbt.setInteger("Level", 1);
        }
        stack.setTagCompound(nbt);
        return super.onEntitySwing(entityLiving, stack);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Level")) {
            tooltip.add("Item Level: " + Integer.toString(stack.getTagCompound().getInteger("Level")));
        }
    }
}
