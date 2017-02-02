/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.workbench;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModAchievements;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.registry.ModItems.*;

/**
 * net.thedragonteam.armorplus.api.crafting.benches
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

        if (stack.getItem() == Item.getItemFromBlock(ModBlocks.arpHighTechBench))
            this.thePlayer.addStat(ModAchievements.craftHighTechBench, 1);

        if (stack.getItem() == lapisSword)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        if (stack.getItem() == lapisBattleAxe)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        if (stack.getItem() == lapisBow)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        /*Vision Like A Bat! - Achievement Trigger*/
        if (stack.getItem() == coalHelmet || stack.getItem() == coalChestplate || stack.getItem() == coalLeggings || stack.getItem() == coalBoots)
            this.thePlayer.addStat(ModAchievements.craftCoalArmor, 1);
        /*Never Drown Again - Achievement Trigger*/
        if (stack.getItem() == lapisHelmet || stack.getItem() == lapisChestplate || stack.getItem() == lapisLeggings || stack.getItem() == lapisBoots)
            this.thePlayer.addStat(ModAchievements.craftLapisArmor, 1);
        /*Speeedy! - Achievement Trigger*/
        if (stack.getItem() == redstoneHelmet || stack.getItem() == redstoneChestplate || stack.getItem() == redstoneLeggings || stack.getItem() == redstoneBoots)
            this.thePlayer.addStat(ModAchievements.craftRedstoneArmor, 1);
        /*Swing Swing Faster! - Achievement Trigger*/
        if (stack.getItem() == emeraldHelmet || stack.getItem() == emeraldChestplate || stack.getItem() == emeraldLeggings || stack.getItem() == emeraldBoots)
            this.thePlayer.addStat(ModAchievements.craftEmeraldArmor, 1);
        /*Indestructible! - Achievement Trigger*/
        if (stack.getItem() == obsidianHelmet || stack.getItem() == obsidianChestplate || stack.getItem() == obsidianLeggings || stack.getItem() == obsidianBoots)
            this.thePlayer.addStat(ModAchievements.craftObsidianArmor, 1);
        /*The Overpowered! - Achievement Trigger*/
        if (stack.getItem() == lavaHelmet || stack.getItem() == lavaChestplate || stack.getItem() == lavaLeggings || stack.getItem() == lavaBoots)
            this.thePlayer.addStat(ModAchievements.craftLavaArmor, 1);
        /*Jump Jump Jump! - Achievement Trigger*/
        if (stack.getItem() == slimeHelmet || stack.getItem() == slimeChestplate || stack.getItem() == slimeLeggings || stack.getItem() == slimeBoots)
            this.thePlayer.addStat(ModAchievements.craftSlimeArmor, 1);
        /*Its time to go Faster! - Achievement Trigger*/
        if (stack.getItem() == chickenHelmet || stack.getItem() == chickenChestplate || stack.getItem() == chickenLeggings || stack.getItem() == chickenBoots)
            this.thePlayer.addStat(ModAchievements.craftChickenArmor, 1);
    }

    public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
        net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerCraftingEvent(playerIn, stack, craftMatrix);
        this.onCrafting(stack);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(playerIn);
        ItemStack[] aitemstack = WorkbenchCraftingManager.getInstance().getRemainingItems(this.craftMatrix, playerIn.world);
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
