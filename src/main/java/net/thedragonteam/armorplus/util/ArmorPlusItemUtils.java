/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.thedragonteam.armorplus.ModConfig;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.addPotion;
import static net.thedragonteam.armorplus.util.Utils.convertToSeconds;
import static net.thedragonteam.armorplus.util.Utils.isArmorEmpty;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public final class ArmorPlusItemUtils {

    public static boolean isItemRepairable(ItemStack repair, ItemStack expert) {
        return ModConfig.getRD().isItemRepairable(repair, expert);
    }

    public static boolean isFullSet(EntityPlayer player, Item head, Item chest, Item legs, Item feet) {
        ItemStack headStack = getHead(player), chestStack = getChest(player), legsStack = getLegs(player), feetStack = getFeet(player);
        if (isArmorEmpty(headStack, chestStack, legsStack, feetStack)) {
            return false;
        }
        return (headStack.getItem() == head && chestStack.getItem() == chest && legsStack.getItem() == legs && feetStack.getItem() == feet);
    }

    public static ItemStack getHead(EntityPlayer player) {
        return player.getItemStackFromSlot(HEAD);
    }

    public static ItemStack getChest(EntityPlayer player) {
        return player.getItemStackFromSlot(CHEST);
    }

    public static ItemStack getLegs(EntityPlayer player) {
        return player.getItemStackFromSlot(LEGS);
    }

    public static ItemStack getFeet(EntityPlayer player) {
        return player.getItemStackFromSlot(FEET);
    }

    public static void applyEffects(EntityPlayer player, List<String> applyEffectNames, List<Integer> applyEffectDurations, List<Integer> applyEffectLevels, List<String> removableEffects) {
        List<Potion> potions = applyEffectNames.stream().map(PotionUtils::getPotion).collect(toList());
        IntStream.range(0, potions.size()).forEach(potionID -> {
            Potion potionEffect = potions.get(potionID);
            if ((player.getActivePotionEffect(potionEffect) == null || potionEffect == MobEffects.NIGHT_VISION)) {
                addPotion(player, potionEffect, convertToSeconds(applyEffectDurations.get(potionID)), applyEffectLevels.get(potionID), GOOD);
            }
        });

        List<Potion> removablePotions = removableEffects.stream().map(PotionUtils::getPotion).collect(toList());
        removablePotions.stream().filter(
            potionEffect -> player.getActivePotionEffect(potionEffect) != null
        ).forEach(
            player::removeActivePotionEffect
        );
    }
}
