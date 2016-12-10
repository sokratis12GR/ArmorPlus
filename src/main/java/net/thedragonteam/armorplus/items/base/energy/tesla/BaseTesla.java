/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base.energy.tesla;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.lib.TeslaUtils;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Optional.Method;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.base.BaseAPTeslaContainerProvider;
import net.thedragonteam.armorplus.items.base.BaseItem;
import net.thedragonteam.armorplus.util.APTeslaUtils;

import java.util.List;
import java.util.Set;

import static net.thedragonteam.armorplus.APConfig.teslaWeaponItemNameColor;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseTesla extends BaseItem {
    private int maxCapacity;
    private int output;
    private int input;

    public BaseTesla(String name, ToolMaterial diamond, Set<Block> effectiveOn, int maxCapacity, int input, int output) {
        super(name);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
        formattingName = addRarity("TESLA", teslaWeaponItemNameColor, "Tesla");
    }

    public BaseTesla(String name, Set<Block> effectiveOn, int maxCapacity, int input, int output) {
        this(name, null, effectiveOn, maxCapacity, input, output);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTesla(String name, ToolMaterial diamond, int maxCapacity, int input, int output) {
        this(name, diamond, null, maxCapacity, input, output);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTesla(String name, int maxCapacity, int input, int output) {
        this(name, null, null, maxCapacity, input, output);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    @Method(modid = "tesla")
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        ItemStack powered = APTeslaUtils.createChargedStack(new ItemStack(itemIn));
        ItemStack unpowered = new ItemStack(itemIn);
        subItems.add(powered);
        subItems.add(unpowered);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.getValueByName(APConfig.teslaWeaponItemNameColor) + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean isRepairable() {
        return true;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 30;
    }


    @Method(modid = "tesla")
    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return (1 - (double) APTeslaUtils.getStoredPower(stack) / (double) APTeslaUtils.getMaxCapacity(stack));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Method(modid = "tesla")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        TeslaUtils.createTooltip(stack, tooltip);
    }

    @Method(modid = "tesla")
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new BaseAPTeslaContainerProvider(new BaseTeslaContainer(), maxCapacity, output, input);
    }

}