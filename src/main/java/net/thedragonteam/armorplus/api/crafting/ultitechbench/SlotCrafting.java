/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.registry.ModAchievements;

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

        if (stack.getItem() == ModItems.guardianSword)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 1);
        if (stack.getItem() == ModItems.guardianBattleAxe)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 1);
        if (stack.getItem() == ModItems.guardianBow)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("power"), 1);
        /*Guardian Armor Thorns*/
        if (stack.getItem() == ModItems.guardianHelmet || stack.getItem() == ModItems.guardianChestplate || stack.getItem() == ModItems.guardianLeggings || stack.getItem() == ModItems.guardianBoots)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("thorns"), 3);
        /*Guardian Armor Unbreaking 3*/
        if (stack.getItem() == ModItems.guardianHelmet || stack.getItem() == ModItems.guardianChestplate || stack.getItem() == ModItems.guardianLeggings || stack.getItem() == ModItems.guardianBoots)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 3);
        /* Guardian Boots Enchantments*/
        if (stack.getItem() == ModItems.guardianBoots)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("depth_strider"), 3);
        /*Mending*/
        if (stack.getItem() == ModItems.theUltimateHelmet || stack.getItem() == ModItems.theUltimateChestplate || stack.getItem() == ModItems.theUltimateLeggings || stack.getItem() == ModItems.theUltimateBoots)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("mending"), 1);
        /*Full of Thorns! - Achievement Trigger*/
        if (stack.getItem() == ModItems.guardianHelmet || stack.getItem() == ModItems.guardianChestplate || stack.getItem() == ModItems.guardianLeggings || stack.getItem() == ModItems.guardianBoots)
            this.thePlayer.addStat(ModAchievements.craftGuardianArmor, 1);
        /*Godlike! - Achievement Trigger*/
        if (stack.getItem() == ModItems.superStarHelmet || stack.getItem() == ModItems.superStarChestplate || stack.getItem() == ModItems.superStarLeggings || stack.getItem() == ModItems.superStarBoots)
            this.thePlayer.addStat(ModAchievements.craftSuperStarArmor, 1);
        /*The Power of the Ender Dragon! - Achievement Trigger*/
        if (stack.getItem() == ModItems.enderDragonHelmet || stack.getItem() == ModItems.enderDragonChestplate || stack.getItem() == ModItems.enderDragonLeggings || stack.getItem() == ModItems.enderDragonBoots)
            this.thePlayer.addStat(ModAchievements.craftEnderDragonArmor, 1);
        /*The Ultimate Power! - Achievement Trigger*/
        if (stack.getItem() == ModItems.theUltimateHelmet || stack.getItem() == ModItems.theUltimateChestplate || stack.getItem() == ModItems.theUltimateLeggings || stack.getItem() == ModItems.theUltimateBoots)
            this.thePlayer.addStat(ModAchievements.craftTheUltimatermor, 1);
    }

    public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
        FMLCommonHandler.instance().firePlayerCraftingEvent(playerIn, stack, craftMatrix);
        this.onCrafting(stack);
        ForgeHooks.setCraftingPlayer(playerIn);
        ItemStack[] aitemstack = UltiTechBenchCraftingManager.getInstance().getRemainingItems(this.craftMatrix, playerIn.world);
        ForgeHooks.setCraftingPlayer(null);

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
