/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.util;

import com.sofodev.armorplus.api.properties.AbilityCanceller;
import com.sofodev.armorplus.api.properties.AbilityProvider;
import com.sofodev.armorplus.api.properties.iface.IEffectHolder;
import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemSpecialArmor;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemUltimateArmor;
import com.sofodev.armorplus.common.registry.items.base.special.effects.Negative;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

import java.util.List;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.common.util.PotionUtils.PotionType.BAD;
import static com.sofodev.armorplus.common.util.PotionUtils.PotionType.GOOD;
import static com.sofodev.armorplus.common.util.PotionUtils.addPotion;
import static com.sofodev.armorplus.common.util.PotionUtils.getPotion;
import static com.sofodev.armorplus.common.util.Utils.*;
import static java.util.stream.Collectors.toList;
import static net.minecraft.init.MobEffects.NIGHT_VISION;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public final class ArmorPlusItemUtils {

    private ArmorPlusItemUtils() {
    }

    /**
     * Creating registry and translation key for armor sets.
     *
     * @param armor The armor that we are going to use.
     * @param slot  the slot piece of the armor.
     * @param name  the name that we are going to be setting to the piece.
     */
    public static void createPieces(ItemArmor armor, EntityEquipmentSlot slot, String name) {
        String piece;
        if (slot == HEAD) {
            piece = name + "_helmet";
        } else if (slot == CHEST) {
            piece = name + "_chestplate";
        } else if (slot == LEGS) {
            piece = name + "_leggings";
        } else if (slot == FEET) {
            piece = name + "_boots";
        } else {
            piece = name + "";
        }
        armor.setRegistryName(setRL(piece));
        armor.setTranslationKey(setName(piece));
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
        return ModConfig.getRD().isItemRepairable(repair, expert);
    }

    //=========================================================
    //||                                                     ||
    //||               ARMORS & EQUIPMENT GEAR               ||
    //||                                                     ||
    //=========================================================


    public static boolean isSetMatchingMaterials(EntityLivingBase player, Item head, Item chest, Item legs, Item feet, APArmorMaterial material) {
        return isEqualMaterial((ItemSpecialArmor) head, material) && isEqualMaterial((ItemSpecialArmor) chest, material) && isEqualMaterial((ItemSpecialArmor) legs, material) && isEqualMaterial((ItemSpecialArmor) feet, material);
    }

    public static boolean isEqualMaterial(ItemSpecialArmor armor, APArmorMaterial material) {
        return armor.material == material;
    }

    public static boolean areAllBaseInstances(Item head, Item chest, Item legs, Item feet) {
        return head instanceof ItemSpecialArmor && chest instanceof ItemSpecialArmor && legs instanceof ItemSpecialArmor && feet instanceof ItemSpecialArmor;
    }

    public static boolean areAllUltimateInstances(Item head, Item chest, Item legs, Item feet) {
        return head instanceof ItemUltimateArmor && chest instanceof ItemUltimateArmor && legs instanceof ItemUltimateArmor && feet instanceof ItemUltimateArmor;
    }

    /**
     * @param player The player entity that we will check if the full armor set is equipped.
     * @param head   The head armor piece item.
     * @param chest  The chest armor piece item.
     * @param legs   The legs armor piece item.
     * @param feet   The feet armor piece item.
     * @return Returns a validated result of the check if the player has the full armor set equipped.
     */
    public static boolean isFullSet(EntityLivingBase player, Item head, Item chest, Item legs, Item feet) {
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
    public static ItemStack getHead(EntityLivingBase player) {
        return player.getItemStackFromSlot(HEAD);
    }

    /**
     * @param player The player that we'll be getting the equipment data from
     * @return Returns the ItemStack that is located in the chest (Armor) slot
     */
    public static ItemStack getChest(EntityLivingBase player) {
        return player.getItemStackFromSlot(CHEST);
    }

    /**
     * @param player The player that we'll be getting the equipment data from
     * @return Returns the ItemStack that is located in the legs (Armor) slot
     */
    public static ItemStack getLegs(EntityLivingBase player) {
        return player.getItemStackFromSlot(LEGS);
    }

    /**
     * @param player The player that we'll be getting the equipment data from
     * @return Returns the ItemStack that is located in the feet (Armor) slot
     */
    public static ItemStack getFeet(EntityLivingBase player) {
        return player.getItemStackFromSlot(FEET);
    }


    public static void applyEffects(EntityLivingBase player, IEffectHolder effectHolder) {
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
    public static void applyEffects(EntityLivingBase player, List<String> applyEffectNames, List<Integer> applyEffectDurations, List<Integer> applyEffectLevels, List<String> removableEffects) {
        List<Potion> potions = applyEffectNames.stream().map(PotionUtils::getPotion).collect(toList());
        int bound = potions.size();
        for (int potionID = 0; potionID < bound; potionID++) {
            if (potions.isEmpty()) {
                return;
            }
            Potion potionEffect = potions.get(potionID);
            if (player.getActivePotionEffect(potionEffect) == null || potionEffect == NIGHT_VISION) {
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
