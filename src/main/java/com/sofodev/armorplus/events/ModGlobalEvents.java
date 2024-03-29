package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.events.data.FlightData;
import com.sofodev.armorplus.network.packet.PacketFlyingSync;
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
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.shadowed.eliotlash.mclib.utils.MathHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.LOGGER;
import static com.sofodev.armorplus.config.ArmorPlusConfig.*;
import static com.sofodev.armorplus.registry.ModItems.THANK_YOU_6M;
import static com.sofodev.armorplus.registry.items.extras.Buff.FLIGHT;
import static com.sofodev.armorplus.registry.items.extras.Buff.WATER_WEAKNESS;
import static com.sofodev.armorplus.registry.items.extras.DeBuff.*;
import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.Utils.*;
import static java.util.stream.Collectors.toList;
import static net.minecraft.world.phys.Vec3.atBottomCenterOf;
import static net.minecraftforge.registries.ForgeRegistries.ENCHANTMENTS;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModGlobalEvents {

    public static final Random RAND = new Random();
    public static int waterTicks = 0;
    public static int thunderingTicks = 0;
    public static FlightData flightData = new FlightData(false, false, false);

    @SubscribeEvent
    public static void onArrowLooseEvent(ArrowLooseEvent e) {
        Level world = e.getLevel();
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
                        LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(world);
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
        Player player = e.player;
        Level world = player.level;
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
                                ArmorMaterial material = armorItem.getMaterial();
                                enchantmentList = EnchantmentHelper.getEnchantments(item);
                                if (!enchantmentList.isEmpty()) {
                                    hasUnknownEnchant = enchantmentList.containsKey(ENCHANTMENTS.getValue(setRL("unknown")));
                                    if (hasUnknownEnchant && (armorItem).getSlot() == EquipmentSlot.HEAD && (material == ArmorMaterials.IRON || material == ArmorMaterials.CHAIN || material == ArmorMaterials.GOLD)) {
                                        BlockPos blockpos = player.blockPosition();
                                        if (world.canSeeSky(blockpos)) {
                                            LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(world);
                                            if (lightningboltentity != null) {
                                                lightningboltentity.moveTo(atBottomCenterOf(blockpos));
                                                lightningboltentity.setCause((ServerPlayer) player);
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
                List<IBuff> buffList = mat.getBuffInstances().get().stream().map(BuffInstance::getBuff).toList();
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

    /*@SubscribeEvent
    public static void onVillagerTradesEvent(VillagerTradesEvent e) {
        Random rand = new Random();
        RandomSource randSource = RandomSource.create();
        VillagerProfession type = e.getType();
        if (type.equals(ForgeRegistries.VILLAGER_PROFESSIONS.getValue(setRL("soul_exchanger")))) {
            ItemStack witherSoul = new ItemStack(getAPItem(WITHER_BOSS_SOUL.getKey()), 1);
            ItemStack enderDragonSoul = new ItemStack(getAPItem(ENDER_DRAGON_SOUL.getKey()), 1);
            ItemStack elderGuardianSoul = new ItemStack(getAPItem(ELDER_GUARDIAN_SOUL.getKey()), 1);
            e.getTrades().put(1, asList(
                    new BasicItemListing(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(LAVA_SHARD.get(), 3 + rand.nextInt(3)), 16, 2, 0.4f),
                    new BasicItemListing(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(FROST_SHARD.get(), 3 + rand.nextInt(3)), 16, 2, 0.4f),
                    new BasicItemListing(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(Items.BLAZE_POWDER, 3 + rand.nextInt(3)), 16, 2, 0.4f)
            ));
            e.getTrades().put(2, asList(
                    new BasicItemListing(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(LAVA_SHARD.get(), 5 + rand.nextInt(4)), 8, 6, 0.2f),
                    new BasicItemListing(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(FROST_SHARD.get(), 5 + rand.nextInt(4)), 8, 6, 0.2f),
                    new BasicItemListing(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(Items.BLAZE_ROD, 2 + rand.nextInt(2)), 8, 6, 0.2f),
                    new BasicItemListing(new ItemStack(EMERALD, 8 + rand.nextInt(6)), new ItemStack(LAVA_CRYSTAL.get(), 1 + rand.nextInt(2)), 8, 10, 0.2f),
                    new BasicItemListing(new ItemStack(EMERALD, 8 + rand.nextInt(6)), new ItemStack(FROST_CRYSTAL.get(), 1 + rand.nextInt(2)), 8, 10, 0.2f)
            ));
            e.getTrades().put(3, asList(
                    new BasicItemListing(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(SOUL_SAND, 4 + rand.nextInt(16)), new ItemStack(WITHER_SKELETON_SOUL.get()), 6, 15, 0.05f),
                    new BasicItemListing(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(SOUL_SAND, 4 + rand.nextInt(16)), new ItemStack(SLAYER_SOUL.get()), 6, 15, 0.05f),
                    new BasicItemListing(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(PRISMARINE, 4 + rand.nextInt(16)), new ItemStack(GUARDIAN_SOUL.get()), 6, 15, 0.05f),
                    new BasicItemListing(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(END_STONE, 4 + rand.nextInt(16)), new ItemStack(ENDERMAN_SOUL.get()), 6, 15, 0.05f)
            ));
            ItemStack priceBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(SOUL_STEALER.get(), 1));
            e.getTrades().put(4, asList(
                    new BasicItemListing(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), witherSoul, elderGuardianSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), enderDragonSoul, elderGuardianSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), elderGuardianSoul, witherSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), enderDragonSoul, witherSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), witherSoul, enderDragonSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), elderGuardianSoul, enderDragonSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(LAVA_CRYSTAL.get(), 10 + rand.nextInt(10)), priceBook, 2, 20, 0.0f)
            ));
            //Tier Emerald Trades
            //Boss Soul for Boss Soul exchange without cost.
            //Soul Stealer enchanted gear.
            int bound = 5 + rand.nextInt(15);
            ItemStack priceILBA = EnchantmentHelper.enchantItem(randSource, new ItemStack(getAPItem("infused_lava_battle_axe")), bound, false);
            ItemStack priceILS = EnchantmentHelper.enchantItem(randSource, new ItemStack(getAPItem("infused_lava_sword")), bound, false);
            ItemStack priceDA = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.DIAMOND_AXE), bound, false);
            ItemStack priceDS = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.DIAMOND_SWORD), bound, false);
            ItemStack priceNA = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.NETHERITE_AXE), bound, false);
            ItemStack priceNS = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.NETHERITE_SWORD), bound, false);
            priceILBA.enchant(SOUL_STEALER.get(), 1);
            priceILS.enchant(SOUL_STEALER.get(), 1);
            priceDA.enchant(SOUL_STEALER.get(), 1);
            priceDS.enchant(SOUL_STEALER.get(), 1);
            priceNA.enchant(SOUL_STEALER.get(), 1);
            priceNS.enchant(SOUL_STEALER.get(), 1);
            int crystalAmount = Math.min(10 + bound, 64);
            ItemStack crystalCost = new ItemStack(LAVA_CRYSTAL.get(), crystalAmount);
            e.getTrades().put(5, asList(
                    new BasicItemListing(witherSoul, elderGuardianSoul, 3, 25, 0f),
                    new BasicItemListing(enderDragonSoul, elderGuardianSoul, 3, 25, 0f),
                    new BasicItemListing(elderGuardianSoul, witherSoul, 3, 25, 0f),
                    new BasicItemListing(enderDragonSoul, witherSoul, 3, 25, 0f),
                    new BasicItemListing(witherSoul, enderDragonSoul, 3, 25, 0f),
                    new BasicItemListing(elderGuardianSoul, enderDragonSoul, 3, 25, 0f),
                    new BasicItemListing(crystalCost, priceILBA, 1, 30, 0f),
                    new BasicItemListing(crystalCost, priceILS, 1, 30, 0f),
                    new BasicItemListing(crystalCost, priceDA, 1, 30, 0f),
                    new BasicItemListing(crystalCost, priceDS, 1, 30, 0f),
                    new BasicItemListing(crystalCost, priceNA, 1, 30, 0f),
                    new BasicItemListing(crystalCost, priceNS, 1, 30, 0f)
            ));
        }
    }*/

    @SubscribeEvent
    public static void onPlayerJoinWorldEvent(EntityJoinLevelEvent e) {

        //disable for now, but don't remove
        final boolean isRunning = false;
        if (isRunning && !e.getLevel().isClientSide()) {
            if (e.getEntity() instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer) e.getEntity();
                CompoundTag nbt = player.serializeNBT();
                if (nbt != null && (!nbt.hasUUID("key") || !nbt.getBoolean("thanked"))) {
                    PlayerAdvancements advancements = player.getAdvancements();
                    AdvancementProgress progress = advancements.getOrStartProgress(Advancement.Builder.advancement()
                            .build(setRL("story/thank_you_6m")));
                    if (!progress.isDone()) {
                        nbt.putBoolean("thanked", true);
                        player.addAdditionalSaveData(nbt);
                        player.serializeNBT();
                        player.spawnAtLocation(new ItemStack(THANK_YOU_6M.get()));
                    }
                }
            }
        }
    }

    //Flight Control Start

    private static void shouldApplyFlight(PlayerTickEvent e, Player player) {
        if (e.phase == TickEvent.Phase.END && e.side.isServer()) {
            if (!player.getAbilities().mayfly || allowsFlightByDefault(player)) {
                player.getAbilities().mayfly = true;
                updateClientServerFlight(player, true);
                //       ArmorPlus.LOGGER.info("Enabling flight, hasFlight: " + flightData.wasFlyingAllowed());
            }
        }
    }

    private static void attemptDisableFlight(PlayerTickEvent e, Player player) {
        if (e.phase == TickEvent.Phase.END && e.side.isServer()) {
            player.getArmorSlots().forEach(i -> {
                if (!i.isEmpty() || flightData.allowFlying() && flightData.wasFlyingAllowed()) {
                    if (player.getAbilities().mayfly && flightData.allowFlying() && flightData.wasFlyingAllowed() && !allowsFlightByDefault(player)) {
                        player.getAbilities().mayfly = false;
                        player.getAbilities().flying = false;
                        updateClientServerFlight(player, false);
                        flightData.setFlying(false);
                        flightData.setAllowFlying(false);
                        //    ArmorPlus.LOGGER.info("Disabling flight [0], hasFlight: " + flightData.allowFlying());
                    } else if ((i.getItem() instanceof APArmorItem)) {
                        if (areExactMatch(((APArmorItem) i.getItem()).getMat(), player) && player.getAbilities().mayfly) {
                            player.getAbilities().mayfly = false;
                            player.getAbilities().flying = false;
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

    private static void updateClientServerFlight(Player player, boolean allowFlying) {
        updateClientServerFlight(player, allowFlying, allowFlying && player.getAbilities().flying);
    }

    private static void updateClientServerFlight(Player player, boolean allowFlying, boolean isFlying) {
        ArmorPlus.PACKET_HANDLER.sendTo(new PacketFlyingSync(allowFlying, isFlying), (ServerPlayer) player);
        player.getAbilities().mayfly = allowFlying;
        player.getAbilities().flying = isFlying;
        updateFlightData(player);
    }

    private static void updateFlightData(Player player) {
        flightData.setFlying(player.getAbilities().flying);
        flightData.setAllowFlying(player.getAbilities().mayfly);
        flightData.setWasFlyingAllowed(player.getAbilities().mayfly);
    }

    //Flight Control End

    private static void shouldApplyWaterWeakness(Player player) {
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
        Level world = event.getEntity().level;
        Player player = event.getEntity();
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
                        LightningBolt northBolt = EntityType.LIGHTNING_BOLT.create(world);
                        LightningBolt southBolt = EntityType.LIGHTNING_BOLT.create(world);
                        LightningBolt westBolt = EntityType.LIGHTNING_BOLT.create(world);
                        LightningBolt eastBolt = EntityType.LIGHTNING_BOLT.create(world);
                        LightningBolt centreBolt = EntityType.LIGHTNING_BOLT.create(world);
                        if (northBolt != null && southBolt != null && westBolt != null && eastBolt != null && centreBolt != null) {
                            northBolt.moveTo(atBottomCenterOf(position.north(2)));
                            southBolt.moveTo(atBottomCenterOf(position.south(2)));
                            westBolt.moveTo(atBottomCenterOf(position.west(2)));
                            eastBolt.moveTo(atBottomCenterOf(position.east(2)));
                            centreBolt.moveTo(atBottomCenterOf(position));
                            world.addFreshEntity(northBolt);
                            world.addFreshEntity(southBolt);
                            world.addFreshEntity(westBolt);
                            world.addFreshEntity(eastBolt);
                            world.addFreshEntity(centreBolt);
                        }
                        player.addEffect(new MobEffectInstance(SLOWNESS.getEffect(), convertToSeconds(4)));
                        player.addEffect(new MobEffectInstance(MINING_FATIGUE.getEffect(), convertToSeconds(4)));
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

            for (LivingEntity entity : world.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox()
                    .inflate(1.0D, 0.25D, 1.0D))) {
                boolean isNewTarget = entity != player && entity != target;
                boolean isValidTarget = !player.isAlliedTo(entity) && (!(entity instanceof ArmorStand) || !((ArmorStand) entity).isMarker());
                boolean isReachable = player.distanceToSqr(entity) < 15.0D;
                if (isNewTarget && isValidTarget && isReachable) {
                    double ratioX = MathHelper.wrapDegrees(player.getYRot() * ((float) Math.PI / 180F));
                    double ratioZ = -MathHelper.wrapDegrees(player.getYRot() * ((float) Math.PI / 180F));
                    entity.knockback(0.4F, ratioX, ratioZ);
                    entity.hurt(DamageSource.playerAttack(player), sweepingDamage);
                }
            }
            if (world instanceof ServerLevel serverLevel) {
                ItemStack newStack = mace.setTag(player.getMainHandItem());
                CompoundTag nbt = newStack.getTag();
                if (nbt != null && nbt.hasUUID("key")) {
                    mace.triggerAnim(player, GeoItem.getOrAssignId(player.getMainHandItem(), serverLevel), mace.controllerName, "animation.mace.swipe_attack");
                }
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
    // ITEMSTACK EVENTS
    //

    @SubscribeEvent
    public static void onLivingDamageEvent(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            if (!player.level.isClientSide()) {
                ItemStack stack = player.getMainHandItem();
                Item item = stack.getItem();
                if (item instanceof Tool) {
                    IAPTool mat = ((Tool) item).getMat();
                    List<IBuff> buffList = mat.getBuffInstances()
                            .get()
                            .stream()
                            .map(BuffInstance::getBuff).toList();
                    if (!buffList.isEmpty()) {
//                        if (buffList.contains(IGNITE)) IGNITE.hitEntity(stack, entity, player); // This damages the player with ignite
                    }
                }
            }
        }

        List<ItemStack> armor = new ArrayList<>();
        for (ItemStack slotStack : entity.getArmorSlots()) {
            armor.add(slotStack);
        }
        if (!armor.isEmpty()) {
            for (ItemStack stack : armor) {
                Map<Enchantment, Integer> enchantmentList;
                boolean hasSoulHarden;
                if (!stack.isDamageableItem() || !(stack.getItem() instanceof ArmorItem)) {
                    continue;
                }
                ArmorItem item = (ArmorItem) stack.getItem();
                enchantmentList = EnchantmentHelper.getEnchantments(stack);
                if (enchantmentList.isEmpty()) {
                    continue;
                }
                hasSoulHarden = enchantmentList.containsKey(ENCHANTMENTS.getValue(setRL("soul_harden")));
                if (!hasSoulHarden) {
                    continue;
                }
                int maxDamageValue = stack.getMaxDamage(); // the max itemstack damage value
                int currentDamageValue = maxDamageValue - stack.getDamageValue(); // Get the actual itemstack value
                int halfDamageValue = Math.min(maxDamageValue / 2, Math.floorDiv(maxDamageValue, 2)); // We get the smallest possible number of a division by 2
                if (currentDamageValue == maxDamageValue) {
                    stack.setDamageValue(0);
                } else {
                    stack.setDamageValue(stack.getDamageValue() - 1);
                }
            }
        }
    }


    /**
     * By using the LivingDeathEvent event, we then check the slain entity's inventory (just before it's slain), and if
     * it contained a list of armors we follow with the next steps of checking if said armors are valid, check if it has
     * any enchantment and if said enchantment matches one of our criteria, in this case we check for "Soul Harden",
     * afterwards we check the current amount of durability and act accordingly (full -> half, half -> none, remove item.)
     *
     * @param event The event is triggered only when an entity is killed/removed from the world.
     */
    @SubscribeEvent
    public static void onLivingDeathEvent(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        List<ItemStack> armor = new ArrayList<>();
        for (ItemStack slotStack : entity.getArmorSlots()) {
            armor.add(slotStack);
        }
        if (!armor.isEmpty()) {
            for (ItemStack stack : armor) {
                Map<Enchantment, Integer> enchantmentList;
                boolean hasSoulHarden;
                if (!stack.isDamageableItem() || !(stack.getItem() instanceof ArmorItem)) {
                    continue;
                }
                ArmorItem item = (ArmorItem) stack.getItem();
                enchantmentList = EnchantmentHelper.getEnchantments(stack);
                if (enchantmentList.isEmpty()) {
                    continue;
                }
                hasSoulHarden = enchantmentList.containsKey(ENCHANTMENTS.getValue(setRL("soul_harden")));
                if (!hasSoulHarden) {
                    continue;
                }
                int maxDamageValue = stack.getMaxDamage(); // the max itemstack damage value
                int incomingDamageValue = stack.getDamageValue();
                int currentDamageValue = maxDamageValue - incomingDamageValue; // Get the actual itemstack value
                int halfDamageValue = Math.min(maxDamageValue / 2, Math.floorDiv(maxDamageValue, 2)); // We get the smallest possible number of a division by 2
                if (currentDamageValue == maxDamageValue) {
                    stack.setDamageValue(halfDamageValue);
                } else if (incomingDamageValue >= halfDamageValue) {
                    stack.setDamageValue(halfDamageValue);
                } else {
                    stack.setDamageValue(maxDamageValue);
                    stack.setCount(0);
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
        LivingEntity entity = event.getEntity();
        Entity trueSource = event.getSource().getEntity();
        boolean isSourcePlayer = trueSource instanceof ServerPlayer;
        ServerPlayer player;
        ItemStack heldItem;
        boolean hasSoulStealer = false;
        Map<Enchantment, Integer> enchantmentList;
        if (isSourcePlayer) {
            player = (ServerPlayer) trueSource;
            heldItem = player.getMainHandItem();
            if (!heldItem.isEmpty()) {
                enchantmentList = EnchantmentHelper.getEnchantments(heldItem);
                hasSoulStealer = enchantmentList.containsKey(ENCHANTMENTS.getValue(setRL("soul_stealer")));
            }
        }
        if (entity != null) {
            if (entity instanceof WitherBoss) {
                if (witherBossDrops.enableTrophyDrops.get()) dropTrophyItem(entity, EntityType.WITHER, 0.2F);
                if (witherBossDrops.enableRegularDrops.get()) dropItem(entity, "wither_bone", amountFourToSix);
                if (hasSoulStealer && witherBossDrops.enableSoulDrops.get()) {
                    dropItem(entity, "soul_wither_boss", 1);
                }
            } else if (entity instanceof EnderDragon) {
                if (enderDragonDrops.enableTrophyDrops.get()) dropTrophyItem(entity, EntityType.ENDER_DRAGON, 0.1F);
                if (enderDragonDrops.enableRegularDrops.get()) dropItem(entity, "ender_dragon_scale", amountFourToSix);
                if (hasSoulStealer && enderDragonDrops.enableSoulDrops.get()) {
                    dropItem(entity, "soul_ender_dragon", 1);
                }
            } else if (entity instanceof ElderGuardian) {
                if (elderGuardianDrops.enableTrophyDrops.get()) dropTrophyItem(entity, EntityType.ELDER_GUARDIAN, 0.2F);
                if (elderGuardianDrops.enableRegularDrops.get()) dropItem(entity, "guardian_scale", amountFourToSix);
                if (hasSoulStealer && elderGuardianDrops.enableSoulDrops.get()) {
                    dropItem(entity, "soul_elder_guardian", 1);
                }
            } else if (entity instanceof WitherSkeleton) {
                if (witherSkeletonDrops.enableRegularDrops.get()) dropItem(entity, "wither_bone", amountZeroToTwo);
                if (hasSoulStealer && oneInFourChance && witherSkeletonDrops.enableSoulDrops.get()) {
                    dropItem(entity, "soul_wither_skeleton", 1);
                }
            } else if (entity instanceof Guardian) {
                if (guardianDrops.enableRegularDrops.get()) dropItem(entity, "guardian_scale", amountZeroToTwo);
                if (hasSoulStealer && oneInFourChance && guardianDrops.enableSoulDrops.get()) {
                    dropItem(entity, "soul_guardian", 1);
                }
            } else if (entity instanceof EnderMan) {
                if (endermanDrops.enableRegularDrops.get()) {
                    //No drops as of this moment
                }
                if (hasSoulStealer && oneInFourChance && endermanDrops.enableSoulDrops.get()) {
                    dropItem(entity, "soul_enderman", 1);
                }
            } else if (entity instanceof Blaze) {
                if (blazeDrops.enableRegularDrops.get()) {
                    //No drops as of this moment
                }
                if (hasSoulStealer && oneInFourChance && blazeDrops.enableSoulDrops.get()) {
                    dropItem(entity, "soul_blaze", 1);
                }
            }
        }
    }

    private static void dropTrophyItem(LivingEntity entity, EntityType<?> type, float scale) {
        ItemStack trophy = new ItemStack(getAPItem("trophy"));
        CompoundTag tag = new CompoundTag();
        SpawnData trophyEntity = new SpawnData();
        ResourceLocation key = ForgeRegistries.ENTITY_TYPES.getKey(type);
        if (key == null) key = new ResourceLocation("minecraft:pig");
        trophyEntity.getEntityToSpawn().putString("id", key.toString());
        tag.put("DisplayEntity", trophyEntity.getEntityToSpawn().copy());
        tag.putFloat("EntityScale", scale);
        trophy.setTag(tag);
        entity.spawnAtLocation(trophy);
    }

    private static void dropItem(Entity entity, String item, int amount) {
        entity.spawnAtLocation(new ItemStack(getAPItem(item), amount));
    }
}