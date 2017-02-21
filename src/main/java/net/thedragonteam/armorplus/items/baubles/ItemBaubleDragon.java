/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.baubles;

import baubles.api.BaubleType;
import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class ItemBaubleDragon extends ItemBauble implements IEnergyContainerItem {

    private int storage;

    public ItemBaubleDragon() {
        super("bauble_cosmetic_dragon");
        this.storage = 1000;
    }

    // ========= Baubles =========

    @Override
    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.HEAD;
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    // ========= RF =========

    @Override
    @SideOnly(Side.CLIENT)
    public boolean showDurabilityBar(ItemStack stack) {
        return this.getEnergyStored(stack) < this.getMaxEnergyStored(stack);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.DARK_RED + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        createTooltip(stack, tooltip);
    }

    public int getMaxExtract(ItemStack container) {
        return 100;
    }

    public int getMaxReceive(ItemStack container) {
        return 100;
    }

    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (!container.hasTagCompound()) container.setTagCompound(new NBTTagCompound());
        int energy = container.getTagCompound().getInteger("Energy");
        int energyReceived = Math.min(getMaxEnergyStored(container) - energy, Math.min(getMaxReceive(container), maxReceive));
        if (!simulate) {
            energy += energyReceived;
            container.getTagCompound().setInteger("Energy", energy);
        }
        return energyReceived;
    }

    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if ((container.getTagCompound() == null) || (!container.getTagCompound().hasKey("Energy"))) return 0;
        int energy = container.getTagCompound().getInteger("Energy");
        int energyExtracted = Math.min(energy, Math.min(getMaxEnergyStored(container), maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
            container.getTagCompound().setInteger("Energy", energy);
        }
        return energyExtracted;
    }

    public int getEnergyStored(ItemStack container) {
        return (container.getTagCompound() == null) || (!container.getTagCompound().hasKey("Energy")) ? 0 : container.getTagCompound().getInteger("Energy");
    }

    public int getMaxEnergyStored(ItemStack container) {
        return 10000;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return (1 - (double) this.getEnergyStored(stack) / (double) this.getMaxEnergyStored(stack));
    }

    private void createTooltip(ItemStack stack, List<String> tooltip) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add(TextFormatting.DARK_RED + I18n.format("") + this.getEnergyStored(stack) + "/" + this.getMaxEnergyStored(stack) + I18n.format(" RF"));
        } else
            tooltip.add(I18n.format("tooltip.rf.showinfo", TextFormatting.DARK_RED, keyBindSneak.getDisplayName(), TextFormatting.GRAY));
    }
}