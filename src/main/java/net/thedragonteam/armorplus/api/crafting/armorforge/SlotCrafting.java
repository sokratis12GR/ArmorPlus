/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.armorforge;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.ARPAchievements;

import javax.annotation.Nullable;

/**
 * net.thedragonteam.armorplus.api.crafting.armorforge
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
            stack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.amountCrafted);
        }

        this.amountCrafted = 0;

        /**Guardian Armor Thorns*/
        if (stack.getItem() == ModItems.GUARDIAN_HELMET || stack.getItem() == ModItems.GUARDIAN_CHESTPLATE || stack.getItem() == ModItems.GUARDIAN_LEGGINGS || stack.getItem() == ModItems.GUARDIAN_BOOTS)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("thorns"), 3);
        /**Guardian Armor Unbreaking 3*/
        if (stack.getItem() == ModItems.GUARDIAN_HELMET || stack.getItem() == ModItems.GUARDIAN_CHESTPLATE || stack.getItem() == ModItems.GUARDIAN_LEGGINGS || stack.getItem() == ModItems.GUARDIAN_BOOTS)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 3);
        /**Full of Thorns! - Achievement Trigger*/
        if (stack.getItem() == ModItems.GUARDIAN_HELMET || stack.getItem() == ModItems.GUARDIAN_CHESTPLATE || stack.getItem() == ModItems.GUARDIAN_LEGGINGS || stack.getItem() == ModItems.GUARDIAN_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_GUARDIAN_ARMOR, 1);
        /** Guardian Boots Enchantments*/
        if (stack.getItem() == ModItems.GUARDIAN_BOOTS)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("depth_strider"), 3);
        /**Vision Like A Bat! - Achievement Trigger*/
        if (stack.getItem() == ModItems.COAL_HELMET || stack.getItem() == ModItems.COAL_CHESTPLATE || stack.getItem() == ModItems.COAL_LEGGINGS || stack.getItem() == ModItems.COAL_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_COAL_ARMOR, 1);
        /**Never Drown Again - Achievement Trigger*/
        if (stack.getItem() == ModItems.LAPIS_HELMET || stack.getItem() == ModItems.LAPIS_CHESTPLATE || stack.getItem() == ModItems.LAPIS_LEGGINGS || stack.getItem() == ModItems.LAPIS_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_LAPIS_ARMOR, 1);
        /**Speeedy! - Achievement Trigger*/
        if (stack.getItem() == ModItems.REDSTONE_HELMET || stack.getItem() == ModItems.REDSTONE_CHESTPLATE || stack.getItem() == ModItems.REDSTONE_LEGGINGS || stack.getItem() == ModItems.REDSTONE_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_REDSTONE_ARMOR, 1);
        /**Swing Swing Faster! - Achievement Trigger*/
        if (stack.getItem() == ModItems.EMERALD_HELMET || stack.getItem() == ModItems.EMERALD_CHESTPLATE || stack.getItem() == ModItems.EMERALD_LEGGINGS || stack.getItem() == ModItems.EMERALD_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_EMERALD_ARMOR, 1);
        /**Indestructible! - Achievement Trigger*/
        if (stack.getItem() == ModItems.OBSIDIAN_HELMET || stack.getItem() == ModItems.OBSIDIAN_CHESTPLATE || stack.getItem() == ModItems.OBSIDIAN_LEGGINGS || stack.getItem() == ModItems.OBSIDIAN_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_OBSIDIAN_ARMOR, 1);
        /**The Overpowered! - Achievement Trigger*/
        if (stack.getItem() == ModItems.LAVA_HELMET || stack.getItem() == ModItems.LAVA_CHESTPLATE || stack.getItem() == ModItems.LAVA_LEGGINGS || stack.getItem() == ModItems.LAVA_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_LAVA_ARMOR, 1);
        /**Jump Jump Jump! - Achievement Trigger*/
        if (stack.getItem() == ModItems.SLIME_HELMET || stack.getItem() == ModItems.SLIME_CHESTPLATE || stack.getItem() == ModItems.SLIME_LEGGINGS || stack.getItem() == ModItems.SLIME_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_SLIME_ARMOR, 1);
        /**Its time to go Faster! - Achievement Trigger*/
        if (stack.getItem() == ModItems.CHICKEN_HELMET || stack.getItem() == ModItems.CHICKEN_CHESTPLATE || stack.getItem() == ModItems.CHICKEN_LEGGINGS || stack.getItem() == ModItems.CHICKEN_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_CHICKEN_ARMOR, 1);

        /** Tinkers' Armors*/
        /**The Tinkers' Armors! - Achievement Trigger*/
        if (stack.getItem() == ModItems.COBALT_HELMET || stack.getItem() == ModItems.COBALT_CHESTPLATE || stack.getItem() == ModItems.COBALT_LEGGINGS || stack.getItem() == ModItems.COBALT_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_COBALT_ARMOR, 1);
        /**The Stronger The Better! - Achievement Trigger*/
        if (stack.getItem() == ModItems.ARDITE_HELMET || stack.getItem() == ModItems.ARDITE_CHESTPLATE || stack.getItem() == ModItems.ARDITE_LEGGINGS || stack.getItem() == ModItems.ARDITE_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_ARDITE_ARMOR, 1);
        /**The Tinkers' Armors God! - Achievement Trigger*/
        if (stack.getItem() == ModItems.MANYULLYN_HELMET || stack.getItem() == ModItems.MANYULLYN_CHESTPLATE || stack.getItem() == ModItems.MANYULLYN_LEGGINGS || stack.getItem() == ModItems.MANYULLYN_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_MANYULLYN_ARMOR, 1);
        /** Oink! - Achievemnt Trigger*/
        if (stack.getItem() == ModItems.PIG_IRON_HELMET || stack.getItem() == ModItems.PIG_IRON_CHESTPLATE || stack.getItem() == ModItems.PIG_IRON_LEGGINGS || stack.getItem() == ModItems.PIG_IRON_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_PIG_IRON_ARMOR, 1);
        /** Fascinating! - Achievemnt Trigger*/
        if (stack.getItem() == ModItems.KNIGHT_SLIME_HELMET || stack.getItem() == ModItems.KNIGHT_SLIME_CHESTPLATE || stack.getItem() == ModItems.KNIGHT_SLIME_LEGGINGS || stack.getItem() == ModItems.KNIGHT_SLIME_BOOTS)
            this.thePlayer.addStat(ARPAchievements.CRAFT_KNIGHT_SLIME_ARMOR, 1);
    }

    public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
        net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerCraftingEvent(playerIn, stack, craftMatrix);
        this.onCrafting(stack);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(playerIn);
        ItemStack[] aitemstack = ArmorForgeCraftingManager.getInstance().getRemainingItems(this.craftMatrix, playerIn.worldObj);
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
