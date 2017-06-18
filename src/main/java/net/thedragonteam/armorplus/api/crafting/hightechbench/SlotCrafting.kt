/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.inventory.Slot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.common.ForgeHooks
import net.thedragonteam.armorplus.registry.APBlocks.ultiTechBench
import net.thedragonteam.armorplus.registry.APItems.*
import net.thedragonteam.armorplus.registry.ModAchievements

/**
 * net.thedragonteam.armorplus.api.crafting.hightechbench
 * ArmorPlus created by sokratis12GR on 6/21/2016 3:55 PM.
 * - TheDragonTeam
 */
class SlotCrafting(
        /**
         * The player that is using the GUI where this slot resides.
         */
        private val player: EntityPlayer,
        /**
         * The craft matrix inventory linked to this result slot.
         */
        private val craftMatrix: InventoryCrafting, inventoryIn: IInventory, slotIndex: Int, xPosition: Int, yPosition: Int) : Slot(inventoryIn, slotIndex, xPosition, yPosition) {
    /**
     * The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset.
     */
    private var amountCrafted: Int = 0

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor itemHandler as well as furnace fuel.
     */
    override fun isItemValid(stack: ItemStack?): Boolean {
        return false
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    override fun decrStackSize(amount: Int): ItemStack {
        if (this.hasStack) this.amountCrafted += Math.min(amount, this.stack.count)

        return super.decrStackSize(amount)
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    override fun onCrafting(stack: ItemStack?, amount: Int) {
        this.amountCrafted += amount
        this.onCrafting(stack)
    }

    override fun onSwapCraft(p_190900_1_: Int) {
        this.amountCrafted += p_190900_1_
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    override fun onCrafting(stack: ItemStack?) {

        if (this.amountCrafted > 0) {
            stack!!.onCrafting(this.player.world, this.player, this.amountCrafted)
        }

        this.amountCrafted = 0

        if (stack!!.item === Item.getItemFromBlock(ultiTechBench))
            this.player.addStat(ModAchievements.craftUltiTechBench, 1)

        when {
            stack!!.item === lavaHelmet || stack!!.item === lavaChestplate || stack!!.item === lavaLeggings || stack!!.item === lavaBoots -> this.player.addStat(ModAchievements.craftLavaArmor, 1)
            stack!!.item === emeraldHelmet || stack!!.item === emeraldChestplate || stack!!.item === emeraldLeggings || stack!!.item === emeraldBoots -> this.player.addStat(ModAchievements.craftEmeraldArmor, 1)
            stack!!.item === obsidianHelmet || stack!!.item === obsidianChestplate || stack!!.item === obsidianLeggings || stack!!.item === obsidianBoots -> this.player.addStat(ModAchievements.craftObsidianArmor, 1)
            stack!!.item === cobaltHelmet || stack!!.item === cobaltChestplate || stack!!.item === cobaltLeggings || stack!!.item === cobaltBoots -> this.player.addStat(ModAchievements.craftCobaltArmor, 1)
            stack!!.item === arditeHelmet || stack!!.item === arditeChestplate || stack!!.item === arditeLeggings || stack!!.item === arditeBoots -> this.player.addStat(ModAchievements.craftArditeArmor, 1)
            stack!!.item === manyullynHelmet || stack!!.item === manyullynChestplate || stack!!.item === manyullynLeggings || stack!!.item === manyullynBoots -> this.player.addStat(ModAchievements.craftManyullynArmor, 1)
            stack!!.item === pigIronHelmet || stack!!.item === pigIronChestplate || stack!!.item === pigIronLeggings || stack!!.item === pigIronBoots -> this.player.addStat(ModAchievements.craftPigIronArmor, 1)
            stack!!.item === knightSlimeHelmet || stack!!.item === knightSlimeChestplate || stack!!.item === knightSlimeLeggings || stack!!.item === knightSlimeBoots -> this.player.addStat(ModAchievements.craftKnightSlimeArmor, 1)/* Fascinating! - Achievement Trigger*//* Oink! - Achievement Trigger*//*The Tinkers' Armors God! - Achievement Trigger*//*The Stronger The Better! - Achievement Trigger*/
        }
    }

    override fun onTake(player: EntityPlayer?, itemStack: ItemStack): ItemStack {
        this.onCrafting(itemStack)
        ForgeHooks.setCraftingPlayer(player)
        val nonnulllist = HighTechBenchCraftingManager.getInstance().getRemainingItems(this.craftMatrix, player!!.world)
        ForgeHooks.setCraftingPlayer(null)

        for (i in nonnulllist.indices) {
            var itemstack = this.craftMatrix.getStackInSlot(i)

            if (!itemstack.isEmpty) {
                this.craftMatrix.decrStackSize(i, 1)
                itemstack = this.craftMatrix.getStackInSlot(i)
            }

            if (!nonnulllist[i].isEmpty) {
                if (itemstack.isEmpty) this.craftMatrix.setInventorySlotContents(i, nonnulllist[i])
                else if (ItemStack.areItemsEqual(itemstack, nonnulllist[i]) && ItemStack.areItemStackTagsEqual(itemstack, nonnulllist[i])) {
                    nonnulllist[i].grow(itemstack.count)
                    this.craftMatrix.setInventorySlotContents(i, nonnulllist[i])
                }
                else if (!this.player.inventory.addItemStackToInventory(nonnulllist[i])) this.player.dropItem(nonnulllist[i], false)
            }
        }

        return itemStack
    }
}
