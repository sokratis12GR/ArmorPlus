package sokratis12gr.armorplus.api.crafting;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import sokratis12gr.armorplus.armors.origin.*;
import sokratis12gr.armorplus.armors.reinforced.RCArmor;
import sokratis12gr.armorplus.armors.reinforced.RDArmor;
import sokratis12gr.armorplus.armors.reinforced.RGArmor;
import sokratis12gr.armorplus.armors.reinforced.RIArmor;
import sokratis12gr.armorplus.armors.special.EnderDragonArmor;
import sokratis12gr.armorplus.armors.special.GuardianArmor;
import sokratis12gr.armorplus.armors.special.SuperStarArmor;
import sokratis12gr.armorplus.armors.special.TheUltimateArmor;
import sokratis12gr.armorplus.armors.special.mob.ChickenArmor;
import sokratis12gr.armorplus.armors.special.mob.SlimeArmor;
import sokratis12gr.armorplus.armors.tconstruct.*;
import sokratis12gr.armorplus.util.ARPAchievements;

import javax.annotation.Nullable;

/**
 * sokratis12gr.armorplus.api
 * ArmorPlus created by sokratis12GR on 6/21/2016 3:55 PM.
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
        if (stack.getItem() == GuardianArmor.helmet || stack.getItem() == GuardianArmor.chestplate || stack.getItem() == GuardianArmor.legs || stack.getItem() == GuardianArmor.boots || stack.getItem() == TheUltimateArmor.helmet || stack.getItem() == TheUltimateArmor.chestplate || stack.getItem() == TheUltimateArmor.legs || stack.getItem() == TheUltimateArmor.boots)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("thorns"), 3);
        /**Guardian Armor Unbreaking 3*/
        if (stack.getItem() == GuardianArmor.helmet || stack.getItem() == GuardianArmor.chestplate || stack.getItem() == GuardianArmor.legs || stack.getItem() == GuardianArmor.boots || stack.getItem() == TheUltimateArmor.helmet || stack.getItem() == TheUltimateArmor.chestplate || stack.getItem() == TheUltimateArmor.legs || stack.getItem() == TheUltimateArmor.boots)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 3);
        /**Full of Thorns! - Achievement Trigger*/
        if (stack.getItem() == GuardianArmor.helmet || stack.getItem() == GuardianArmor.chestplate || stack.getItem() == GuardianArmor.legs || stack.getItem() == GuardianArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_GUARDIAN_ARMOR, 1);
        /** Guardian Boots Enchantments*/
        if (stack.getItem() == GuardianArmor.boots || stack.getItem() == TheUltimateArmor.boots)
            stack.addEnchantment(Enchantment.getEnchantmentByLocation("depth_strider"), 3);
        /**Vision Like A Bat! - Achievement Trigger*/
        if (stack.getItem() == CoalArmor.helmet || stack.getItem() == CoalArmor.chestplate || stack.getItem() == CoalArmor.legs || stack.getItem() == CoalArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_COAL_ARMOR, 1);
        /**Never Drown Again - Achievement Trigger*/
        if (stack.getItem() == LapisArmor.helmet || stack.getItem() == LapisArmor.chestplate || stack.getItem() == LapisArmor.legs || stack.getItem() == LapisArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_LAPIS_ARMOR, 1);
        /**Speeedy! - Achievement Trigger*/
        if (stack.getItem() == RedstoneArmor.helmet || stack.getItem() == RedstoneArmor.chestplate || stack.getItem() == RedstoneArmor.legs || stack.getItem() == RedstoneArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_REDSTONE_ARMOR, 1);
        /**Swing Swing Faster! - Achievement Trigger*/
        if (stack.getItem() == EmeraldArmor.helmet || stack.getItem() == EmeraldArmor.chestplate || stack.getItem() == EmeraldArmor.legs || stack.getItem() == EmeraldArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_EMERALD_ARMOR, 1);
        /**Undestructable! - Achievement Trigger*/
        if (stack.getItem() == ObsidianArmor.helmet || stack.getItem() == ObsidianArmor.chestplate || stack.getItem() == ObsidianArmor.legs || stack.getItem() == ObsidianArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_OBSIDIAN_ARMOR, 1);
        /**The Overpowered! - Achievement Trigger*/
        if (stack.getItem() == LavaArmor.helmet || stack.getItem() == LavaArmor.chestplate || stack.getItem() == LavaArmor.legs || stack.getItem() == LavaArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_LAVA_ARMOR, 1);
        /**Godlike! - Achievement Trigger*/
        if (stack.getItem() == SuperStarArmor.helmet || stack.getItem() == SuperStarArmor.chestplate || stack.getItem() == SuperStarArmor.legs || stack.getItem() == SuperStarArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_SUPER_STAR_ARMOR, 1);
        /**The Power of the Ender Dragon! - Achievement Trigger*/
        if (stack.getItem() == EnderDragonArmor.helmet || stack.getItem() == EnderDragonArmor.chestplate || stack.getItem() == EnderDragonArmor.legs || stack.getItem() == SuperStarArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_ENDER_DRAGON_ARMOR, 1);
        /**The Ultimate Power! - Achievement Trigger*/
        if (stack.getItem() == TheUltimateArmor.helmet || stack.getItem() == TheUltimateArmor.chestplate || stack.getItem() == TheUltimateArmor.legs || stack.getItem() == TheUltimateArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_THE_ULTIMATE_ARMOR, 1);
        /**Jump Jump Jump! - Achievement Trigger*/
        if (stack.getItem() == SlimeArmor.helmet || stack.getItem() == SlimeArmor.chestplate || stack.getItem() == SlimeArmor.legs || stack.getItem() == SlimeArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_SLIME_ARMOR, 1);
        /**Its time to go Faster! - Achievement Trigger*/
        if (stack.getItem() == ChickenArmor.helmet || stack.getItem() == ChickenArmor.chestplate || stack.getItem() == ChickenArmor.legs || stack.getItem() == ChickenArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_CHICKEN_ARMOR, 1);
        /** Reinforcing Armors! - Achievement Trigger*/
        if (stack.getItem() == RCArmor.helmet || stack.getItem() == RCArmor.chestplate || stack.getItem() == RCArmor.legs || stack.getItem() == RCArmor.boots
                || stack.getItem() == RDArmor.helmet || stack.getItem() == RDArmor.chestplate || stack.getItem() == RDArmor.legs || stack.getItem() == RDArmor.boots
                || stack.getItem() == RGArmor.helmet || stack.getItem() == RGArmor.chestplate || stack.getItem() == RGArmor.legs || stack.getItem() == RGArmor.boots
                || stack.getItem() == RIArmor.helmet || stack.getItem() == RIArmor.chestplate || stack.getItem() == RIArmor.legs || stack.getItem() == RIArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_REINFORCED_ARMOR, 1);

        /** Tinkers' Armors*/
        /**The Tinkers' Armors! - Achievement Trigger*/
        if (stack.getItem() == CobaltArmor.helmet || stack.getItem() == CobaltArmor.chestplate || stack.getItem() == CobaltArmor.legs || stack.getItem() == CobaltArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_COBALT_ARMOR, 1);
        /**The Stronger The Better! - Achievement Trigger*/
        if (stack.getItem() == ArditeArmor.helmet || stack.getItem() == ArditeArmor.chestplate || stack.getItem() == ArditeArmor.legs || stack.getItem() == ArditeArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_ARDITE_ARMOR, 1);
        /**The Tinkers' Armors God! - Achievement Trigger*/
        if (stack.getItem() == ManyullynArmor.helmet || stack.getItem() == ManyullynArmor.chestplate || stack.getItem() == ManyullynArmor.legs || stack.getItem() == ManyullynArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_MANYULLYN_ARMOR, 1);
        /** Oink! - Achievemnt Trigger*/
        if (stack.getItem() == PigIronArmor.helmet || stack.getItem() == PigIronArmor.chestplate || stack.getItem() == PigIronArmor.legs || stack.getItem() == PigIronArmor.boots)
            this.thePlayer.addStat(ARPAchievements.CRAFT_PIG_IRON_ARMOR, 1);
        /** Fascinating! - Achievemnt Trigger*/
        if (stack.getItem() == KnightSlimeArmor.helmet || stack.getItem() == KnightSlimeArmor.chestplate || stack.getItem() == KnightSlimeArmor.legs || stack.getItem() == KnightSlimeArmor.boots)
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
