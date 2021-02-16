package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.APConfig;
import com.sofodev.armorplus.events.data.FlightData;
import com.sofodev.armorplus.network.packet.PacketFlyingSync;
import com.sofodev.armorplus.registry.APItems;
import com.sofodev.armorplus.registry.items.armors.APArmorItem;
import com.sofodev.armorplus.registry.items.armors.IAPArmor;
import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import com.sofodev.armorplus.registry.items.extras.IBuff;
import com.sofodev.armorplus.registry.items.tools.APMaceItem;
import com.sofodev.armorplus.registry.items.tools.properties.mace.APMaceType;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.*;

import static com.sofodev.armorplus.registry.items.extras.Buff.FLIGHT;
import static com.sofodev.armorplus.registry.items.extras.Buff.WATER_WEAKNESS;
import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.Utils.*;
import static java.util.stream.Collectors.toList;
import static net.minecraftforge.registries.ForgeRegistries.ENCHANTMENTS;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModGlobalEvents {

    public static final Random RAND = new Random();
    public static int ticks = 0;
    public static FlightData flightData = new FlightData(false, false, false);

    @SubscribeEvent
    public static void onPlayerTickEvent(PlayerTickEvent e) {
        PlayerEntity player = e.player;
        if (!player.world.isRemote()) {
            for (ItemStack stack : player.getArmorInventoryList()) {
                if (!(stack.getItem() instanceof APArmorItem)) {
                    return;
                }
                APArmorItem item = ((APArmorItem) stack.getItem());
                IAPArmor mat = item.getMat();
                boolean areExactMatch = areExactMatch(mat, player);
                List<IBuff> buffList = Arrays.stream(mat.getBuffInstances()).map(BuffInstance::getBuff).collect(toList());
                if (areExactMatch) {
                    if (buffList.contains(FLIGHT)) shouldApplyFlight(e, player);
                    if (buffList.contains(WATER_WEAKNESS)) shouldApplyWaterWeakness(player);
                } else {
                    attemptDisableFlight(e, player);
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorldEvent(EntityJoinWorldEvent e) {
        if (APConfig.SERVER.enableThankYouAdvancement.get() && !e.getWorld().isRemote()) {
            if (e.getEntity() instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) e.getEntity();
                CompoundNBT nbt = player.serializeNBT();
                if (nbt != null && (!nbt.hasUniqueId("key") || !nbt.getBoolean("thanked"))) {
                    PlayerAdvancements advancements = player.getAdvancements();
                    AdvancementProgress progress = advancements.getProgress(Advancement.Builder.builder().build(setRL("story/thank_you_6m")));
                    if (!progress.isDone()) {
                        nbt.putBoolean("thanked", true);
                        player.writeAdditional(nbt);
                        player.serializeNBT();
                        player.entityDropItem(new ItemStack(APItems.THANK_YOU_6M.get()));
                    }
                }
            }
        }
    }

    private static void shouldApplyFlight(PlayerTickEvent e, PlayerEntity player) {
        if (e.phase == TickEvent.Phase.END && e.side.isServer()) {
            if (!player.abilities.allowFlying || allowsFlightByDefault(player)) {
                player.abilities.allowFlying = true;
                updateClientServerFlight(player, true);
                //       ArmorPlus.LOGGER.info("Enabling flight, hasFlight: " + flightData.wasFlyingAllowed());
            }
        }
    }

    private static void attemptDisableFlight(PlayerTickEvent e, PlayerEntity player) {
        if (e.phase == TickEvent.Phase.END && e.side.isServer()) {
            player.getArmorInventoryList().forEach(i -> {
                if (!i.isEmpty() || flightData.allowFlying() && flightData.wasFlyingAllowed()) {
                    if (player.abilities.allowFlying && flightData.allowFlying() && flightData.wasFlyingAllowed() && !allowsFlightByDefault(player)) {
                        player.abilities.allowFlying = false;
                        player.abilities.isFlying = false;
                        updateClientServerFlight(player, false);
                        flightData.setFlying(false);
                        flightData.setAllowFlying(false);
                        //    ArmorPlus.LOGGER.info("Disabling flight [0], hasFlight: " + flightData.allowFlying());
                    } else if ((i.getItem() instanceof APArmorItem)) {
                        if (areExactMatch(((APArmorItem) i.getItem()).getMat(), player) && player.abilities.allowFlying) {
                            player.abilities.allowFlying = false;
                            player.abilities.isFlying = false;
                            updateClientServerFlight(player, false);
                            flightData.setFlying(false);
                            flightData.setAllowFlying(false);
                            //    ArmorPlus.LOGGER.info("Disabling flight [1], hasFlight: " + flightData.wasFlyingAllowed());
                        }
                    }
                }
            });
        }
    }

    private static void updateClientServerFlight(PlayerEntity player, boolean allowFlying) {
        updateClientServerFlight(player, allowFlying, allowFlying && player.abilities.isFlying);
    }

    private static void updateClientServerFlight(PlayerEntity player, boolean allowFlying, boolean isFlying) {
        ArmorPlus.PACKET_HANDLER.sendTo(new PacketFlyingSync(allowFlying, isFlying), (ServerPlayerEntity) player);
        player.abilities.allowFlying = allowFlying;
        player.abilities.isFlying = isFlying;
        updateFlightData(player);
    }

    private static void updateFlightData(PlayerEntity player) {
        flightData.setFlying(player.abilities.isFlying);
        flightData.setAllowFlying(player.abilities.allowFlying);
        flightData.setWasFlyingAllowed(player.abilities.allowFlying);
    }

    private static void shouldApplyWaterWeakness(PlayerEntity player) {
        if (player.isInWater()) {
            ticks++;
            if ((ticks + 1) % 20 == 0) {
                for (ItemStack stack : player.getArmorInventoryList()) {
                    if (!stack.isEmpty()) {
                        stack.damageItem(1, player, e -> e.sendBreakAnimation(Objects.requireNonNull(stack.getEquipmentSlot())));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttackEntityEvent(AttackEntityEvent event) {
        World world = event.getEntityLiving().world;
        PlayerEntity player = event.getPlayer();
        Entity target = event.getTarget();
        float attackDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        double movedDistance = player.distanceWalkedModified - player.prevDistanceWalkedModified;
        boolean isMace = false;
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        if (player.isOnGround() && movedDistance < (double) player.getAIMoveSpeed() && stack.getItem() instanceof APMaceItem) {
            isMace = true;
        }

        if (isMace) {
            APMaceItem mace = (APMaceItem) stack.getItem();
            float sweepingDamage = 1.0F + APMaceType.getMaceSweepingRatio(mace.mat.getType()) * attackDamage;

            for (LivingEntity entity : world.getEntitiesWithinAABB(LivingEntity.class, target.getBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
                boolean isNewTarget = entity != player && entity != target;
                boolean isValidTarget = !player.isOnSameTeam(entity) && (!(entity instanceof ArmorStandEntity) || !((ArmorStandEntity) entity).hasMarker());
                boolean isReachable = player.getDistanceSq(entity) < 15.0D;
                if (isNewTarget && isValidTarget && isReachable) {
                    double ratioX = MathHelper.sin(player.rotationYaw * ((float) Math.PI / 180F));
                    double ratioZ = -MathHelper.cos(player.rotationYaw * ((float) Math.PI / 180F));
                    entity.applyKnockback(0.4F, ratioX, ratioZ);
                    entity.attackEntityFrom(DamageSource.causePlayerDamage(player), sweepingDamage);
                }
            }
            if (world.isRemote) {
                mace.swingAnimation(GeckoLibUtil.getControllerForStack(mace.factory, stack, mace.controllerName));
            }
            world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
            player.spawnSweepParticles();
        }
    }

    @SubscribeEvent
    public static void onMobDeathEvent(LivingDropsEvent event) {
        boolean oneInFourChance = RAND.nextInt(4) == 0; // 1/4 chance
        int amountZeroToTwo = RAND.nextInt(3); //0..1..2
        int amountFourToSix = RAND.nextInt(3) + 4; //4+(0..1..2)
        LivingEntity entity = event.getEntityLiving();
        Entity trueSource = event.getSource().getTrueSource();
        boolean isSourcePlayer = trueSource instanceof ServerPlayerEntity;
        ServerPlayerEntity player;
        ItemStack heldItem;
        boolean hasSoulStealer = false;
        Map<Enchantment, Integer> enchantmentList;
        if (isSourcePlayer) {
            player = (ServerPlayerEntity) trueSource;
            heldItem = player.getHeldItemMainhand();
            if (!heldItem.isEmpty()) {
                enchantmentList = EnchantmentHelper.getEnchantments(heldItem);
                hasSoulStealer = enchantmentList.containsKey(ENCHANTMENTS.getValue(setRL("soul_stealer")));
            }
        }
        if (entity != null) {
            if (entity instanceof WitherEntity) {
                dropItem(entity, "wither_bone", amountFourToSix);
                if (hasSoulStealer) {
                    dropItem(entity, "soul_wither_boss", 1);
                }
            } else if (entity instanceof EnderDragonEntity) {
                dropItem(entity, "ender_dragon_scale", amountFourToSix);
                if (hasSoulStealer) {
                    dropItem(entity, "soul_ender_dragon", 1);
                }
            } else if (entity instanceof ElderGuardianEntity) {
                dropItem(entity, "guardian_scale", amountFourToSix);
                if (hasSoulStealer) {
                    dropItem(entity, "soul_elder_guardian", 1);
                }
            } else if (entity instanceof WitherSkeletonEntity) {
                dropItem(entity, "wither_bone", amountZeroToTwo);
                if (hasSoulStealer && oneInFourChance) {
                    dropItem(entity, "soul_wither_skeleton", 1);
                }
            } else if (entity instanceof GuardianEntity) {
                dropItem(entity, "guardian_scale", amountZeroToTwo);
                if (hasSoulStealer && oneInFourChance) {
                    dropItem(entity, "soul_guardian", 1);
                }
            } else if (entity instanceof EndermanEntity) {
                if (hasSoulStealer && oneInFourChance) {
                    dropItem(entity, "soul_enderman", 1);
                }
            } else if (entity instanceof BlazeEntity) {
                if (hasSoulStealer && oneInFourChance) {
                    dropItem(entity, "soul_blaze", 1);
                }
            }
        }
    }

    private static void dropItem(Entity entity, String item, int amount) {
        entity.entityDropItem(new ItemStack(getAPItem(item), amount));
    }
}