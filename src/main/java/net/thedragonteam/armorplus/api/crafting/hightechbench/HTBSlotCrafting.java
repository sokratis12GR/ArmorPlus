package net.thedragonteam.armorplus.api.crafting.hightechbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.thedragonteam.armorplus.api.crafting.utils.CraftingUtils;
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved;

import javax.annotation.Nonnull;

public class HTBSlotCrafting extends Slot {
    /**
     * The craft matrix inventory linked to this result slot.
     */
    private final InventoryCraftingImproved craftMatrix;
    /**
     * The player that is using the GUI where this slot resides.
     */
    private final EntityPlayer player;
    /**
     * The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset.
     */
    private int amountCrafted;

    public HTBSlotCrafting(EntityPlayer player, InventoryCraftingImproved craftingInventory, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.player = player;
        this.craftMatrix = craftingInventory;
    }

    public void setAmountCrafted(int amountCrafted) {
        this.amountCrafted = amountCrafted;
    }

    public int getAmountCrafted() {
        return amountCrafted;
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor itemHandler as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    @Override
    @Nonnull
    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack())
            this.amountCrafted += Math.min(amount, this.getStack().getCount());

        return super.decrStackSize(amount);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    @Override
    protected void onCrafting(ItemStack stack, int amount) {
        this.amountCrafted += amount;
        this.onCrafting(stack);
    }

    @Override
    protected void onSwapCraft(int p_190900_1_) {
        this.setAmountCrafted(this.getAmountCrafted() + p_190900_1_);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    @Override
    protected void onCrafting(ItemStack stack) {
        if (this.getAmountCrafted() > 0) {
            stack.onCrafting(this.player.world, this.player, this.getAmountCrafted());
        }

        this.setAmountCrafted(0);
    }


    @Override
    @Nonnull
    public ItemStack onTake(EntityPlayer player, @Nonnull ItemStack stack) {
        this.onCrafting(stack);
        ForgeHooks.setCraftingPlayer(player);
        NonNullList<ItemStack> nonnulllist = HighTechBenchCraftingManager.getInstance().getRemainingItems(this.craftMatrix, player.world);
        ForgeHooks.setCraftingPlayer(null);
        CraftingUtils.onTake(player, craftMatrix, nonnulllist);

        return stack;
    }
}
