/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.caps.abilities;

import com.sofodev.armorplus.items.armors.base.ItemArmorV2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.sofodev.armorplus.caps.abilities.AbilityDataHandler.getHandler;
import static com.sofodev.armorplus.caps.abilities.MaterialType.*;
import static net.minecraft.init.MobEffects.WITHER;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

public class ImplementedAbilities {

    public static IForgeRegistry<AbilityData> ABILITY_REGISTRY;

    public static final AbilityData NONE = new AbilityData("armorplus:empty", "ability.armorplus.empty.name", new EquipmentSlot(EntityEquipmentSlot.values()), MaterialType.NONE);
    public static final AbilityData NIGHT_VISION = new AbilityData("minecraft:night_vision", "ability.armorplus.night_vision.name", HEAD, COAL, INFUSED_LAVA, GUARDIAN).setPotion(true);
    public static final AbilityData WATER_BREATHING = new AbilityData("minecraft:water_breathing", "ability.armorplus.water_breathing.name", HEAD, LAPIS, GUARDIAN).setPotion(true);
    public static final AbilityData RESISTANCE = new AbilityData("minecraft:resistance", "ability.armorplus.resistance.name", HEAD, CHEST, LEGS, FEET, OBSIDIAN, INFUSED_LAVA).setPotion(true);
    public static final AbilityData FIRE_RESISTANCE = new AbilityData("minecraft:fire_resistance", "ability.armorplus.fire_resistance.name", HEAD, CHEST, LEGS, FEET, OBSIDIAN, INFUSED_LAVA).setPotion(true);
    public static final AbilityData HASTE = new AbilityData("minecraft:haste", "ability.armorplus.haste.name", CHEST, REDSTONE, EMERALD).setPotion(true);
    public static final AbilityData SPEED = new AbilityData("minecraft:speed", "ability.armorplus.speed.name", FEET, REDSTONE, EMERALD, GUARDIAN).setPotion(true);
    public static final AbilityData JUMP_BOOST = new AbilityData("minecraft:jump_boost", "ability.armorplus.jump_boost.name", FEET, SLIME).setPotion(true);
    public static final AbilityData REGENERATION = new AbilityData("minecraft:regeneration", "ability.armorplus.regeneration.name", CHEST, SUPER_STAR, ULTIMATE).setPotion(true);
    public static final AbilityData STRENGTH = new AbilityData("minecraft:strength", "ability.armorplus.strength.name", CHEST, MANYULLYN).setPotion(true);
    public static final AbilityData INVISIBILITY = new AbilityData("minecraft:invisibility", "ability.armorplus.invisibility.name", CHEST, ULTIMATE).setPotion(true);
    public static final AbilityData ABSORPTION = new AbilityData("minecraft:absorption", "ability.armorplus.absorption.name", CHEST, INFUSED_LAVA).setPotion(true);
    public static final AbilityData WITHER_PROOF = new AbilityData("armorplus:wither_proof", "ability.armorplus.wither_proof.name", CHEST, SUPER_STAR, ENDER_DRAGON, ULTIMATE) {
        @Override
        public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
            PotionEffect effect = player.getActivePotionEffect(WITHER);
            if (effect != null) {
                player.removePotionEffect(WITHER);
            }
        }
    };
    public static final AbilityData FLIGHT = new AbilityData("armorplus:flight", "ability.armorplus.flight.name", CHEST, ENDER_DRAGON, ULTIMATE) {
        @Override
        public void onSpecialArmorTick(ItemStack stack, World world, EntityPlayer player) {
            if (stack.getItem() instanceof ItemArmorV2) {
                ItemArmorV2 armor = (ItemArmorV2) stack.getItem();
                EntityEquipmentSlot slot = armor.getEquipmentSlot();
                boolean contains = contains(getHandler(stack), this);
                ItemStack chestPlate = player.inventory.armorInventory.get(2);
                if (slot == CHEST && canProvide(stack, this) && contains || player.abilities.isCreativeMode || player.isSpectator()) {
                    player.abilities.allowFlying = true;
                } else if (!contains || chestPlate.isEmpty()) {
                    player.abilities.isFlying = false;
                    player.abilities.allowFlying = false;
                } else {
                    player.abilities.isFlying = false;
                    player.abilities.allowFlying = false;
                }
            }
        }
    };
    public static final AbilityData STEP_ASSIST = new AbilityData("armorplus:step_assist", "ability.armorplus.step_assist.name", LEGS, REDSTONE, SUPER_STAR);
    public static final AbilityData BONUS_XP_ON_KILL = new AbilityData("armorplus:bonus_xp_on_kill", "ability.armorplus.bonus_xp_on_kill.name", CHEST, COAL, LAPIS, EMERALD) {
        @Override
        public void onPlayerKillEntity(ItemStack stack, LivingDeathEvent event) {
            Entity entity = event.getEntity();
            entity.world.spawnEntity(new EntityXPOrb(entity.world, entity.posX, entity.posY + 0.5D, entity.posZ, 1 + entity.world.rand.nextInt(4)));
        }
    };

    public static final AbilityData WALK_ON_LAVA = new AbilityData("armorplus:walk_on_lava", "ability.armorplus.walk_on_lava.name", FEET, INFUSED_LAVA) {
        @Override
        public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
            //   if (player.onGround) {
            //       IBlockState iblockstate = blockMeltingObsidian.getDefaultState();
            //       float f = (float) Math.min(16, 2);
            //       BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(0, 0, 0);
            //       BlockPos pos = new BlockPos(player);
//
            //       for (BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.getAllInBoxMutable(pos.add((double) (-f), -1.0D, (double) (-f)), pos.add((double) f, -1.0D, (double) f))) {
            //           if (blockpos$mutableblockpos1.distanceSqToCenter(player.posX, player.posY, player.posZ) <= (double) (f * f)) {
            //               blockpos$mutableblockpos.setPos(blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getY() + 1, blockpos$mutableblockpos1.getZ());
            //               IBlockState iblockstate1 = world.getBlockState(blockpos$mutableblockpos);
            //               if (iblockstate1.isAir()) {
            //                   IBlockState iblockstate2 = world.getBlockState(blockpos$mutableblockpos1);
            //                   boolean isFull = iblockstate2.getBlock() == Blocks.LAVA && iblockstate2.get(BlockFlowingFluid.LEVEL) == 0; //TODO: Forge, modded waters?
            //                   if (iblockstate2.getMaterial() == Material.LAVA && isFull && iblockstate.isValidPosition(world, blockpos$mutableblockpos1) && world.checkNoEntityCollision(iblockstate, blockpos$mutableblockpos1)) {
            //                       world.setBlockState(blockpos$mutableblockpos1, iblockstate);
            //                       world.getPendingBlockTicks().scheduleTick(blockpos$mutableblockpos1.toImmutable(), blockMeltingObsidian, MathHelper.nextInt(player.getRNG(), 60, 120));
            //                   }
            //               }
            //           }
            //       }
//
            //   }
        }
    };
    public static final AbilityData SWIMMING_SPEED = new AbilityData("armorplus:swimming_speed", "ability.armorplus.swimming_speed.name", FEET, GUARDIAN) {
        private static final double MULTIPLIER = 1.2;
        private static final double MAX_SPEED = 1.3;

        @Override
        public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
            if (player.isInWater()) {
                //Wor
                double motionX = player.motionX * MULTIPLIER;
                double motionY = player.motionY * MULTIPLIER;
                double motionZ = player.motionZ * MULTIPLIER;

                boolean flying = player.abilities.isFlying;

                if (!flying) {
                    if (Math.abs(motionX) < MAX_SPEED) {
                        player.motionX = motionX;
                    }
                    if (Math.abs(motionY) < MAX_SPEED) {
                        player.motionY = motionY;
                    }
                    if (Math.abs(motionZ) < MAX_SPEED) {
                        player.motionZ = motionZ;
                    }
                }
            }
        }
    };
    //public static final AbilityData UNDERWATER_VISION = new AbilityData("armorplus:underwater_vision", "ability.armorplus.underwater_vision.name", HEAD, GUARDIAN);
}
