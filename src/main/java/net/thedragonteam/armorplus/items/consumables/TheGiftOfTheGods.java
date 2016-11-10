/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.consumables;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.items.base.BaseItem;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.items.consumables
 * ArmorPlus created by sokratis12GR on 6/30/2016 2:59 PM.
 * - TheDragonTeam
 */
public class TheGiftOfTheGods extends BaseItem {

    private static Random random = new Random();

    public int maxUsable;

    public TheGiftOfTheGods() {
        super("the_gift_of_the_gods");
        this.maxUsable = maxUses - 1;
        setMaxDamage(maxUsable);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.GOLD + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        List<String> blackListedItems = Arrays.asList(ARPConfig.blackListedItems);

        NBTTagCompound nbt;
        nbt = itemStackIn.hasTagCompound() ? itemStackIn.getTagCompound() : new NBTTagCompound();

        nbt.setInteger("Clicked", nbt.hasKey("Clicked") ? nbt.getInteger("Clicked") + 1 : 1);
        itemStackIn.setTagCompound(nbt);

        if (worldIn.isRemote)
            return new ActionResult<>(EnumActionResult.PASS, itemStackIn);

        int count;
        Item item = null;
        do if (!ARPConfig.enableWhiteList) {
            count = 256 + random.nextInt(32000 - 256);
            item = Item.getItemById(count);
        } else if (ARPConfig.enableWhiteList)
            item = Item.getByNameOrId(whiteListedItems[random.nextInt(whitelistmax - whitelistmin + 1) + whitelistmin]);

        while (item == null || item == Item.getByNameOrId(blackListedItems.toString()) && enableBlackList);
        if (enableTheGiftOfTheGods) {
            int cooldown = 0;
            if (playerIn.getHeldItemMainhand() != null && playerIn.getHeldItemMainhand().getItem() == itemStackIn.getItem() || playerIn.getHeldItemOffhand() != null && playerIn.getHeldItemOffhand().getItem() == itemStackIn.getItem())
                if (!debugMode && !playerIn.getCooldownTracker().hasCooldown(itemStackIn.getItem())) {
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItemMainhand().getItem(), cooldownTicks);
                } else if (debugMode && debugModeTGOTG)
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItemMainhand().getItem(), cooldown);

            playerIn.dropItem(item, 1);
            playerIn.addChatMessage(new TextComponentString("You got: " + item.getItemStackDisplayName(itemStackIn) + " [" + item.getRegistryName() + "]"));
            if (debugMode && debugModeTGOTG)
                LogHelper.info("Item's Registry Name: " + item.getRegistryName() + " ; Item's Creative Tab: " + item.getCreativeTab() +
                        " ; Item's Unlocalized Name: " + item.getUnlocalizedName() + " ; Does the Item have Subtypes: " + item.getHasSubtypes() +
                        " ; Item's Max Damage: " + item.getMaxDamage());

            itemStackIn.damageItem(1, playerIn);
        }
        return new ActionResult<>(EnumActionResult.PASS, itemStackIn);

    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        int maxUses = ARPConfig.maxUses;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247rGrants Random Item");
            tooltip.add("\2479Max Uses: " + "\247r" + maxUses);
            tooltip.add("\2473Use: " + "\247rRight-Click");
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", ChatFormatting.GOLD, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }
}