/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.caps.abilities.AbilityData;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler.IAbilityHandler;
import com.sofodev.armorplus.items.armors.base.ItemArmorV2;
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
            ItemStack stack = player.getArmorInventoryList().iterator().next();
            if (isValid(stack)) {
                IAbilityHandler handler = getHandler(stack);
                if (validateHandler(handler)) {
                    handler.getAbilities().stream().filter(
                        data -> canApply(handler, data, stack)
                    ).forEach(
                        data -> data.onPlayerJump(event, stack)
                    );
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerPreDamaged(LivingHurtEvent event) {
        if (isEnabled()) {
            Entity entity = event.getEntity();
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                ItemStack stack = player.getArmorInventoryList().iterator().next();
                if (stack.getItem() instanceof ItemArmorV2) {
                    IAbilityHandler handler = getHandler(stack);
                    if (validateHandler(handler)) {
                        handler.getAbilities().stream().filter(
                            data -> canApply(handler, data, stack)
                        ).forEach(
                            data -> data.onPlayerPreDamaged(event, stack)
                        );
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDamaged(LivingDamageEvent event) {
        if (isEnabled() && event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if (player == null) return;
            ItemStack stack = player.getArmorInventoryList().iterator().next();
            if (isValid(stack)) {
                IAbilityHandler handler = getHandler(stack);
                if (validateHandler(handler)) {
                    handler.getAbilities().stream().filter(
                        data -> canApply(handler, data, stack)
                    ).forEach(
                        data -> data.onPlayerDamaged(event, stack)
                    );
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerBreakBlock(BlockEvent.BreakEvent event) {
        if (isEnabled()) {
            EntityPlayer player = event.getPlayer();
            if (player == null) return;
            ItemStack stack = player.getArmorInventoryList().iterator().next();
            if (isValid(stack)) {
                IAbilityHandler handler = getHandler(stack);
                if (validateHandler(handler)) {
                    handler.getAbilities().stream().filter(
                        data -> canApply(handler, data, stack)
                    ).forEach(
                        data -> data.onPlayerBreakBlock(event, stack)
                    );
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerKilledEntity(LivingDeathEvent event) {
        Entity attacker = event.getSource().getTrueSource();
        if (attacker == null) return;
        if (isEnabled() && attacker instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) attacker;
            ItemStack stack = player.getArmorInventoryList().iterator().next();
            if (isValid(stack)) {
                IAbilityHandler handler = getHandler(stack);
                if (validateHandler(handler)) {
                    handler.getAbilities().stream().filter(
                        data -> canApply(handler, data, stack)
                    ).forEach(
                        data -> data.onPlayerKillEntity(event, stack)
                    );
                }
            }
        }
    }

    private static boolean isEnabled() {
        return enableExperimentalMode;
    }

    /**
     * @param stack the ItemStack that we will use
     * @return true if the {@code stack#getItem()} is an instanceof ItemArmorV2, otherwise false
     */
    private static boolean isValid(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() instanceof ItemArmorV2;
    }

    private static boolean validateHandler(IAbilityHandler handler) {
        return handler != null && hasAbilities(handler);
    }

    private static boolean canApply(IAbilityHandler handler, AbilityData data, ItemStack stack) {
        return canProvide(stack, data) && contains(handler, data);
    }
}
