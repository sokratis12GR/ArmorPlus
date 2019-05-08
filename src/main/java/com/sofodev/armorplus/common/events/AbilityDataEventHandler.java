/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.caps.abilities.CapabilityAbility.IArmorAbilityHandler;
import com.sofodev.armorplus.common.caps.abilities.data.AbilityData;
import com.sofodev.armorplus.common.caps.abilities.data.ISpecialItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.sofodev.armorplus.common.caps.abilities.CapabilityAbility.CAPABILITY_ABILITIES;
import static com.sofodev.armorplus.common.caps.abilities.data.AbilityData.*;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class AbilityDataEventHandler {

    @SubscribeEvent
    public static void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
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
        if (event.getEntityLiving() instanceof EntityPlayer) {
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
        EntityPlayer player = event.getPlayer();
        if (player == null) return;
        for (ItemStack stack : player.getArmorInventoryList()) {
            onPlayerBreakBlock(event, stack);
        }
        for (ItemStack stack : player.getHeldEquipment()) {
            onPlayerBreakBlock(event, stack);
        }
    }

    @SubscribeEvent
    public static void onPlayerKilledEntity(LivingDeathEvent event) {
        Entity attacker = event.getSource().getTrueSource();
        if (attacker == null) return;
        if (attacker instanceof EntityPlayer) {
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
            stack.getCapability(CAPABILITY_ABILITIES).ifPresent(handler -> {
                if (validateHandler(handler)) {
                    handler.getAbilities().stream().filter(
                        data -> canApply(data, stack)
                    ).forEach(
                        data -> data.onPlayerJump(stack, event)
                    );
                }
            });
        }
    }

    private static void onPlayerPreDamaged(LivingHurtEvent event, ItemStack stack) {
        if (isValid(stack)) {
            stack.getCapability(CAPABILITY_ABILITIES).ifPresent(handler -> {
                if (validateHandler(handler)) {
                    handler.getAbilities().stream().filter(
                        data -> canApply(data, stack)
                    ).forEach(
                        data -> data.onPlayerPreDamaged(stack, event)
                    );
                }
            });
        }
    }

    private static void onPlayerDamaged(LivingDamageEvent event, ItemStack stack) {
        if (isValid(stack)) {
            stack.getCapability(CAPABILITY_ABILITIES).ifPresent(handler -> {
                if (validateHandler(handler)) {
                    handler.getAbilities().stream().filter(
                        data -> canApply(data, stack)
                    ).forEach(
                        data -> data.onPlayerDamaged(stack, event)
                    );
                }
            });
        }
    }

    private static void onPlayerBreakBlock(BlockEvent.BreakEvent event, ItemStack stack) {
        if (isValid(stack)) {
            stack.getCapability(CAPABILITY_ABILITIES).ifPresent(handler -> {
                if (validateHandler(handler)) {
                    handler.getAbilities().stream().filter(
                        data -> canApply(data, stack)
                    ).forEach(
                        data -> data.onPlayerBreakBlock(stack, event)
                    );
                }
            });
        }
    }

    private static void onPlayerKilledEntity(LivingDeathEvent event, ItemStack stack) {
        if (isValid(stack)) {
            stack.getCapability(CAPABILITY_ABILITIES).ifPresent(handler -> {
                if (validateHandler(handler)) {
                    handler.getAbilities().stream().filter(
                        data -> canApply(data, stack)
                    ).forEach(
                        data -> data.onPlayerKillEntity(stack, event)
                    );
                }
            });
        }
    }

    /**
     * @param stack the ItemStack that we will use
     * @return true if the {@code stack#getItem()} is an instanceof ISpecialItem, otherwise false
     */
    private static boolean isValid(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() instanceof ISpecialItem;
    }

    private static boolean validateHandler(IArmorAbilityHandler handler) {
        return handler != null && hasAbilities(handler);
    }

    private static boolean canApply(AbilityData data, ItemStack stack) {
        return canProvide(stack, data) && contains(stack, data);
    }
}
