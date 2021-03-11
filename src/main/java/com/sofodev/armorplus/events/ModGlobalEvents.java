package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.APConfig;
import com.sofodev.armorplus.events.data.FlightData;
import com.sofodev.armorplus.network.packet.PacketFlyingSync;
import com.sofodev.armorplus.registry.ModItems;
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
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.*;

import static com.sofodev.armorplus.registry.ModEnchantments.SOUL_STEALER;
import static com.sofodev.armorplus.registry.ModItems.FROST_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModVillagers.SOUL_EXCHANGER;
import static com.sofodev.armorplus.registry.items.extras.Buff.FLIGHT;
import static com.sofodev.armorplus.registry.items.extras.Buff.WATER_WEAKNESS;
import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.Utils.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static net.minecraft.item.Items.*;
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
    public static void onVillagerTradesEvent(VillagerTradesEvent e) {
        Random rand = new Random();
        VillagerProfession type = e.getType();
        if (type.equals(SOUL_EXCHANGER.get())) {
            ItemStack witherSoul = new ItemStack(ModItems.WITHER_BOSS_SOUL.get(), 1);
            ItemStack enderDragonSoul = new ItemStack(ModItems.ENDER_DRAGON_SOUL.get(), 1);
            ItemStack elderGuardianSoul = new ItemStack(ModItems.ELDER_GUARDIAN_SOUL.get(), 1);
            e.getTrades().put(1, asList(
                    new BasicTrade(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(ModItems.LAVA_SHARD.get(), 3 + rand.nextInt(3)), 16, 2, 0.4f),
                    new BasicTrade(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(ModItems.FROST_SHARD.get(), 3 + rand.nextInt(3)), 16, 2, 0.4f),
                    new BasicTrade(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(Items.BLAZE_POWDER, 3 + rand.nextInt(3)), 16, 2, 0.4f)
            ));
            e.getTrades().put(2, asList(
                    new BasicTrade(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(ModItems.LAVA_SHARD.get(), 5 + rand.nextInt(4)), 8, 6, 0.2f),
                    new BasicTrade(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(ModItems.FROST_SHARD.get(), 5 + rand.nextInt(4)), 8, 6, 0.2f),
                    new BasicTrade(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(Items.BLAZE_ROD, 2 + rand.nextInt(2)), 8, 6, 0.2f),
                    new BasicTrade(new ItemStack(EMERALD, 8 + rand.nextInt(6)), new ItemStack(LAVA_CRYSTAL.get(), 1 + rand.nextInt(2)), 8, 10, 0.2f),
                    new BasicTrade(new ItemStack(EMERALD, 8 + rand.nextInt(6)), new ItemStack(FROST_CRYSTAL.get(), 1 + rand.nextInt(2)), 8, 10, 0.2f)
            ));
            e.getTrades().put(3, asList(
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(SOUL_SAND, 4 + rand.nextInt(16)), new ItemStack(ModItems.WITHER_SKELETON_SOUL.get()), 6, 15, 0.05f),
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(SOUL_SAND, 4 + rand.nextInt(16)), new ItemStack(ModItems.BLAZE_SOUL.get()), 6, 15, 0.05f),
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(PRISMARINE, 4 + rand.nextInt(16)), new ItemStack(ModItems.GUARDIAN_SOUL.get()), 6, 15, 0.05f),
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(END_STONE, 4 + rand.nextInt(16)), new ItemStack(ModItems.ENDERMAN_SOUL.get()), 6, 15, 0.05f)
            ));
            ItemStack priceBook = EnchantedBookItem.getEnchantedItemStack(new EnchantmentData(SOUL_STEALER.get(), 1));
            e.getTrades().put(4, asList(
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), witherSoul, elderGuardianSoul, 2, 20, 0.0f),
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), enderDragonSoul, elderGuardianSoul, 2, 20, 0.0f),
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), elderGuardianSoul, witherSoul, 2, 20, 0.0f),
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), enderDragonSoul, witherSoul, 2, 20, 0.0f),
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), witherSoul, enderDragonSoul, 2, 20, 0.0f),
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), elderGuardianSoul, enderDragonSoul, 2, 20, 0.0f),
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 10 + rand.nextInt(10)), priceBook, 2, 20, 0.0f)
            ));
            //Tier Emerald Trades
            //Boss Soul for Boss Soul exchange without cost.
            //Soul Stealer enchanted gear.
            int bound = 5 + rand.nextInt(15);
            ItemStack priceILBA = EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(getAPItem("infused_lava_battle_axe")), bound, false);
            ItemStack priceILS = EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(getAPItem("infused_lava_sword")), bound, false);
            ItemStack priceDA = EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(Items.DIAMOND_AXE), bound, false);
            ItemStack priceDS = EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(Items.DIAMOND_SWORD), bound, false);
            ItemStack priceNA = EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(Items.NETHERITE_AXE), bound, false);
            ItemStack priceNS = EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(Items.NETHERITE_SWORD), bound, false);
            priceILBA.addEnchantment(SOUL_STEALER.get(), 1);
            priceILS.addEnchantment(SOUL_STEALER.get(), 1);
            priceDA.addEnchantment(SOUL_STEALER.get(), 1);
            priceDS.addEnchantment(SOUL_STEALER.get(), 1);
            priceNA.addEnchantment(SOUL_STEALER.get(), 1);
            priceNS.addEnchantment(SOUL_STEALER.get(), 1);
            int crystalAmount = Math.min(10 + bound, 64);
            ItemStack crystalCost = new ItemStack(LAVA_CRYSTAL.get(), crystalAmount);
            e.getTrades().put(5, asList(
                    new BasicTrade(witherSoul, elderGuardianSoul, 3, 25, 0f),
                    new BasicTrade(enderDragonSoul, elderGuardianSoul, 3, 25, 0f),
                    new BasicTrade(elderGuardianSoul, witherSoul, 3, 25, 0f),
                    new BasicTrade(enderDragonSoul, witherSoul, 3, 25, 0f),
                    new BasicTrade(witherSoul, enderDragonSoul, 3, 25, 0f),
                    new BasicTrade(elderGuardianSoul, enderDragonSoul, 3, 25, 0f),
                    new BasicTrade(crystalCost, priceILBA, 1, 30, 0f),
                    new BasicTrade(crystalCost, priceILS, 1, 30, 0f),
                    new BasicTrade(crystalCost, priceDA, 1, 30, 0f),
                    new BasicTrade(crystalCost, priceDS, 1, 30, 0f),
                    new BasicTrade(crystalCost, priceNA, 1, 30, 0f),
                    new BasicTrade(crystalCost, priceNS, 1, 30, 0f)
            ));
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
                        player.entityDropItem(new ItemStack(ModItems.THANK_YOU_6M.get()));
                    }
                }
            }
        }
    }

    //Flight Control Start

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

    //Flight Control End

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