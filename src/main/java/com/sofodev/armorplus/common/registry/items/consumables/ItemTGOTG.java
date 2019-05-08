/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.consumables;

import com.sofodev.armorplus.client.utils.ToolTipUtils;
import com.sofodev.armorplus.common.registry.items.base.ItemBase;
import com.sofodev.thedragonlib.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
import static net.minecraftforge.registries.ForgeRegistries.ITEMS;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemTGOTG extends ItemBase {

    private Random random = new Random();

    public ItemTGOTG(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack heldStack = playerIn.getHeldItem(hand);

        NBTTagCompound nbt = (heldStack.hasTag()) ? heldStack.getTag() : new NBTTagCompound();

        nbt.putInt("Clicked", (nbt.contains("Clicked")) ? nbt.getInt("Clicked") + 1 : 1);
        heldStack.setTag(nbt);

        int count;
        Item item = Items.AIR;
        while (item == Items.AIR || new ItemStack(item).isEmpty()) {
            if (tgotg.enableBlackList) {
                String[] blackList = tgotg.blackListedItems;
                for (String disabled : blackList) {
                    if (item == ITEMS.getValue(new ResourceLocation(disabled))) {
                        break;
                    }
                }
            }
            if (tgotg.enableWhiteList) {
                String[] whiteList = tgotg.whiteListedItems;
                ResourceLocation rl = new ResourceLocation(whiteList[random.nextInt(whiteList.length + 1)]);
                item = ITEMS.getValue(rl);
            } else {
                ArrayList<Item> items = new ArrayList<>(ITEMS.getValues());
                count = random.nextInt(items.size());
                item = items.get(count);
            }
        }

        CooldownTracker tracker = playerIn.getCooldownTracker();
        boolean flag = debugMode && debugModeTGOTG;
        if (!worldIn.isRemote) {
            if (tgotg.enable) {
                this.setCooldown(tracker, heldStack, flag);
                playerIn.dropItem(new ItemStack(item), false);
                playerIn.sendStatusMessage(translate("status.armorplus.tgotg.gained_item", item.getDisplayName(heldStack), item.getRegistryName()), false);
                if (debugMode && debugModeTGOTG) {
                    LogHelper.info(format("Item's Registry Name: {0};\n Item's Creative Tab: {1}\n{2}{3}", item.getRegistryName(), item.getGroup(),
                        " ; Item's Unlocalized Name: " + item.getTranslationKey() + ";\n",
                        " ; Item's Max Damage: " + getMaxDamage(new ItemStack(item))));
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

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        int maxUses = tgotg.maxUses;
        tooltip.add(new TextComponentString(format("{0}{1}This item can summon items which can potentially cause crashes", ITALIC, RED)));
        if (InputMappings.isKeyDown(keyBindSneak.getKey().getKeyCode())) {
            tooltip.add(new TextComponentString("\u00a79Ability: \u00a7rGrants Random Item"));
            tooltip.add(new TextComponentString("\u00a79Max Uses: \u00a7r" + maxUses));
            tooltip.add(new TextComponentString("\u00a73Use: \u00a7rRight-Click"));
        } else {
            ToolTipUtils.showInfo(tooltip, keyBindSneak, GOLD);
        }
    }

}