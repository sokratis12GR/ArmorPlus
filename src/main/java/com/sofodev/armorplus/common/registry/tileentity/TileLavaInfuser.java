/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.tileentity;

import com.sofodev.armorplus.api.lavainfuser.LavaInfuserManager;
import com.sofodev.armorplus.api.lavainfuser.SlotLavaInfuserFuel;
import com.sofodev.armorplus.common.container.ContainerLavaInfuser;
import com.sofodev.armorplus.common.registry.blocks.lava.BlockLavaInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.sofodev.armorplus.common.registry.ModTileEntities.LAVA_INFUSER;
import static com.sofodev.armorplus.common.util.Utils.getItem;
import static net.minecraft.item.ItemStack.areItemStacksEqual;
import static net.minecraftforge.event.ForgeEventFactory.getItemBurnTime;

/**
 * @author Sokratis Fotkatzikis
 **/
public class TileLavaInfuser extends TileEntityLockable implements ITickable, ISidedInventory {
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 1};
    private static final int[] SLOTS_SIDES = new int[]{1};
    private NonNullList<ItemStack> infuserItemStacks = NonNullList.withSize(3, ItemStack.EMPTY);
    /**
     * The number of ticks that the furnace will keep burning
     */
    private int infuserInfusingTime;
    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    private int currentItemInfusingTime;
    private int infusingTime;
    private int totalInfusingTime;
    private ITextComponent infuserCustomName;
    private IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
    private IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
    private IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

    public TileLavaInfuser() {
        super(LAVA_INFUSER.type());
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean isInfusing(IInventory inventory) {
        return inventory.getField(0) > 0;
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemChargeTime(ItemStack stack) {
        if (stack.isEmpty()) return 0;
        Item item = stack.getItem();
        return item == Items.LAVA_BUCKET || item == getItem("lava_crystal") ? 500 : getInfusionTime(stack);
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static boolean isItemFuel(ItemStack stack) {
        return isItemValid(stack) && getItemChargeTime(stack) > 0;
    }

    public static boolean isItemValid(ItemStack stack) {
        return areItemStacksEqual(stack, new ItemStack(Items.LAVA_BUCKET)) || areItemStacksEqual(stack, new ItemStack(getItem("lava_crystal")));
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't fuel
     */
    private static int getInfusionTime(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        } else {
            int ret = stack.getBurnTime();
            return getItemBurnTime(stack, ret == -1 ? 0 : ret);
        }
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return this.infuserItemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        return this.infuserItemStacks.stream().allMatch(ItemStack::isEmpty);
    }

    /**
     * Returns the stack in the given slot.
     */
    @Override
    @Nonnull
    public ItemStack getStackInSlot(int index) {
        return this.infuserItemStacks.get(index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    @Override
    @Nonnull
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.infuserItemStacks, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    @Override
    @Nonnull
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.infuserItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.infuserItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.infuserItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());

        if (index == 0 && !flag) {
            this.totalInfusingTime = this.getInfusionTime(stack);
            this.infusingTime = 0;
            this.markDirty();
        }
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    @Override
    @Nonnull
    public ITextComponent getName() {
        return this.hasCustomName() ? this.infuserCustomName : new TextComponentTranslation("container.armorplus.lava_infuser");
    }

    @Nullable
    @Override
    public ITextComponent getCustomName() {
        return getName();
    }

    /**
     * Returns true if this thing is named
     */
    @Override
    public boolean hasCustomName() {
        return this.infuserCustomName != null;
    }

    public void setCustomInventoryName(ITextComponent invName) {
        this.infuserCustomName = invName;
    }

    @Override
    public void read(NBTTagCompound compound) {
        super.read(compound);
        this.infuserItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.infuserItemStacks);
        this.infuserInfusingTime = compound.getInt("BurnTime");
        this.infusingTime = compound.getInt("InfusingTime");
        this.totalInfusingTime = compound.getInt("InfusingTimeTotal");
        this.currentItemInfusingTime = getItemChargeTime(this.infuserItemStacks.get(1));

        if (compound.contains("CustomName", 8)) {
            this.infuserCustomName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
        }
    }

    @Override
    @Nonnull
    public NBTTagCompound write(NBTTagCompound compound) {
        super.write(compound);
        compound.putInt("BurnTime", (short) this.infuserInfusingTime);
        compound.putInt("InfusingTime", (short) this.infusingTime);
        compound.putInt("InfusingTimeTotal", (short) this.totalInfusingTime);
        ItemStackHelper.saveAllItems(compound, this.infuserItemStacks);

        if (this.hasCustomName()) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.infuserCustomName));
        }
        return compound;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    /**
     * Furnace isInfusing
     */
    public boolean isInfusing() {
        return this.infuserInfusingTime > 0;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public void tick() {
        boolean flag = this.isInfusing();
        boolean flag1 = false;

        if (this.isInfusing()) {
            --this.infuserInfusingTime;
        }

        if (!this.world.isRemote) {
            ItemStack itemstack = this.infuserItemStacks.get(1);

            if (this.isInfusing() || !itemstack.isEmpty() && !this.infuserItemStacks.get(0).isEmpty()) {
                if (!this.isInfusing() && this.canCharge()) {
                    this.infuserInfusingTime = getItemChargeTime(itemstack);
                    this.currentItemInfusingTime = this.infuserInfusingTime;

                    if (this.isInfusing()) {
                        flag1 = true;

                        if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty()) {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.infuserItemStacks.set(1, item1);
                            }
                        }
                    }
                }

                if (this.isInfusing() && this.canCharge()) {
                    ++this.infusingTime;

                    if (this.infusingTime == this.totalInfusingTime) {
                        this.infusingTime = 0;
                        this.totalInfusingTime = this.getInfusionTime(this.infuserItemStacks.get(0));
                        this.chargeItem();
                        flag1 = true;
                    }
                } else this.infusingTime = 0;
            } else if (!this.isInfusing() && this.infusingTime > 0)
                this.infusingTime = MathHelper.clamp(this.infusingTime - 2, 0, this.totalInfusingTime);

            if (flag != this.isInfusing()) {
                flag1 = true;
                BlockLavaInfuser.setState(this.isInfusing(), this.world, this.pos);
            }
        }

        if (flag1) this.markDirty();
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canCharge() {
        if (this.infuserItemStacks.get(0).isEmpty()) return false;
        ItemStack itemstack = LavaInfuserManager.getInstance().getInfusingResult(this.infuserItemStacks.get(0));

        if (itemstack.isEmpty()) return false;
        ItemStack itemstack1 = this.infuserItemStacks.get(2);
        if (itemstack1.isEmpty()) return true;
        if (!itemstack1.isItemEqual(itemstack)) return false;
        int result = itemstack1.getCount() + itemstack.getCount();
        return result <= getInventoryStackLimit() && result <= itemstack1.getMaxStackSize();
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void chargeItem() {
        if (this.canCharge()) {
            ItemStack itemstack = this.infuserItemStacks.get(0);
            ItemStack itemstack1 = LavaInfuserManager.getInstance().getInfusingResult(itemstack);
            ItemStack itemstack2 = this.infuserItemStacks.get(2);

            if (itemstack2.isEmpty()) {
                this.infuserItemStacks.set(2, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }

            itemstack.shrink(1);
        }
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == 2) {
            return false;
        } else if (index != 1) {
            return true;
        }
        ItemStack itemstack = this.infuserItemStacks.get(1);
        return isItemFuel(stack) || SlotLavaInfuserFuel.isAllowed(stack) && itemstack.getItem() != Items.BUCKET;
    }

    @Override
    @Nonnull
    public int[] getSlotsForFace(EnumFacing side) {
        if (side == EnumFacing.DOWN) {
            return SLOTS_BOTTOM;
        } else {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if (direction == EnumFacing.DOWN && index == 1) {
            Item item = stack.getItem();

            return item == Items.WATER_BUCKET || item == Items.BUCKET;
        }

        return true;
    }

    @Override
    @Nonnull
    public String getGuiID() {
        return "armorplus:lava_infuser";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerLavaInfuser(playerInventory, this);
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return this.infuserInfusingTime;
            case 1:
                return this.currentItemInfusingTime;
            case 2:
                return this.infusingTime;
            case 3:
                return this.totalInfusingTime;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.infuserInfusingTime = value;
                break;
            case 1:
                this.currentItemInfusingTime = value;
                break;
            case 2:
                this.infusingTime = value;
                break;
            case 3:
                this.totalInfusingTime = value;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public void clear() {
        this.infuserItemStacks.clear();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, EnumFacing facing) {
        if (facing != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            switch (facing) {
                case DOWN:
                    return (LazyOptional<T>) handlerBottom;
                case UP:
                    return (LazyOptional<T>) handlerTop;
                default:
                    return (LazyOptional<T>) handlerSide;
            }
        }
        return super.getCapability(cap, facing);
    }

}