package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import com.sofodev.armorplus.registry.item.armor.IAPArmor;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.item.material.FrostCrystalItem;
import com.sofodev.armorplus.registry.item.tool.APMaceItem;
import com.sofodev.armorplus.registry.item.tool.properties.mace.APMaceType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.animatable.GeoItem;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.registry.item.extra.Buff.FLIGHT;
import static com.sofodev.armorplus.registry.item.extra.Buff.WATER_WEAKNESS;
import static com.sofodev.armorplus.registry.item.extra.DeBuff.MINING_FATIGUE;
import static com.sofodev.armorplus.registry.item.extra.DeBuff.SLOWNESS;
import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.Utils.*;
import static net.minecraft.world.phys.Vec3.atBottomCenterOf;
import static net.minecraftforge.registries.ForgeRegistries.ENCHANTMENTS;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModGlobalEvents {

    public static final Random RAND = new Random();
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


}