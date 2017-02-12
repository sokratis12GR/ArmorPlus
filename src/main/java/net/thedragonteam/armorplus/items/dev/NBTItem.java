/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.dev;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.items.base.BaseItem;

import javax.annotation.Nonnull;
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
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Level")) {
            stack.addEnchantment(Enchantments.SHARPNESS, stack.getTagCompound().getInteger("Level"));
        }
        return super.onEntitySwing(entityLiving, stack);
    }

    @Override
    @Nonnull
    public Item setCreativeTab(@Nonnull CreativeTabs tab) {
        return this;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Level"))
            tooltip.add("Item Level: " + Integer.toString(stack.getTagCompound().getInteger("Level")));
    }

    @Override
    public Item getItem() {
        return this;
    }
}
