/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import com.sofodev.armorplus.api.properties.AbilityCanceller;
import com.sofodev.armorplus.api.properties.AbilityProvider;
import com.sofodev.armorplus.api.properties.iface.IEffectHolder;
import com.sofodev.armorplus.items.weapons.effects.Negative;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

import java.util.List;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.util.PotionUtils.PotionValue.BAD;
import static com.sofodev.armorplus.util.PotionUtils.PotionValue.GOOD;
import static com.sofodev.armorplus.util.PotionUtils.addPotion;
import static com.sofodev.armorplus.util.PotionUtils.getPotion;
import static com.sofodev.armorplus.util.Utils.boxList;
import static java.util.stream.Collectors.toList;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public final class ArmorPlusItemUtils {

    private ArmorPlusItemUtils() {
    }

    //=========================================================
    //||                                                     ||
    //||                        ITEMS                        ||
    //||                                                     ||
    //=========================================================

    /**
     * @param repair The ItemStack that we will check if it can be repaired
     * @param expert The ItemStack that will represent the item that will be used for repairing the repair ItemStack
     * @return Returns true if the repair ItemStack can be repaired depending on the `game difficulty` set in armorplus' configuration file.
     */
    public static boolean isItemRepairable(ItemStack repair, ItemStack expert) {
        return true;//todo ModConfig.getRD().isItemRepairable(repair, expert);
    }

    //=========================================================
    //||                                                     ||
    //||               ARMORS & EQUIPMENT GEAR               ||
    //||                                                     ||
    //=========================================================

    /**
     * @param player The player entity that we will check if the full armor set is equipped.
     * @param head   The head armor piece item.
     * @param chest  The chest armor piece item.
     * @param legs   The legs armor piece item.
     * @param feet   The feet armor piece item.
     * @return Returns a validated result of the check if the player has the full armor set equipped.
     */
    public static boolean isFullSet(EntityPlayer player, Item head, Item chest, Item legs, Item feet) {
        ItemStack headStack = getHead(player);
        ItemStack chestStack = getChest(player);
        ItemStack legsStack = getLegs(player);
        ItemStack feetStack = getFeet(player);
        return !isArmorEmpty(headStack, chestStack, legsStack, feetStack) && areEqual(headStack, head) && areEqual(chestStack, chest) && areEqual(legsStack, legs) && areEqual(feetStack, feet);
    }

    /**
     * @param a The ItemStack that we will get the Item from.
     * @param b The Item that we will compare with the Item from ItemStack `a`
     * @return Returns true if the items are the same, false if they are not.
     */
    public static boolean areEqual(ItemStack a, Item b) {
        return a.getItem() == b;
    }

    /**
     * @param helmet     The helmet ItemStack
     * @param chestplate The chestplate ItemStack
     * @param leggings   The leggings ItemStack
     * @param boots      The boots ItemStack
     * @return Returns true if any of the equipped armor pieces are missing (Only needed for checking if a full armor set has valid ItemStacks)
     */
    public static boolean isArmorEmpty(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        return (helmet.isEmpty() || chestplate.isEmpty() || leggings.isEmpty() || boots.isEmpty());
    }

    /**
     * @param player The player that we'll be getting the equipment data from
     * @return Returns the ItemStack that is located in the head (Armor) slot
     */
    public static ItemStack getHead(EntityPlayer player) {
        return player.getItemStackFromSlot(HEAD);
    }

    /**
     * @param player The player that we'll be getting the equipment data from
     * @return Returns the ItemStack that is located in the chest (Armor) slot
     */
    public static ItemStack getChest(EntityPlayer player) {
        return player.getItemStackFromSlot(CHEST);
    }

    /**
     * @param player The player that we'll be getting the equipment data from
     * @return Returns the ItemStack that is located in the legs (Armor) slot
     */
    public static ItemStack getLegs(EntityPlayer player) {
        return player.getItemStackFromSlot(LEGS);
    }

    /**
     * @param player The player that we'll be getting the equipment data from
     * @return Returns the ItemStack that is located in the feet (Armor) slot
     */
    public static ItemStack getFeet(EntityPlayer player) {
        return player.getItemStackFromSlot(FEET);
    }


    public static void applyEffects(EntityPlayer player, IEffectHolder effectHolder) {
        AbilityProvider applicable = effectHolder.getApplicableAbilities();
        AbilityCanceller removable = effectHolder.getRemovableAbilities();
        applyEffects(player, boxList(applicable.getAbilities().name), boxList(applicable.getAbilities().duration), boxList(applicable.getAbilities().level), boxList(removable.getAbilities()));
    }

    /**
     * @param player               The player that we will apply the effects on (The entity with the equipped armor pieces)
     * @param applyEffectNames     The list of the effects that will get applied
     * @param applyEffectDurations A list of the applied effects' durations
     * @param applyEffectLevels    A list of the applied effects' levels
     * @param removableEffects     A list of the effects that will be getting removed as soon as they are applied to the entity (From other sources)
     */
    public static void applyEffects(EntityPlayer player, List<String> applyEffectNames, List<Integer> applyEffectDurations, List<Integer> applyEffectLevels, List<String> removableEffects) {
        List<Potion> potions = applyEffectNames.stream().map(PotionUtils::getPotion).collect(toList());
        int bound = potions.size();
        for (int potionID = 0; potionID < bound; potionID++) {
            if (potions.isEmpty()) {
                return;
            }
            Potion potionEffect = potions.get(potionID);
            if ((player.getActivePotionEffect(potionEffect) == null || potionEffect == MobEffects.NIGHT_VISION)) {
                int duration = Utils.convertToSeconds(applyEffectDurations.get(potionID));
                int level = applyEffectLevels.get(potionID);
                addPotion(player, potionEffect, duration, level, GOOD);
            }
        }

        List<Potion> removablePotions = removableEffects.stream().map(PotionUtils::getPotion).collect(toList());
        removablePotions.stream().filter(
            potionEffect -> player.getActivePotionEffect(potionEffect) != null
        ).forEach(
            player::removeActivePotionEffect
        );
    }

    //=========================================================
    //||                                                     ||
    //||                   WEAPONS & TOOLS                   ||
    //||                                                     ||
    //=========================================================

    /**
     * @param target The target entity that we will apply the negative or "de-buff" effects on
     * @param effect The negative effects that we will apply ({@link Negative#getEffects()},{@link Negative#getEffectDurations()},{@link Negative#getEffectLevels()})
     */
    public static void applyNegativeEffect(EntityLivingBase target, Negative effect) {
        if (effect.isEnabled()) {
            IntStream.range(0, effect.getEffects().length).forEach(potionID -> {
                Potion negative = getPotion(effect.getEffects()[potionID]);
                int duration = Utils.convertToSeconds(effect.getEffectDurations()[potionID]);
                int level = effect.getEffectLevels()[potionID];
                addPotion(target, negative, duration, level, BAD);
            });
        }
    }

}
