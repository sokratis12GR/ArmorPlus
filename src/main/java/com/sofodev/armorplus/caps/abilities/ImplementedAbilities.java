/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.caps.abilities;

import com.sofodev.armorplus.items.armors.base.ItemArmorV2;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.sofodev.armorplus.caps.abilities.AbilityDataHandler.getHandler;
import static com.sofodev.armorplus.caps.abilities.Material.*;
import static com.sofodev.armorplus.registry.ModBlocks.blockMeltingObsidian;
import static net.minecraft.block.material.Material.AIR;
import static net.minecraft.block.material.Material.WATER;
import static net.minecraft.init.Blocks.FLOWING_LAVA;
import static net.minecraft.init.Blocks.LAVA;
import static net.minecraft.init.MobEffects.WITHER;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

public class ImplementedAbilities {

    public static IForgeRegistry<AbilityData> ABILITY_REGISTRY;

    public static final AbilityData NONE = new AbilityData("armorplus:empty", "ability.armorplus.empty.name", new EquipmentSlot(EntityEquipmentSlot.values()), Material.NONE);
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
        public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
            PotionEffect effect = player.getActivePotionEffect(WITHER);
            if (effect != null) {
                player.removePotionEffect(WITHER);
            }
        }
    };
    public static final AbilityData FLIGHT = new AbilityData("armorplus:flight", "ability.armorplus.flight.name", CHEST, ENDER_DRAGON, ULTIMATE) {
        @Override
        public void onSpecialArmorTick(World world, EntityPlayer player, ItemStack stack) {
            if (stack.getItem() instanceof ItemArmorV2) {
                ItemArmorV2 armor = (ItemArmorV2) stack.getItem();
                EntityEquipmentSlot slot = armor.getEquipmentSlot();
                boolean contains = contains(getHandler(stack), this);
                ItemStack chestPlate = player.inventory.armorInventory.get(2);
                if (slot == CHEST && canProvide(stack, this) && contains || player.capabilities.isCreativeMode || player.isSpectator()) {
                    player.capabilities.allowFlying = true;
                } else if (!contains || chestPlate.isEmpty()) {
                    player.capabilities.isFlying = false;
                    player.capabilities.allowFlying = false;
                }
            }
        }
    };
    public static final AbilityData STEP_ASSIST = new AbilityData("armorplus:step_assist", "ability.armorplus.step_assist.name", LEGS, REDSTONE, SUPER_STAR);
    public static final AbilityData BONUS_XP_ON_KILL = new AbilityData("armorplus:bonus_xp_on_kill", "ability.armorplus.bonus_xp_on_kill.name", CHEST, COAL, LAPIS, EMERALD) {
        @Override
        public void onPlayerKillEntity(LivingDeathEvent event, ItemStack stack) {
            Entity entity = event.getEntity();
            entity.world.spawnEntity(new EntityXPOrb(entity.world, entity.posX, entity.posY + 0.5D, entity.posZ, 1 + entity.world.rand.nextInt(4)));
        }
    };

    public static final AbilityData WALK_ON_LAVA = new AbilityData("armorplus:walk_on_lava", "ability.armorplus.walk_on_lava.name", FEET, INFUSED_LAVA) {
        @Override
        public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
            if (player.onGround) {
                player.extinguish(); //Called so the player doesn't catch on fire
                float area = (float) Math.min(16, 1);
                BlockPos.MutableBlockPos firstPos = new BlockPos.MutableBlockPos(0, 0, 0);
                BlockPos pos = new BlockPos(player);

                for (BlockPos.MutableBlockPos posB : BlockPos.getAllInBoxMutable(pos.add((double) -area, -1.0D, (double) -area), pos.add((double) area, -1.0D, (double) area))) {
                    if (posB.distanceSqToCenter(player.posX, player.posY, player.posZ) <= (double) (area * area)) {
                        firstPos.setPos(posB.getX(), posB.getY() + 1, posB.getZ());
                        IBlockState firstState = world.getBlockState(firstPos);

                        if (firstState.getMaterial() == AIR) {
                            IBlockState secondaryState = world.getBlockState(posB);

                            if (secondaryState.getMaterial() == net.minecraft.block.material.Material.LAVA && (secondaryState.getBlock() == LAVA || secondaryState.getBlock() == FLOWING_LAVA) && secondaryState.getValue(BlockLiquid.LEVEL) == 0 && world.mayPlace(blockMeltingObsidian, posB, false, EnumFacing.DOWN, null)) {
                                world.setBlockState(posB, blockMeltingObsidian.getDefaultState());
                                world.scheduleUpdate(posB.toImmutable(), blockMeltingObsidian, MathHelper.getInt(player.getRNG(), 60, 120));
                            }
                        }
                    }
                }
            }
        }
    };
    public static final AbilityData SWIMMING_SPEED = new AbilityData("armorplus:swimming_speed", "ability.armorplus.swimming_speed.name", FEET, GUARDIAN) {
        private static final double MULTIPLIER = 1.2;
        private static final double MAX_SPEED = 1.3;

        @Override
        public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
            if (player.isInsideOfMaterial(WATER)) {
                //Wor
                double motionX = player.motionX * MULTIPLIER;
                double motionY = player.motionY * MULTIPLIER;
                double motionZ = player.motionZ * MULTIPLIER;

                boolean flying = player.capabilities.isFlying;

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
