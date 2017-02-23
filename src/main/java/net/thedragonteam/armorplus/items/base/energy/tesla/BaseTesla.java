/*
 * Copyright (c) TheDragonTeam 2016-2017.
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
import net.thedragonteam.thedragonlib.util.TextHelper;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

import static net.thedragonteam.armorplus.APConfig.teslaWeaponItemNameColor;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;

public class BaseTesla extends BaseItem {
    private int maxCapacity;
    private int output;
    private int input;

    public BaseTesla(String name, ToolMaterial material, Set<Block> effectiveOn, int maxCapacity, int input, int output) {
        super(name);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        this.setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
        this.setFormattingName(addRarity("TESLA", teslaWeaponItemNameColor, "Tesla"));
    }

    public BaseTesla(String name, Set<Block> effectiveOn, int maxCapacity, int input, int output) {
        this(name, null, effectiveOn, maxCapacity, input, output);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        this.setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTesla(String name, ToolMaterial diamond, int maxCapacity, int input, int output) {
        this(name, diamond, null, maxCapacity, input, output);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        this.setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTesla(String name, int maxCapacity, int input, int output) {
        this(name, null, null, maxCapacity, input, output);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        this.setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    @Method(modid = "tesla")
    @Override
    public void getSubItems(@Nonnull Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        ItemStack powered = APTeslaUtils.INSTANCE.createChargedStack(new ItemStack(itemIn));
        ItemStack unpowered = new ItemStack(itemIn);
        subItems.add(powered);
        subItems.add(unpowered);
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return (TextFormatting.getValueByName(APConfig.teslaWeaponItemNameColor) + TextHelper.INSTANCE.localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
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
        return (1 - (double) APTeslaUtils.INSTANCE.getStoredPower(stack) / (double) APTeslaUtils.INSTANCE.getMaxCapacity(stack));
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

    @Override
    public Item getItem() {
        return this;
    }
}