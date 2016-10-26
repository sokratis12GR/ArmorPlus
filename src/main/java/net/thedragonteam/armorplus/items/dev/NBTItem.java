/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.dev;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import java.util.List;

public class NBTItem extends Item {

    public NBTItem() {
        setRegistryName("dev_item");
        setUnlocalizedName(ArmorPlus.MODID + "." + "dev_item");
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
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
    public Item setCreativeTab(CreativeTabs tab) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Level")) {
            tooltip.add("Item Level: " + Integer.toString(stack.getTagCompound().getInteger("Level")));
        }
    }
}
