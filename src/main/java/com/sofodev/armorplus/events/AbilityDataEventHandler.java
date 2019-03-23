/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.caps.abilities.AbilityData;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler.IAbilityHandler;
import com.sofodev.armorplus.caps.abilities.ISpecialItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.sofodev.armorplus.caps.abilities.AbilityData.*;
import static com.sofodev.armorplus.caps.abilities.AbilityDataHandler.getHandler;
import static com.sofodev.armorplus.config.ModConfig.Experimental.enableExperimentalMode;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class AbilityDataEventHandler {

    @SubscribeEvent
    public static void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (isEnabled() && event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if (player == null) return;
            for (ItemStack stack : player.getArmorInventoryList()) {
                onPlayerJump(event, stack);
            }
            for (ItemStack stack : player.getHeldEquipment()) {
                onPlayerJump(event, stack);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerPreDamaged(LivingHurtEvent event) {
        if (!isEnabled()) {
            return;
        }
        Entity entity = event.getEntity();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            for (ItemStack stack : player.getArmorInventoryList()) {
                onPlayerPreDamaged(event, stack);
            }
            for (ItemStack stack : player.getHeldEquipment()) {
                onPlayerPreDamaged(event, stack);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDamaged(LivingDamageEvent event) {
        if (isEnabled() && event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if (player == null) return;
            for (ItemStack stack : player.getArmorInventoryList()) {
                onPlayerDamaged(event, stack);
            }
            for (ItemStack stack : player.getHeldEquipment()) {
                onPlayerDamaged(event, stack);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerBreakBlock(BlockEvent.BreakEvent event) {
        if (isEnabled()) {
            EntityPlayer player = event.getPlayer();
            if (player == null) return;
            for (ItemStack stack : player.getArmorInventoryList()) {
                onPlayerBreakBlock(event, stack);
            }
            for (ItemStack stack : player.getHeldEquipment()) {
                onPlayerBreakBlock(event, stack);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerKilledEntity(LivingDeathEvent event) {
        Entity attacker = event.getSource().getTrueSource();
        if (attacker == null) return;
        if (isEnabled() && attacker instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) attacker;
            for (ItemStack stack : player.getArmorInventoryList()) {
                onPlayerKilledEntity(event, stack);
            }
            for (ItemStack stack : player.getHeldEquipment()) {
                onPlayerKilledEntity(event, stack);
            }
        }
    }

    //////////////////////
    //  HELPER METHODS  //
    //////////////////////

    private static void onPlayerJump(LivingEvent.LivingJumpEvent event, ItemStack stack) {
        if (isValid(stack)) {
            IAbilityHandler handler = getHandler(stack);
            if (validateHandler(handler)) {
                handler.getAbilities().stream().filter(
                    data -> canApply(handler, data, stack)
                ).forEach(
                    data -> data.onPlayerJump(stack, event)
                );
            }
        }
    }

    private static void onPlayerPreDamaged(LivingHurtEvent event, ItemStack stack) {
        if (isValid(stack)) {
            IAbilityHandler handler = getHandler(stack);
            if (validateHandler(handler)) {
                handler.getAbilities().stream().filter(
                    data -> canApply(handler, data, stack)
                ).forEach(
                    data -> data.onPlayerPreDamaged(stack, event)
                );
            }
        }
    }

    private static void onPlayerDamaged(LivingDamageEvent event, ItemStack stack) {
        if (isValid(stack)) {
            IAbilityHandler handler = getHandler(stack);
            if (validateHandler(handler)) {
                handler.getAbilities().stream().filter(
                    data -> canApply(handler, data, stack)
                ).forEach(
                    data -> data.onPlayerDamaged(stack, event)
                );
            }
        }
    }

    private static void onPlayerBreakBlock(BlockEvent.BreakEvent event, ItemStack stack) {
        if (isValid(stack)) {
            IAbilityHandler handler = getHandler(stack);
            if (validateHandler(handler)) {
                handler.getAbilities().stream().filter(
                    data -> canApply(handler, data, stack)
                ).forEach(
                    data -> data.onPlayerBreakBlock(stack, event)
                );
            }
        }
    }

    private static void onPlayerKilledEntity(LivingDeathEvent event, ItemStack stack) {
        if (isValid(stack)) {
            IAbilityHandler handler = getHandler(stack);
            if (validateHandler(handler)) {
                handler.getAbilities().stream().filter(
                    data -> canApply(handler, data, stack)
                ).forEach(
                    data -> data.onPlayerKillEntity(stack, event)
                );
            }
        }
    }

    private static boolean isEnabled() {
        return enableExperimentalMode;
    }

    /**
     * @param stack the ItemStack that we will use
     * @return true if the {@code stack#getItem()} is an instanceof ISpecialItem, otherwise false
     */
    private static boolean isValid(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() instanceof ISpecialItem;
    }

    private static boolean validateHandler(IAbilityHandler handler) {
        return handler != null && hasAbilities(handler);
    }

    private static boolean canApply(IAbilityHandler handler, AbilityData data, ItemStack stack) {
        return canProvide(stack, data) && contains(handler, data);
    }
}
