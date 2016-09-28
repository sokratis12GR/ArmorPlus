/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.consumables;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.items.base.BaseItem;
import net.thedragonteam.core.util.LogHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.core.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.items.consumables
 * ArmorPlus created by sokratis12GR on 6/30/2016 2:59 PM.
 * - TheDragonTeam
 */
public class TheGiftOfTheGods extends BaseItem {

    private static Random random = new Random();
    private int cooldown = 0;

    public TheGiftOfTheGods() {
        super("the_gift_of_the_gods");
        setMaxDamage(maxUses);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.GOLD + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        List<String> blackListedItems = Arrays.asList(ARPConfig.blackListedItems);

        NBTTagCompound nbt;
        if (itemStackIn.hasTagCompound()) {
            nbt = itemStackIn.getTagCompound();
        } else {
            nbt = new NBTTagCompound();
        }

        if (nbt.hasKey("Clicked")) {
            nbt.setInteger("Clicked", nbt.getInteger("Clicked") + 1);
        } else {
            nbt.setInteger("Clicked", 1);
        }
        itemStackIn.setTagCompound(nbt);

        if (worldIn.isRemote) {
            return new ActionResult(EnumActionResult.PASS, itemStackIn);
        }

        int count;
        Item item = null;
        do {
            if (!ARPConfig.enableWhiteList) {
                count = 256 + random.nextInt(32000 - 256);
                item = Item.getItemById(count);
            }
            if (ARPConfig.enableWhiteList) {
                item = Item.getByNameOrId(whiteListedItems[random.nextInt(whitelistmax - whitelistmin + 1) + whitelistmin]);
            }
        }
        while (item == null /*|| (item != null && item instanceof ItemBlock)*/ || item == Item.getByNameOrId(blackListedItems.toString()) && enableBlackList);
        if (enableTheGiftOfTheGods) {
            if (!playerIn.getCooldownTracker().hasCooldown(itemStackIn.getItem()) && playerIn.getHeldItemMainhand().getItem() == itemStackIn.getItem() && !debugMode)
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItemMainhand().getItem(), cooldownTicks);
            else if (debugMode && debugModeGOTG)
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItemMainhand().getItem(), this.cooldown);

            playerIn.dropItem(item, 1);
<<<<<<< HEAD
            playerIn.addChatMessage(new TextComponentString("You got: " + item.getItemStackDisplayName(itemStackIn) + " [ Registry name: " + item.getRegistryName() + " ] "));
=======
            playerIn.addChatMessage(new TextComponentString("You got: " + item.getItemStackDisplayName(itemStackIn) + " ( " + item.getRegistryName() +" ) "));
>>>>>>> a5dd1e733636fed0f548ae9da8c1e97af1d5823b
            if (debugMode && debugModeGOTG)
                LogHelper.info("Item's Registry Name: " + item.getRegistryName() + " ; Item's Creative Tab: " + item.getCreativeTab() +
                        " ; Item's Unlocalized Name: " + item.getUnlocalizedName() + " ; Does the Item have Subtypes: " + item.getHasSubtypes() +
                        " ; Item's Max Damage: " + item.getMaxDamage());

            itemStackIn.damageItem(1, playerIn);
        }
        return new ActionResult(EnumActionResult.PASS, itemStackIn);

    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("\2479Ability: " + "\247rGrants Random Item");
        tooltip.add("\2473Use: " + "\247rRight-Click");
    }
}
