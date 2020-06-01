package com.sofodev.armorplus.registry.blocks.special;

import com.sofodev.armorplus.registry.blocks.BaseTile;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.registry.ModBlocks.SWORD_DISPLAY_TYPE;
import static net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND;

public class SwordDisplayTile extends BaseTile {

    private ItemStack cachedSword;

    public SwordDisplayTile() {
        super(SWORD_DISPLAY_TYPE.get());
        this.cachedSword = ItemStack.EMPTY;
    }

    public SwordDisplayTile(ItemStack sword) {
        super(SWORD_DISPLAY_TYPE.get());
        this.cachedSword = sword;
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.loadFromNBT(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        this.saveToNbt(compound);
        return compound;
    }

    public CompoundNBT saveToNbt(CompoundNBT compound) {
        compound.put("displayed_item", this.cachedSword.write(new CompoundNBT()));
        return compound;
    }

    public void loadFromNBT(CompoundNBT compound) {
        if (compound.contains("displayed_item", TAG_COMPOUND)) {
            this.cachedSword = ItemStack.read(compound.getCompound("displayed_item"));
        }
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 12, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(pkt.getNbtCompound());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        super.handleUpdateTag(tag);
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

    public ItemStack getSword() {
        return this.cachedSword;
    }

    public void setSword(ItemStack stack) {
        this.cachedSword = stack;
        this.markDirty();
        this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
    }
}