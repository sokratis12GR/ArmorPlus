/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.base;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.lib.TeslaUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.base.BaseARPTeslaContainerProvider;
import net.thedragonteam.armorplus.util.ARPTeslaUtils;

import java.util.List;
import java.util.Set;

public class BaseTesla extends Item {
    private long maxCapacity;
    private long output;
    private long input;

    public BaseTesla(String name, float f, float g, ToolMaterial diamond, Set<Block> effectiveOn, long maxCapacity, long input, long output) {
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTesla(String name, ToolMaterial diamond, Set<Block> effectiveOn, long maxCapacity, long input, long output) {
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTesla(String name, Set<Block> effectiveOn, long maxCapacity, long input, long output) {
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTesla(String name, ToolMaterial diamond, long maxCapacity, long input, long output) {
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTesla(String name, float f, float g, ToolMaterial diamond, long maxCapacity, long input, long output) {
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTesla(String name, float f, float g, long maxCapacity, long input, long output) {
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTesla(String name, long maxCapacity, long input, long output) {
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        ItemStack powered = ARPTeslaUtils.createChargedStack(new ItemStack(itemIn));
        ItemStack unpowered = new ItemStack(itemIn);
        subItems.add(powered);
        subItems.add(unpowered);
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return (1 - (double) ARPTeslaUtils.getStoredPower(stack) / (double) ARPTeslaUtils.getMaxCapacity(stack));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }


    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        TeslaUtils.createTooltip(stack, tooltip);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new BaseARPTeslaContainerProvider(new BaseTeslaContainer(), maxCapacity, output, input);
    }
}