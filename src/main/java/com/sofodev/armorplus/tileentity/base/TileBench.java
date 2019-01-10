/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.tileentity.base;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

/**
 * @author Sokratis Fotkatzikis
 **/
public abstract class TileBench extends TileEntityInventoryBase {

    /**
     * the amount of itemHandler for the crafting grid
     */
    public NonNullList<ItemStack> inventory;
    private int inventorySize;
    private String tileEntityName;
    private String customName;

    public TileBench(String tileEntityName, int inventorySize) {
        super(inventorySize);
        this.tileEntityName = tileEntityName;
        this.inventorySize = inventorySize;
        this.inventory = this.itemHandler.getItems();
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return rangeClosed(0, itemHandler.getSlots()).anyMatch(i -> itemHandler.getStackInSlot(slot) != ItemStack.EMPTY);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack) {
        return rangeClosed(0, itemHandler.getSlots()).anyMatch(i -> itemHandler.getStackInSlot(slot) != ItemStack.EMPTY);
    }

    @Override
    public int getMaxStackSizePerSlot(int slot) {
        return 128;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getName() {
        return this.hasCustomName() ? this.customName : "container.armorplus." + tileEntityName;
    }

    public boolean hasCustomName() {
        return this.customName != null && !this.customName.equals("");
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(tileEntityName) : new TextComponentTranslation(tileEntityName);
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        NBTTagList list = new NBTTagList();
        range(0, this.inventorySize).filter(i -> !this.itemHandler.getStackInSlot(i).isEmpty()).forEachOrdered(i -> {
            NBTTagCompound stackTag = new NBTTagCompound();
            stackTag.setByte("Slot", (byte) i);
            this.itemHandler.getStackInSlot(i).writeToNBT(stackTag);
            list.appendTag(stackTag);
        });
        nbt.setTag("Items", list);

        if (this.hasCustomName()) nbt.setString("CustomName", this.getCustomName());
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);
        range(0, list.tagCount()).mapToObj(list::getCompoundTagAt).forEachOrdered(stackTag -> {
            int slot = stackTag.getByte("Slot") & 255;
            this.itemHandler.setStackInSlot(slot, new ItemStack(stackTag));
        });

        if (nbt.hasKey("CustomName", 8)) this.setCustomName(nbt.getString("CustomName"));
    }

    //getUpdateTag, getUpdatePacket, onDataPacket

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

}