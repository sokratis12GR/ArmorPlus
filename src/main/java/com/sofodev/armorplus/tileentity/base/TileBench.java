/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.tileentity.base;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

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

    public TileBench(TileEntityType<?> type, String tileEntityName, int inventorySize) {
        super(type, inventorySize);
        this.tileEntityName = tileEntityName;
        this.inventorySize = inventorySize;
        this.inventory = this.itemHandler.getItems();
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (slot < inventorySize) {
            return itemHandler.getStackInSlot(slot).isEmpty() || stack.getCount() + inventory.get(slot).getCount() <= getMaxStackSizePerSlot(slot);
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack) {
        int bound = itemHandler.getSlots();
        return IntStream.rangeClosed(0, bound).anyMatch(i -> !itemHandler.getStackInSlot(slot).isEmpty());
    }

    @Override
    public int getMaxStackSizePerSlot(int slot) {
        return 64;
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

    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(tileEntityName) : new TextComponentTranslation(tileEntityName);
    }

    @Override
    @Nonnull
    public NBTTagCompound write(NBTTagCompound nbt) {
        NBTTagList list = new NBTTagList();
        int bound = this.inventorySize;
        for (int i = 0; i < bound; i++) {
            if (!this.itemHandler.getStackInSlot(i).isEmpty()) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.putByte("Slot", (byte) i);
                this.itemHandler.getStackInSlot(i).write(stackTag);
                list.add(stackTag);
            }
        }
        nbt.put("Items", list);

        if (this.hasCustomName()) nbt.putString("CustomName", this.getCustomName());
        return nbt;
    }

    @Override
    public void read(NBTTagCompound nbt) {
        super.read(nbt);

        NBTTagList list = nbt.getList("Items", 10);
        int bound = list.size();
        for (int i1 = 0; i1 < bound; i1++) {
            NBTTagCompound stackTag = list.getCompound(i1);
            int slot = stackTag.getByte("Slot") & 255;
            this.itemHandler.setStackInSlot(slot, ItemStack.read(stackTag));
        }

        if (nbt.contains("CustomName", 8)) this.setCustomName(nbt.getString("CustomName"));
    }

    //getUpdateTag, getUpdatePacket, onDataPacket

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return write(new NBTTagCompound());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        read(pkt.getNbtCompound());
    }

}