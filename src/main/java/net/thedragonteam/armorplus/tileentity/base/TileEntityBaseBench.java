/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.tileentity.base;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.IntStream;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class TileEntityBaseBench extends TileEntityInventoryBase {

    public int inventorySize;
    public String tileEntityName;

    /**
     * the amount of itemHandler for the crafting grid
     */
    public NonNullList<ItemStack> inventory = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
    public String customName;

    public TileEntityBaseBench(String tileEntityName, int inventorySize) {
        super(inventorySize);
        this.tileEntityName = tileEntityName;
        this.inventorySize = inventorySize;
        this.inventory = this.itemHandler.getItems();
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return IntStream.rangeClosed(0, itemHandler.getSlots()).anyMatch(i -> itemHandler.getStackInSlot(slot) != ItemStack.EMPTY);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack) {
        return IntStream.rangeClosed(0, itemHandler.getSlots()).anyMatch(i -> itemHandler.getStackInSlot(slot) != ItemStack.EMPTY);
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
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < this.inventorySize; ++i) {
            if (!this.itemHandler.getStackInSlot(i).isEmpty()) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                this.itemHandler.getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }
        nbt.setTag("Items", list);

        if (this.hasCustomName()) nbt.setString("CustomName", this.getCustomName());
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            this.itemHandler.setStackInSlot(slot, new ItemStack(stackTag));
        }

        if (nbt.hasKey("CustomName", 8)) this.setCustomName(nbt.getString("CustomName"));
    }

    //getUpdateTag, onDataTag, getUpdatePacket, onDataPacket

    @Override
    @Nonnull
    public NBTTagCompound getUpdateTag() {
        return super.getUpdateTag();
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return super.getUpdatePacket();
    }
}