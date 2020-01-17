package com.sofodev.armorplus.common.tileentity;

import com.sofodev.armorplus.common.tileentity.base.TileEntityBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class TileSwordDisplay extends TileEntityBase {

    private Item cachedSword;

    public TileSwordDisplay() {
    }

    public TileSwordDisplay(Item sword) {
        this.cachedSword = sword;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.loadFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.saveToNbt(compound);
        return compound;
    }

    public NBTTagCompound saveToNbt(NBTTagCompound compound) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.cachedSword);
        compound.setString("Item", resourcelocation == null ? "minecraft:iron_sword" : resourcelocation.toString());
        return compound;
    }

    public void loadFromNBT(NBTTagCompound compound) {
        if (compound.hasKey("Item", 8)) {
            this.cachedSword = ForgeRegistries.ITEMS.getValue(new ResourceLocation(compound.getString("Item")));
        }
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 12, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public boolean onlyOpsCanSetNbt() {
        return false;
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        return super.receiveClientEvent(id, type);
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public BlockPos getPos() {
        return this.pos;
    }

    public void setItem(ResourceLocation rl) {
        this.cachedSword = ForgeRegistries.ITEMS.getValue(rl);
    }

    public ItemStack getItemStack() {
        return this.cachedSword == null ? ItemStack.EMPTY : new ItemStack(this.cachedSword, 1);
    }


}

