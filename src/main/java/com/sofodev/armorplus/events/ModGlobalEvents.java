package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.api.EffectScopes;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import com.sofodev.armorplus.registry.item.armor.IAPArmor;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.item.material.FrostCrystalItem;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.VanillaGameEvent;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
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
    private static final String ARMORPLUS_FLIGHT_TAG = "ArmorPlusFlight";
    private static final String ARMORPLUS_PREV_MAYFLY = "ArmorPlusPrevMayfly";
    /** Fallback interval of the flight check, equipment changes apply it right away (see onEquipmentChange). */
    private static final int FLIGHT_INTERVAL_TICKS = 20;
    private static final int ILLUMINATION_INTERVAL_TICKS = 20;
    private static final int STALE_LIGHT_INTERVAL_TICKS = 100;
    /** Wardens are also stopped right away by the target change and damage events, this only calms down angry ones. */
    private static final int WARDEN_PACIFY_INTERVAL_TICKS = 20;
    private static final double WARDEN_PACIFY_RADIUS = 32.0;

    private record LitSpot(ServerLevel level, BlockPos pos) {
    }

    private static final Map<UUID, LitSpot> ILLUMINATED_SPOTS = new ConcurrentHashMap<>();

    /**
     * @return true if the player gets the buff from their worn ArmorPlus set (enough pieces) or from a socketed armor rune,
     * and hasn't switched it off. Checked very often (every vanilla game event for sculk immunity, ...), so this is a
     * cached lookup, see {@link ArmorEffects}.
     */
    private static boolean isFullArmorWithBuff(Player player, IBuff buff) {
        return EffectScopes.ARMOR.has(player, buff);
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
        if (spot != null) IlluminationData.of(spot.level()).clear(spot.level(), spot.pos());
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

        if (!IlluminationData.canLight(level.getBlockState(desired))) return;

        IlluminationData.of(level).place(level, desired);
        ILLUMINATED_SPOTS.put(playerId, new LitSpot(level, desired));
    }

    /** Applies flight and illumination right away, e.g. after an equipment change or an effect toggle. */
    public static void checkAndApplyBuffs(Player player) {
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

        if (player.tickCount % FLIGHT_INTERVAL_TICKS == 0) {
            checkAndApplyFlight(player);
        }
        if (world instanceof ServerLevel level && player.tickCount % ILLUMINATION_INTERVAL_TICKS == 0) {
            checkAndApplyIllumination(player, level);
        }
        if (world instanceof ServerLevel level && player.tickCount % WARDEN_PACIFY_INTERVAL_TICKS == 0) {
            clearPacifiedWardenTargets(player, level);
        }
    }

    /** Removes the light blocks left behind by a crash or in chunks that were unloaded when they were no longer needed. */
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post e) {
        if (e.getLevel() instanceof ServerLevel level && level.getGameTime() % STALE_LIGHT_INTERVAL_TICKS == 0) {
            IlluminationData.of(level).clearStale(level, pos -> ILLUMINATED_SPOTS.containsValue(new LitSpot(level, pos)));
        }
    }

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent e) {
        if (e.getEntity() instanceof Player player && !player.level().isClientSide()) {
            List<BuffInstance> previous = new ArrayList<>(EffectScopes.ARMOR.active(player));
            previous.addAll(ToolEffects.held(player));
            EffectScopes.invalidateAll(player);
            RuneEvents.syncEffects(player, previous);
            checkAndApplyBuffs(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent e) {
        clearIllumination(e.getEntity().getUUID());
        EffectScopes.invalidateAll(e.getEntity());
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
                && event.getSource().is(DamageTypes.FREEZE)
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