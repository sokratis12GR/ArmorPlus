package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.events.data.FlightData;
import com.sofodev.armorplus.network.packet.PacketFlyingSync;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.items.armors.APArmorItem;
import com.sofodev.armorplus.registry.items.armors.IAPArmor;
import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import com.sofodev.armorplus.registry.items.extras.IBuff;
import com.sofodev.armorplus.registry.items.materials.FrostCrystalItem;
import com.sofodev.armorplus.registry.items.tools.APMaceItem;
import com.sofodev.armorplus.registry.items.tools.properties.mace.APMaceType;
import com.sofodev.armorplus.registry.items.tools.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.items.tools.properties.tool.Tool;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.LOGGER;
import static com.sofodev.armorplus.registry.ModEnchantments.SOUL_STEALER;
import static com.sofodev.armorplus.registry.ModItems.FROST_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModVillagers.SOUL_EXCHANGER;
import static com.sofodev.armorplus.registry.items.extras.Buff.FLIGHT;
import static com.sofodev.armorplus.registry.items.extras.Buff.WATER_WEAKNESS;
import static com.sofodev.armorplus.registry.items.extras.DeBuff.*;
import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.Utils.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static net.minecraft.item.Items.*;
import static net.minecraft.util.math.vector.Vector3d.atBottomCenterOf;
import static net.minecraftforge.registries.ForgeRegistries.ENCHANTMENTS;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModGlobalEvents {

    public static final Random RAND = new Random();
    public static int waterTicks = 0;
    public static int thunderingTicks = 0;
    public static FlightData flightData = new FlightData(false, false, false);

    @SubscribeEvent
    public static void onArrowLooseEvent(ArrowLooseEvent e) {
        World world = e.getWorld();
        if (!world.isClientSide()) {
            ItemStack bow = e.getBow();
            int charge = e.getCharge();
            LOGGER.info("Charge at: " + charge);
            Map<Enchantment, Integer> enchantmentList;
            boolean hasUnknownEnchant = false;

            enchantmentList = EnchantmentHelper.getEnchantments(bow);
            if (!enchantmentList.isEmpty()) {
                hasUnknownEnchant = enchantmentList.containsKey(ENCHANTMENTS.getValue(setRL("unknown")));
                if (hasUnknownEnchant) {
                    Entity entity = e.getEntity();
                    Direction direction = entity.getDirection();
                    BlockPos position = entity.blockPosition();
                    int distance = 5 + (charge / (charge / 2));
                    IntStream.range(distance, charge).forEach(i -> {
                        LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(world);
                        if (lightningboltentity != null) {
                            switch (direction) {
                                case NORTH:
                                    lightningboltentity.moveTo(atBottomCenterOf(position.north(i)));
                                    break;
                                case SOUTH:
                                    lightningboltentity.moveTo(atBottomCenterOf(position.south(i)));
                                    break;
                                case WEST:
                                    lightningboltentity.moveTo(atBottomCenterOf(position.west(i)));
                                    break;
                                case EAST:
                                    lightningboltentity.moveTo(atBottomCenterOf(position.east(i)));
                                    break;
                            }
                            world.addFreshEntity(lightningboltentity);
                            bow.hurtAndBreak(10, (LivingEntity) entity, event -> event.broadcastBreakEvent(((LivingEntity) entity).getUsedItemHand()));
                        }
                    });
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTickEvent(PlayerTickEvent e) {
        PlayerEntity player = e.player;
        World world = player.level;
        Map<Enchantment, Integer> enchantmentList;
        if (!world.isClientSide()) {
            boolean thundering = world.isThundering();
            if (thundering) {
                boolean hasUnknownEnchant = false;
                thunderingTicks++;
                if ((thunderingTicks + 1) % 20 == 0) {
                    int chance = RAND.nextInt(100) + 1;
                    if (chance == 100) {
                        for (ItemStack item : player.getArmorSlots()) {
                            Item itemHead = item.getItem();
                            if (itemHead instanceof ArmorItem) {
                                ArmorItem armorItem = (ArmorItem) itemHead;
                                IArmorMaterial material = armorItem.getMaterial();
                                enchantmentList = EnchantmentHelper.getEnchantments(item);
                                if (!enchantmentList.isEmpty()) {
                                    hasUnknownEnchant = enchantmentList.containsKey(ENCHANTMENTS.getValue(setRL("unknown")));
                                    if (hasUnknownEnchant && (armorItem).getSlot() == EquipmentSlotType.HEAD && (material == ArmorMaterial.IRON || material == ArmorMaterial.CHAIN || material == ArmorMaterial.GOLD)) {
                                        BlockPos blockpos = player.blockPosition();
                                        if (world.canSeeSky(blockpos)) {
                                            LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(world);
                                            if (lightningboltentity != null) {
                                                lightningboltentity.moveTo(atBottomCenterOf(blockpos));
                                                lightningboltentity.setCause((ServerPlayerEntity) player);
                                                lightningboltentity.setDamage(0f);
                                                world.addFreshEntity(lightningboltentity);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (ItemStack stack : player.getArmorSlots()) {
                Item item = stack.getItem();
                if (!(item instanceof APArmorItem)) {
                    return;
                }
                IAPArmor mat = ((APArmorItem) item).getMat();
                boolean areExactMatch = areExactMatch(mat, player);
                List<IBuff> buffList = mat.getBuffInstances().get().stream().map(BuffInstance::getBuff).collect(toList());
                if (areExactMatch && mat.config().enableArmorEffects.get()) {
                    if (!buffList.isEmpty()) {
                        if (buffList.contains(FLIGHT)) shouldApplyFlight(e, player);
                        if (buffList.contains(WATER_WEAKNESS)) shouldApplyWaterWeakness(player);
                    }
                } else {
                    attemptDisableFlight(e, player);
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDamageEvent(LivingDamageEvent e) {
        if (e.getSource().getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) e.getSource().getEntity();
            if (!player.level.isClientSide()) {
                ItemStack stack = player.getMainHandItem();
                Item item = stack.getItem();
                if (item instanceof Tool) {
                    IAPTool mat = ((Tool) item).getMat();
                    List<IBuff> buffList = mat.getBuffInstances().get().stream().map(BuffInstance::getBuff).collect(toList());
                    if (!buffList.isEmpty()) {
                        if (buffList.contains(IGNITE)) IGNITE.hitEntity(stack, e.getEntityLiving(), player);
                    }
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
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(SOUL_SAND, 4 + rand.nextInt(16)), new ItemStack(ModItems.SLAYER_SOUL.get()), 6, 15, 0.05f),
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(PRISMARINE, 4 + rand.nextInt(16)), new ItemStack(ModItems.GUARDIAN_SOUL.get()), 6, 15, 0.05f),
                    new BasicTrade(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(END_STONE, 4 + rand.nextInt(16)), new ItemStack(ModItems.ENDERMAN_SOUL.get()), 6, 15, 0.05f)
            ));
            ItemStack priceBook = EnchantedBookItem.createForEnchantment(new EnchantmentData(SOUL_STEALER.get(), 1));
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
            ItemStack priceILBA = EnchantmentHelper.enchantItem(rand, new ItemStack(getAPItem("infused_lava_battle_axe")), bound, false);
            ItemStack priceILS = EnchantmentHelper.enchantItem(rand, new ItemStack(getAPItem("infused_lava_sword")), bound, false);
            ItemStack priceDA = EnchantmentHelper.enchantItem(rand, new ItemStack(Items.DIAMOND_AXE), bound, false);
            ItemStack priceDS = EnchantmentHelper.enchantItem(rand, new ItemStack(Items.DIAMOND_SWORD), bound, false);
            ItemStack priceNA = EnchantmentHelper.enchantItem(rand, new ItemStack(Items.NETHERITE_AXE), bound, false);
            ItemStack priceNS = EnchantmentHelper.enchantItem(rand, new ItemStack(Items.NETHERITE_SWORD), bound, false);
            priceILBA.enchant(SOUL_STEALER.get(), 1);
            priceILS.enchant(SOUL_STEALER.get(), 1);
            priceDA.enchant(SOUL_STEALER.get(), 1);
            priceDS.enchant(SOUL_STEALER.get(), 1);
            priceNA.enchant(SOUL_STEALER.get(), 1);
            priceNS.enchant(SOUL_STEALER.get(), 1);
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

        //disable for now, but don't remove
        final boolean isRunning = false;
        if (isRunning && !e.getWorld().isClientSide()) {
            if (e.getEntity() instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) e.getEntity();
                CompoundNBT nbt = player.serializeNBT();
                if (nbt != null && (!nbt.hasUUID("key") || !nbt.getBoolean("thanked"))) {
                    PlayerAdvancements advancements = player.getAdvancements();
                    AdvancementProgress progress = advancements.getOrStartProgress(Advancement.Builder.advancement().build(setRL("story/thank_you_6m")));
                    if (!progress.isDone()) {
                        nbt.putBoolean("thanked", true);
                        player.addAdditionalSaveData(nbt);
                        player.serializeNBT();
                        player.spawnAtLocation(new ItemStack(ModItems.THANK_YOU_6M.get()));
                    }
                }
            }
        }
    }

    //Flight Control Start

    private static void shouldApplyFlight(PlayerTickEvent e, PlayerEntity player) {
        if (e.phase == TickEvent.Phase.END && e.side.isServer()) {
            if (!player.abilities.mayfly || allowsFlightByDefault(player)) {
                player.abilities.mayfly = true;
                updateClientServerFlight(player, true);
                //       ArmorPlus.LOGGER.info("Enabling flight, hasFlight: " + flightData.wasFlyingAllowed());
            }
        }
    }

    private static void attemptDisableFlight(PlayerTickEvent e, PlayerEntity player) {
        if (e.phase == TickEvent.Phase.END && e.side.isServer()) {
            player.getArmorSlots().forEach(i -> {
                if (!i.isEmpty() || flightData.allowFlying() && flightData.wasFlyingAllowed()) {
                    if (player.abilities.mayfly && flightData.allowFlying() && flightData.wasFlyingAllowed() && !allowsFlightByDefault(player)) {
                        player.abilities.mayfly = false;
                        player.abilities.flying = false;
                        updateClientServerFlight(player, false);
                        flightData.setFlying(false);
                        flightData.setAllowFlying(false);
                        //    ArmorPlus.LOGGER.info("Disabling flight [0], hasFlight: " + flightData.allowFlying());
                    } else if ((i.getItem() instanceof APArmorItem)) {
                        if (areExactMatch(((APArmorItem) i.getItem()).getMat(), player) && player.abilities.mayfly) {
                            player.abilities.mayfly = false;
                            player.abilities.flying = false;
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
        updateClientServerFlight(player, allowFlying, allowFlying && player.abilities.flying);
    }

    private static void updateClientServerFlight(PlayerEntity player, boolean allowFlying, boolean isFlying) {
        ArmorPlus.PACKET_HANDLER.sendTo(new PacketFlyingSync(allowFlying, isFlying), (ServerPlayerEntity) player);
        player.abilities.mayfly = allowFlying;
        player.abilities.flying = isFlying;
        updateFlightData(player);
    }

    private static void updateFlightData(PlayerEntity player) {
        flightData.setFlying(player.abilities.flying);
        flightData.setAllowFlying(player.abilities.mayfly);
        flightData.setWasFlyingAllowed(player.abilities.mayfly);
    }

    //Flight Control End

    private static void shouldApplyWaterWeakness(PlayerEntity player) {
        if (player.isInWater()) {
            waterTicks++;
            if ((waterTicks + 1) % 20 == 0) {
                for (ItemStack stack : player.getArmorSlots()) {
                    if (!stack.isEmpty() && stack.getEquipmentSlot() != null) {
                        stack.hurtAndBreak(1, player, event -> event.broadcastBreakEvent(stack.getEquipmentSlot()));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttackEntityEvent(AttackEntityEvent event) {
        World world = event.getEntityLiving().level;
        PlayerEntity player = event.getPlayer();
        Entity target = event.getTarget();
        float attackDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        double movedDistance = player.walkDist - player.walkDistO;
        boolean isMace = false;
        ItemStack stack = player.getMainHandItem();
        boolean hasUnknown = false;
        Map<Enchantment, Integer> enchantmentList;
        if (!world.isClientSide()) {
            if (!stack.isEmpty() && stack.getItem() instanceof TridentItem) {
                enchantmentList = EnchantmentHelper.getEnchantments(stack);
                if (!enchantmentList.isEmpty()) {
                    hasUnknown = enchantmentList.containsKey(ENCHANTMENTS.getValue(setRL("unknown")));
                    if (hasUnknown) {
                        BlockPos position = target.blockPosition();

                        LightningBoltEntity northBolt = EntityType.LIGHTNING_BOLT.create(world);
                        LightningBoltEntity southBolt = EntityType.LIGHTNING_BOLT.create(world);
                        LightningBoltEntity westBolt = EntityType.LIGHTNING_BOLT.create(world);
                        LightningBoltEntity eastBolt = EntityType.LIGHTNING_BOLT.create(world);
                        LightningBoltEntity centreBolt = EntityType.LIGHTNING_BOLT.create(world);
                        if (northBolt != null && southBolt != null && westBolt != null && eastBolt != null && centreBolt != null) {
                            northBolt.moveTo(atBottomCenterOf(position.north(1)));
                            southBolt.moveTo(atBottomCenterOf(position.south(1)));
                            westBolt.moveTo(atBottomCenterOf(position.west(1)));
                            eastBolt.moveTo(atBottomCenterOf(position.east(1)));
                            centreBolt.moveTo(atBottomCenterOf(position));
                            world.addFreshEntity(northBolt);
                            world.addFreshEntity(southBolt);
                            world.addFreshEntity(westBolt);
                            world.addFreshEntity(eastBolt);
                            world.addFreshEntity(centreBolt);
                        }
                        player.addEffect(new EffectInstance(SLOWNESS.getEffect(), convertToSeconds(4)));
                        player.addEffect(new EffectInstance(MINING_FATIGUE.getEffect(), convertToSeconds(4)));
                    }
                }
            }
        }
        if (player.isOnGround() && movedDistance < (double) player.getSpeed() && stack.getItem() instanceof APMaceItem) {
            isMace = true;
        }
        if (isMace) {
            APMaceItem mace = (APMaceItem) stack.getItem();
            float sweepingDamage = 1.0F + APMaceType.getMaceSweepingRatio(mace.mat.getType()) * attackDamage;

            for (LivingEntity entity : world.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1.0D, 0.25D, 1.0D))) {
                boolean isNewTarget = entity != player && entity != target;
                boolean isValidTarget = !player.isAlliedTo(entity) && (!(entity instanceof ArmorStandEntity) || !((ArmorStandEntity) entity).isMarker());
                boolean isReachable = player.distanceToSqr(entity) < 15.0D;
                if (isNewTarget && isValidTarget && isReachable) {
                    double ratioX = MathHelper.sin(player.yRot * ((float) Math.PI / 180F));
                    double ratioZ = -MathHelper.cos(player.yRot * ((float) Math.PI / 180F));
                    entity.knockback(0.4F, ratioX, ratioZ);
                    entity.hurt(DamageSource.playerAttack(player), sweepingDamage);
                }
            }
            if (world.isClientSide) {
                mace.swingAnimation(GeckoLibUtil.getControllerForStack(mace.factory, stack, mace.controllerName));
            }
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
            player.sweepAttack();
        }
    }

    @SubscribeEvent
    public static void onStructByLightningEvent(EntityStruckByLightningEvent event) {
        if (!event.getEntity().level.isClientSide && event.getEntity() instanceof ItemEntity) {
            ItemEntity entity = (ItemEntity) event.getEntity();
            Item item = entity.getItem().getItem();
            if (item instanceof FrostCrystalItem) {
                boolean infused = ((FrostCrystalItem) item).isInfused();
                if (!infused) {
                    FrostCrystalItem infusedCrystal = (FrostCrystalItem) getAPItem("infused_frost_crystal");
                    entity.spawnAtLocation(new ItemStack(infusedCrystal, entity.getItem().getCount()), 1f);
                    entity.getItem().setCount(0);
                    event.getLightning().setVisualOnly(true);
                    event.setCanceled(true);
                }
            }
        }
    }

    //
    // ENTITY DROPS
    //

    @SubscribeEvent
    public static void onMobDeathEvent(LivingDropsEvent event) {
        boolean oneInFourChance = RAND.nextInt(4) == 0; // 1/4 chance
        int amountZeroToTwo = RAND.nextInt(3); //0..1..2
        int amountFourToSix = RAND.nextInt(3) + 4; //4+(0..1..2)
        LivingEntity entity = event.getEntityLiving();
        Entity trueSource = event.getSource().getEntity();
        boolean isSourcePlayer = trueSource instanceof ServerPlayerEntity;
        ServerPlayerEntity player;
        ItemStack heldItem;
        boolean hasSoulStealer = false;
        Map<Enchantment, Integer> enchantmentList;
        if (isSourcePlayer) {
            player = (ServerPlayerEntity) trueSource;
            heldItem = player.getMainHandItem();
            if (!heldItem.isEmpty()) {
                enchantmentList = EnchantmentHelper.getEnchantments(heldItem);
                hasSoulStealer = enchantmentList.containsKey(ENCHANTMENTS.getValue(setRL("soul_stealer")));
            }
        }
        if (entity != null) {
            if (entity instanceof WitherEntity) {
                dropTrophyItem(entity, EntityType.WITHER, 0.2F);
                dropItem(entity, "wither_bone", amountFourToSix);
                if (hasSoulStealer) {
                    dropItem(entity, "soul_wither_boss", 1);
                }
            } else if (entity instanceof EnderDragonEntity) {
                dropTrophyItem(entity, EntityType.ENDER_DRAGON, 0.1F);
                dropItem(entity, "ender_dragon_scale", amountFourToSix);
                if (hasSoulStealer) {
                    dropItem(entity, "soul_ender_dragon", 1);
                }
            } else if (entity instanceof ElderGuardianEntity) {
                dropTrophyItem(entity, EntityType.ELDER_GUARDIAN, 0.2F);
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

    private static void dropTrophyItem(LivingEntity entity, EntityType<?> type, float scale) {
        ItemStack trophy = new ItemStack(getAPItem("trophy"));
        CompoundNBT tag = new CompoundNBT();
        WeightedSpawnerEntity trophyEntity = new WeightedSpawnerEntity();
        trophyEntity.getTag().putString("id", ForgeRegistries.ENTITIES.getKey(type).toString());
        tag.put("DisplayEntity", trophyEntity.getTag().copy());
        tag.putFloat("EntityScale", scale);
        trophy.setTag(tag);
        entity.spawnAtLocation(trophy);
    }

    private static void dropItem(Entity entity, String item, int amount) {
        entity.spawnAtLocation(new ItemStack(getAPItem(item), amount));
    }
}