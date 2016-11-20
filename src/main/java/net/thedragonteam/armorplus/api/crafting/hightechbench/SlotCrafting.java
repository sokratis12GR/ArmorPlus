/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.ARPAchievements;

import javax.annotation.Nullable;

/**
 * net.thedragonteam.armorplus.api.crafting.hightechbench
 * ArmorPlus created by sokratis12GR on 6/21/2016 3:55 PM.
 * - TheDragonTeam
 */
public class SlotCrafting extends Slot {
    /**
     * The craft matrix inventory linked to this result slot.
     */
    private final InventoryCrafting craftMatrix;
    /**
     * The player that is using the GUI where this slot resides.
     */
    private final EntityPlayer thePlayer;
    public PlayerEvent.ItemCraftedEvent event;
    /**
     * The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset.
     */
    private int amountCrafted;

    public SlotCrafting(EntityPlayer player, InventoryCrafting craftingInventory, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.thePlayer = player;
        this.craftMatrix = craftingInventory;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(@Nullable ItemStack stack) {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) {
            this.amountCrafted += Math.min(amount, this.getStack().stackSize);
        }

        return super.decrStackSize(amount);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onCrafting(ItemStack stack, int amount) {
        this.amountCrafted += amount;
        this.onCrafting(stack);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    @Override
    protected void onCrafting(ItemStack stack) {

        if (this.amountCrafted > 0) {
            stack.onCrafting(this.thePlayer.world, this.thePlayer, this.amountCrafted);
        }

        this.amountCrafted = 0;

        if (stack.getItem() == Item.getItemFromBlock(ModBlocks.arpUltiTechBench))
            this.thePlayer.addStat(ARPAchievements.craftUltiTechBench, 1);

        /* Tinkers' Armors*/
        /*The Tinkers' Armors! - Achievement Trigger*/
        if (stack.getItem() == ModItems.cobaltHelmet || stack.getItem() == ModItems.cobaltChestplate || stack.getItem() == ModItems.cobaltLeggings || stack.getItem() == ModItems.cobaltBoots)
            this.thePlayer.addStat(ARPAchievements.craftCobaltArmor, 1);
        /*The Stronger The Better! - Achievement Trigger*/
        if (stack.getItem() == ModItems.arditeHelmet || stack.getItem() == ModItems.arditeChestplate || stack.getItem() == ModItems.arditeLeggings || stack.getItem() == ModItems.arditeBoots)
            this.thePlayer.addStat(ARPAchievements.craftArditeArmor, 1);
        /*The Tinkers' Armors God! - Achievement Trigger*/
        if (stack.getItem() == ModItems.manyullynHelmet || stack.getItem() == ModItems.manyullynChestplate || stack.getItem() == ModItems.manyullynLeggings || stack.getItem() == ModItems.manyullynBoots)
            this.thePlayer.addStat(ARPAchievements.craftManyullynArmor, 1);
        /* Oink! - Achievement Trigger*/
        if (stack.getItem() == ModItems.pigIronHelmet || stack.getItem() == ModItems.pigIronChestplate || stack.getItem() == ModItems.pigIronLeggings || stack.getItem() == ModItems.pigIronBoots)
            this.thePlayer.addStat(ARPAchievements.craftPigIronArmor, 1);
        /* Fascinating! - Achievement Trigger*/
        if (stack.getItem() == ModItems.knightSlimeHelmet || stack.getItem() == ModItems.knightSlimeChestplate || stack.getItem() == ModItems.knightSlimeLeggings || stack.getItem() == ModItems.knightSlimeBoots)
            this.thePlayer.addStat(ARPAchievements.craftKnightSlimeArmor, 1);
    }

    public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
        net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerCraftingEvent(playerIn, stack, craftMatrix);
        this.onCrafting(stack);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(playerIn);
        ItemStack[] aitemstack = HighTechBenchCraftingManager.getInstance().getRemainingItems(this.craftMatrix, playerIn.world);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);

        for (int i = 0; i < aitemstack.length; ++i) {
            ItemStack itemstack = this.craftMatrix.getStackInSlot(i);
            ItemStack itemstack1 = aitemstack[i];

            if (itemstack != null) {
                this.craftMatrix.decrStackSize(i, 1);
                itemstack = this.craftMatrix.getStackInSlot(i);
            }

            if (itemstack1 != null) {
                if (itemstack == null) {
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                } else if (ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1)) {
                    itemstack1.stackSize += itemstack.stackSize;
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                } else if (!this.thePlayer.inventory.addItemStackToInventory(itemstack1)) {
                    this.thePlayer.dropItem(itemstack1, false);
                }
            }
        }
    }
}
