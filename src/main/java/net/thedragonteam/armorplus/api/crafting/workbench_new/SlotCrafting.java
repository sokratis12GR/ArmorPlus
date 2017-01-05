/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench_new;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.thedragonteam.armorplus.container.inventory.InventoryCraftingNew;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.util.APAchievements;

import static net.thedragonteam.armorplus.registry.ModItems.*;

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
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) {
            this.amountCrafted += Math.min(amount, this.getStack().getCount());
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

        if (stack.getItem() == Item.getItemFromBlock(ModBlocks.benches[1]))
            this.player.addStat(APAchievements.craftHighTechBench, 1);

        if (stack.getItem() == sword[1])
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        if (stack.getItem() == lapisBattleAxe)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        if (stack.getItem() == lapisBow)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        /*Vision Like A Bat! - Achievement Trigger*/
        if (stack.getItem() == coal[0] || stack.getItem() == coal[1] || stack.getItem() == coal[2] || stack.getItem() == coal[3])
            this.player.addStat(APAchievements.craftCoalArmor, 1);
        /*Never Drown Again - Achievement Trigger*/
        if (stack.getItem() == lapis[0] || stack.getItem() == lapis[1] || stack.getItem() == lapis[2] || stack.getItem() == lapis[3])
            this.player.addStat(APAchievements.craftLapisArmor, 1);
        /*Speeedy! - Achievement Trigger*/
        if (stack.getItem() == redstone[0] || stack.getItem() == redstone[1] || stack.getItem() == redstone[2] || stack.getItem() == redstone[3])
            this.player.addStat(APAchievements.craftRedstoneArmor, 1);
        /*Swing Swing Faster! - Achievement Trigger*/
        if (stack.getItem() == emerald[0] || stack.getItem() == emerald[1] || stack.getItem() == emerald[2] || stack.getItem() == emerald[3])
            this.player.addStat(APAchievements.craftEmeraldArmor, 1);
        /*Indestructible! - Achievement Trigger*/
        if (stack.getItem() == obsidian[0] || stack.getItem() == obsidian[1] || stack.getItem() == obsidian[2] || stack.getItem() == obsidian[3])
            this.player.addStat(APAchievements.craftObsidianArmor, 1);
        /*The Overpowered! - Achievement Trigger*/
        if (stack.getItem() == lava[0] || stack.getItem() == lava[1] || stack.getItem() == lava[2] || stack.getItem() == lava[3])
            this.player.addStat(APAchievements.craftLavaArmor, 1);
        /*Jump Jump Jump! - Achievement Trigger*/
        if (stack.getItem() == slime[0] || stack.getItem() == slime[1] || stack.getItem() == slime[2] || stack.getItem() == slime[3])
            this.player.addStat(APAchievements.craftSlimeArmor, 1);
        /*Its time to go Faster! - Achievement Trigger*/
        if (stack.getItem() == chicken[0] || stack.getItem() == chicken[1] || stack.getItem() == chicken[2] || stack.getItem() == chicken[3])
            this.player.addStat(APAchievements.craftChickenArmor, 1);
    }


    public ItemStack onTake(EntityPlayer player, ItemStack stack) {
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
