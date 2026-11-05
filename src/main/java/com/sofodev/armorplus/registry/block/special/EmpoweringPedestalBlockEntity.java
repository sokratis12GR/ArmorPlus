package com.sofodev.armorplus.registry.block.special;

import com.sofodev.armorplus.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

/**
 * Holds the one stack placed on an Empowering Pedestal, which the ritual around a Rune Altar can use up. It's synced to
 * clients, which draw it and show it in the altar's screens.
 */
public class EmpoweringPedestalBlockEntity extends BlockEntity implements Clearable {

    private static final String ITEM_KEY = "Item";

    private ItemStack item = ItemStack.EMPTY;

    public EmpoweringPedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.EMPOWERING_PEDESTAL_ENTITY.get(), pos, state);
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack stack) {
        this.item = stack;
        contentsChanged();
    }

    /** @return the removed items, at most the given count */
    public ItemStack remove(int count) {
        ItemStack removed = this.item.split(count);
        if (this.item.isEmpty()) this.item = ItemStack.EMPTY;
        contentsChanged();
        return removed;
    }

    private void contentsChanged() {
        setChanged();
        if (this.level != null) this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), 3);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.item = input.read(ITEM_KEY, ItemStack.CODEC).orElse(ItemStack.EMPTY);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (!this.item.isEmpty()) output.store(ITEM_KEY, ItemStack.CODEC, this.item);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (this.level != null) Containers.dropItemStack(this.level, pos.getX(), pos.getY(), pos.getZ(), this.item);
    }

    @Override
    public void clearContent() {
        this.item = ItemStack.EMPTY;
    }
}
