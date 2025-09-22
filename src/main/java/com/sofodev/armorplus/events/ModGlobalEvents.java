package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
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
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.animatable.GeoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.LOGGER;
import static com.sofodev.armorplus.config.ArmorPlusConfig.*;
import static com.sofodev.armorplus.registry.ModItems.THANK_YOU_6M;
import static com.sofodev.armorplus.registry.items.extras.Buff.FLIGHT;
import static com.sofodev.armorplus.registry.items.extras.Buff.WATER_WEAKNESS;
import static com.sofodev.armorplus.registry.items.extras.DeBuff.MINING_FATIGUE;
import static com.sofodev.armorplus.registry.items.extras.DeBuff.SLOWNESS;
import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.Utils.*;
import static net.minecraft.world.phys.Vec3.atBottomCenterOf;
import static net.minecraftforge.registries.ForgeRegistries.ENCHANTMENTS;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModGlobalEvents {

    public static final Random RAND = new Random();
    public static int waterTicks = 0;
    public static int thunderingTicks = 0;
    private static final String ARMORPLUS_FLIGHT_TAG = "ArmorPlusFlight";
    private static final String ARMORPLUS_PREV_MAYFLY = "ArmorPlusPrevMayfly";

    private static boolean hasEnchant(ItemStack stack, String name) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        return !enchantments.isEmpty() && enchantments.containsKey(ENCHANTMENTS.getValue(setRL(name)));
    }

    private static boolean isFullArmorWithBuff(Player player, IBuff buff) {
        for (ItemStack stack : player.getArmorSlots()) {
            if (stack.getItem() instanceof APArmorItem armor) {
                IAPArmor mat = armor.getMat();
                if (areExactMatch(mat, player) && mat.config().enableArmorEffects.get()) {
                    return mat.getBuffInstances().get().stream()
                            .map(BuffInstance::getBuff)
                            .anyMatch(b -> b.equals(buff));
                }
            }
        }
        return false;
    }

    private static void grantFlight(Player player) {
        if (player.isCreative() || player.isSpectator()) return;

        if (!player.getPersistentData().contains(ARMORPLUS_PREV_MAYFLY)) {
            player.getPersistentData().putBoolean(ARMORPLUS_PREV_MAYFLY, player.getAbilities().mayfly);
        }

        player.getAbilities().mayfly = true;
        player.getPersistentData().putBoolean(ARMORPLUS_FLIGHT_TAG, true);
        player.onUpdateAbilities();
    }

    private static void revokeFlight(Player player) {
        if (player.isCreative() || player.isSpectator()) return;

        boolean hadAPFlight = player.getPersistentData().getBoolean(ARMORPLUS_FLIGHT_TAG);
        if (!hadAPFlight) return;

        player.getPersistentData().remove(ARMORPLUS_FLIGHT_TAG);

        boolean prevMayfly = player.getPersistentData().getBoolean(ARMORPLUS_PREV_MAYFLY);
        player.getAbilities().mayfly = prevMayfly;

        if (player.getAbilities().flying) player.getAbilities().flying = false;

        // Remove stored previous state
        player.getPersistentData().remove(ARMORPLUS_PREV_MAYFLY);

        player.onUpdateAbilities();
    }

    private static void checkAndApplyFlight(Player player) {
        boolean hasOurFlight = isFullArmorWithBuff(player, FLIGHT);

        if (hasOurFlight) {
            grantFlight(player);
        } else {
            revokeFlight(player);
        }
    }

    private static void checkAndApplyBuffs(Player player) {
        checkAndApplyFlight(player);

        if (isFullArmorWithBuff(player, WATER_WEAKNESS) && player.isInWaterRainOrBubble()) {
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0, false, false, true));
        }
    }

    @SubscribeEvent
    public static void onPlayerTickEvent(PlayerTickEvent e) {
        Player player = e.player;
        Level world = player.level();
        if (world.isClientSide()) return;

        checkAndApplyBuffs(player);

        if (!world.isThundering()) return;
        thunderingTicks++;
        if ((thunderingTicks + 1) % 20 != 0 || RAND.nextInt(100) + 1 != 100) return;

        for (ItemStack item : player.getArmorSlots()) {
            if (!(item.getItem() instanceof ArmorItem armor)) continue;
            if (!hasEnchant(item, "unknown")) continue;
            if (armor.getEquipmentSlot() != EquipmentSlot.HEAD) continue;

            ArmorMaterial material = armor.getMaterial();
            if (material != ArmorMaterials.IRON && material != ArmorMaterials.CHAIN && material != ArmorMaterials.GOLD)
                continue;

            BlockPos pos = player.blockPosition();
            if (!world.canSeeSky(pos)) continue;

            LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(world);
            if (bolt != null) {
                bolt.moveTo(atBottomCenterOf(pos));
                bolt.setCause((ServerPlayer) player);
                bolt.setDamage(0f);
                world.addFreshEntity(bolt);
            }
        }
    }

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent e) {
        if (e.getEntity() instanceof Player player && !player.level().isClientSide()) {
            checkAndApplyBuffs(player);
        }
    }


    @SubscribeEvent
    public static void onArrowLooseEvent(ArrowLooseEvent e) {
        Level world = e.getLevel();
        if (world.isClientSide()) return;

        ItemStack bow = e.getBow();
        if (!hasEnchant(bow, "unknown")) return;

        int charge = e.getCharge();
        LivingEntity shooter = e.getEntity();
        BlockPos pos = shooter.blockPosition();
        Direction dir = shooter.getDirection();

        IntStream.range(5 + (charge / (charge / 2)), charge).forEach(i -> {
            LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(world);
            if (bolt == null) return;
            switch (dir) {
                case NORTH -> bolt.moveTo(atBottomCenterOf(pos.north(i)));
                case SOUTH -> bolt.moveTo(atBottomCenterOf(pos.south(i)));
                case WEST -> bolt.moveTo(atBottomCenterOf(pos.west(i)));
                case EAST -> bolt.moveTo(atBottomCenterOf(pos.east(i)));
            }
            world.addFreshEntity(bolt);
            bow.hurtAndBreak(10, shooter, ev -> ev.broadcastBreakEvent(shooter.getUsedItemHand()));
        });
    }

    @SubscribeEvent
    public static void onAttackEntityEvent(AttackEntityEvent e) {
        Player player = e.getEntity();
        Level world = player.level();
        if (world.isClientSide()) return;

        ItemStack mainHand = player.getMainHandItem();
        Entity target = e.getTarget();

        // Trident with "unknown" enchant lightning strike
        if (mainHand.getItem() instanceof TridentItem && hasEnchant(mainHand, "unknown")) {
            spawnLightningCross(world, target.blockPosition());
            player.addEffect(new MobEffectInstance(SLOWNESS.getEffect(), convertToSeconds(4)));
            player.addEffect(new MobEffectInstance(MINING_FATIGUE.getEffect(), convertToSeconds(4)));
        }

        // Mace sweeping attack
        if (player.onGround() && mainHand.getItem() instanceof APMaceItem mace) {
            double movedDistance = player.walkDist - player.walkDistO;
            if (movedDistance < player.getSpeed()) performMaceSweep(world, player, target, mace);
        }
    }

    private static void spawnLightningCross(Level world, BlockPos pos) {
        List<BlockPos> positions = List.of(pos, pos.north(2), pos.south(2), pos.east(2), pos.west(2));
        for (BlockPos p : positions) {
            LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(world);
            if (bolt != null) {
                bolt.moveTo(atBottomCenterOf(p));
                world.addFreshEntity(bolt);
            }
        }
    }

    private static void performMaceSweep(Level world, Player player, Entity target, APMaceItem mace) {
        float baseDmg = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float sweepDmg = 1.0F + APMaceType.getMaceSweepingRatio(mace.mat.getType()) * baseDmg;

        for (LivingEntity entity : world.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1, 0.25, 1))) {
            if (entity == player || entity == target || player.isAlliedTo(entity)) continue;
            if (entity instanceof ArmorStand stand && stand.isMarker()) continue;
            if (player.distanceToSqr(entity) >= 15) continue;

            double x = Mth.wrapDegrees(player.getYRot() * ((float) Math.PI / 180F));
            double z = -x;
            entity.knockback(0.4F, x, z);
            entity.hurt(player.damageSources().playerAttack(player), sweepDmg);
        }

        if (world instanceof ServerLevel server) {
            ItemStack stack = mace.setTag(player.getMainHandItem());
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.hasUUID("key")) {
                mace.triggerAnim(player, GeoItem.getOrAssignId(stack, server), mace.controllerName, "animation.mace.swipe_attack");
            }
        }

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
        player.sweepAttack();
    }

    @SubscribeEvent
    public static void onStructByLightningEvent(EntityStruckByLightningEvent event) {
        if (!event.getEntity().level().isClientSide && event.getEntity() instanceof ItemEntity entity) {
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
        for (ItemStack stack : entity.getArmorSlots()) {
            if (!stack.isDamageableItem() || !(stack.getItem() instanceof ArmorItem)) continue;

            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
            if (enchantments.isEmpty()) continue;
            if (!enchantments.containsKey(ENCHANTMENTS.getValue(setRL("soul_harden")))) continue;

            int max = stack.getMaxDamage();
            int current = max - stack.getDamageValue();

            if (current == max) {
                stack.setDamageValue(0);
            } else {
                stack.setDamageValue(stack.getDamageValue() - 1);
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

        for (ItemStack stack : entity.getArmorSlots()) {
            if (!stack.isDamageableItem() || !(stack.getItem() instanceof ArmorItem)) continue;

            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
            if (enchantments.isEmpty()) continue;
            if (!enchantments.containsKey(ENCHANTMENTS.getValue(setRL("soul_harden")))) continue;

            int max = stack.getMaxDamage();
            int incoming = stack.getDamageValue();
            int current = max - incoming;
            int half = Math.min(max / 2, Math.floorDiv(max, 2));

            if (current == max) {
                stack.setDamageValue(half);
            } else if (incoming >= half) {
                stack.setDamageValue(half);
            } else {
                stack.setDamageValue(max);
                stack.setCount(0);
            }
        }
    }

    //
    // ENTITY DROPS
    //

    @SubscribeEvent
    public static void onMobDeathEvent(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        Entity killer = event.getSource().getEntity();

        boolean hasSoulStealer = false;
        if (killer instanceof ServerPlayer player) {
            ItemStack held = player.getMainHandItem();
            hasSoulStealer = !held.isEmpty() &&
                    EnchantmentHelper.getEnchantments(held).containsKey(ENCHANTMENTS.getValue(setRL("soul_stealer")));
        }

        record MobDropConfig(Supplier<Boolean> trophyEnabled, Supplier<Boolean> regularEnabled,
                             Supplier<Boolean> soulEnabled, String regularItem, int regularAmountMin,
                             int regularAmountMax, String soulItem, float trophyScale) {}

        // Map the mob class to the configuration
        Map<Class<? extends LivingEntity>, MobDropConfig> dropMap = Map.of(
                WitherBoss.class, new MobDropConfig(() -> witherBossDrops.enableTrophyDrops.get(),
                        () -> witherBossDrops.enableRegularDrops.get(),
                        () -> witherBossDrops.enableSoulDrops.get(),
                        "wither_bone", 4, 6, "soul_wither_boss", 0.2f),
                EnderDragon.class, new MobDropConfig(() -> enderDragonDrops.enableTrophyDrops.get(),
                        () -> enderDragonDrops.enableRegularDrops.get(),
                        () -> enderDragonDrops.enableSoulDrops.get(),
                        "ender_dragon_scale", 4, 6, "soul_ender_dragon", 0.1f),
                ElderGuardian.class, new MobDropConfig(() -> elderGuardianDrops.enableTrophyDrops.get(),
                        () -> elderGuardianDrops.enableRegularDrops.get(),
                        () -> elderGuardianDrops.enableSoulDrops.get(),
                        "guardian_scale", 4, 6, "soul_elder_guardian", 0.2f),
                WitherSkeleton.class, new MobDropConfig(() -> false,
                        () -> witherSkeletonDrops.enableRegularDrops.get(),
                        () -> witherSkeletonDrops.enableSoulDrops.get(),
                        "wither_bone", 0, 3, "soul_wither_skeleton", 0f),
                Guardian.class, new MobDropConfig(() -> false,
                        () -> guardianDrops.enableRegularDrops.get(),
                        () -> guardianDrops.enableSoulDrops.get(),
                        "guardian_scale", 0, 3, "soul_guardian", 0f),
                EnderMan.class, new MobDropConfig(() -> false,
                        () -> false,
                        () -> endermanDrops.enableSoulDrops.get(),
                        null, 0, 0, "soul_enderman", 0f),
                Blaze.class, new MobDropConfig(() -> false,
                        () -> false,
                        () -> blazeDrops.enableSoulDrops.get(),
                        null, 0, 0, "soul_blaze", 0f)
        );


        MobDropConfig cfg = dropMap.entrySet().stream()
                .filter(entry -> entry.getKey().isInstance(entity))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);

        if (cfg == null) return;

        // Trophy
        if (cfg.trophyEnabled.get()) dropTrophyItem(entity, entity.getType(), cfg.trophyScale);

        // Regular drops
        if (cfg.regularEnabled.get() && cfg.regularItem != null) {
            int amount = RAND.nextInt(cfg.regularAmountMax - cfg.regularAmountMin + 1) + cfg.regularAmountMin;
            dropItem(entity, cfg.regularItem, amount);
        }

        // Soul drops
        if (hasSoulStealer && cfg.soulEnabled.get() && cfg.soulItem != null) {
            boolean drop = !(entity instanceof WitherSkeleton || entity instanceof Guardian
                    || entity instanceof EnderMan || entity instanceof Blaze) || RAND.nextInt(4) == 0;
            if (drop) dropItem(entity, cfg.soulItem, 1);
        }
    }

    private static void dropTrophyItem(LivingEntity entity, EntityType<?> type, float scale) {
        ItemStack trophy = new ItemStack(getAPItem("trophy"));
        CompoundTag tag = new CompoundTag();
        SpawnData trophyEntity = new SpawnData();
        ResourceLocation key = ForgeRegistries.ENTITY_TYPES.getKey(type);
        if (key == null) key = ResourceLocation.parse("minecraft:pig");
        trophyEntity.getEntityToSpawn().putString("id", key.toString());
        tag.put("DisplayEntity", trophyEntity.getEntityToSpawn().copy());
        tag.putFloat("EntityScale", scale);
        trophy.setTag(tag);
        entity.spawnAtLocation(trophy);
    }

    private static void dropItem(Entity entity, String item, int amount) {
        entity.spawnAtLocation(new ItemStack(getAPItem(item), amount));
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

}