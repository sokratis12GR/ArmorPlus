package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import com.sofodev.armorplus.registry.item.armor.IAPArmor;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.item.material.FrostCrystalItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.VanillaGameEvent;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.sofodev.armorplus.registry.item.extra.Buff.FLIGHT;
import static com.sofodev.armorplus.registry.item.extra.Buff.ILLUMINATE;
import static com.sofodev.armorplus.registry.item.extra.Buff.POWDERED_SNOW_IMMUNITY;
import static com.sofodev.armorplus.registry.item.extra.Buff.SCULK_IMMUNITY;
import static com.sofodev.armorplus.registry.item.extra.Buff.WARDEN_PACIFY;
import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.Utils.getAPItem;

@EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModGlobalEvents {

    public static final Random RAND = new Random();
    public static int waterTicks = 0;
    public static int thunderingTicks = 0;
    private static final String ARMORPLUS_FLIGHT_TAG = "ArmorPlusFlight";
    private static final String ARMORPLUS_PREV_MAYFLY = "ArmorPlusPrevMayfly";
    private static final int ILLUMINATION_INTERVAL_TICKS = 10;
    private static final int WARDEN_PACIFY_INTERVAL_TICKS = 5;
    private static final double WARDEN_PACIFY_RADIUS = 32.0;

    private record LitSpot(ServerLevel level, BlockPos pos) {
    }

    private static final Map<UUID, LitSpot> ILLUMINATED_SPOTS = new ConcurrentHashMap<>();

    private static boolean isFullArmorWithBuff(Player player, IBuff buff) {
        for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
            ItemStack stack = player.getItemBySlot(slot);
            if (stack.getItem() instanceof APArmorItem armor) {
                IAPArmor mat = armor.getMat();
                if (areExactMatch(mat, player) && mat.config().enableArmorEffects().get()) {
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

        boolean hadAPFlight = player.getPersistentData().getBoolean(ARMORPLUS_FLIGHT_TAG).orElse(false);
        if (!hadAPFlight) return;

        player.getPersistentData().remove(ARMORPLUS_FLIGHT_TAG);

        boolean prevMayfly = player.getPersistentData().getBoolean(ARMORPLUS_PREV_MAYFLY).orElse(false);
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

    private static void clearIllumination(UUID playerId) {
        LitSpot spot = ILLUMINATED_SPOTS.remove(playerId);
        if (spot == null) return;

        // Only clear if it's still our light block - a player may have replaced it with their own block already.
        if (spot.level().isLoaded(spot.pos()) && spot.level().getBlockState(spot.pos()).is(Blocks.LIGHT)) {
            spot.level().setBlockAndUpdate(spot.pos(), Blocks.AIR.defaultBlockState());
        }
    }

    private static void checkAndApplyIllumination(Player player, ServerLevel level) {
        boolean shouldIlluminate = isFullArmorWithBuff(player, ILLUMINATE);
        UUID playerId = player.getUUID();

        if (!shouldIlluminate) {
            clearIllumination(playerId);
            return;
        }

        BlockPos desired = player.blockPosition();
        LitSpot current = ILLUMINATED_SPOTS.get(playerId);
        if (current != null && current.level() == level && current.pos().equals(desired)) return;

        clearIllumination(playerId);

        if (!level.getBlockState(desired).isAir()) return;

        level.setBlockAndUpdate(desired, Blocks.LIGHT.defaultBlockState().setValue(LightBlock.LEVEL, 15));
        ILLUMINATED_SPOTS.put(playerId, new LitSpot(level, desired));
    }

    private static void checkAndApplyBuffs(Player player) {
        checkAndApplyFlight(player);

        if (player.level() instanceof ServerLevel level) {
            checkAndApplyIllumination(player, level);
        }
    }

    private static void clearPacifiedWardenTargets(Player player, ServerLevel level) {
        if (!isFullArmorWithBuff(player, WARDEN_PACIFY)) return;

        List<Warden> nearbyWardens = level.getEntitiesOfClass(Warden.class,
                player.getBoundingBox().inflate(WARDEN_PACIFY_RADIUS));
        for (Warden warden : nearbyWardens) {
            // Wardens track hostility via their own anger management, separate from the
            // vanilla target field, so clearing the target alone leaves them able to re-engage.
            warden.clearAnger(player);
            if (warden.getTarget() == player) {
                warden.setTarget(null);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTickEvent(PlayerTickEvent.Post e) {
        Player player = e.getEntity();
        Level world = player.level();
        if (world.isClientSide()) return;

        checkAndApplyFlight(player);
        if (world instanceof ServerLevel level && player.tickCount % ILLUMINATION_INTERVAL_TICKS == 0) {
            checkAndApplyIllumination(player, level);
        }
        if (world instanceof ServerLevel level && player.tickCount % WARDEN_PACIFY_INTERVAL_TICKS == 0) {
            clearPacifiedWardenTargets(player, level);
        }

        if (!world.isThundering()) return;
        thunderingTicks++;
        if ((thunderingTicks + 1) % 20 != 0 || RAND.nextInt(100) + 1 != 100) return;

        // TODO 26.1: restore conductive vanilla-armor lightning behavior using equipment components.

    }

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent e) {
        if (e.getEntity() instanceof Player player && !player.level().isClientSide()) {
            checkAndApplyBuffs(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent e) {
        clearIllumination(e.getEntity().getUUID());
    }


    @SubscribeEvent
    public static void onVanillaGameEvent(VanillaGameEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (event.getCause() instanceof Player player && isFullArmorWithBuff(player, SCULK_IMMUNITY)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        if (event.getEntity() instanceof Warden warden
                && event.getNewAboutToBeSetTarget() instanceof Player player
                && !player.level().isClientSide()
                && isFullArmorWithBuff(player, WARDEN_PACIFY)) {
            event.setNewAboutToBeSetTarget(null);
            warden.clearAnger(player);
        }
    }

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player
                && !player.level().isClientSide()
                && event.getSource().getEntity() instanceof Warden
                && isFullArmorWithBuff(player, WARDEN_PACIFY)) {
            event.setCanceled(true);
        }
        if (event.getEntity() instanceof Player player
                && !player.level().isClientSide()
                && event.getSource().is(net.minecraft.world.damagesource.DamageTypes.FREEZE)
                && isFullArmorWithBuff(player, POWDERED_SNOW_IMMUNITY)) {
            player.setTicksFrozen(0);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onStructByLightningEvent(EntityStruckByLightningEvent event) {
        if (!event.getEntity().level().isClientSide() && event.getEntity() instanceof ItemEntity entity) {
            Item item = entity.getItem().getItem();
            if (item instanceof FrostCrystalItem) {
                boolean infused = ((FrostCrystalItem) item).isInfused();
                if (!infused) {
                    FrostCrystalItem infusedCrystal = (FrostCrystalItem) getAPItem("infused_frost_crystal");
                    if (entity.level() instanceof ServerLevel server)
                        entity.spawnAtLocation(server, new ItemStack(infusedCrystal, entity.getItem().getCount()), 1f);
                    entity.getItem().setCount(0);
                    event.getLightning().setVisualOnly(true);

                }
            }
        }
    }


}