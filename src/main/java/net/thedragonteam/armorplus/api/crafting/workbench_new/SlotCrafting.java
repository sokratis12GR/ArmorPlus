/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench_new;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.thedragonteam.armorplus.container.inventory.InventoryCraftingNew;
import net.thedragonteam.armorplus.registry.APBlocks;
import net.thedragonteam.armorplus.registry.ModAchievements;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.util.EnchantmentUtils.INSTANCE;

public class SlotCrafting extends Slot {
    /**
     * The craft matrix inventory linked to this result slot.
     */
    private final InventoryCraftingNew craftMatrix;
    /**
     * The player that is using the GUI where this slot resides.
     */
    private final EntityPlayer player;
    /**
     * The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset.
     */
    private int amountCrafted;

    public SlotCrafting(EntityPlayer player, InventoryCraftingNew craftingInventory, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.player = player;
        this.craftMatrix = craftingInventory;
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
        this.amountCrafted += p_190900_1_;
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    @Override
    protected void onCrafting(ItemStack stack) {

        if (this.amountCrafted > 0) {
            stack.onCrafting(this.player.world, this.player, this.amountCrafted);
        }

        this.amountCrafted = 0;
        if (stack.getItem() == Item.getItemFromBlock(APBlocks.highTechBench))
            this.player.addStat(ModAchievements.craftHighTechBench, 1);
        else if (stack.getItem() == lapisSword)
            stack.addEnchantment(INSTANCE.getEnchantment("looting"), 3);
        else if (stack.getItem() == lapisBattleAxe)
            stack.addEnchantment(INSTANCE.getEnchantment("looting"), 3);
        else if (stack.getItem() == lapisBow)
            stack.addEnchantment(INSTANCE.getEnchantment("looting"), 3);
        /*Vision Like A Bat! - Achievement Trigger*/
        else if (stack.getItem() == coalHelmet || stack.getItem() == coalChestplate || stack.getItem() == coalLeggings || stack.getItem() == coalBoots)
            this.player.addStat(ModAchievements.craftCoalArmor, 1);
        /*Never Drown Again - Achievement Trigger*/
        else if (stack.getItem() == lapisHelmet || stack.getItem() == lapisChestplate || stack.getItem() == lapisLeggings || stack.getItem() == lapisBoots)
            this.player.addStat(ModAchievements.craftLapisArmor, 1);
        /*Speeedy! - Achievement Trigger*/
        else if (stack.getItem() == redstoneHelmet || stack.getItem() == redstoneChestplate || stack.getItem() == redstoneLeggings || stack.getItem() == redstoneBoots)
            this.player.addStat(ModAchievements.craftRedstoneArmor, 1);
        /*Swing Swing Faster! - Achievement Trigger*/
        else if (stack.getItem() == emeraldHelmet || stack.getItem() == emeraldChestplate || stack.getItem() == emeraldLeggings || stack.getItem() == emeraldBoots)
            this.player.addStat(ModAchievements.craftEmeraldArmor, 1);
        /*Indestructible! - Achievement Trigger*/
        else if (stack.getItem() == obsidianHelmet || stack.getItem() == obsidianChestplate || stack.getItem() == obsidianLeggings || stack.getItem() == obsidianBoots)
            this.player.addStat(ModAchievements.craftObsidianArmor, 1);
        /*The Overpowered! - Achievement Trigger*/
        else if (stack.getItem() == lavaHelmet || stack.getItem() == lavaChestplate || stack.getItem() == lavaLeggings || stack.getItem() == lavaBoots)
            this.player.addStat(ModAchievements.craftLavaArmor, 1);
        /*Jump Jump Jump! - Achievement Trigger*/
        else if (stack.getItem() == slimeHelmet || stack.getItem() == slimeChestplate || stack.getItem() == slimeLeggings || stack.getItem() == slimeBoots)
            this.player.addStat(ModAchievements.craftSlimeArmor, 1);
        /*Its time to go Faster! - Achievement Trigger*/
        else if (stack.getItem() == chickenHelmet || stack.getItem() == chickenChestplate || stack.getItem() == chickenLeggings || stack.getItem() == chickenBoots)
            this.player.addStat(ModAchievements.craftChickenArmor, 1);
    }


    @Override
    @Nonnull
    public ItemStack onTake(EntityPlayer player, @Nonnull ItemStack stack) {
        this.onCrafting(stack);
        ForgeHooks.setCraftingPlayer(player);
        NonNullList<ItemStack> nonnulllist = WorkbenchNewCraftingManager.getInstance().getRemainingItems(this.craftMatrix, player.world);
        ForgeHooks.setCraftingPlayer(null);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = this.craftMatrix.getStackInSlot(i);
            ItemStack itemstack1 = (ItemStack) nonnulllist.get(i);

            if (!itemstack.isEmpty()) {
                this.craftMatrix.decrStackSize(i, 1);
                itemstack = this.craftMatrix.getStackInSlot(i);
            }

            if (!itemstack1.isEmpty()) {
                if (itemstack.isEmpty()) {
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                } else if (ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1)) {
                    itemstack1.grow(itemstack.getCount());
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                } else if (!this.player.inventory.addItemStackToInventory(itemstack1)) {
                    this.player.dropItem(itemstack1, false);
                }
            }
        }

        return stack;
    }
}
