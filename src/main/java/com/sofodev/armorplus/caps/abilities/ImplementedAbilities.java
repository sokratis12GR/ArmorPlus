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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Objects;

import static com.sofodev.armorplus.caps.abilities.AbilityDataHandler.getHandler;
import static com.sofodev.armorplus.caps.abilities.Material.*;
import static com.sofodev.armorplus.registry.ModBlocks.blockMeltingObsidian;
import static com.sofodev.armorplus.util.Utils.setRL;
import static net.minecraft.block.material.Material.AIR;
import static net.minecraft.init.Blocks.FLOWING_LAVA;
import static net.minecraft.init.Blocks.LAVA;
import static net.minecraft.init.MobEffects.WITHER;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

public class ImplementedAbilities {

    public static IForgeRegistry<AbilityData> ABILITY_REGISTRY;

    public static final AbilityData NONE = new AbilityData("armorplus:empty", "Empty", EntityEquipmentSlot.values(), Material.NONE);
    public static final AbilityData NIGHT_VISION = new AbilityData("minecraft:night_vision", "Night Vision", HEAD, COAL, INFUSED_LAVA).setPotion(true);
    public static final AbilityData WATER_BREATHING = new AbilityData("minecraft:water_breathing", "Water Breathing", HEAD, LAPIS, GUARDIAN).setPotion(true);
    public static final AbilityData RESISTANCE = new AbilityData("minecraft:resistance", "Resistance", HEAD, CHEST, LEGS, FEET, OBSIDIAN, INFUSED_LAVA).setPotion(true);
    public static final AbilityData FIRE_RESISTANCE = new AbilityData("minecraft:fire_resistance", "Fire Resistance", HEAD, CHEST, LEGS, FEET, OBSIDIAN, INFUSED_LAVA).setPotion(true);
    public static final AbilityData HASTE = new AbilityData("minecraft:haste", "Haste", CHEST, REDSTONE, EMERALD).setPotion(true);
    public static final AbilityData SPEED = new AbilityData("minecraft:speed", "Speed", FEET, REDSTONE, EMERALD, GUARDIAN).setPotion(true);
    public static final AbilityData JUMP_BOOST = new AbilityData("minecraft:jump_boost", "Jump Boost", FEET, SLIME).setPotion(true);
    public static final AbilityData REGENERATION = new AbilityData("minecraft:regeneration", "Regeneration", CHEST, SUPER_STAR, ULTIMATE).setPotion(true);
    public static final AbilityData STRENGTH = new AbilityData("minecraft:strength", "Strength", CHEST, MANYULLYN).setPotion(true);
    public static final AbilityData INVISIBILITY = new AbilityData("minecraft:invisibility", "Invisibility", CHEST, ULTIMATE).setPotion(true);
    public static final AbilityData ABSORPTION = new AbilityData("minecraft:absorption", "Absorption", CHEST, INFUSED_LAVA).setPotion(true);
    public static final AbilityData WITHER_PROOF = new AbilityData("armorplus:wither_proof", "Wither Proof", CHEST, SUPER_STAR, ENDER_DRAGON, ULTIMATE) {
        @Override
        public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
            if (player.getActivePotionEffect(WITHER) != null && player.isPotionActive(WITHER)) {
                player.removePotionEffect(WITHER);
            }
        }
    };
    public static final AbilityData FLIGHT = new AbilityData("armorplus:flight", "Flight", CHEST, ENDER_DRAGON, ULTIMATE) {
        @Override
        public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
            if (player.capabilities.isCreativeMode || player.isSpectator()) {
                player.capabilities.allowFlying = true;
            } else if (!contains(Objects.requireNonNull(getHandler(stack)), this.getSafeName()) && !(player.inventory.armorInventory.get(2).getItem() instanceof ItemArmorV2)) {
                player.capabilities.isFlying = false;
                player.capabilities.allowFlying = false;
            }
        }
    };
    public static final AbilityData STEP_ASSIST = new AbilityData("armorplus:step_assist", "Step Assist", LEGS, REDSTONE, SUPER_STAR);
    public static final AbilityData BONUS_XP_ON_KILL = new AbilityData("armorplus:bonus_xp_on_kill", "Bonus XP on Kill", CHEST, COAL, LAPIS, EMERALD) {
        @Override
        public void onPlayerKillEntity(LivingDeathEvent event, ItemStack stack) {
            Entity entity = event.getEntity();
            entity.world.spawnEntity(new EntityXPOrb(entity.world, entity.posX, entity.posY + 0.5D, entity.posZ, 1 + entity.world.rand.nextInt(4)));
        }
    };

    public static final AbilityData WALK_ON_LAVA = new AbilityData("armorplus:walk_on_lava", "Walk on Lava", FEET, INFUSED_LAVA) {
        @Override
        public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
            if (player.onGround) {
                player.extinguish(); //Called so the player doesn't catch on fire
                float area = (float) Math.min(16, 1);
                BlockPos.MutableBlockPos firstPos = new BlockPos.MutableBlockPos(0, 0, 0);
                BlockPos pos = new BlockPos(player);

                for (BlockPos.MutableBlockPos secondaryPos : BlockPos.getAllInBoxMutable(pos.add((double) -area, -1.0D, (double) -area), pos.add((double) area, -1.0D, (double) area))) {
                    if (secondaryPos.distanceSqToCenter(player.posX, player.posY, player.posZ) <= (double) (area * area)) {
                        firstPos.setPos(secondaryPos.getX(), secondaryPos.getY() + 1, secondaryPos.getZ());
                        IBlockState firstState = world.getBlockState(firstPos);

                        if (firstState.getMaterial() == AIR) {
                            IBlockState secondaryState = world.getBlockState(secondaryPos);

                            if (secondaryState.getMaterial() == net.minecraft.block.material.Material.LAVA && (secondaryState.getBlock() == LAVA || secondaryState.getBlock() == FLOWING_LAVA) && secondaryState.getValue(BlockLiquid.LEVEL) == 0 && world.mayPlace(blockMeltingObsidian, secondaryPos, false, EnumFacing.DOWN, null)) {
                                world.setBlockState(secondaryPos, blockMeltingObsidian.getDefaultState());
                                world.scheduleUpdate(secondaryPos.toImmutable(), blockMeltingObsidian, MathHelper.getInt(player.getRNG(), 120, 240));
                            }
                        }
                    }
                }
            }
        }
    };
    public static final AbilityData SWIMMING_SPEED = new AbilityData("armorplus:swimming_speed", "Swimming Speed", FEET, GUARDIAN);
    public static final AbilityData UNDERWATER_VISION = new AbilityData("armorplus:underwater_vision", "Underwater Vision", HEAD, GUARDIAN);

    public static void createRegistry() {
        if (ABILITY_REGISTRY == null) {
            ResourceLocation registryName = setRL("abilities");
            ABILITY_REGISTRY = new RegistryBuilder<AbilityData>().setType(AbilityData.class).setName(registryName).create();
            MinecraftForge.EVENT_BUS.post(new RegistryEvent.Register<>(registryName, ABILITY_REGISTRY));
        }
    }
}
