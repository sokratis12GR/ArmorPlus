/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base.energy.tesla;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Optional;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.BaseSword;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.Set;

public class BaseTeslaSword extends BaseSword {

    private int maxCapacity;
    private int output;
    private int input;

    public BaseTeslaSword(ToolMaterial material, String name, Set<Block> effectiveOn, int maxCapacity, int input, int output) {
        super(material, name, ModItems.steelIngot, ModItems.steelIngot, TextFormatting.getValueByName(ARPConfig.teslaWeaponItemNameColor), null);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTeslaSword(ToolMaterial material, String name, int maxCapacity, int input, int output) {
        this(material, name, null, maxCapacity, input, output);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    @Override
    public boolean isItemTool(ItemStack stack) {
        return true;
    }

    @Optional.Method(modid = "tesla")
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        //    ItemStack powered = ARPTeslaUtils.createChargedStack(new ItemStack(itemIn));
        ItemStack unpowered = new ItemStack(itemIn);
        //   subItems.add(powered);
        subItems.add(unpowered);
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

    // @Optional.Method(modid = "tesla")
    // @Override
    // public double getDurabilityForDisplay(ItemStack stack) {
    //     return (1 - (double) ARPTeslaUtils.getStoredPower(stack) / (double) ARPTeslaUtils.getMaxCapacity(stack));
    // }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    /*@Optional.Method(modid = "tesla")
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new BaseARPTeslaContainerProvider(new BaseTeslaContainer(), maxCapacity, output, input);
    }*/
}