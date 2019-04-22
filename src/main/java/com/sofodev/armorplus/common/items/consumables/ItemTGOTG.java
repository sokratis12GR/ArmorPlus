/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.items.consumables;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.client.utils.ToolTipUtils;
import com.sofodev.armorplus.common.items.base.ItemBase;
import com.sofodev.armorplus.common.util.EnumHelperUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.thedragonlib.util.ItemStackUtils;
import net.thedragonteam.thedragonlib.util.LogHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.sofodev.armorplus.common.config.ModConfig.DebugConfig.debugMode;
import static com.sofodev.armorplus.common.config.ModConfig.DebugConfig.debugModeTGOTG;
import static com.sofodev.armorplus.common.config.ModConfig.MainConfig.tgotg;
import static com.sofodev.armorplus.common.util.TextUtils.translate;
import static java.text.MessageFormat.format;
import static net.minecraft.util.text.TextFormatting.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemTGOTG extends ItemBase {

    private Random random = new Random();

    public ItemTGOTG() {
        super("the_gift_of_the_gods");
        int maxUsable = tgotg.maxUses - 1;
        this.setMaxDamage(maxUsable);
        this.setCreativeTab(ArmorPlus.tabArmorPlusItems);
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return this.getRarity(GOLD, "GOLD");
    }

    @SuppressWarnings("unchecked")
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack heldStack = playerIn.getHeldItem(hand);

        NBTTagCompound nbt = (heldStack.hasTagCompound()) ? heldStack.getTagCompound() : new NBTTagCompound();

        nbt.setInteger("Clicked", (nbt.hasKey("Clicked")) ? nbt.getInteger("Clicked") + 1 : 1);
        heldStack.setTagCompound(nbt);

        int count;
        Item item = null;
        while (item == null || getItemStack(item).isEmpty()) {
            if (tgotg.enableBlackList) {
                String[] blackList = tgotg.blackListedItems;
                for (String disabled : blackList) {
                    if (item == ItemStackUtils.getItem(disabled)) {
                        break;
                    }
                }
            }
            if (tgotg.enableWhiteList) {
                String[] whiteList = tgotg.whiteListedItems;
                ResourceLocation rl = new ResourceLocation(whiteList[random.nextInt(whiteList.length + 1)]);
                item = ForgeRegistries.ITEMS.getValue(rl);
            } else {
                ArrayList<Item> items = new ArrayList<>(ForgeRegistries.ITEMS.getValuesCollection());
                count = random.nextInt(items.size());
                item = items.get(count);
            }
        }

        CooldownTracker tracker = playerIn.getCooldownTracker();
        boolean flag = debugMode && debugModeTGOTG;
        if (!worldIn.isRemote) {
            if (tgotg.enable) {
                this.setCooldown(tracker, heldStack, flag);
                playerIn.dropItem(item, 1);
                playerIn.sendStatusMessage(translate("status.armorplus.tgotg.gained_item", item.getItemStackDisplayName(heldStack), item.getRegistryName()), false);
                if (debugMode && debugModeTGOTG) {
                    LogHelper.info(format("Item's Registry Name: {0};\n Item's Creative Tab: {1}\n{2}{3}", item.getRegistryName(), item.getCreativeTab(),
                        " ; Item's Unlocalized Name: " + item.getTranslationKey() + ";\n Does the Item have Subtypes: " + item.getHasSubtypes() + "\n",
                        " ; Item's Max Damage: " + getMaxDamage(getItemStack(item))));
                }
            }
            heldStack.damageItem(1, playerIn);
        }
        return new ActionResult(EnumActionResult.PASS, heldStack);
    }

    private void setCooldown(CooldownTracker tracker, ItemStack heldStack, boolean flag) {
        int cd = 0;
        Item handItem = heldStack.getItem();
        if (!heldStack.isEmpty()) {
            if (!flag && !tracker.hasCooldown(handItem)) {
                tracker.setCooldown(handItem, tgotg.cooldownTicks);
            } else if (flag) {
                tracker.setCooldown(handItem, cd);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        int maxUses = tgotg.maxUses;
        tooltip.add(format("{0}{1}This item can summon items which can potentially cause crashes", ITALIC, RED));
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\u00a79Ability: \u00a7rGrants Random Item");
            tooltip.add("\u00a79Max Uses: \u00a7r" + maxUses);
            tooltip.add("\u00a73Use: \u00a7rRight-Click");
        } else {
            ToolTipUtils.showInfo(tooltip, keyBindSneak, GOLD);
        }
    }

}